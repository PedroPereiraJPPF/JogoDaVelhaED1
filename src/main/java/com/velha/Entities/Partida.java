package com.velha.Entities;

import java.io.Serializable;
import java.util.Date;

public class Partida implements Serializable{
    private Table table;
    private String jogador1;
    private String jogador2;
    private Date horario;

    public  Partida(Table table, String jogador1, String jogador2) {
        setTable(table);
        setJogador1(jogador1);
        setJogador2(jogador2);
        horario = new Date();
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public String getJogador1() {
        return jogador1;
    }

    public void setJogador1(String jogador1) {
        this.jogador1 = jogador1;
    }

    public String getJogador2() {
        return jogador2;
    }

    public void setJogador2(String jogador2) {
        this.jogador2 = jogador2;
    }

    public Date getHorario() {
        return horario;
    }

    @Override
    public String toString()
    {
        return this.jogador1 + " vs " + this.jogador2;
    }
}
