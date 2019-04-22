package com.example.syncapp.controller;

import com.example.syncapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    ProductRepository productRepository;



    @GetMapping(name = "/getAllProducts")
    public String getAllProducts( Model model){

        model.addAttribute("numberProducts", productRepository.count());
        model.addAttribute("products", productRepository.findAll());


        return "product/index";
    }
}
