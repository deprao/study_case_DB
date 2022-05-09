package com.assignment.bd1.models;

import java.util.Date;

public class NotebooksColetados {

    public Long id;
    public String modelo_coletado;
    public String loja_retirada;
    public Double preco;
    public String data_coleta;

    public NotebooksColetados(Long id, String modelo_coletado, String loja_retirada, Double preco, String data_coleta) {
        this.id = id;
        this.modelo_coletado = modelo_coletado;
        this.loja_retirada = loja_retirada;
        this.preco = preco;
        this.data_coleta = data_coleta;
    }

    public Long getId() {
        return id;
    }

    public String getModelo_coletado() {
        return modelo_coletado;
    }

    public String getLoja_retirada() {
        return loja_retirada;
    }

    public Double getPreco() {
        return preco;
    }

    public String getData_coleta() {
        return data_coleta;
    }
}
