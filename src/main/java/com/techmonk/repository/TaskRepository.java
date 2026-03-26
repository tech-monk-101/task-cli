package com.techmonk.repository;

import com.techmonk.entity.Task;
import com.techmonk.entity.TaskStatus;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TaskRepository {
    private final Map<Long, Task> tasks = new ConcurrentHashMap<>();

    public void save(Task task) {
        tasks.put(task.getId(), task);
    }

    public Task getTaskById(Long id) {
        if(tasks.containsKey(id))
            return tasks.get(id);
        return null;
    }

    public void deleteTaskById(Long id) {
        tasks.remove(id);
    }

    public void markInProgress(Long id) {
        updateStatus(id, TaskStatus.IN_PROGRESS);
    }

    public void markDone(Long id) {
        updateStatus(id, TaskStatus.DONE);
    }

    private void updateStatus(Long id, TaskStatus status) {
        if(tasks.containsKey(id)) {
            tasks.get(id).setStatus(status);
        }
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }
}