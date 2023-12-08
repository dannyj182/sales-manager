package com.dannyj182.salesmanager.service;

import com.dannyj182.salesmanager.model.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    ProductDTO saveProduct(ProductDTO productDTO);
    Optional<ProductDTO> findById(Long id);
    List<ProductDTO> findAll();
    boolean deleteById(Long id);
    ProductDTO editProduct (Long id, ProductDTO productDTO);
}
