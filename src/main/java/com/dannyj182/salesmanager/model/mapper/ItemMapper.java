package com.dannyj182.salesmanager.model.mapper;

import com.dannyj182.salesmanager.model.dto.ItemDTO;
import com.dannyj182.salesmanager.model.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {SaleMapper.class, ProductMapper.class})
public interface ItemMapper {

    @Mappings({
            @Mapping(source = "product.productId", target = "productId"),
            @Mapping(source = "product.name", target = "productName"),
            @Mapping(source = "product.brand", target = "productBrand")
    })
    ItemDTO toItemDTO(Item item);

    @Mappings({
            @Mapping(target = "itemId.saleId", ignore = true),
            @Mapping(target = "itemId.productId", ignore = true),
            @Mapping(target = "sale", ignore = true),
            @Mapping(target = "product", ignore = true),
    })
    Item toItem(ItemDTO itemDTO);
}
