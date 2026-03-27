package com.techmonk.exception;

public class GlobalExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (e instanceof TaskNotFoundException || e instanceof BlankTaskException) {
            System.out.println("ERROR : " + e.getMessage());
        }
        else if(e instanceof EmptyCollectionException || e instanceof InvalidTaskStatusException) {
            System.out.println(e.getMessage());
        }
        else {
            System.out.println("UNKNOWN EXCEPTION OCCURRED: " + e.getMessage());
        }
    }
}
