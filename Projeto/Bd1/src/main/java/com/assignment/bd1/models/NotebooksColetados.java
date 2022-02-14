package com.assignment.bd1.models;

import java.util.Date;

public class NotebooksColetados {

    public Long id;
    public String modelo;
    public String sist_op;
    public String memo_ram;
    public String placa_video;
    public String processador;
    public String armazena_hd;
    public String armazena_ssd;
    public String loja_retirada;
    public String preco;
    public String data_coleta;

    public NotebooksColetados(Long id, String modelo, String sist_op, String memo_ram, String placa_video, String processsador, String armazena_hd, String armazena_ssd, String loja_retirada, String preco, String data_coleta) {
        this.id = id;
        this.modelo = modelo;
        this.sist_op = sist_op;
        this.memo_ram = memo_ram;
        this.placa_video = placa_video;
        this.processador = processsador;
        this.armazena_hd = armazena_hd;
        this.armazena_ssd = armazena_ssd;
        this.loja_retirada = loja_retirada;
        this.preco = preco;
        this.data_coleta = data_coleta;
    }
}
