package com.dannyj182.salesmanager.model.mapper;

import com.dannyj182.salesmanager.model.dto.CustomerDTO;
import com.dannyj182.salesmanager.model.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO toCustomerDTO(Customer customer);
    List<CustomerDTO> toCustomersDTO (List<Customer> customerList);
    Customer toCustomer(CustomerDTO customerDTO);
}
