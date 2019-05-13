package com.example.syncapp.controller;


import com.example.syncapp.repository.ProductRepository;
import com.example.syncapp.utils.ProductUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@Controller()
public class HomeController {

    @Autowired
    ProductRepository productRepository;

    private static String UPLOADED_FOLDER = "/Users/asanta/personal_goals/ThinkInJavaApp/uploads/";



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getAllProducts( Model model){

        model.addAttribute("numberProducts", productRepository.count());
        model.addAttribute("products", productRepository.findAll());


        return "product/index";
    }

    @RequestMapping(value = "/importProducts", method = RequestMethod.GET)
    public String getSyncProducts(){
        return  "product/import";
    }



    @RequestMapping(value = "/importProducts", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public String syncProducts(@RequestParam("file") MultipartFile file, Model model) {


        BufferedReader reader = null;
        ProductUtil productUtil = new ProductUtil();
        int countLine = 1;

        try {
            reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null ){
                String [] product = line.split(";");
                productUtil.parseProduct(product);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        productRepository.saveAll(productUtil.getListOfProducts());
        return "redirect:/";

    }



}
