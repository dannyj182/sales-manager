package com.dannyj182.salesmanager.model.mapper;

import com.dannyj182.salesmanager.model.dto.ProductDTO;
import com.dannyj182.salesmanager.model.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toProductDTO(Product product);

    List<ProductDTO> toProductsDTO(List<Product> products);

    Product toProduct(ProductDTO productDTO);
}
