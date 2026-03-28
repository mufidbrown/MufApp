package com.muf.modules.workflow.task;

import com.muf.common.constant.TaskPriority;
import com.muf.common.constant.TaskStatus;
import com.muf.common.exception.customleadflow.UserNotFoundException;
import com.muf.common.exception.customtaskmanagement.FinalTaskStatusException;
import com.muf.common.exception.customtaskmanagement.InvalidTaskStatusTransitionException;
import com.muf.common.exception.customtaskmanagement.TaskNotFoundException;
import com.muf.modules.master.user.entity.domain.User;
import com.muf.modules.master.user.repository.UserRepository;
import com.muf.modules.workflow.task.mapper.TaskMapper;
import com.muf.modules.workflow.task.rules.TaskStatusRules;
import com.muf.modules.workflow.task.validator.TaskValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskValidator taskValidator;
    private final TaskStatusRules taskStatusRules;
    private final TaskMapper taskMapper;


        @Override
        @Transactional
        public TaskResponse createTask(CreateTaskRequest request) {

            // VALIDASI USER ADA
            taskValidator.validateUserExists(request.getAssignedTo());

            // CREATE ENTITY TASK
            Task task = new Task();
            task.setTitle(request.getTitle());
            task.setDueDate(request.getDueDate());

            // HANDLE PRIORITY
            TaskPriority priority =
                    request.getPriority() != null
                            ? TaskPriority.valueOf(request.getPriority())
                            : TaskPriority.MEDIUM;

            taskValidator.validatePriority(priority);

            task.setPriority(priority.name());
            task.setStatus(TaskStatus.TODO.name());

            // Ambil User entity dari database
            // supaya relasi JPA benar-benar terbentuk

            User user = userRepository.findById(request.getAssignedTo())
                    .orElseThrow(() ->
                            new RuntimeException("User not found with id: " + request.getAssignedTo())
                    );

            // Set FK (kalau field assignedTo masih digunakan)
            task.setAssignedTo(user.getId());

            // Set RELATION ENTITY
            // ini yang membuat task.getAssignedUser() tidak null
            task.setAssignedUser(user);

            // SAVE TASK
            Task saved = taskRepository.save(task);

            // FETCH ULANG DENGAN JOIN USER
            Task taskWithUser = taskRepository.findByIdWithUser(saved.getId())
                    .orElseThrow(() -> new TaskNotFoundException(saved.getId()));

            // =========================
            // DEBUG (optional) dan nanti praktek lihat hasil debuggingnya ini dengan menjalankan mode debugging
            // =========================
            System.out.println("AssignedTo: " + taskWithUser.getAssignedTo());
            System.out.println("AssignedUser: " + taskWithUser.getAssignedUser());

            // MAPPING RESPONSE
            return taskMapper.toTaskResponse(taskWithUser);
        }

    @Override
    @Transactional
    public TaskResponse updateTask(Integer id, UpdateTaskRequest request) {

            Task task = taskRepository.findById(id)
                    .orElseThrow(() -> new TaskNotFoundException(id));

            if (request.getTitle() != null){
                task.setTitle(request.getTitle());
            }

            if (request.getDueDate() != null){
                task.setDueDate(new Date());
            }

            if (request.getPriority() != null) {
                TaskPriority priority = TaskPriority.valueOf(request.getPriority());

                taskValidator.validatePriority(priority);

                task.setPriority(priority.name());
            }

            if (request.getStatus() != null){
                TaskStatus current = TaskStatus.valueOf(task.getStatus());
                TaskStatus target = TaskStatus.valueOf(task.getStatus());

                if (taskStatusRules.isFinalStatus(current)){
                    throw new FinalTaskStatusException(current.name());
                }

                if (!taskStatusRules.isValidTransition(current, target)){
                    throw new InvalidTaskStatusTransitionException(current.name(), target.name());
                }

                task.setStatus(target.name());
            }


            if (request.getAssignedTo() != null) {
                taskValidator.validateUserExists(request.getAssignedTo());

                task.setAssignedTo(request.getAssignedTo());
            }

            taskRepository.save(task);

            Task withUser = taskRepository.findByIdWithUser(id)
                    .orElseThrow(() -> new TaskNotFoundException(id));

            return taskMapper.toTaskResponse(withUser);
    }


    @Override
    @Transactional
    public TaskResponse updateTaskStatus(Integer id, UpdateTaskStatusRequest request) {

            Task task = taskRepository.findById(id)
                    .orElseThrow(() -> new TaskNotFoundException(id));

            TaskStatus current = TaskStatus.valueOf(task.getStatus());
            TaskStatus target = TaskStatus.valueOf(request.getStatus());

            if (taskStatusRules.isFinalStatus(current)){
                throw new FinalTaskStatusException(current.name());
            }

            if (!taskStatusRules.isValidTransition(current, target)){
                throw new InvalidTaskStatusTransitionException(current.name(), target.name());
            }

            task.setStatus(target.name());

            taskRepository.save(task);

            Task withUser = taskRepository.findByIdWithUser(id)
                    .orElseThrow(() -> new TaskNotFoundException(id));

            return taskMapper.toTaskResponse(withUser);
    }

    @Override
    @Transactional
    public TaskResponse getTaskById(Integer id){
            Task task = taskRepository.findByIdWithUser(id)
                    .orElseThrow(() -> new TaskNotFoundException(id));

            return taskMapper.toTaskResponse(task);
    }

    @Override
    @Transactional
    public List<TaskResponse> getTaskByAssignedUser(Integer userId){

            taskValidator.validateUserExists(userId);

            List<Task> tasks = taskRepository.findByAssignedTo(userId);

            return tasks.stream()
                .map(taskMapper::toTaskResponse)
                .collect(Collectors.toList());
    }

}



/*
    @Override
    @Transactional
    public TaskResponse createTask(CreateTaskRequest request) {

        // ✅ Validate assigned user exists
        User user = userRepository.findById(request.getAssignedTo())
                .orElseThrow(() -> new UserNotFoundException(request.getAssignedTo()));

        // memastikan user yang di-assign benar-benar ada
        taskValidator.validateUserExists(request.getAssignedTo());

        task.setAssignedUser(user);
        // CREATE ENTITY
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDueDate(request.getDueDate());

        // HANDLE PRIORITY
        // jika request priority null maka default MEDIUM
        TaskPriority priority =
                request.getPriority() != null
                        ? TaskPriority.valueOf(request.getPriority())
                        : TaskPriority.MEDIUM;

        // VALIDATE PRIORITY VALUE
//        taskValidator.validatePriority(priority);

        // SET BASIC FIELD
        task.setPriority(request.getPriority());
        task.setStatus(TaskStatus.TODO.name());
        task.setAssignedTo(request.getAssignedTo());

        Task saved = taskRepository.save(task);

        Task taskWithUser = taskRepository.findByIdWithUser(saved.getId())
                .orElseThrow(() -> new TaskNotFoundException(saved.getId()));

        return taskMapper.toTaskResponse(taskWithUser);
    }
}
*/
