package com.techmonk.cli;

import com.techmonk.controller.TaskController;
import com.techmonk.entity.Command;

import java.util.Objects;

public class TaskCLI {

    private final TaskController controller;

    public TaskCLI(TaskController controller) {
        this.controller = controller;
    }

    public void run(String[] args) {
        if (args.length < 1 || Objects.equals(args[0], "-h") || Objects.equals(args[0], "--help")) {
            throw new IllegalArgumentException();
        }

        Command command = Command.from(args[0]);

        switch (command) {
            case ADD -> {
                if (args.length < 2) {
                    System.out.println("USAGE : add <task> ");
                } else {
                    controller.add(args[1]);
                }
            }

            case UPDATE -> {
                if (args.length < 3) {
                    System.out.println("USAGE : update <id> <new-task>");
                } else {
                    controller.update(parseID(args[1]), args[2]);
                }
            }

            case DELETE -> {
                if (args.length < 2) {
                    System.out.println("USAGE : delete <id>");
                } else {
                    controller.delete(parseID(args[1]));
                }
            }

            case MARK_IN_PROGRESS -> {
                if (args.length < 2) {
                    System.out.println("USAGE : mark-in-progress <id>");
                } else {
                    controller.markInProgress(parseID(args[1]));
                }
            }

            case MARK_DONE -> {
                if (args.length < 2) {
                    System.out.println("USAGE : mark-done <id>");
                } else {
                    controller.markDone(parseID(args[1]));
                }
            }

            case LIST -> controller.list(args.length > 1 ? args[1] : "");

        }
    }

    private long parseID(String arg) {
        long id = Long.parseLong(arg);
        if (id < 1L)
            throw new NumberFormatException();
        return id;
    }
}