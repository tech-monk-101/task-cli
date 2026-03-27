package com.techmonk;

import com.techmonk.cli.TaskCLI;
import com.techmonk.controller.TaskController;
import com.techmonk.exception.GlobalExceptionHandler;
import com.techmonk.repository.TaskRepository;
import com.techmonk.service.TaskService;

public class Main {
    public static void main(String[] args) {

        Thread.setDefaultUncaughtExceptionHandler(new GlobalExceptionHandler());

        TaskRepository repository = new TaskRepository();
        TaskService service = new TaskService(repository);
        TaskController controller = new TaskController(service);

        new TaskCLI(controller).run(args);

    }
}