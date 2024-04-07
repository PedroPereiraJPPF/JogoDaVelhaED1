package com.velha.Entities;

import java.io.Serializable;
import java.util.List;

import com.velha.collections.Collections_Certas.list.DoubleLinkedList;
import com.velha.collections.linkedList.singlyLinkedList.SinglyLinkedList;
import com.velha.collections.stack.LinkedStack;
import com.velha.collections.stack.StackInterface;

public class Table implements Serializable {
    private Integer[][] tableMatriz;
    private int actualPlayer; // esse valor pode ser um ou zero 
    private int turn;
    private LinkedStack<Integer[][]> playsStack;

    public Table(int n) {
        this.actualPlayer = this.turn = 0;
        this.tableMatriz = new Integer[n][n];
        this.playsStack = new LinkedStack<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < this.tableMatriz[i].length; j++) {
                this.tableMatriz[i][j] = 0;
            }
        }

        this.saveNewPlay();
    }

    public Integer[][] getTableMatriz() {
        return this.tableMatriz;
    }

    public void addTurn() {
        this.turn += 1;
    }

    public int getTurn() {
        return this.turn;
    }

    // muda o player atual
    public int changePlayer() {
        this.actualPlayer = this.turn % 2;

        return this.actualPlayer;
    }

    public int getActualPlayer() {
        return this.actualPlayer;
    }

    public boolean play(int i, int j) {
        if (this.tableMatriz[i][j] != 0) {
            return false;
        }

        this.saveNewPlay();

        if (this.actualPlayer == 0){
            this.tableMatriz[i][j] = 1;
        } else{
            this.tableMatriz[i][j] = 2;
        }

        return true;
    }

    // checa se o player atual ganhou o jogo
    public boolean checkWinner() {
        int value;
        int n = this.tableMatriz.length;

        value = this.actualPlayer == 0 ? 1 : 2; // se o jogador atual for o zero o valor vai ser circulo caso contrario vai ser X

        for (int i = 0; i < n; i++) {
            if ((this.tableMatriz[i][0] == value && this.tableMatriz[i][1] == value && this.tableMatriz[i][2] == value) ||
                (this.tableMatriz[0][i] == value && this.tableMatriz[1][i] == value && this.tableMatriz[2][i] == value)) {
                    this.saveNewPlay();
                    return true;
            }
        }

        if ((this.tableMatriz[0][0] == value && this.tableMatriz[1][1] == value && this.tableMatriz[2][2] == value) ||
            (this.tableMatriz[0][2] == value && this.tableMatriz[1][1] == value && this.tableMatriz[2][0] == value)) {
                this.saveNewPlay();
                return true;
        }

        return false;
    }

    // serve para voltar ao movimento anterior
    public void undoMove() {
        try {
            this.tableMatriz = playsStack.pop();
            this.turn--;
            this.changePlayer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SinglyLinkedList<Integer[][]> replayMoves() {
        return playsStack.toLinkedList();
    }

    // salva o estado atual da tabela
    private void saveNewPlay() {
        try {
            Integer[][] copy = this.copyState(this.tableMatriz);

            playsStack.push(copy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // cria uma copia da tabela atual
    private Integer[][] copyState(Integer[][] original) {
        Integer[][] copy = new Integer[original.length][original[0].length];
        for (int i = 0; i < original.length; i++) {
            for (int j = 0; j < original[i].length; j++) {
                copy[i][j] = original[i][j];
            }
        }
        return copy;
    }

    // função para testes no terminal
    public void renderTestTable() {
        for (int i = 0; i < this.tableMatriz.length; i++) {
            for (int j = 0; j < this.tableMatriz[i].length; j++) {
                System.out.print(this.tableMatriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}
