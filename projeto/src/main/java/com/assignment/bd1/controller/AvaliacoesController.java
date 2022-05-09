package com.assignment.bd1.controller;

import com.assignment.bd1.repository.AvaliacoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AvaliacoesController {

    @Autowired
    AvaliacoesService service;
    String modelo;

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getModelo() {
        return this.modelo;
    }

    @RequestMapping(value = "/Listar-Avaliacoes", method = RequestMethod.GET)
    public String listarTodasAvaliacoes(Model md){
        md.addAttribute("avaliacoes", service.findAll());

        return "listavaliacoes";
    }

    @RequestMapping("/Avaliacao-Filter")
    public String avaliacaoFilterRequest(){

        return "avaliacaofilter";
    }

    @RequestMapping(value = "/Listar-Avaliacoes-Filtradas", method = RequestMethod.GET)
    public String listarAvaliacoesFiltradas(Model md){

        md.addAttribute("avaliacoes", service.findByModel(getModelo()));

        return "chooseavaliacao";
    }

    @PostMapping("/avaliacaoFilterChosen")
    public String avaliacaoFilterChosen(@RequestParam(value = "avaliacaoFilter") String filter, HttpServletRequest request) {

        setModelo(filter);

        return "redirect:/Listar-Avaliacoes-Filtradas";
    }

}
