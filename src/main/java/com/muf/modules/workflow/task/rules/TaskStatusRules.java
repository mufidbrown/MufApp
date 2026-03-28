package com.muf.modules.workflow.task.rules;

import com.muf.common.constant.TaskStatus;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TaskStatusRules {

    public static final Map<TaskStatus, List<TaskStatus>> VALID_STATUS_TRANSITIONS = new EnumMap<>(TaskStatus.class);

    static {

        VALID_STATUS_TRANSITIONS.put(
                TaskStatus.TODO,
                List.of(TaskStatus.IN_PROGRESS, TaskStatus.CANCELLED)
        );

        VALID_STATUS_TRANSITIONS.put(
                TaskStatus.IN_PROGRESS,
                List.of(TaskStatus.COMPLETED, TaskStatus.CANCELLED)
        );

        VALID_STATUS_TRANSITIONS.put(TaskStatus.COMPLETED, List.of());
        VALID_STATUS_TRANSITIONS.put(TaskStatus.CANCELLED, List.of());
    }

    public boolean isFinalStatus(TaskStatus status) {
        return status == TaskStatus.COMPLETED || status == TaskStatus.CANCELLED;
    }

    public boolean isValidTransition(TaskStatus from, TaskStatus to) {

        if (from == to) {
            return false;
        }

        List<TaskStatus> allowed = VALID_STATUS_TRANSITIONS.get(from);

        return allowed != null && allowed.contains(to);
    }
}
