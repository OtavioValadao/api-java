package com.bmo.processbmo.repository;
import com.bmo.processbmo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    void deleteById(Long id);
    void merge(Product product);
}
