package com.assignment.bd1.controller;

import com.assignment.bd1.repository.CrawlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CrawlController {

    @Autowired
    CrawlService service;

    @RequestMapping(value = "/Crawling-Requisitions", method = RequestMethod.GET)
    public String index(Model md){
        md.addAttribute("crawls", service.findAll());

        return "listcrawlingrequisitions";
    }

}
