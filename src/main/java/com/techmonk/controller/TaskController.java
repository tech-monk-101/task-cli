package com.techmonk.controller;

import com.techmonk.entity.Task;
import com.techmonk.entity.TaskStatus;
import com.techmonk.exception.BlankTaskException;
import com.techmonk.exception.EmptyCollectionException;
import com.techmonk.service.TaskService;

import java.util.List;

public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    public void add(String info) {
        Task task = taskService.addTask(info);
        System.out.println("[ TASK CREATED ] : " + task.toString());
    }

    public void update(Long id, String info) {
        if(info.isEmpty())
            throw new BlankTaskException();
        Task task = taskService.updateTask(id, info);
        System.out.println("[ TASK UPDATED ] : " + task.toString());
    }

    public void delete(Long id) {
        Task task = taskService.deleteTask(id);
        System.out.println("[ TASK DELETED ] : " + task.toString());
    }

    public void list(String status) {
        List<Task> tasks;
        if(status.isEmpty())
            tasks = taskService.listAllTasks();
        else
            tasks = taskService.listTasksByStatus(status);

        if(tasks.isEmpty())
            throw new EmptyCollectionException();

        for(Task task: tasks)
            System.out.println(task.toString());
    }

    public void markInProgress(Long id) {
        Task task = taskService.updateTaskStatus(id, TaskStatus.IN_PROGRESS);
        System.out.println("[ TASK MARKED IN-PROGRESS ] : " + task.toString());
    }

    public void markDone(Long id) {
        Task task = taskService.updateTaskStatus(id, TaskStatus.DONE);
        System.out.println("[ TASK MARKED DONE ] : " + task.toString());
    }
}
