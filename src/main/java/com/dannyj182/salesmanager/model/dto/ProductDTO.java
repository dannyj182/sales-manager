package com.dannyj182.salesmanager.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductDTO {

    private Long id;
    private String name;
    private String brand;
    private Double cost;
    private Double quantity;
}
