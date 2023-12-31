package com.dannyj182.salesmanager.model.mapper;

import com.dannyj182.salesmanager.model.dto.ItemDTO;
import com.dannyj182.salesmanager.model.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SaleMapper.class, ProductMapper.class, ItemPKMapper.class})
public interface ItemMapper {

    @Mappings({
            @Mapping(source = "product.name", target = "productName"),
            @Mapping(source = "product.brand", target = "productBrand")
    })
    ItemDTO toItemDTO(Item item);

    List<ItemDTO> toItemsDTO(List<Item> items);

    @Mappings({
            @Mapping(target = "sale", ignore = true),
            @Mapping(target = "product", ignore = true),
    })
    Item toItem(ItemDTO itemDTO);
}
