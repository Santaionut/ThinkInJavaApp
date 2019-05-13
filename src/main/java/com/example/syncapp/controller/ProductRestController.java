package com.example.syncapp.controller;


import com.example.syncapp.exception.ResourceNotFoundException;
import com.example.syncapp.model.Product;
import com.example.syncapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductRestController  {


    @Autowired
    ProductRepository productRepository;


    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return  productRepository.findAll();
    }

    @GetMapping("/sortProductsByFieldAsc")
    public List<Product> sortProductsByFieldAsc(@PathParam(value = "field") String field ){

        return productRepository.findAll(Sort.by(field).ascending());
    }


    @PostMapping("/products")
    public Product createNote(@Valid @RequestBody Product product) {
        return productRepository.save(product);
    }


    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable(value = "id") Long productId){
        return productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product","id",productId));
    }

    @PutMapping("/product/{id}")
    public Product updateProduct(@PathVariable(value = "id") Long productId, @Valid @RequestBody Product productDetails){

        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product","id",productId));

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setShort_description(productDetails.getShort_description());
        product.setPrice(productDetails.getPrice());

        Product updatedProduct = productRepository.save(product);


        return  updatedProduct;

    }

}
