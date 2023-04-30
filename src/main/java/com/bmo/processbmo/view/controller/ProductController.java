package com.bmo.processbmo.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bmo.processbmo.services.ProductService;
import com.bmo.processbmo.shared.ProdutDTO;
import com.bmo.processbmo.view.Model.ProductRequest;
import com.bmo.processbmo.view.Model.ProductResponse;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll(){
        List<ProdutDTO> productDTOs = productService.getAll();

        ModelMapper mapper = new ModelMapper();

        List<ProductResponse> productResponse = productDTOs.stream()
            .map(productDtos -> mapper.map(
                productDtos, 
                ProductResponse.class))
            .collect(Collectors.toList());

        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest){
        ModelMapper mapper = new ModelMapper();

        ProdutDTO produtDTO = mapper.map(productRequest, ProdutDTO.class);

        produtDTO =  productService.add(produtDTO);

        return new ResponseEntity<>(mapper.map(produtDTO, ProductResponse.class), HttpStatus.CREATED );

        
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductResponse>> getbyId(@PathVariable Integer id){
        Optional<ProdutDTO> productDTO =  productService.getbyId(id);

        ProductResponse productResponse = new ModelMapper().map(productDTO.get(), ProductResponse.class);

        return new ResponseEntity<>(Optional.of(productResponse), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest productRequest, @PathVariable Integer id){
        ModelMapper mapper = new ModelMapper();
        ProdutDTO produtDTO = mapper.map(productRequest, ProdutDTO.class);

        produtDTO = productService.update(id, produtDTO);

        return new ResponseEntity<>(
            mapper.map(produtDTO, ProductResponse.class),
            HttpStatus.OK
        );
    }
}
