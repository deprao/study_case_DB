package com.assignment.bd1.models;

public class Crawls {

    public Integer id;
    public String versao;
    public String loja_coleta;
    public String data_coleta;
    public String data_alteracao;

    public Crawls(Integer id, String versao, String loja_coleta, String data_coleta, String data_alteracao) {
        this.id = id;
        this.versao = versao;
        this.loja_coleta = loja_coleta;
        this.data_coleta = data_coleta;
        this.data_alteracao = data_alteracao;
    }
}
