package com.dannyj182.salesmanager.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter @Setter
public class ItemPK implements Serializable {
    @Column(name = "sale_id")
    private Long saleId;
    @Column(name = "product_id")
    private Long productId;
}
