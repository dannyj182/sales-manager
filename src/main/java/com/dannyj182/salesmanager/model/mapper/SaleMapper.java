package com.dannyj182.salesmanager.model.mapper;

import com.dannyj182.salesmanager.model.dto.SaleDTO;
import com.dannyj182.salesmanager.model.entity.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class, ItemMapper.class})
public interface SaleMapper {

    SaleDTO toSaleDTO(Sale sale);
    List<SaleDTO> toSalesDTO(List<Sale> sales);
    @Mappings({
            @Mapping(target = "items", ignore = true)
    })
    Sale toSale(SaleDTO saleDTO);
}
