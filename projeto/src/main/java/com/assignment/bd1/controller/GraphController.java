package com.assignment.bd1.controller;

import com.assignment.bd1.models.NotebookGamer;
import com.assignment.bd1.models.NotebooksColetados;
import com.assignment.bd1.repository.AvaliacoesService;
import com.assignment.bd1.repository.NotebookGamerService;
import com.assignment.bd1.repository.NotebooksColetadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Controller
public class GraphController {

    private String modelo;

    public void setModelo(String modelo) {

        this.modelo = modelo;
    }

    public String getModelo() {

        return this.modelo;
    }

    @Autowired
    NotebooksColetadosService notebooksColetadosService;

    @Autowired
    NotebookGamerService notebookGamerService;

    @Autowired
    AvaliacoesService avaliacoesService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/GraphRequisition")
    public String GraphRequisition(){

        return "graphrequisition";
    }

    @RequestMapping("/GraphRequisitionMenu")
    public String GraphRequisitionMenu(){

        return "graphrequisitionmenu";
    }

    @RequestMapping("/GraphMenu")
    public String GraphMenu(){

        return "graphmenu";
    }

    @RequestMapping("/GraphMenuSiteInfo")
    public String GraphMenuSiteInfo(){

        return "graphmenusiteinfo";
    }

    @PostMapping("/graphfilterchosen")
    public String graphFilterChosen(@RequestParam(value = "graphfilter") String filter, HttpServletRequest request) {

        setModelo(filter);

        return "redirect:/GraphMenu";
    }

    @RequestMapping("/Lista-Graphs-Site-Data")
    public String GraphListDataQuantity(Model md){
        Integer quantidadeMagalu = 0;
        Integer quantidadeShoptime = 0;
        Integer quantidadeSubmarino = 0;
        List<NotebooksColetados> notebooksColetadosList = notebooksColetadosService.findAll();

        Map<String, Integer> graphData = new TreeMap<>();
        for(NotebooksColetados notes : notebooksColetadosList){
            if(notes.getLoja_retirada().equals("Magazineluiza")){
                quantidadeMagalu = quantidadeMagalu + 1;
            }
            else if(notes.getLoja_retirada().equals("Shoptime")){
                quantidadeShoptime = quantidadeShoptime + 1;
            }
            else if(notes.getLoja_retirada().equals("Submarino")){
                quantidadeSubmarino = quantidadeSubmarino + 1;
            }
        }

        graphData.put("Magazineluiza", quantidadeMagalu);
        graphData.put("Shoptime", quantidadeShoptime);
        graphData.put("Submarino", quantidadeSubmarino);

        md.addAttribute("chartData", graphData);

        return "graphlistdataquantity";
    }

    @RequestMapping("/Lista-Graphs-Site-Data-Insert")
    public String GraphListDataInsertion(Model md){
        Integer quantidade03_02 = 0;
        Integer quantidade07_02 = 0;
        Integer quantidade14_02 = 0;
        List<NotebooksColetados> notebooksColetadosList = notebooksColetadosService.findAll();

        Map<String, Integer> graphData = new TreeMap<>();
        for(NotebooksColetados notes : notebooksColetadosList){
            if(notes.getData_coleta().equals("03-02-2022")){
                quantidade03_02 = quantidade03_02 + 1;
            }
            else if(notes.getData_coleta().equals("07-02-2022")){
                quantidade07_02 = quantidade07_02 + 1;
            }
            else if(notes.getData_coleta().equals("14-02-2022")){
                quantidade14_02 = quantidade14_02 + 1;
            }
        }

        graphData.put("03-02-2022", quantidade03_02);
        graphData.put("07-02-2022", quantidade07_02);
        graphData.put("14-02-2022", quantidade14_02);

        md.addAttribute("chartData", graphData);

        return "graphlistinsertng";
    }

