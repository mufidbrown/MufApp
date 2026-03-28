package com.muf.modules.workflow.task.validator;

import com.muf.common.constant.TaskPriority;
import com.muf.common.exception.customleadflow.UserNotFoundException;
import com.muf.common.exception.customtaskmanagement.InvalidTaskPriorityException;
import com.muf.modules.master.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskValidator {

    private final UserRepository userRepository;

    public void validateUserExists(Integer userId) {

        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
    }

    public void validatePriority(TaskPriority priority) {

        if (priority == null) {
            throw new InvalidTaskPriorityException(null);
        }
    }
}
