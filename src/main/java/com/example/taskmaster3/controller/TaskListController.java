package com.example.taskmaster3.controller;

import com.example.taskmaster3.model.Task;
import com.example.taskmaster3.model.JsonStorageManager;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TaskListController implements Initializable {

    @FXML
    private TableView<Task> taskTableView;

    @FXML
    private TableColumn<Task, Integer> idColumn;

    @FXML
    private TableColumn<Task, String> titleColumn;

    @FXML
    private TableColumn<Task, String> descriptionColumn;

    @FXML
    private TableColumn<Task, String> dueDateColumn;

    @FXML
    private TableColumn<Task, String> priorityColumn;


    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;


    @FXML
    private Button modifyButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadTasksFromJson();
        // Charger les tâches depuis le gestionnaire de tâches
        //taskTableView.getItems().addAll(TaskManager.getTasks());
// Modifier la cellValueFactory de la colonne ID
        idColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(taskTableView.getItems().indexOf(cellData.getValue()) + 1));

        // Associer les propriétés des tâches aux colonnes de la table
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        dueDateColumn.setCellValueFactory(cellData -> cellData.getValue().dueDateProperty().asString());
        priorityColumn.setCellValueFactory(cellData -> cellData.getValue().priorityProperty().asString());


    }

    private void loadTasksFromJson() {
        List<Task> tasks = JsonStorageManager.loadTasks();
        // Mettre à jour la TableView avec les données chargées depuis le fichier JSON
        taskTableView.getItems().setAll(tasks);
    }
    public TableColumn<Task, Integer> getIdColumn() {
        return idColumn;
    }



    @FXML
    private void handleModifyButtonAction(ActionEvent event) {
        // Vérifier si une tâche est sélectionnée
        Task selectedTask = taskTableView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            try {
                // Charger le fichier FXML de la vue de modification
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/taskmaster3/modify-task.fxml"));
                Parent root = loader.load();

                // Obtenir le contrôleur de la vue de modification
                ModifyTaskController modifyTaskController = loader.getController();

                // Passer la tâche sélectionnée au contrôleur de la vue de modification
                modifyTaskController.setTask(selectedTask);

                modifyTaskController.initialize();
                // Créer une nouvelle scène avec la racine chargée
                Scene scene = new Scene(root);

                // Créer une nouvelle fenêtre pour la vue de modification
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Modify Task");

                // Afficher la fenêtre de modification
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Afficher un message d'avertissement si aucune tâche n'est sélectionnée
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Task Selected");
            alert.setHeaderText("No Task Selected");
            alert.setContentText("Please select a task to modify.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleAddButtonAction(ActionEvent event) {
        try {
            // Chargez la racine de la scène main-view.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/taskmaster3/main-view.fxml"));
            Parent root = loader.load();

            // Créez une nouvelle scène avec la racine chargée
            Scene scene = new Scene(root);

            // Obtenez la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) addButton.getScene().getWindow();

            // Remplacez la scène actuelle par la nouvelle scène
            stage.setScene(scene);
            stage.setTitle("Hello View");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDeleteButtonAction(ActionEvent event) {
        // Récupérer l'index de l'élément sélectionné dans la table
        int selectedIndex = taskTableView.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            // Supprimer l'élément de la table
            taskTableView.getItems().remove(selectedIndex);

            // Supprimer l'élément du fichier JSON en utilisant la méthode removeTask de JsonStorageManager
            JsonStorageManager.removeTask(selectedIndex);
        } else {
            // Afficher un message d'avertissement si aucune tâche n'est sélectionnée
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Task Selected");
            alert.setHeaderText("No Task Selected");
            alert.setContentText("Please select a task to delete.");
            alert.showAndWait();
        }

    }

    private void closeCurrentWindow (ActionEvent event){
            // Obtenez une référence à la fenêtre actuelle
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            // Fermez la fenêtre actuelle
            stage.close();
        }
    }

