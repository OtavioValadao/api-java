package com.bmo.processbmo.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@Tag(name= "Gerenciamento de produtos Api")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @Operation(summary = "Realiza consulta de produtos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca dos produtos.")
    })
    @GetMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
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


    @Operation(summary = "Realiza o cadastro de produtos", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Valores passado na requisição inválidos"),
            @ApiResponse(responseCode = "403", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o cadastro de produto")
    })
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
    @Operation(summary = "Realiza consulta de produtos por Id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de produto.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductResponse>> getbyId(@PathVariable Integer id){
        Optional<ProdutDTO> productDTO =  productService.getbyId(id);

        ProductResponse productResponse = new ModelMapper().map(productDTO.get(), ProductResponse.class);

        return new ResponseEntity<>(Optional.of(productResponse), HttpStatus.OK);

    }
    @Operation(summary = "Realiza delete de produto por Id", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "403", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de produto.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    };

    @Operation(summary = "Realiza atualização de produto por Id", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "403", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de produto.")
    })
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
