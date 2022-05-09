package com.assignment.bd1.models;

public class Avaliacoes {

    public String modelo_avaliacao;
    public Double nota;
    public String descricao;
    public String nome_avaliador;
    public Integer vezes_avaliado;

    public Avaliacoes(String modelo_avaliacao, Double nota, String descricao, String nome_avaliador, Integer vezes_avaliado) {
        this.modelo_avaliacao = modelo_avaliacao;
        this.nota = nota;
        this.descricao = descricao;
        this.nome_avaliador = nome_avaliador;
        this.vezes_avaliado = vezes_avaliado;
    }

    public String getModelo_avaliacao() {
        return modelo_avaliacao;
    }

    public Double getNota() {
        return nota;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNome_avaliador() {
        return nome_avaliador;
    }

    public Integer getVezes_avaliado() {
        return vezes_avaliado;
    }
}
