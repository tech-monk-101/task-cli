package com.techmonk;

import com.techmonk.controller.TaskController;
import com.techmonk.repository.TaskRepository;
import com.techmonk.service.TaskService;

public class Main {
    public static void main(String[] args) {

        TaskRepository repository = new TaskRepository();
        TaskService service = new TaskService(repository);
        TaskController controller = new TaskController(service);

        if (args.length < 1)
            return;

        controller.loadTasks();
        switch (args[0]) {
            case "add" -> {
                controller.add(args[1]);
            }
            case "update" -> {
                controller.update(Long.valueOf(args[1]), args[2]);
            }
            case "delete" -> {
                controller.delete(Long.valueOf(args[1]));
            }
            case "mark-in-progress" -> {
                controller.markInProgress(Long.valueOf(args[1]));
            }
            case "mark-done" -> {
                controller.markDone(Long.valueOf(args[1]));
            }
            case "list" -> {
                if(args.length == 1)
                    controller.list("");
                else
                    controller.list(args[1]);
            }
        }
        controller.saveTasks();
    }
}