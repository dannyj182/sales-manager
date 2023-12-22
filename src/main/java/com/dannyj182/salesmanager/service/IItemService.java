package com.dannyj182.salesmanager.service;

import com.dannyj182.salesmanager.model.dto.ItemDTO;
import com.dannyj182.salesmanager.model.entity.Item;

import java.util.List;

public interface IItemService {
    List<Item> saveAll(List<Item> itemList);
    List<Item> validateItems(List<ItemDTO> itemDTOList);
    void emptyList(Long id);
}
