package com.techmonk.exception;

public class GlobalExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (e instanceof TaskNotFoundException) {
            System.out.println("ERROR : " + e.getMessage());
        }
        if(e instanceof EmptyCollectionException) {
            System.out.println(e.getMessage());
        }
    }
}
