package com.bmo.processbmo.repository;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import com.bmo.processbmo.model.Product;
import com.bmo.processbmo.model.exception.ResourceNotFoundException;

@Repository
public class ProductRepositoryLocal {
    private List<Product> products = new ArrayList<Product>();
    private Integer lastId = 0;

    /**
     * Get the list of products
     * @return The list of products
     */
    public List<Product> getAll(){
        return products;
    }

    /**
     * Get product searched by ID.
     * @param id Of the product that will be located.
     * @return One product if it has been found.
     */
    public Optional<Product> getbyId(Integer id){
        return products
                .stream()
                .filter(product -> product.getId() == id)
                .findFirst();
    }

    /**
     * Method to add product to the list.
     * @param product That will be added
     * @return The product that was added to the list.
     */
    public Product add(Product product){
        lastId++;
        product.setId(lastId);
        products.add(product);
        return product;
    }

    /**
     * Method to delete products by passing the ID.
     * @param id Of the product that will be deleted.
     */
    public void delete(Integer id){
        products.removeIf(product -> product.getId() == id);
    }

    /**
     * Method that will update a product.
     * @param product That will be updated.
     * @return The product after updating the list.
     */
    public Product update(Product product){
        
        Optional<Product> productFound = getbyId(product.getId());

        if(productFound.isEmpty()){
            throw new ResourceNotFoundException("Product not found");
        }

        delete(product.getId());
        
        products.add(product);
        return product;
    }
}
