package com.dannyj182.salesmanager.controller;

import com.dannyj182.salesmanager.model.dto.ProductDTO;
import com.dannyj182.salesmanager.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final IProductService service;

    @PostMapping("/")
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(service.saveProduct(productDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
        return service.findById(id)
                .map(productDTO -> new ResponseEntity<>(productDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDTO>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        if(service.deleteById(id)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTO> editCustomer(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        ProductDTO productDTOEdited = service.editProduct(id, productDTO);
        if (productDTOEdited != null) return new ResponseEntity<>(productDTOEdited, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
