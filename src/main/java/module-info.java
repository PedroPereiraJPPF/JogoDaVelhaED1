module com.velha {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.velha to javafx.fxml;
    exports com.velha;
}
