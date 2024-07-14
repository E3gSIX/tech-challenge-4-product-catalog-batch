package com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DomainProduct {

    private String name;
    private String description;
    private double price;
    private String type;
    private int quantity;
    private LocalDateTime dateInclusion;
}