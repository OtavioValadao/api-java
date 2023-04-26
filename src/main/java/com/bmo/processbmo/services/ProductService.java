package com.bmo.processbmo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bmo.processbmo.model.Product;
import com.bmo.processbmo.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    /**
     * Get the list of products
     * @return The list of products
     */
    public List<ProdutDTO> getAll(){

        List<Product> products = productRepository.findAll();

        return products.stream()
        .map(product -> new ModelMapper().map(product, ProductDTO.class))
        .collect(Collectors.toList());
    }

    /**
     * Get product searched by ID.
     * @param id Of the product that will be located.
     * @return One product if it has been found.
     */
    public Optional<ProdutDTO> getbyId(Integer id){
        return productRepository.findById(id);
    }

    /**
     * Method to add product to the list.
     * @param product That will be added
     * @return The product that was added to the list.
     */
    public ProdutDTO add(ProdutDTO product){
        return productRepository.save(product);
    }

    /**
     * Method to delete products by passing the ID.
     * @param id Of the product that will be deleted.
     */
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }



    /**
     * Method that will update a product.
     * @param product That will be updated.
     * @return The product after updating the list.
     */
    
    public Product update(Product product){        
        productRepository.merge(product);
        return product;
    }
}
