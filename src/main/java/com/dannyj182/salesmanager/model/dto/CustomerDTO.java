package com.dannyj182.salesmanager.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomerDTO {

    private Long id;
    private String name;
    private String lastName;
    private String passportCard;
}
