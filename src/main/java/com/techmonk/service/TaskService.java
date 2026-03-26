package com.techmonk.service;

import com.techmonk.entity.Task;
import com.techmonk.entity.TaskStatus;
import com.techmonk.repository.TaskRepository;


public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //CREATE
    public void addTask(String info) {
        Task task = new Task(info);
        taskRepository.save(task);
    }

    //READ
    private void printTask(Task t) {
        System.out.printf("ID: %d Task: %s Status: %s%n", t.getId(), t.getTask(), t.getStatus());
    }

    public void listTasks(String status) {
        if (status.isEmpty()) {
            taskRepository.getAllTasks().forEach(this::printTask);
        } else {
            taskRepository.getAllTasks()
                    .stream()
                    .filter(t -> t.getStatus().toString().equals(status))
                    .forEach(this::printTask);
        }
    }

    //UPDATE
    public void updateTask(Long id, String info) {
        Task task = taskRepository.getTaskById(id);
        if(task != null) {
            task.setTask(info);
            taskRepository.save(task);
        }
    }

    public void markInProgress(Long id) {
        Task task = taskRepository.getTaskById(id);
        if(task != null) {
            task.setStatus(TaskStatus.IN_PROGRESS);
            taskRepository.save(task);
        }
    }

    public void markDone(Long id) {
        Task task = taskRepository.getTaskById(id);
        if(task != null) {
            task.setStatus(TaskStatus.DONE);
            taskRepository.save(task);
        }
    }


    //DELETE
    public void deleteTask(Long id) {
        Task task = taskRepository.getTaskById(id);
        if(task != null)
            taskRepository.deleteTaskById(id);
    }
}
