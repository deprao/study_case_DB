package com.assignment.bd1.controller;

import com.assignment.bd1.treatment.readJson;
import com.assignment.bd1.treatment.treatCrawling;
import com.assignment.bd1.treatment.treatNotebooksColetados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class CrawlingController
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/Crawling")
    public String crawling(){
        return "crawlingrequisition";
    }

    @RequestMapping("/Crawling-Success")
    public String crawlingsuccess(){
        return "crawlingrequisitionaccepted";
    }

    @PostMapping("/fileChosen")
    public String fileChosen(@RequestParam(value = "jsonFile") String file, HttpServletRequest request) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = new Date();

        String timeDate = dateFormat.format(date);

        treatCrawling treatment = new treatCrawling(jdbcTemplate);

        String crawlingStore = treatment.findStore(file);
        String crawlingDate = treatment.findDate(file);

        treatment.insertRequisition(file, timeDate);

        treatNotebooksColetados treatNGC = new treatNotebooksColetados();

        treatNGC.insertNG_Coletados(file, jdbcTemplate, crawlingDate, crawlingStore);


        //System.out.println(timeDate);
        //System.out.println(file);
        return "redirect:/Crawling-Success";
    }
}
