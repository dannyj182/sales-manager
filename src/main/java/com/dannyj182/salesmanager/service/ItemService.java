package com.dannyj182.salesmanager.service;

import com.dannyj182.salesmanager.model.dto.ItemDTO;
import com.dannyj182.salesmanager.model.dto.ItemPKDTO;
import com.dannyj182.salesmanager.model.entity.Item;
import com.dannyj182.salesmanager.model.entity.ItemPK;
import com.dannyj182.salesmanager.model.entity.Product;
import com.dannyj182.salesmanager.model.mapper.ItemMapper;
import com.dannyj182.salesmanager.repository.IItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemService implements IItemService {

    private final IItemRepository repository;
    private final ItemMapper mapper;
    private final IProductService productService;

    @Override
    public Optional<ItemDTO> findById(Long saleId, Long productId) {
        ItemPK itemId = new ItemPK(saleId,productId);
        return repository.findById(itemId).map(mapper::toItemDTO);
    }

    @Override
    public List<ItemDTO> findAll() {
        return mapper.toItemsDTO((List<Item>) repository.findAll());
    }

    @Override
    public List<Item> validateItems(List<ItemDTO> itemDTOList, Long saleId) {
        List<Item> itemList = new ArrayList<>();
        if(itemDTOList == null || saleId == null) return itemList;
        for(ItemDTO itemDTO: itemDTOList){
            if(itemDTO.getItemId() == null || itemDTO.getItemId().getProductId() == null) continue;
            if(itemDTO.getQuantity() > 0 && this.getItem(saleId, itemDTO.getItemId().getProductId()) == null){
                Item item = this.validateItem(itemDTO);
                if (item != null) itemList.add(item);
            }
        }
        return itemList;
    }

    private Item validateItem(ItemDTO itemDTO) {
        Product product = productService.extractProduct(itemDTO.getItemId().getProductId(), itemDTO.getQuantity());
        if (product == null) return null;
        itemDTO.setPrice(product.getPrice());
        itemDTO.setTotalItem(itemDTO.getPrice() * itemDTO.getQuantity());
        Item item = mapper.toItem(itemDTO);
        item.setProduct(product);
        return item;
    }

    @Override
    public List<Item> saveAll(List<Item> itemList) {
        return (List<Item>) repository.saveAll(itemList);
    }

    @Override
    public void emptyList(Long id) {
        List<Item> itemList = repository.findByItemId_SaleId(id);
        this.deleteAll(itemList);
    }

    @Override
    public void deleteAll(List<Item> itemList){
        if (itemList != null && !itemList.isEmpty()){
            this.returnProducts(itemList);
            repository.deleteAll(itemList);
        }
    }

    private void returnProducts(List<Item> itemList){
        itemList.forEach(item -> productService.returnProduct(item.getProduct().getProductId(), item.getQuantity()));
    }

    @Override
    public Item updateItem(ItemDTO itemDTO) {
        Item item = this.getItem(itemDTO.getItemId().getSaleId(), itemDTO.getItemId().getProductId());
        if (item == null) return null;
        Double newQuantity = itemDTO.getQuantity();
        Double currentQuantity = item.getQuantity();
        Long productId = item.getItemId().getProductId();
        if (newQuantity == 0 || newQuantity.equals(currentQuantity)) return null;
        else if (newQuantity > currentQuantity) {
            if (productService.extractProduct(productId, (newQuantity - currentQuantity)) == null) return null;
        }
        else productService.returnProduct(productId, currentQuantity - newQuantity);
        item.setQuantity(newQuantity);
        item.setTotalItem(item.getPrice() * item.getQuantity());
        return repository.save(item);
    }

    @Override
    public Item getItem(Long saleId, Long productId){
        if (saleId == null || productId == null) return null;
        else return repository.findById(new ItemPK(saleId,productId)).orElse(null);
    }
}
