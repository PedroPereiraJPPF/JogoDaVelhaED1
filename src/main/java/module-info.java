module com.velha {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens com.velha to javafx.fxml;
    opens com.velha.controller to javafx.fxml;
    exports com.velha;
    exports com.velha.Entities;
}
