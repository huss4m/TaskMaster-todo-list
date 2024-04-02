package com.example.taskmaster3;

import com.example.taskmaster3.controller.TaskListController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Chargez la racine de la scène principale
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("task-list.fxml"));
        Parent root = mainLoader.load();

        // Obtenez une référence au contrôleur de la scène principale
        TaskListController taskListController = mainLoader.getController();

        // Créez une nouvelle scène avec la racine chargée
        Scene scene = new Scene(root);

        // Configurez la scène principale dans la fenêtre principale
        primaryStage.setScene(scene);
        primaryStage.setTitle("Task List");

        // Affichez la fenêtre principale
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
