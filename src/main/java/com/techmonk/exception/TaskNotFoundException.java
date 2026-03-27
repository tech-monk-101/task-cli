package com.techmonk.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("TASK WITH ID " + id.toString() + " NOT FOUND");
    }
}
