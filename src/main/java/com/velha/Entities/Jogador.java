package com.velha.Entities;

import java.io.Serializable;

public class Jogador implements Serializable{
    private int colocacao;
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

    public int getColocacao() {
        return colocacao;
    }

    public String getNome() {
        return nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public int getPartidasJogadas() {
        return partidasJogadas;
    }

    public void setColocacao(int colocacao) {
        this.colocacao = colocacao;
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Jogador) {
            return this.nome.equals(((Jogador) obj).getNome());
        }

        return super.equals(obj);
    }
}
