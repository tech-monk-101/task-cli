package com.techmonk.exception;

public class EmptyCollectionException extends RuntimeException {
    public EmptyCollectionException() {
        super("NO TASKS FOUND");
    }
}
