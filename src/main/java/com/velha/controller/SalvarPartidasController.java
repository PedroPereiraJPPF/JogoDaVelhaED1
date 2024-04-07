package com.velha.controller;

import java.util.Random;

import com.velha.App;
import com.velha.Entities.Jogador;
import com.velha.Entities.Partida;
import com.velha.Entities.Table;
import com.velha.arquivo.ArchiveManager;
import com.velha.collections.Collections_Certas.stack.InterfacePilha;
import com.velha.collections.Collections_Certas.stack.PilhaEncadeada;
import com.velha.collections.linkedList.singlyLinkedList.SinglyLinkedList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class SalvarPartidasController {
    
    private static Table table;

    @FXML GridPane gridPane;
    @FXML Text motivacional;
    @FXML Text vencedor1;
    @FXML Text vencedor2;

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

        if (table.checkWinner()) {
            if (table.getActualPlayer() == 0) {
                vencedor2.setVisible(false);
            } else {
                vencedor1.setVisible(false);
            }
        } else {
            vencedor1.setVisible(false);
            vencedor2.setVisible(false);
        }

        Random random = new Random();
        Double rotacao = 10 + ((30 - 10) * random.nextDouble());

        vencedor1.setRotate(rotacao);
        vencedor2.setRotate(rotacao);

        rotacao = rotacao * -1;

        motivacional.setRotate(rotacao);

        if (table.getTurn() > 7) {
            motivacional.setText("Partida Disputada!!!");
        } else {
            motivacional.setText("Fácil Demais!!!");
        }
    }

    @FXML TextField j1;
    @FXML TextField j2;
    
    public void submit() {
        if (j1.getText().isEmpty()) {
            j1.setText("reservado1");
        }
        if (j2.getText().isEmpty()) {
            j2.setText("reservado2");
        }

        salvarJogadores();
        salvarPartida();

        App.telaInicio();
    }

    private void salvarJogadores() {
        ArchiveManager<Jogador> playerManager = new ArchiveManager<>();

        SinglyLinkedList<Jogador> rankingList;

        try {
            rankingList = playerManager.lerBinario("Ranking.bin");

            Jogador jogador1 = rankingList.search(new Jogador(j1.getText()));
            Jogador jogador2 = rankingList.search(new Jogador(j2.getText()));

            if (jogador1 == null) {
                jogador1 = new Jogador(j1.getText(), (vencedor1.isVisible() ? 1 : 0));

                rankingList.addLast(jogador1);
            } else {
                jogador1.incrementPartidasJogadas();

                if (vencedor1.isVisible()) {
                    jogador1.incrementPontuacao();
                }
            }

            if (jogador2 == null) {
                jogador2 = new Jogador(j2.getText(), (vencedor2.isVisible() ? 1 : 0));

                rankingList.addLast(jogador2);
            } else {
                jogador2.incrementPartidasJogadas();

                if (vencedor2.isVisible()) {
                    jogador2.incrementPontuacao();
                }
            }

            sort(rankingList);

            playerManager.salvarEmBinario(rankingList, "Ranking.bin");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void salvarPartida() {
        ArchiveManager<Partida> tableManager = new ArchiveManager<>();

        Partida partida = new Partida(table, j1.getText(), j2.getText());

        SinglyLinkedList<Partida> partidasList = new SinglyLinkedList<>();
        
        try {   
            partidasList = tableManager.lerBinario("Partidas.bin");

            partidasList.addLast(partida);

            partidasList.iterate();

            tableManager.salvarEmBinario(partidasList, "Partidas.bin");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public SinglyLinkedList<Jogador> sort(SinglyLinkedList<Jogador> list) {
        InterfacePilha<Integer> h = new PilhaEncadeada<>();
        int hi, j;
        Jogador comp;
    
        try {
        h.push(1);
    
            while (h.peek() < list.size()) {
                    h.push(((3 * h.peek()) + 1));
            }
    
            while (h.peek() > 1) {
                hi = h.pop() / 3;
    
                for (int i = hi; i < list.size(); i++ ) {
                    comp = list.get(i);
                    j = i - hi;

                    while ((j >= 0) && (comp.getPontuacao() < list.get(j).getPontuacao())) {
                        list.set(list.get(j), (j + hi));
                        j = j - hi;
                    }

                    list.set(comp, j + hi);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void delete() {
        App.telaInicio();
    }
}
