package com.dannyj182.salesmanager.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class SaleDTO {

    private long saleId;
    private LocalDate dateSale;
    private double totalSale;
    private List<ItemDTO> items;
    private CustomerDTO customer;
}
