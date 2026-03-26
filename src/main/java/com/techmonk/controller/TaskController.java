package com.techmonk.controller;

import com.techmonk.service.TaskService;

public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    public void add(String task) {
        taskService.addTask(task);
    }

    public void update(Long id, String task) {
        taskService.updateTask(id, task);
    }

    public void delete(Long id) {
        taskService.deleteTask(id);
    }

    public void list(String status) {
        taskService.listTasks(status);
    }

    public void markInProgress(Long id) {
        taskService.markInProgress(id);
    }

    public void markDone(Long id) {
        taskService.markDone(id);
    }
}
