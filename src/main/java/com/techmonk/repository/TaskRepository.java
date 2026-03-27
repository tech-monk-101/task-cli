package com.techmonk.repository;
import com.techmonk.entity.Task;
import util.JSONParser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class TaskRepository {
    public static Long currentID = 1L;

    private final Map<Long, Task> tasks = new HashMap<>();

    public TaskRepository() {
        loadJSON();
    }

    public void save(Task task) {
        if(task.getId() == null) {
            task.setId(currentID);
            currentID += 1;
        }
        tasks.put(task.getId(), task);
        saveJSON();
    }
    public Task getTaskById(Long id) {
        if(tasks.containsKey(id))
            return tasks.get(id);
        return null;
    }

    public void deleteTaskById(Long id) {
        tasks.remove(id);
        saveJSON();
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    private void loadJSON() {
        try {
            File Obj = new File(getFilePath());
            if (!Obj.exists()) {
                return;
            }
            Scanner Reader = new Scanner(Obj);
            StringBuilder buffer = new StringBuilder();

            while (Reader.hasNextLine()) {
                buffer.append(Reader.nextLine());
            }

            String data = buffer.toString();
            Reader.close();

            List<Task> tasklist = JSONParser.parseJSON(data);
            for (Task task : tasklist) {
                save(task);
                currentID = Math.max(currentID, task.getId() + 1);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveJSON() {
        String data = JSONParser.toJSON(getAllTasks());

        String fileName = getFilePath();

        Path path = Paths.get(fileName);
        if (Files.notExists(path)) {
            try {
                Files.writeString(path, "[]", StandardOpenOption.CREATE);
            } catch (IOException e) {
                throw new RuntimeException("Could not create " + fileName, e);
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getFilePath() {
        try {
            String jarDir = new File(
                    TaskRepository.class
                            .getProtectionDomain()
                            .getCodeSource()
                            .getLocation()
                            .toURI()
            ).getParent();

            return Paths.get(jarDir, "tasks.json").toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to resolve app directory", e);
        }
    }
}