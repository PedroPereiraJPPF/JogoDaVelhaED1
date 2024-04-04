package com.velha.controller;

import com.velha.App;
import com.velha.Entities.Table;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class SalvarPartidasController {
    
    private static Table table;

    @FXML GridPane gridPane;
    @FXML Text motivacional;

    public void initialize() {
        table = App.getTable();
        int n = table.getTableMatriz().length;
        
        Button[][] buttons = new Button[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Button button = new Button();
                button.setMinSize(50, 50);
                gridPane.add(button, j, i);
                buttons[i][j] = button;
                buttons[i][j].setDisable(true);
                buttons[i][j].setOpacity(0.99);
            }
        }

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                int value = table.getTableMatriz()[i][j];
                buttons[i][j].setText(value == 0 ? "" : (value == 1 ? "O" : "X"));
            }
        }

        if (table.getTurn() > 7) {
            motivacional.setText("Partida Disputada!!!");
        } else {
            motivacional.setText("Fácil Demais!!!");
        }
    }

    @FXML TextField j1;
    @FXML TextField j2;
    
    public void submit() {
        
    }

    public void delete() {
        App.telaInicio();
    }
}
