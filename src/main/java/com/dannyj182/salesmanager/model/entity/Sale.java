package com.dannyj182.salesmanager.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sale {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long saleId;
    private LocalDate dateSale;
    private Double totalSale;
    @OneToMany
    private List<Item> items;
    @ManyToOne
    @JoinColumn (name = "customer_id")
    private Customer customer;
}
