package com.stockapi.StockAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;


@AllArgsConstructor
@Data
@Document
@NoArgsConstructor
@SuperBuilder
public class Product {
    @Id
    private String id;
    private String name;
    @Column(
            unique = true,
            nullable = false
    )
    private String refId;
    private int price;
    private int quantity;
    private int length;
    private int width;
    private int height;
    private String color;
}