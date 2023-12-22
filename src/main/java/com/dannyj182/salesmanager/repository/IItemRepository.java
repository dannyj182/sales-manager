package com.dannyj182.salesmanager.repository;

import com.dannyj182.salesmanager.model.entity.Item;
import com.dannyj182.salesmanager.model.entity.ItemPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IItemRepository extends CrudRepository<Item, ItemPK> {
    List<Item> findByItemId_SaleId(Long saleId);
}
