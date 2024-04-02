package com.example.taskmaster3.controller;

import com.example.taskmaster3.model.Priority;
import com.example.taskmaster3.model.TaskManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import com.example.taskmaster3.model.Task;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class MainController {

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
    private Button addTaskButton;

    @FXML
    private Button backTaskListButton;

    private Task task;
    private TaskManager taskManager;

    public void initialize(TaskManager taskManager) {
        this.taskManager = taskManager;
    }


    @FXML
    private void handleBackTaskListButtonAction(ActionEvent event) {
        try {
            // Chargez la racine de la scène main-view.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/taskmaster3/task-list.fxml"));
            Parent root = loader.load();

            // Créez une nouvelle scène avec la racine chargée
            Scene scene = new Scene(root);

            // Obtenez la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) backTaskListButton.getScene().getWindow();

            // Remplacez la scène actuelle par la nouvelle scène
            stage.setScene(scene);
            stage.setTitle("TaskMaster - Task List");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleAddTaskButtonAction(ActionEvent event) {
        // Récupérer les valeurs des champs
        String title = titleField.getText();
        String description = descriptionField.getText();
        LocalDate dueDate = datePicker.getValue();
        Priority priority = null;

        // Vérifier quelle priorité est sélectionnée
        if (lowPriorityButton.isSelected()) {
            priority = Priority.LOW;
        } else if (mediumPriorityButton.isSelected()) {
            priority = Priority.MEDIUM;
        } else if (highPriorityButton.isSelected()) {
            priority = Priority.HIGH;
        }

        // Créer une nouvelle tâche avec les valeurs des champs
        Task newTask = new Task(title, description, dueDate, priority);

        // Ajouter la nouvelle tâche à la liste des tâches
        TaskManager.addTask(newTask);

        // Effacer les champs après avoir ajouté la tâche
        clearFields();
    }

    // Méthode pour effacer les champs après avoir ajouté une tâche
    private void clearFields() {
        titleField.clear();
        descriptionField.clear();
        datePicker.setValue(null);
        lowPriorityButton.setSelected(false);
        mediumPriorityButton.setSelected(false);
        highPriorityButton.setSelected(false);
    }

}
