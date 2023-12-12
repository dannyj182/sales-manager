package com.dannyj182.salesmanager.controller;

import com.dannyj182.salesmanager.model.dto.CustomerDTO;
import com.dannyj182.salesmanager.service.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {

    private final ICustomerService service;

    @PostMapping("/")
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customerDTO){
        CustomerDTO customerDTOSaved = service.saveCustomer(customerDTO);
        if (customerDTOSaved != null) return new ResponseEntity<>(customerDTOSaved, HttpStatus.CREATED);
        else return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(customerDTO -> new ResponseEntity<>(customerDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/")
    public ResponseEntity<List<CustomerDTO>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        if (service.deleteById(id)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDTO> editCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){
        CustomerDTO customerDTOEdited = service.editCustomer(id, customerDTO);
        if (customerDTOEdited != null) return new ResponseEntity<>(customerDTOEdited, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
