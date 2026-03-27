package com.techmonk.service;

import com.techmonk.entity.Task;
import com.techmonk.entity.TaskStatus;
import com.techmonk.exception.TaskNotFoundException;
import com.techmonk.repository.TaskRepository;

import java.util.List;


public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //CREATE
    public Task addTask(String info) {
        Task task = new Task(info);
        taskRepository.save(task);
        return task;
    }

    //READ
    public List<Task> listAllTasks() {
        return taskRepository.getAllTasks();
    }

    public List<Task> listTasksByStatus(String status) {
        return taskRepository.getAllTasks()
                .stream()
                .filter(t -> t.getStatus().toString().equals(status)).toList();

    }

    //UPDATE
    public Task updateTask(Long id, String info) {
        Task task = taskRepository.getTaskById(id);
        if (task == null)
            throw new TaskNotFoundException(id);
        task.setTask(info);
        taskRepository.save(task);
        return task;
    }

    public Task updateTaskStatus(Long id, TaskStatus status) {
        Task task = taskRepository.getTaskById(id);
        if (task == null)
            throw new TaskNotFoundException(id);
        task.setStatus(status);
        taskRepository.save(task);
        return task;
    }

    //DELETE
    public Task deleteTask(Long id) {
        Task task = taskRepository.getTaskById(id);
        if (task == null)
            throw new TaskNotFoundException(id);
        taskRepository.deleteTaskById(id);
        return task;
    }
}
