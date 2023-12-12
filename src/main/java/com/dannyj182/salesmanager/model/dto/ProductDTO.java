package com.dannyj182.salesmanager.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductDTO {

    private long productId;
    private String name;
    private String brand;
    private double price;
    private double quantity;

    public void editProduct(ProductDTO productDTO) {
        if(productDTO.name != null) this.setName(productDTO.name);
        if(productDTO.brand != null) this.setBrand(productDTO.brand);
        if(productDTO.price >= 0) this.setPrice(productDTO.price);
        if(productDTO.quantity >= 0) this.setQuantity(productDTO.quantity);
    }

    public void setProduct() {
        if (this.getPrice() < 0 ) this.setPrice(0);
        if (this.getQuantity() < 0 ) this.setQuantity(0);
    }
}
