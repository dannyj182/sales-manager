package com.dannyj182.salesmanager.service;

import com.dannyj182.salesmanager.model.dto.SaleDTO;

import java.util.List;
import java.util.Optional;

public interface ISaleService {
    SaleDTO saveSale(SaleDTO saleDTO);
    SaleDTO addItems(SaleDTO saleDTO);
    Optional<SaleDTO> findById(Long id);
    List<SaleDTO> findAll();
    boolean deleteById(Long id);
    SaleDTO deleteItems(SaleDTO saleDTO);
    SaleDTO editSale(Long id, SaleDTO saleDTO);
}
