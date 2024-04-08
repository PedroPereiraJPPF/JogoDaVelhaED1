package com.velha;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import com.velha.Entities.Table;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Stage stage;
    private static Table table;

    public static void main (String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        setStage(stage);
        stage.setTitle("Jogo do Novo X");
        stage.show();
        Image image = new Image("file:src/main/resources/com/velha/Images/icon.jpeg");
        stage.getIcons().add(image);
        telaInicio();
    }

    public void setStage(Stage newStage) {
        stage = newStage;
    }

    public Stage getStage() {
        return stage;
    }

    public static void telaJogoDaVelha() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("TelaJogoDaVelha.fxml"));
            Parent root = loader.load();

            Scene jogo = new Scene(root);
            stage.setScene(jogo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void telaSalvarPartida(Table saveTable) {
        try {
            table = saveTable;

            FXMLLoader loader = new FXMLLoader(App.class.getResource("TelaSalvarPartida.fxml"));
            Parent root = loader.load();

            Scene salvamento = new Scene(root);
            stage.setScene(salvamento);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void telaInicio() {
        try {
            table = null;

            FXMLLoader loader = new FXMLLoader(App.class.getResource("TelaInicio.fxml"));
            Parent root = loader.load();

            Scene inicio = new Scene(root);
            stage.setScene(inicio);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void telaRanking() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("TelaRanking.fxml"));
            Parent root = loader.load();

            Scene ranking = new Scene(root);
            stage.setScene(ranking);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void telaReplay() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("TelaReplay.fxml"));
            Parent root = loader.load();

            Scene ranking = new Scene(root);
            stage.setScene(ranking);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Table getTable(){
        return table;
    }
}