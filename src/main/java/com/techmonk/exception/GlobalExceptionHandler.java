package com.techmonk.exception;

public class GlobalExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("ERROR : " + e.getMessage());
    }
}
