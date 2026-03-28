package com.techmonk.entity;


public enum Command {

    ADD("add"),
    UPDATE("update"),
    DELETE("delete"),
    MARK_IN_PROGRESS("mark-in-progress"),
    MARK_DONE("mark-done"),
    LIST("list");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command from(String input) {
        for (Command cmd : values()) {
            if (cmd.value.equalsIgnoreCase(input)) {
                return cmd;
            }
        }
        throw new IllegalArgumentException("UNKNOWN COMMAND : " + input);
    }

    public static void printUsage() {
        String format = "  %-25s %s%n";
        System.out.println("USAGE : \n");

        System.out.printf(format, "add <task>", "Add a new task");
        System.out.printf(format, "update <id> <new-task>", "Update task text by ID");
        System.out.printf(format, "delete <id>", "Delete task by ID");
        System.out.printf(format, "mark-in-progress <id>", "Mark task as in-progress");
        System.out.printf(format, "mark-done <id>", "Mark task as done");
        System.out.printf(format, "list", "List all tasks");
        System.out.printf(format, "list <status>", "List tasks by status (todo | in-progress | done)");
    }
}