package com.dannyj182.salesmanager.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemDTO {

    private String productName;
    private String productBrand;
    private double price;
    private double quantity;
    private double totalItem;
}
