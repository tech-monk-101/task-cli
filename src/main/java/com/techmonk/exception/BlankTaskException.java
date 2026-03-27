package com.techmonk.exception;

public class BlankTaskException extends RuntimeException {
    public BlankTaskException() {
        super("TASK CANNOT BE BLANK");
    }
}
