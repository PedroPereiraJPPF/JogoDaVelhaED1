package com.velha;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import com.velha.Entities.Table;

/**
 * JavaFX App
 */
public class App extends Application {

    private Table table;
    private Button[][] buttons;

    @Override
    public void start(Stage primaryStage) throws Exception {
        int n = 3;

        table = new Table(n);
        buttons = new Button[n][n];

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Button button = new Button();
                button.setMinSize(50, 50);
                button.setOnAction(event -> handleButtonClick(button));
                gridPane.add(button, j, i);
                buttons[i][j] = button;
            }
        }

        Button undoButton = new Button("Desfazer");
        undoButton.setOnAction(event -> {
            try {
                handleUndoButtonClick();
            } catch (Exception e) {
               System.out.println(e.getMessage());
            }
        });

        HBox hbox = new HBox(10, undoButton);
        hbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(new VBox(10, gridPane, hbox), 250, 250);
        primaryStage.setTitle("Jogo da Velha");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleButtonClick(Button button) {
        int linha = GridPane.getRowIndex(button);
        int coluna = GridPane.getColumnIndex(button);
        
        if (table.play(linha, coluna)) {
            updateButton(button);
            
            if (table.checkWinner()) {
                showAlert("Jogador " + (table.getActualPlayer() == 0 ? "1" : "2") + " ganhou!");
            } else if (table.getTurn() == 8) {
                showAlert("Jogo empatado!");
            } else {
                table.addTurn();
                table.changePlayer();
            }
        } else {
            showAlert("Jogada inválida!");
        }
    }

    private void handleUndoButtonClick() throws Exception {
        table.undoMove();
        updateButtons();
    }

    private void updateButtons() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                int value = table.getTableMatriz()[i][j];
                buttons[i][j].setText(value == 0 ? "" : (value == 1 ? "O" : "X"));
                buttons[i][j].setDisable(value != 0);
            }
        }
    }

    private void updateButton(Button button) {
        int player = table.getActualPlayer();
        
        button.setText(player == 0 ? "O" : "X");
        button.setDisable(true);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resultado");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

}