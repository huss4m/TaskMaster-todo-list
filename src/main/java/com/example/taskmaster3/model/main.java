package com.example.taskmaster3.model;

import java.time.LocalDate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    private static Scanner scanner = new Scanner(System.in);


    private static TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        boolean continuer = true;
        while (continuer) {
            afficherMenu();
            int choix = scanner.nextInt();
            scanner.nextLine(); // Pour consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    afficherListeTaches();
                    ajouterTache();
                    break;
                case 2:
                    supprimerTache();
                    break;
                default:
                    System.out.println("Choix invalide.");
            }

            afficherListeTaches();

            System.out.println("Voulez-vous continuer ? (oui/non)");
            String reponse = scanner.nextLine();
            continuer = reponse.equalsIgnoreCase("oui");
        }

        scanner.close();
    }

    private static void afficherMenu() {


        System.out.println("Que voulez-vous faire ?");
        System.out.println("1. Ajouter une tâche");
        System.out.println("2. Supprimer une tâche");
        System.out.print("Votre choix : ");
    }

    private static void ajouterTache() {
        System.out.println("Ajout d'une nouvelle tâche :");
        System.out.print("Titre : ");
        String titre = scanner.nextLine();
        System.out.print("Description : ");
        String description = scanner.nextLine();
        System.out.print("Date d'échéance (AAAA-MM-JJ) : ");
        LocalDate dueDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Priorité (LOW, MEDIUM ou HIGH) : ");
        Priority priority = Priority.valueOf(scanner.nextLine().toUpperCase());

        Task nouvelleTache = new Task(titre, description, dueDate, priority);
        TaskManager.addTask(nouvelleTache);
        System.out.println("Tâche ajoutée avec succès.");
    }

    private static void supprimerTache() {
        System.out.println("Suppression d'une tâche :");
        System.out.print("Indice de la tâche à supprimer : ");
        int indice = scanner.nextInt();
        scanner.nextLine(); // Pour consommer la nouvelle ligne
        TaskManager.removeTask(indice);
        System.out.println("Tâche supprimée avec succès.");
    }


    private static void afficherListeTaches() {
        System.out.println("\nListe des tâches :");
        int i = 1;
        for (Task task : taskManager.getTasks()) {
            System.out.println(i++ + ". " + task);
        }
        System.out.println();
    }
}
