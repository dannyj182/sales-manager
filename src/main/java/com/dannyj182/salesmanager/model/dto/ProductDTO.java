package com.dannyj182.salesmanager.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductDTO {

    private long productId;
    private String name;
    private String brand;
    private double price;
    private double quantity;
}
