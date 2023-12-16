package com.dannyj182.salesmanager.model.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter @Setter
public class ItemPK implements Serializable {
    private Long saleId;
    private Long productId;
}
