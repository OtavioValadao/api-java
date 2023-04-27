package com.bmo.processbmo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bmo.processbmo.model.Product;
import com.bmo.processbmo.model.exception.ResourceNotFoundException;
import com.bmo.processbmo.repository.ProductRepository;
import com.bmo.processbmo.shared.ProdutDTO;

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
        .map(product -> new ModelMapper().map(product, ProdutDTO.class))
        .collect(Collectors.toList());
    }

    /**
     * Get product searched by ID.
     * @param id Of the product that will be located.
     * @return One product if it has been found.
     */
    public Optional<ProdutDTO> getbyId(Integer id){
        Optional<Product> product = productRepository.findById(id);
        
        if(product.isEmpty()){
            throw new ResourceNotFoundException("Produto com id: " + id + " não encontrado");
        }

        ProdutDTO produtDTO = new ModelMapper().map(product.get(), ProdutDTO.class);

        return Optional.of(produtDTO);
        
    }

    /**
     * Method to add product to the list.
     * @param product That will be added
     * @return The product that was added to the list.
     */
    public ProdutDTO add(ProdutDTO productDTO){
        productDTO.setId(null);   
        
        ModelMapper mapper = new ModelMapper();

        Product product = mapper.map(productDTO, Product.class);
        
        product = productRepository.save(product);

        productDTO.setId(product.getId());

        return productDTO;
    }

    /**
     * Method to delete products by passing the ID.
     * @param id Of the product that will be deleted.
     */
    public void deleteProduct(Integer id) {

        Optional<Product> productFindId = productRepository.findById(id);

        if(productFindId.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível deletar o produto com o id:"+ id);
        }

        productRepository.deleteById(id);
    }



    /**
     * Method that will update a product.
     * @param product That will be updated.
     * @return The product after updating the list.
     */
    
    public ProdutDTO update(Integer id, ProdutDTO produtDTO){        

        produtDTO.setId(id);

        ModelMapper mapper = new ModelMapper();

        Product product = mapper.map(produtDTO, Product.class);

        productRepository.save(product);

        return produtDTO;

    }
}
