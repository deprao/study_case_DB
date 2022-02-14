package com.assignment.bd1.treatment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

public class treatCrawling {
    String crawlingFile;
    String crawlingStore;
    String crawlingDate;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public treatCrawling(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    public String findStore(String crawlingFile){
        String magalu = "maga";
        String shop = "shop";
        String sub = "sub";

        boolean testMaga = crawlingFile.contains(magalu);
        boolean testShop = crawlingFile.contains(shop);
        boolean testSub = crawlingFile.contains(sub);

        if (testMaga)
            return "Magazineluiza";
        else if (testShop)
            return "Shoptime";
        else if(testSub)
            return "Submarino";
        else
            return "NT/NI";
    }

    public String findDate(String crawlingFile){
        String date0302 = "03-02";
        String date0702 = "07-02";
        String date1402 = "14-02";

        boolean test1 = crawlingFile.contains(date0302);
        boolean test2 = crawlingFile.contains(date0702);
        boolean test3 = crawlingFile.contains(date1402);

        if (test1)
            return "03-02-2022";
        else if (test2)
            return "07-02-2022";
        else if (test3)
            return "14-02-2022";
        else
            return "NT/NI";
    }

    public void insertRequisition(String crawlingF, String crawlingD){

        String loja = findStore(crawlingF);
        String executionDate = findDate(crawlingF);

        String sqlng = "INSERT INTO crawlers (versao, loja_coleta, data_inclusao, data_alteracao) VALUES (?, ?, ?, ?)";

        int rows;
        rows = jdbcTemplate.update(sqlng, crawlingF, loja, executionDate, crawlingD);

        if (rows > 0) {
        System.out.println("Conexao aceita!");
        }

    }


    public String getCrawlingFile() {
        return crawlingFile;
    }

    public void setCrawlingFile(String crawlingFile) {
        this.crawlingFile = crawlingFile;
    }

    public String getCrawlingStore() {
        return crawlingStore;
    }

    public void setCrawlingStore(String crawlingStore) {
        this.crawlingStore = crawlingStore;
    }

    public String getCrawlingDate() {
        return crawlingDate;
    }

    public void setCrawlingDate(String crawlingDate) {
        this.crawlingDate = crawlingDate;
    }
}
