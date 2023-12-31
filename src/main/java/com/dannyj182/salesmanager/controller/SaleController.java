package com.dannyj182.salesmanager.controller;

import com.dannyj182.salesmanager.model.dto.SaleDTO;
import com.dannyj182.salesmanager.service.ISaleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
@AllArgsConstructor
public class SaleController {

    private final ISaleService service;

    @PostMapping("/")
    public ResponseEntity<SaleDTO> saveSale(@RequestBody SaleDTO saleDTO){
        SaleDTO saleDTOSaved = service.saveSale(saleDTO);
        if(saleDTOSaved != null) return new ResponseEntity<>(saleDTOSaved, HttpStatus.CREATED);
        else return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PatchMapping("/items")
    public ResponseEntity<SaleDTO> addItems(@RequestBody SaleDTO saleDTO){
        SaleDTO saleDTOSaved = service.addItems(saleDTO);
        if(saleDTOSaved != null) return new ResponseEntity<>(saleDTOSaved, HttpStatus.CREATED);
        else return new ResponseEntity<>(HttpStatus.CONFLICT);
    }


    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(saleDTO -> new ResponseEntity<>(saleDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/")
    public ResponseEntity<List<SaleDTO>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        if(service.deleteById(id)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/items")
    public ResponseEntity<SaleDTO> deleteItems(@RequestBody SaleDTO saleDTO){
        SaleDTO saleDTOSaved = service.deleteItems(saleDTO);
        if(saleDTOSaved != null) return new ResponseEntity<>(saleDTOSaved, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SaleDTO> editSale(@PathVariable Long id, @RequestBody SaleDTO saleDTO){
        SaleDTO saleDTOEdited = service.editSale(id, saleDTO);
        if (saleDTOEdited != null) return new ResponseEntity<>(saleDTOEdited, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
