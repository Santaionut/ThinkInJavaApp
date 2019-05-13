package com.example.syncapp.utils;

import com.example.syncapp.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductUtil {

    private List<Product> listOfProducts = new ArrayList<Product>();


    public void parseProduct(String [] productAttributes){

        Float price = Float.parseFloat(productAttributes[3]);

        Product product = new Product();
        product.setName(productAttributes[0]);
        product.setDescription(productAttributes[1]);
        product.setShort_description(productAttributes[2]);
        product.setPrice(price);

        listOfProducts.add(product);
    }

    public List<Product> getListOfProducts() {
        return listOfProducts;
    }
}
