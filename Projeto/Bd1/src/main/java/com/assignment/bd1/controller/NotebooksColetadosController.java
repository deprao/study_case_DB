package com.assignment.bd1.controller;

import com.assignment.bd1.repository.CrawlService;
import com.assignment.bd1.repository.NotebooksColetadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NotebooksColetadosController {

    @Autowired
    NotebooksColetadosService service;

    @RequestMapping(value = "/Listar-NG_Coletados", method = RequestMethod.GET)
    public String listaNGColetados(Model md){
        md.addAttribute("notebookscoletados", service.findAll());

        return "listnotebooks";
    }

    @RequestMapping("/FiltrarPesquisa")
    public String filtrar(){
        return "choosengrequisition";
    }

}
