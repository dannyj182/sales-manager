package com.dannyj182.salesmanager.service;

import com.dannyj182.salesmanager.model.dto.ItemDTO;
import com.dannyj182.salesmanager.model.entity.Item;
import com.dannyj182.salesmanager.model.entity.Product;
import com.dannyj182.salesmanager.model.mapper.ItemMapper;
import com.dannyj182.salesmanager.repository.IItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ItemService implements IItemService {

    private final IItemRepository repository;
    private final ItemMapper mapper;
    private final IProductService productService;

    @Override
    public List<Item> saveAll(List<Item> itemList) {
        return (List<Item>) repository.saveAll(itemList);
    }
    @Override
    public List<Item> validateItems(List<ItemDTO> itemDTOList) {
        List<Item> itemList = new ArrayList<>();
        for(ItemDTO itemDTO: itemDTOList){
            if(itemDTO.getQuantity() > 0){
                Item item = this.validateItem(itemDTO);
                if (item != null) itemList.add(item);
            }
        }
        return itemList;
    }

    private Item validateItem(ItemDTO itemDTO) {
        Product product = productService.validateProduct(itemDTO.getProductId(), itemDTO.getQuantity());
        if (product == null) return null;
        itemDTO.setPrice(product.getPrice());
        itemDTO.setTotalItem(itemDTO.getPrice() * itemDTO.getQuantity());
        Item item = mapper.toItem(itemDTO);
        item.setProduct(product);
        return item;
    }
}
