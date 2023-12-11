package com.dannyj182.salesmanager.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CustomerDTO {

    private long customerId;
    private String name;
    private String lastName;
    private String passportCard;

    public void editCustomer(CustomerDTO customerDTO) {
        if(customerDTO.name != null) this.setName(customerDTO.name);
        if(customerDTO.lastName != null) this.setLastName(customerDTO.lastName);
        if(customerDTO.passportCard != null) this.setPassportCard(customerDTO.passportCard);
    }
}
