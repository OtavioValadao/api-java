package com.bmo.processbmo.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
import com.bmo.processbmo.shared.ProdutDTO;
import com.bmo.processbmo.view.Model.ProductResponse;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductResponse> getAll(){
        List<ProdutDTO> productDTOs = productService.getAll();

        ModelMapper mapper = new ModelMapper();

        List<ProductResponse> productResponse = productDTOs.stream()
            .map(productDtos -> mapper.map(
                productDtos, 
                ProductResponse.class))
            .collect(Collectors.toList());

        return productResponse;
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
    public void deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
    }
    
    @PutMapping("/{id}")
    @Transactional
    public Product updateProduct(@RequestBody Product product, @PathVariable Integer id){
        return productService.update(id, product);
    }
}
