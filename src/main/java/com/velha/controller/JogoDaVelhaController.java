package com.velha.controller;

import com.velha.App;
import com.velha.Entities.Table;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class JogoDaVelhaController {

    private static Table table;
    private Button[][] buttons;

    @FXML GridPane gridPane;
    @FXML Text j1;
    @FXML Text j2;
    @FXML Button desfazer;
    @FXML Button finalizar;

    public void initialize() {
        int n = 3;

        table = new Table(n);

        buttons = new Button[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Button button = new Button();
                button.setMinSize(50, 50);
                button.setOnAction(event -> mark(button));
                gridPane.add(button, j, i);
                buttons[i][j] = button;
            }
        }
        
        finalizar.setDisable(true);
        desfazer.setDisable(true);
        j1.setUnderline(true);
    }

    public void mark(Button button) {
        int linha = GridPane.getRowIndex(button);
        int coluna = GridPane.getColumnIndex(button);

        if (table.play(linha, coluna)) {
            updateButton(button);

            // TODO Checar lógica do Finalizar com Pedro
            if (table.checkWinner()) {
                table.addTurn();
                finalizar.setDisable(false);
                showAlert("Jogador " + (table.getActualPlayer() == 0 ? "1" : "2") + " ganhou!");
                blockAll();
            } else if (table.getTurn() == 8) {
                table.addTurn();
                finalizar.setDisable(false);
                showAlert("Jogo empatado!");
                blockAll();
            } else {
                table.addTurn();
                table.changePlayer();
                playerUnderline();
            }

            desfazer.setDisable(false);
        } else {
            showAlert("Jogada Inválida");
        }
    }

    public void updateButton(Button button) {
        int player = table.getActualPlayer();

        button.setText(player == 0 ? "O" : "X");
        button.setDisable(true);
    }

    private void blockAll(){
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j].setDisable(true);
            }
        }
    }

    private void playerUnderline(){
        if (table.getActualPlayer() == 0){
            j1.setUnderline(true);
            j2.setUnderline(false);
        } else {
            j1.setUnderline(false);
            j2.setUnderline(true);
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resultado");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void undo() {
        table.undoMove();
        playerUnderline();
        unblockButtons();
        updateButtons(table.getTableMatriz());

        finalizar.setDisable(true);

        if (table.getTurn() == 0) {
            desfazer.setDisable(true);
        }
    }

    private void updateButtons(Integer[][] moves) {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                int value = moves[i][j];
                buttons[i][j].setText(value == 0 ? "" : (value == 1 ? "O" : "X"));
                buttons[i][j].setDisable(value != 0);
            }
        }
    }

    private void unblockButtons() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                if (buttons[i][j].getText().equals("")){
                    buttons[i][j].setDisable(false);
                }
            }
        }
    }

    public void end() {
        App.telaSalvarPartida(table);
    }
}
