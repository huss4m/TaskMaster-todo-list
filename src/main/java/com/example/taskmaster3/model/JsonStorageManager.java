package com.example.taskmaster3.model;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonStorageManager {
    private static final String JSON_FILE_PATH = "tasks.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule());
    }

    public static List<Task> loadTasks() {
        File file = new File(JSON_FILE_PATH);
        if (file.exists()) {
            try {
                return objectMapper.readValue(file, new TypeReference<List<Task>>() {});
            } catch (IOException e) {
                System.err.println("Erreur lors de la lecture du fichier JSON : " + e.getMessage());
            }
        }
        return new ArrayList<>();
    }

    public static void saveTasks(List<Task> tasks) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(JSON_FILE_PATH), tasks);
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier JSON : " + e.getMessage());
        }
    }

    public static void addTask(Task task) {
        List<Task> tasks = loadTasks();
        tasks.add(task);
        saveTasks(tasks);
    }

    public static void removeTask(int index) {
        List<Task> tasks = loadTasks();
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            saveTasks(tasks);
        } else {
            System.out.println("Indice invalide. Aucune tâche n'a été supprimée.");
        }
    }
}
