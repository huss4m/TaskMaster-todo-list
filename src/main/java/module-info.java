module com.example.taskmaster3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;

    opens com.example.taskmaster3 to javafx.fxml;
    exports com.example.taskmaster3;

    opens com.example.taskmaster3.controller to javafx.fxml;
    exports com.example.taskmaster3.controller;

    opens com.example.taskmaster3.model;

    exports com.example.taskmaster3.model;

}