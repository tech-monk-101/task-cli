package com.techmonk.exception;

public class InvalidTaskStatusException extends RuntimeException {
    public InvalidTaskStatusException(String status) {
        super("INVALID STATUS : " + status + ", CHOOSE FROM : todo/in-progress/done");
    }
}
