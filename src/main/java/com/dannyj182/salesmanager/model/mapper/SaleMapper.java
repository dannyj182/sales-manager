package com.dannyj182.salesmanager.model.mapper;

import com.dannyj182.salesmanager.model.dto.SaleDTO;
import com.dannyj182.salesmanager.model.entity.Sale;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class, ProductMapper.class})
public interface SaleMapper {

    SaleDTO toSaleDTO(Sale sale);
    List<SaleDTO> toSalesDTO(List<Sale> sales);
    Sale toSale(SaleDTO saleDTO);
}
