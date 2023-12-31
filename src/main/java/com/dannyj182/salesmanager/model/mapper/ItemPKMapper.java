package com.dannyj182.salesmanager.model.mapper;

import com.dannyj182.salesmanager.model.dto.ItemPKDTO;
import com.dannyj182.salesmanager.model.entity.ItemPK;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemPKMapper {

    ItemPKDTO toItemPKDTO(ItemPK itemPK);

    @InheritInverseConfiguration
    ItemPK toItemPK(ItemPKDTO itemPKDTO);
}
