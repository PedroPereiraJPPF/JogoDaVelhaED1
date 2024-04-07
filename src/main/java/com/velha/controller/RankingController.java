package com.velha.controller;

import com.velha.App;
import com.velha.Entities.Jogador;
import com.velha.arquivo.ArchiveManager;
import com.velha.collections.linkedList.singlyLinkedList.SinglyLinkedList;
import com.velha.collections.queue.QueueInterface;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class RankingController {
    SinglyLinkedList<Jogador> arquivo;

    @FXML TableView<Jogador> tabela = new TableView<>();
    @FXML TextField pesquisa;
    
    @SuppressWarnings("unchecked")
    public void initialize() {
        TableColumn<Jogador, Integer> colocacao = new TableColumn<>("Colocação");
        TableColumn<Jogador, String> jogador = new TableColumn<>("Jogador");
        TableColumn<Jogador, Integer> pontuacao = new TableColumn<>("Pontuação");
        TableColumn<Jogador, Integer> partidas = new TableColumn<>("Partidas");

        colocacao.setCellValueFactory(new PropertyValueFactory<Jogador, Integer>("colocacao"));
        jogador.setCellValueFactory(new PropertyValueFactory<Jogador, String>("nome"));
        pontuacao.setCellValueFactory(new PropertyValueFactory<Jogador, Integer>("pontuacao"));
        partidas.setCellValueFactory(new PropertyValueFactory<Jogador, Integer>("partidasJogadas"));

        tabela.getColumns().setAll(colocacao, jogador, pontuacao, partidas);

        try {
            ArchiveManager<Jogador> playerManager = new ArchiveManager<>();

            arquivo = playerManager.lerBinario("Ranking.bin");
            QueueInterface<Jogador> jogadores = arquivo.toQueueInterface();

            for (int i = 0; i < jogadores.size(); i++) {
                if (!jogadores.peek().getNome().equals("reservado1" ) || !jogadores.peek().getNome().equals("reservado2")) {
                    jogadores.peek().setColocacao(i+1);
                    tabela.getItems().add(jogadores.remove());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void search() {
        String jogadorPesquisado = pesquisa.getText();
        if (jogadorPesquisado.equals("")) {
            clearTable();
            stableTable();
        } else {
            try {
                Jogador jogador = arquivo.search(new Jogador(jogadorPesquisado));

                updateTable(jogador);
            } catch (Exception e) {

            }
        }
    }

    public void updateTable(Jogador jogador) {
        if (!jogador.equals(null)) {
            tabela.getItems().setAll(jogador);
        }
    }

    public void stableTable() {
        try {
            QueueInterface<Jogador> jogadores = arquivo.toQueueInterface();

            for (int i = 0; i < jogadores.size(); i++) {
                if (!jogadores.peek().getNome().equals("reservado1") || !jogadores.peek().getNome().equals("reservado2")) {
                    tabela.getItems().add(jogadores.remove());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearTable() {
        tabela.getColumns().clear();
    }

    public void voltar() {
        App.telaInicio();
    }
}
