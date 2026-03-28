package com.techmonk.exception;

import com.techmonk.entity.Command;

public class GlobalExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (e instanceof TaskNotFoundException || e instanceof BlankTaskException) {
            System.out.println("ERROR : " + e.getMessage());
        } else if (e instanceof EmptyCollectionException || e instanceof InvalidTaskStatusException) {
            System.out.println(e.getMessage());
        } else if (e instanceof NumberFormatException) {
            System.out.println("INVALID ID");
        } else if (e instanceof IllegalArgumentException) {
            if(e.getMessage() != null)
                System.out.println(e.getMessage());
            Command.printUsage();
        } else {
            System.out.println("UNKNOWN EXCEPTION OCCURRED: " + e.getMessage());
        }
    }
}
