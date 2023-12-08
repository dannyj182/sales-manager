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
public class Product {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column(name = "product_id")
    private Long productId;
    private String name;
    private String brand;
    private Double price;
    private Double quantity;
}
