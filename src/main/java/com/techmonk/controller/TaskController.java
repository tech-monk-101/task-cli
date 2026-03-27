package com.techmonk.controller;

import com.techmonk.entity.Task;
import com.techmonk.entity.TaskStatus;
import com.techmonk.service.TaskService;

import java.util.List;

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
        List<Task> tasks;
        if(status.isEmpty())
            tasks = taskService.listAllTasks();
        else
            tasks = taskService.listTasksByStatus(status);

        for(Task t: tasks)
            System.out.printf("#%d - [ %11s ] %s %n", t.getId(), t.getStatus().toString().toUpperCase(), t.getTask());
    }

    public void markInProgress(Long id) {
        taskService.updateTaskStatus(id, TaskStatus.IN_PROGRESS);
    }

    public void markDone(Long id) {
        taskService.updateTaskStatus(id, TaskStatus.DONE);
    }
}
