package com.assignment.bd1.treatment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.assignment.bd1.treatment.readJson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class treatNotebooksColetados {

    public void insertNG_Coletados(String FileName, JdbcTemplate jdbcTemplate){

        readJson read = new readJson();
        LinkedList notebooks;
        String filePath = FileName;
        notebooks = read.readFile(filePath);
        String sqlcol = "INSERT INTO ng_coletado (modelo, sist_op, memo_ram, placa_video, processador, armazena_hd, armazena_ssd, preco, loja_retirada, data_coleta, marca) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlava = "INSERT INTO avaliacoes VALUES (?, ?, ?, ?)";
        //String sqloja = "INSERT INTO loja_cadastrada VALUES (?, ?)";
        //jdbcTemplate.update(sqloja, "Magazineluiza", "https://www.magazineluiza.com.br/notebook-gamer/informatica/s/in/ntbg/");
        //jdbcTemplate.update(sqloja, "Shoptime", "https://www.shoptime.com.br/busca/notebook-gamer?chave_search=achistory");
        //jdbcTemplate.update(sqloja, "Submarino", "https://www.submarino.com.br/busca/notebook");

        for (int i=0; i< notebooks.size(); i++) {
            HashMap hm = (HashMap) notebooks.get(i);
            List<String> usercomm = new ArrayList<String>();
            List<String> users = new ArrayList<String>();
            org.json.simple.JSONArray comments;
            org.json.simple.JSONArray clients;
            comments = (org.json.simple.JSONArray) hm.get("comentarios");
            clients = (org.json.simple.JSONArray) hm.get("avaliadores");
            for(int j=0; j<comments.size(); j++){
                usercomm.add(comments.get(j).toString());
            }
            for(int j=0; j<clients.size(); j++){
                users.add(clients.get(j).toString());
            }
            int csize = usercomm.size(), usize = users.size();
            String[] comentarios = usercomm.toArray(new String[csize]);
            String[] avaliadores = users.toArray(new String[usize]);

            jdbcTemplate.update(sqlcol, hm.get("name"), hm.get("so"), hm.get("ram"), hm.get("placa"), hm.get("processador"), hm.get("hd"), hm.get("ssd"), hm.get("preco"), "magalu", "03/02/2022", hm.get("marca"));
            jdbcTemplate.update(sqlava, hm.get("name"), hm.get("nota"), comentarios, avaliadores);
        }
    }

}
