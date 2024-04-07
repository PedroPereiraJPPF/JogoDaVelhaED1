package com.velha.controller;

import java.io.IOException;

import com.velha.Entities.Partida;
import com.velha.Entities.Table;
import com.velha.arquivo.ArchiveManager;
import com.velha.collections.linkedList.singlyLinkedList.SinglyLinkedList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class ReplayController {
    @FXML
    private GridPane containerJogo;

    @FXML
    private Button voltarJogada;

    @FXML
    private Button avancarJogada;

    @FXML
    private ListView<Partida> listaPartidas;

    private Button[][] buttons;

    private Table tableBuffer;

    SinglyLinkedList<Integer[][]> movimentos;

    int movimentoAtual;

    public void initialize() 
    {
        try {
            ArchiveManager<Partida> arquiveManager = new ArchiveManager<>();
            SinglyLinkedList<Partida> partidas = arquiveManager.lerBinario("Partidas.bin");

            partidas.iterate();

            ObservableList<Partida> items = FXCollections.observableArrayList();

            while(!partidas.isEmpty()) {
                items.add(partidas.removeLast());
            }

            this.listaPartidas.setItems(items);

            this.initializeTable(3);

            listaPartidas.setOnMouseClicked(event -> {
                Partida partidaSelecionada = listaPartidas.getSelectionModel().getSelectedItem();
                this.tableBuffer = partidaSelecionada.getTable();
                movimentos = this.tableBuffer.replayMoves();
                this.movimentoAtual = 0;
                try {
                    this.updateButtons(movimentos.get(movimentos.size()));
                } catch (Exception e) {
                    e.printStackTrace();
                }              
            });

            avancarJogada.setOnAction(event -> {
                try {
                    this.updateButtons(this.movimentos.get(++this.movimentoAtual));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void initializeTable(int n) 
    {
        buttons = new Button[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Button button = new Button();
                button.setMinSize(50, 50);
                containerJogo.add(button, j, i);
                buttons[i][j] = button;
                buttons[i][j].setDisable(true);
            }
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

}
