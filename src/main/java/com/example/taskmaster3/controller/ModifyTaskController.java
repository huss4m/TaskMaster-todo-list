package com.example.taskmaster3.controller;

import com.example.taskmaster3.model.Priority;
import com.example.taskmaster3.model.Task;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class ModifyTaskController {

    @FXML
    private TextField titleField;

    @FXML
    private TextField descriptionField;


    @FXML
    private DatePicker datePicker;
    @FXML
    private RadioButton lowPriorityButton;

    @FXML
    private RadioButton mediumPriorityButton;

    @FXML
    private RadioButton highPriorityButton;
    @FXML
    private RadioButton completed;

    @FXML
    private RadioButton inProgress;

    private Task task;

    public void initialize() {
        System.out.println("Initialize method called");
        // Initialiser les champs avec les valeurs de la tâche
        if (task != null) {
            titleField.setText(task.getTitle());
            descriptionField.setText(task.getDescription());
            // Assurez-vous de convertir la date au format adapté pour le DatePicker
            //datePicker.setValue(task.getDueDate().toLocalDate());
            // Vous devez ajuster cela en fonction de votre logique de priorité
            lowPriorityButton.setSelected(task.getPriority().equals(Priority.LOW));
            mediumPriorityButton.setSelected(task.getPriority().equals(Priority.MEDIUM));
            highPriorityButton.setSelected(task.getPriority().equals(Priority.HIGH));
            completed.setSelected(task.isCompleted());
            inProgress.setSelected(!task.isCompleted());
        }
    }

    @FXML
    private void handleSaveButtonAction() {
        // Mettre à jour les propriétés de la tâche avec les valeurs des champs
        if (task != null) {
            task.setTitle(titleField.getText());
            task.setDescription(descriptionField.getText());
            // Mettre à jour les autres propriétés de la tâche
            // ...
            // Mettre à jour l'état de la tâche en fonction de l'état des boutons radio
            task.setCompleted(completed.isSelected());
            // Enregistrer les modifications dans le fichier JSON ou dans la base de données
            // (vous devrez implémenter cette fonctionnalité)
        }
        // Fermer la fenêtre de modification
        closeWindow();
    }

    @FXML
    private void handleCancelButtonAction() {

        closeWindow();
    }

    private void closeWindow() {
        // Fermer la fenêtre actuelle
        titleField.getScene().getWindow().hide();
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
