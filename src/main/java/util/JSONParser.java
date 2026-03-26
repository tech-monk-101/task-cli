package util;

import com.techmonk.entity.Task;
import com.techmonk.entity.TaskStatus;

import java.util.ArrayList;
import java.util.List;

public class JSONParser {

    /**
     * Converts a List of Task objects into a JSON array string.
     */
    public static String toJSON(List<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            // Using toString() because TaskStatus overrides it to return "todo", etc.
            sb.append(String.format("{\"id\":%d,\"task\":\"%s\",\"status\":\"%s\"}",
                    task.getId(),
                    task.getTask().replace("\"", "\\\""),
                    task.getStatus().toString()));

            if (i < tasks.size() - 1) {
                sb.append(",");
            }
        }
        return sb.append("]").toString();
    }

    /**
     * Manually parses a JSON array string into a List of Task objects.
     */
    public static List<Task> parseJSON(String json) {
        List<Task> tasks = new ArrayList<>();
        if (json == null || json.trim().isEmpty() || json.equals("[]")) {
            return tasks;
        }

        String content = json.trim();
        // Remove outer array brackets
        if (content.startsWith("[") && content.endsWith("]")) {
            content = content.substring(1, content.length() - 1).trim();
        }

        int i = 0;
        while (i < content.length()) {
            // Locate the boundaries of a single { object }
            int start = content.indexOf('{', i);
            int end = content.indexOf('}', start);

            if (start == -1 || end == -1) break;

            String objStr = content.substring(start + 1, end);
            tasks.add(mapToTask(objStr));

            i = end + 1; // Move pointer past current object
        }
        return tasks;
    }

    private static Task mapToTask(String pairString) {
        // Extract raw string values from JSON keys
        String idStr = extract(pairString, "id");
        String taskName = extract(pairString, "task");
        String statusStr = extract(pairString, "status");

        Task task = new Task(taskName);

        // Manual ID assignment (Assumes setId exists in Task entity)
        if (!idStr.isEmpty()) {
            // Remove any potential non-numeric junk if present
            task.setId(Long.parseLong(idStr.replaceAll("[^0-9]", "")));
        }

        // Manual mapping of status string to Enum constants
        if (statusStr.equals("in-progress")) {
            task.setStatus(TaskStatus.IN_PROGRESS);
        } else if (statusStr.equals("done")) {
            task.setStatus(TaskStatus.DONE);
        } else {
            task.setStatus(TaskStatus.TODO);
        }

        return task;
    }

    private static String extract(String row, String key) {
        String search = "\"" + key + "\":";
        int startIdx = row.indexOf(search);
        if (startIdx == -1) return "";

        startIdx += search.length();

        // Find where this value ends (at the next comma or end of string)
        int endIdx = row.indexOf(",", startIdx);
        if (endIdx == -1) {
            endIdx = row.length();
        }

        String value = row.substring(startIdx, endIdx).trim();
        // Strip quotes from strings, leave numbers as-is
        return value.replace("\"", "");
    }
}
