package com.dannyj182.salesmanager.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {

    @EmbeddedId
    private ItemPK id;
    private Double price;
    private Double quantity;
    private Double totalItem;
    @ManyToOne
    @MapsId("saleId")
    @JoinColumn(name = "sale_id", insertable = false, updatable = false)
    private Sale sale;
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;
}
