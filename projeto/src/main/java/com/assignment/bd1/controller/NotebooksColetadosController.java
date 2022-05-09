package com.assignment.bd1.controller;

import com.assignment.bd1.repository.CrawlService;
import com.assignment.bd1.repository.NotebooksColetadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotebooksColetadosController {

    Double price;

    public Double getPrice() {

        return this.price;
    }

    public void setPrice(Double price) {

        this.price = price;
    }

    @Autowired
    NotebooksColetadosService service;

    @RequestMapping(value = "/Listar-NG_Coletados", method = RequestMethod.GET)
    public String listaNGColetados(Model md){
        md.addAttribute("notebookscoletados", service.findAll());

        return "listnotebookcoletados";
    }

    @RequestMapping("/FiltrarPesquisa")
    public String filtrar(){
        return "choosengrequisition";
    }

    @RequestMapping("/NG-Price-Filter")
    public String ngPriceFilterRequest(){

        return "ngpricefilter";
    }

    @RequestMapping(value = "/Listar-NG-Filtrados-Preco", method = RequestMethod.GET)
    public String listarNGFiltradas(Model md){

        md.addAttribute("notebookscoletados", service.findByPrice(getPrice()));

        return "listnotebookcoletados";
    }

    @RequestMapping(value = "/Listar-NG-Ordenados-Preco-Asc", method = RequestMethod.GET)
    public String listarNGFiltradasOrdPriceAsc(Model md){

            md.addAttribute("notebookscoletados", service.orderByPriceAsc());

        return "listnotebookcoletados";
    }

    @RequestMapping(value = "/Listar-NG-Ordenados-Preco-Desc", method = RequestMethod.GET)
    public String listarNGFiltradasOrdPriceDesc(Model md){

        md.addAttribute("notebookscoletados", service.orderByPriceDesc());

        return "listnotebookcoletados";
    }

    @PostMapping("/ngpricefilterchosen")
    public String NGFilterChosen(@RequestParam(value = "ngpricefilter") Double filter, HttpServletRequest request) {

        setPrice(filter);

        return "redirect:/Listar-NG-Filtrados-Preco";
    }


}
