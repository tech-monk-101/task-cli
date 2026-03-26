package com.techmonk;

import com.techmonk.cli.TaskCLI;
import com.techmonk.controller.TaskController;
import com.techmonk.repository.TaskRepository;
import com.techmonk.service.TaskService;

public class Main {
    public static void main(String[] args) {

        TaskRepository repository = new TaskRepository();
        TaskService service = new TaskService(repository);
        TaskController controller = new TaskController(service);

        new TaskCLI(controller).run(args);

    }
}