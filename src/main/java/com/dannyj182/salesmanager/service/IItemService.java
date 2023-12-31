package com.dannyj182.salesmanager.service;

import com.dannyj182.salesmanager.model.dto.ItemDTO;
import com.dannyj182.salesmanager.model.entity.Item;

import java.util.List;
import java.util.Optional;

public interface IItemService {
    Optional<ItemDTO> findById(Long saleId, Long productId);
    List<ItemDTO> findAll();
    List<Item> validateItems(List<ItemDTO> itemDTOList, Long saleId);
    List<Item> saveAll(List<Item> itemList);
    void emptyList(Long id);
    void deleteAll(List<Item> itemList);
    Item updateItem(ItemDTO itemDTO);
    Item getItem(Long saleId, Long productId);
}
