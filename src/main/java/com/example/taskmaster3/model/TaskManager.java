package com.example.taskmaster3.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.example.taskmaster3.model.JsonStorageManager.loadTasks;

public class TaskManager {
    private static final List<Task> tasks = loadTasks();


    public static void addTask(Task task) {
        JsonStorageManager.addTask(task);
    }

    public static void removeTask(int index) {
        JsonStorageManager.removeTask(index);
    }

    public static List<Task> getTasks() {
        return tasks;
    }



    public static void changeTaskStatus(int index, boolean completed) {

        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.setCompleted(completed);
            saveTasks();
            System.out.println("Statut de la tâche modifié avec succès.");
        } else {
            System.out.println("Indice invalide. Aucune tâche n'a été modifiée.");
        }
    }

    public static void changeTaskPriority(int index, Priority priority) {

        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.setPriority(priority);
            saveTasks();
            System.out.println("Priorité de la tâche modifiée avec succès.");
        } else {
            System.out.println("Indice invalide. Aucune tâche n'a été modifiée.");
        }
    }

    public static void changeTaskDueDate(int index, LocalDate dueDate) {

        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.setDueDate(dueDate);
            saveTasks();
            System.out.println("Date d'échéance de la tâche modifiée avec succès.");
        } else {
            System.out.println("Indice invalide. Aucune tâche n'a été modifiée.");
        }
    }

    // Méthode pour sauvegarder les tâches dans le fichier JSON
    public static void saveTasks() {
        JsonStorageManager.saveTasks(tasks);
    }
}