    @RequestMapping("/Lista-Graphs-Site-Data-Price-Range")
    public String GraphListDataPriceRange(Model md){
        Integer lessThan1000 = 0;
        Integer lessThan3000 = 0;
        Integer lessThan5000 = 0;
        Integer lessThan8000 = 0;
        Integer moreThan8000 = 0;

        lessThan1000 = notebooksColetadosService.returnNgColetadoPriceLess(1000.0);
        lessThan3000 = notebooksColetadosService.returnNgColetadoPriceLessMore(1000.0, 3000.0);
        lessThan5000 = notebooksColetadosService.returnNgColetadoPriceLessMore(3000.0, 5000.0);
        lessThan8000 = notebooksColetadosService.returnNgColetadoPriceLessMore(5000.0, 8000.0);
        moreThan8000 = notebooksColetadosService.returnNgColetadoPriceMore(8000.0);

        Map<String, Integer> graphData = new TreeMap<>();

        graphData.put("0 - 1000", lessThan1000);
        graphData.put("1000 - 3000", lessThan3000);
        graphData.put("3000 - 5000", lessThan5000);
        graphData.put("5000 - 8000", lessThan8000);
        graphData.put("> 8000", moreThan8000);

        md.addAttribute("chartData", graphData);

        return "graphlistpricerange";
    }

    @RequestMapping("/Lista-Graphs-Site-Avaliacoes-Data")
    public String GraphListAvaliacoesStats(Model md){
        Integer NotebooksGamerTotais = 0;
        Integer NotebooksAvaliados  = 0;
        Integer NotebooksNaoAvaliados = 0;

        NotebooksGamerTotais = notebookGamerService.returnNotebookGamerQuantity();
        NotebooksAvaliados = avaliacoesService.returnAvaliacoesQuantity();

        NotebooksNaoAvaliados = NotebooksGamerTotais - NotebooksAvaliados;


        Map<String, Integer> graphData = new TreeMap<>();

        graphData.put("Notebooks Avaliados", NotebooksAvaliados);
        graphData.put("Notebooks Nao Avaliados", NotebooksNaoAvaliados);

        md.addAttribute("chartData", graphData);

        return "graphlistavaliacoes";
    }



    @RequestMapping("/Lista-Graphs-Notebooks-Coletados-Magalu")
    public String GraphListPriceMagalu(Model md){

        List<NotebooksColetados> notebooksColetadosList = notebooksColetadosService.findByModel(getModelo());

        Map<String, Double> graphData = new TreeMap<>();
        for(NotebooksColetados notes : notebooksColetadosList){
            if(notes.getLoja_retirada().equals("Magazineluiza")){
                graphData.put(notes.getData_coleta(), notes.getPreco());
            }
        }

        md.addAttribute("chartData", graphData);

        return "graphlist";
    }

    @RequestMapping("/Lista-Graphs-Notebooks-Coletados-Shoptime")
    public String GraphListPriceShoptime(Model md){

        List<NotebooksColetados> notebooksColetadosList = notebooksColetadosService.findByModel(getModelo());

        Map<String, Double> graphData = new TreeMap<>();
        for(NotebooksColetados notes : notebooksColetadosList){
            if(notes.getLoja_retirada().equals("Shoptime")){
                graphData.put(notes.getData_coleta(), notes.getPreco());
            }
        }

        md.addAttribute("chartData", graphData);

        return "graphlist";
    }

    @RequestMapping("/Lista-Graphs-Notebooks-Coletados-Sub")
    public String GraphListPriceSub(Model md){

        List<NotebooksColetados> notebooksColetadosList = notebooksColetadosService.findByModel(getModelo());

        Map<String, Double> graphData = new TreeMap<>();
        for(NotebooksColetados notes : notebooksColetadosList){
            if(notes.getLoja_retirada().equals("Submarino")){
                graphData.put(notes.getData_coleta(), notes.getPreco());
            }
        }

        md.addAttribute("chartData", graphData);

        return "graphlist";
    }


}
