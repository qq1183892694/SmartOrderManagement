package com.example.demo3.controller;

import com.example.demo3.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ViewController")
public class ViewController {


    @Autowired
    IOrdersService ordersService;

    @RequestMapping("/View")
    public String View(Model model){

        model.addAttribute("falt",2);
        return "view/view";
    }
}
