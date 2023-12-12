package com.dannyj182.salesmanager.service;

import com.dannyj182.salesmanager.model.dto.ProductDTO;
import com.dannyj182.salesmanager.model.entity.Product;
import com.dannyj182.salesmanager.model.mapper.ProductMapper;
import com.dannyj182.salesmanager.repository.IProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    private final IProductRepository repository;
    private final ProductMapper mapper;

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        productDTO.setProduct();
        return mapper.toProductDTO(repository.save(mapper.toProduct(productDTO)));
    }

    @Override
    public Optional<ProductDTO> findById(Long id) {
        return repository.findById(id).map(product -> mapper.toProductDTO(product));
    }

    @Override
    public List<ProductDTO> findAll() {
        return mapper.toProductsDTO((List<Product>) repository.findAll());
    }

    @Override
    public boolean deleteById(Long id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public ProductDTO editProduct(Long id, ProductDTO productDTO) {
        Optional<Product> optionalProduct = repository.findById(id);
        if(optionalProduct.isPresent()){
            ProductDTO productDTOEdited = mapper.toProductDTO(optionalProduct.get());
            productDTOEdited.editProduct(productDTO);
            return mapper.toProductDTO(repository.save(mapper.toProduct(productDTOEdited)));
        }
        return null;
    }
}
