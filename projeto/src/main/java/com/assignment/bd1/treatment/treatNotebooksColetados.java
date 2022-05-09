package com.assignment.bd1.treatment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.assignment.bd1.treatment.readJson;

import java.util.*;

public class treatNotebooksColetados {

    public void insertNG_Coletados(String FileName, JdbcTemplate jdbcTemplate, String date, String crawlingStore){

        readJson read = new readJson();
        LinkedList notebooks;
        String filePath = FileName;
        notebooks = read.readFile(filePath);
        String sqlng = "INSERT INTO Notebook_gamer (nome, modelo, sist_op, memo_ram, placa_video, processador, armazena_hd, armazena_ssd, marca) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlcol = "INSERT INTO ng_coletado (modelo_coletado, preco, loja_retirada, data_coleta) VALUES (?, ?, ?, ?)";
        String sqlava = "INSERT INTO avaliacoes VALUES (?, ?, ?, ?, ?)";
        //String sqloja = "INSERT INTO loja_cadastrada VALUES (?, ?)";
        //jdbcTemplate.update(sqloja, "Magazineluiza", "https://www.magazineluiza.com.br/notebook-gamer/informatica/s/in/ntbg/");
        //jdbcTemplate.update(sqloja, "Shoptime", "https://www.shoptime.com.br/busca/notebook-gamer?chave_search=achistory");
        //jdbcTemplate.update(sqloja, "Submarino", "https://www.submarino.com.br/busca/note");
        int modelexists, avaexists;
        int ngColetadoExists;
        int rcount;
        String price, rating, tablenames, crawlnames;
        String[] avals;
        double p, r, rsum;

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

            modelexists = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Notebook_gamer WHERE modelo = ?", Integer.class, hm.get("modelo"));
            avaexists = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM avaliacoes WHERE modelo_avaliacao = ?", Integer.class, hm.get("modelo"));

            price = (String)hm.get("preco");
            if(price.equals("Fora de Estoque")){
                p = 0;
            }else {
                price = price.replace(".", "");
                price = price.replace(",", ".");
                p = Double.parseDouble(price);
            }

            rating = (String)hm.get("nota");
            if(rating.equals("null")){
                r = 0;
            }else{
                rating = rating.replace("Nota:","");
                rating = rating.replace(",", ".");
                r = Double.parseDouble(rating);
            }

            if(modelexists == 0) {
                jdbcTemplate.update(sqlng, hm.get("name"), hm.get("modelo"), hm.get("so"), hm.get("ram"), hm.get("placa"), hm.get("processador"), hm.get("hd"), hm.get("ssd"), hm.get("marca"));
                jdbcTemplate.update(sqlcol, hm.get("modelo"), p, crawlingStore, date);
                if(r != 0)
                    jdbcTemplate.update(sqlava, hm.get("modelo"), r, comentarios, avaliadores, 1);
            }
            else{
                jdbcTemplate.update("UPDATE Notebook_gamer SET sist_op = ? WHERE sist_op = 'NT/NI' AND modelo = ?", hm.get("so"), hm.get("modelo"));
                jdbcTemplate.update("UPDATE Notebook_gamer SET processador = ? WHERE processador = 'NT/NI' AND modelo = ?", hm.get("processador"), hm.get("modelo"));
                jdbcTemplate.update("UPDATE Notebook_gamer SET memo_ram = ? WHERE memo_ram = 'NT/NI' AND modelo = ?", hm.get("ram"), hm.get("modelo"));
                jdbcTemplate.update("UPDATE Notebook_gamer SET placa_video = ? WHERE placa_video = 'NT/NI' AND modelo = ?", hm.get("placa"), hm.get("modelo"));
                jdbcTemplate.update("UPDATE Notebook_gamer SET armazena_hd = ? WHERE armazena_hd = 'NT/NI' AND modelo = ?", hm.get("hd"), hm.get("modelo"));
                jdbcTemplate.update("UPDATE Notebook_gamer SET armazena_ssd = ? WHERE armazena_ssd = 'NT/NI' AND modelo = ?", hm.get("ssd"), hm.get("modelo"));
                jdbcTemplate.update("UPDATE Notebook_gamer SET marca = ? WHERE marca = 'NT/NI' AND modelo = ?", hm.get("marca"), hm.get("modelo"));

                if(avaexists != 0 && r != 0){
                    avals = jdbcTemplate.queryForObject("SELECT nome_avaliador FROM avaliacoes WHERE modelo_avaliacao = ?", String[].class, hm.get("modelo"));
                    tablenames = Arrays.toString(avals);
                    crawlnames = Arrays.toString(avaliadores);
                    tablenames = tablenames.replace("{","");
                    tablenames = tablenames.replace("}", "");
                    tablenames = tablenames.replace("\"", "");
                    if(!tablenames.equals(crawlnames)){
                        jdbcTemplate.update("UPDATE avaliacoes  SET nome_avaliador = CAST(nome_avaliador AS VARCHAR[]) || ? WHERE modelo_avaliacao = ?", avaliadores, hm.get("modelo"));
                        jdbcTemplate.update("UPDATE avaliacoes  SET descricao = CAST(descricao AS VARCHAR[]) || ? WHERE modelo_avaliacao = ?", comentarios, hm.get("modelo"));
                    }
                    rsum = jdbcTemplate.queryForObject("SELECT nota FROM avaliacoes WHERE modelo_avaliacao = ?", Double.class, hm.get("modelo"));
                    rcount = jdbcTemplate.queryForObject("SELECT vezes_avaliado FROM avaliacoes WHERE modelo_avaliacao = ?", Integer.class, hm.get("modelo"));
                    r += rsum*rcount;
                    rcount++;
                    r = r/rcount;
                    jdbcTemplate.update("UPDATE avaliacoes SET nota = ? WHERE modelo_avaliacao = ?", r, hm.get("modelo"));

                }

                ngColetadoExists = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM ng_coletado WHERE modelo_coletado = ? AND preco = ? AND loja_retirada = ? AND data_coleta = ? ", Integer.class, hm.get("modelo"), p, crawlingStore, date);
                if (ngColetadoExists == 0) {
                    jdbcTemplate.update(sqlcol, hm.get("modelo"), p, crawlingStore, date);
                }
            }
        }

    }

}
