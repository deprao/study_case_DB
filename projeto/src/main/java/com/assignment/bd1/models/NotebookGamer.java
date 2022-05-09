package com.assignment.bd1.models;

public class NotebookGamer {

    public String nome;
    public String modelo;
    public String sist_op;
    public String memo_ram;
    public String placa_video;
    public String processador;
    public String armazena_hd;
    public String armazena_ssd;
    public String marca;

    public NotebookGamer(String nome, String modelo, String sist_op, String memo_ram, String placa_video, String processador, String armazena_hd, String armazena_ssd, String marca) {
        this.nome = nome;
        this.modelo = modelo;
        this.sist_op = sist_op;
        this.memo_ram = memo_ram;
        this.placa_video = placa_video;
        this.processador = processador;
        this.armazena_hd = armazena_hd;
        this.armazena_ssd = armazena_ssd;
        this.marca = marca;
    }

    public String getNome() {
        return nome;
    }

    public String getModelo() {
        return modelo;
    }

    public String getSist_op() {
        return sist_op;
    }

    public String getMemo_ram() {
        return memo_ram;
    }

    public String getPlaca_video() {
        return placa_video;
    }

    public String getProcessador() {
        return processador;
    }

    public String getArmazena_hd() {
        return armazena_hd;
    }

    public String getArmazena_ssd() {
        return armazena_ssd;
    }

    public String getMarca() {
        return marca;
    }
}
