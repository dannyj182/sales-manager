package com.dannyj182.salesmanager.service;

import com.dannyj182.salesmanager.model.dto.CustomerDTO;
import com.dannyj182.salesmanager.model.entity.Customer;
import com.dannyj182.salesmanager.model.mapper.CustomerMapper;
import com.dannyj182.salesmanager.repository.ICustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService{

    private final ICustomerRepository repository;
    private final CustomerMapper mapper;

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        if (repository.existsByPassportCard(customerDTO.getPassportCard())) return null;
        return mapper.toCustomerDTO(repository.save(mapper.toCustomer(customerDTO)));
    }

    @Override
    public Optional<CustomerDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toCustomerDTO);
    }

    @Override
    public List<CustomerDTO> findAll() {
        return mapper.toCustomersDTO((List<Customer>) repository.findAll());
    }

    @Override
    public boolean deleteById(Long id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }

    @Override
    public CustomerDTO editCustomer(Long id, CustomerDTO customerDTO) {
        Optional<Customer> optionalCustomer = repository.findById(id);
        if (optionalCustomer.isEmpty()) return null;
        CustomerDTO customerDTOEdited = mapper.toCustomerDTO(optionalCustomer.get());
        customerDTOEdited.editCustomer(customerDTO);
        return mapper.toCustomerDTO(repository.save(mapper.toCustomer(customerDTOEdited)));
    }

    @Override
    public Customer getCustomer(Long id) {
        if (id == null) return null;
        else return repository.findById(id).orElse(null);
    }
}
