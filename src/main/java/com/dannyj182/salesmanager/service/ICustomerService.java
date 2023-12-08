package com.dannyj182.salesmanager.service;

import com.dannyj182.salesmanager.model.dto.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    Optional<CustomerDTO> findById(Long id);
    List<CustomerDTO> findAll();
    boolean deleteById(Long id);
    CustomerDTO editCustomer(Long id, CustomerDTO customerDTO);
}
