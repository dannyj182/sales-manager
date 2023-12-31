package com.dannyj182.salesmanager.controller;

import com.dannyj182.salesmanager.model.dto.ItemDTO;
import com.dannyj182.salesmanager.service.IItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
public class ItemController {

    private final IItemService service;

    @GetMapping("/")
    public ResponseEntity<ItemDTO> findById(@RequestParam(name = "saleId") Long saleId,
                                            @RequestParam(name = "productId") Long productId){
        return service.findById(saleId, productId)
                .map(itemDTO -> new ResponseEntity<>(itemDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ItemDTO>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
}
