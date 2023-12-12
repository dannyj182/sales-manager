package com.dannyj182.salesmanager.repository;

import com.dannyj182.salesmanager.model.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends CrudRepository<Product, Long> {
    Boolean existsByProductId(Long id);
}
