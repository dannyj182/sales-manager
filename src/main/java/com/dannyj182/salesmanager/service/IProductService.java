package com.dannyj182.salesmanager.service;

import com.dannyj182.salesmanager.model.dto.ProductDTO;
import com.dannyj182.salesmanager.model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    ProductDTO saveProduct(ProductDTO productDTO);
    Optional<ProductDTO> findById(Long id);
    List<ProductDTO> findAll();
    boolean deleteById(Long id);
    ProductDTO editProduct (Long id, ProductDTO productDTO);
    Product extractProduct(Long id, Double quantity);
    void returnProduct(Long id, Double quantity);
}
