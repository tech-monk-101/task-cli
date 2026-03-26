package com.techmonk.entity;

public class Task {

    private Long id;
    private String task;
    private TaskStatus status;

    public static Long currentID = 1L;

    public Task(String task) {
        this.task = task;
        this.status = TaskStatus.TODO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
