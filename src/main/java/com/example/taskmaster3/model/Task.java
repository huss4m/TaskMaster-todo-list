package com.example.taskmaster3.model;

import com.example.taskmaster3.model.Priority;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.time.LocalDate;

public class Task {
    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> dueDate = new SimpleObjectProperty<>();
    private final ObjectProperty<Priority> priority = new SimpleObjectProperty<>();
    private final BooleanProperty completed = new SimpleBooleanProperty();

    // Constructeur par défaut
    public Task() {
        // Constructeur par défaut vide nécessaire pour Jackson
    }

    // Constructeur
    public Task(String title, String description, LocalDate dueDate, Priority priority) {
        setTitle(title);
        setDescription(description);
        setDueDate(dueDate);
        setPriority(priority);
        setCompleted(false); // Par défaut, une tâche n'est pas complétée
    }

    // Getters et setters pour title
    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty titleProperty() {
        return title;
    }

    // Getters et setters pour description
    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    // Getters et setters pour dueDate
    public LocalDate getDueDate() {
        return dueDate.get();
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate.set(dueDate);
    }

    public ObjectProperty<LocalDate> dueDateProperty() {
        return dueDate;
    }

    // Getters et setters pour priority
    public Priority getPriority() {
        return priority.get();
    }

    public void setPriority(Priority priority) {
        this.priority.set(priority);
    }

    public ObjectProperty<Priority> priorityProperty() {
        return priority;
    }

    // Getters et setters pour completed
    public boolean isCompleted() {
        return completed.get();
    }

    public void setCompleted(boolean completed) {
        this.completed.set(completed);
    }

    public BooleanProperty completedProperty() {
        return completed;
    }

    @Override
    public String toString() {
        return String.format("Title: %s%nDescription: %s%nDue Date: %s%nPriority: %s%nCompleted: %s%n",
                getTitle(), getDescription(), getDueDate(), getPriority(), isCompleted() ? "Yes" : "No");
    }
}
