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
}
