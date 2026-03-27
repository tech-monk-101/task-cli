package com.techmonk.cli;

import com.techmonk.controller.TaskController;

import java.util.function.BiConsumer;

public class TaskCLI {

    private final TaskController controller;

    public TaskCLI(TaskController controller) {
        this.controller = controller;
    }

    public void run(String[] args) {
        if (args.length < 1) {
            printUsage();
            return;
        }

        switch (args[0]) {
            case "add" -> {
                if (args.length < 2) {
                    printUsage();
                } else {
                    controller.add(args[1]);
                }
            }

            case "update" -> {
                parseIdAndRun(args, controller::update);
            }

            case "delete" -> {
                parseIdAndRun(args, (id, ignored) -> controller.delete(id));
            }

            case "mark-in-progress" -> {
                parseIdAndRun(args, (id, ignored) -> controller.markInProgress(id));
            }

            case "mark-done" -> {
                parseIdAndRun(args, (id, ignored) -> controller.markDone(id));
            }

            case "list" -> {
                if (args.length == 1) {
                    controller.list("");
                } else {
                    controller.list(args[1]);
                }
            }

            default -> {
                System.out.println("Unknown command: " + args[0]);
                printUsage();
            }
        }
    }

    private void printUsage() {
        System.out.println("Usage:");
        System.out.println("  add <task>                         Add a new task");
        System.out.println("  update <id> <new-task>            Update task text by ID");
        System.out.println("  delete <id>                        Delete task by ID");
        System.out.println("  mark-in-progress <id>             Mark task as in-progress");
        System.out.println("  mark-done <id>                    Mark task as done");
        System.out.println("  list                               List all tasks");
        System.out.println("  list <status>                      List tasks by status (todo|in-progress|done)");
    }

    private void parseIdAndRun(String[] args, BiConsumer<Long, String> action) {
        if (args.length < 2) {
            printUsage();
            return;
        }

        try {
            long id = Long.parseLong(args[1]);
            String info =  args.length > 2 ? args[2] : "";
            action.accept(id, info);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID: " + args[1]);
        }
    }
}