package com.dannyj182.salesmanager.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class SaleDTO {

    private Long id;
    private LocalDate dateSale;
    private Double total;
    private List<ProductDTO> productList;
    private CustomerDTO customer;
}
