package com.assignment.bd1.controller;

import com.assignment.bd1.repository.NotebookGamerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotebookGamerController {

    String nome;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    @Autowired
    NotebookGamerService service;

    @RequestMapping(value = "/Listar-NG", method = RequestMethod.GET)
    public String listaNGColetados(Model md){
        md.addAttribute("notebooksgamer", service.findAll());

        return "listnotebooks";
    }

    @RequestMapping("/NG-Nome-Filter")
    public String ngNomeFilterRequest(){

        return "ngnamefilter";
    }

    @RequestMapping(value = "/Listar-NG-Filtrados", method = RequestMethod.GET)
    public String listarNGFiltradas(Model md){

        md.addAttribute("notebooksgamer", service.findByName(getNome()));

        return "listnotebooks";
    }


    @PostMapping("/ngnamefilterchosen")
    public String NGFilterChosen(@RequestParam(value = "ngnamefilter") String filter, HttpServletRequest request) {

        setNome(filter);

        return "redirect:/Listar-NG-Filtrados";
    }

}
