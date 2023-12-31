package com.cydeo.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Lab {

    @RequestMapping("lab")
    public String getLabs(Model model){
        model.addAttribute("firstLab","lab-00-coupling");
        model.addAttribute("secondLab","lab-01-ioc");
        model.addAttribute("thirdLab","lab-02-di");
        model.addAttribute("fourthLab","lab-03-springboot");
        model.addAttribute("fifthLab","lab-04-springmvc");

        return "lab/lab-list";
    }
}
