package com.assignment.bd1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FileReaderController {
    @RequestMapping("/FileReader")
    public String index(){
        return "filereader";
    }
}
