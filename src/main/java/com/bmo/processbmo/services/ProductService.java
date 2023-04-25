package com.bmo.processbmo.services;

import java.util.List;
import java.util.Optional;

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
    public List<Product> getAll(){
        return productRepository.findAll();
    }

    /**
     * Get product searched by ID.
     * @param id Of the product that will be located.
     * @return One product if it has been found.
     */
    public Optional<Product> getbyId(Integer id){
        return productRepository.findById(id);
    }

    /**
     * Method to add product to the list.
     * @param product That will be added
     * @return The product that was added to the list.
     */
    public Product add(Product product){
        return productRepository.save(product);
    }

    /**
     * Method to delete products by passing the ID.
     * @param id Of the product that will be deleted.
     */
    public ResponseEntity<Void> delete(Integer id) {
        ProductService product = new ProductService();
        product.getbyId(id);

        if (product.isPresent()) {
            produtoRepository.delete(produtoOptional.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    /**
     * Method that will update a product.
     * @param product That will be updated.
     * @return The product after updating the list.
     */
    public Product update(Integer id, Product product){        
        product.setId(id);
        return productRepository.update(product);
    }
}
