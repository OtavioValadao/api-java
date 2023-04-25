package com.bmo.processbmo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bmo.processbmo.model.Product;
import com.bmo.processbmo.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAll(){
        return productService.getAll();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return productService.add(product);
    }

    @GetMapping("/{id}")
    public Optional<Product> getbyId(@PathVariable Integer id){
        return productService.getbyId(id);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Integer id){
        productService.delete(id);
        return "Produto com id:" + id + " foi deletado com sucesso";
    }

    @PutMapping("/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable Integer id){
        return productService.update(id, product);
    }
}
