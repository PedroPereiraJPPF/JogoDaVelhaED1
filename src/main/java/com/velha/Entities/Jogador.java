package com.velha.Entities;

import java.io.Serializable;

public class Jogador implements Serializable{
    private String nome;
    private int pontuacao;
    private int partidasJogadas;

    public Jogador (String nome, int pontuacao) {
        setNome(nome);
        setPontuacao(pontuacao);
        incrementPartidasJogadas();
    }

    public Jogador (String nome) {
        setNome(nome);
    }

    public Jogador () {}

    public String getNome() {
        return nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public int getPartidasJogadas() {
        return partidasJogadas;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public void incrementPontuacao() {
        pontuacao++;
    }

    public void incrementPartidasJogadas() {
        partidasJogadas++;
    }

    public boolean equals(Jogador obj) {
        return this.nome == obj.getNome();
    }
}
