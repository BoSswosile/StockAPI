package com.stockapi.StockAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    private int id;
    private String name;
    private int price;
    private int quantity;
    private int length;
    private int width;
    private int height;
    private String color;
}
