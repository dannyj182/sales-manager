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
public class Customer {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long customerId;
    private String name;
    private String lastName;
    private String passportCard;
}
