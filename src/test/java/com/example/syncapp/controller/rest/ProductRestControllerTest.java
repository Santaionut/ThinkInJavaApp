package com.example.syncapp.controller.rest;


import com.example.syncapp.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ProductRestControllerTest  extends  AbstractTest{

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }


    @Test
    public void getProductsList() throws Exception {
        String uri = "/api/products";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Product[] productlist = super.mapFromJson(content, Product[].class);
        assertTrue(productlist.length > 0);
    }

    @Test
    public void createProduct() throws Exception {
        String uri = "/api/products";
        Product product = new Product();
        product.setIdproduct((long) 44);
        product.setName("Ginger");
        String inputJson = super.mapToJson(product);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Product product_response = super.mapFromJson(content,Product.class);
        assertEquals(product_response.getName(), "Ginger");
    }

    @Test
    public void updateProduct() throws Exception {
        String uri = "/api/product/1";
        Product product = new Product();
        product.setName("Lemon");
        product.setShort_description("This product are a best ");

        String inputJson = super.mapToJson(product);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();


        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Product product_response = super.mapFromJson(content,Product.class);
        assertEquals(product_response.getName(), "Lemon");
    }

    @Test
    public void findById() throws Exception{
        String uri = "/api/product/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Product product_response = super.mapFromJson(content,Product.class);
        assertEquals(product_response.getName(), "Lemon");
    }


}