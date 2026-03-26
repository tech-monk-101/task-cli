package com.techmonk.service;

import com.techmonk.entity.Task;
import com.techmonk.repository.TaskRepository;
import utils.JSONParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.nio.file.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(String task) {
        Task t = new Task(task);
        t.setId(Task.currentID);
        taskRepository.save(t);
    }

    public void updateTask(Long id, String info) {
        taskRepository.getTaskById(id).setTask(info);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteTaskById(id);
    }

    public void markInProgress(Long id) {
        taskRepository.markInProgress(id);
    }

    public void markDone(Long id) {
        taskRepository.markDone(id);
    }

    public void listAllTasks() {
        List<Task> tasks = taskRepository.getAllTasks();
        for(Task t : tasks) {
            System.out.println("ID: " + t.getId().toString() + " Task: " + t.getTask() + " Status: " + t.getStatus().toString());
        }
    }

    public void listTasksByStatus(String status) {
        List<Task> tasks = taskRepository.getAllTasks();
        for(Task t : tasks) {
            if(t.getStatus().toString().equals(status))
                System.out.println("ID: " + t.getId().toString() + " Task: " + t.getTask() + " Status: " + t.getStatus().toString());
        }
    }

    public void loadJSON() {
        try {
            File Obj = new File("tasks.json");
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

            List<Task> tasks = JSONParser.parseJSON(data);
            for(Task task: tasks) {
                taskRepository.save(task);
                Task.currentID = Math.max(Task.currentID, task.getId() + 1);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveJSON() {
        String data = JSONParser.toJSON(taskRepository.getAllTasks());

        String fileName = "tasks.json";

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
}
