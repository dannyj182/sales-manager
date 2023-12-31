package com.dannyj182.salesmanager.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class SaleDTO {

    private long saleId;
    private String dateSale;
    private double totalSale;
    private List<ItemDTO> items;
    private CustomerDTO customer;
}
