package com.stockapi.StockAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Product {
    private int id;
    private String name;
    private int price;
    private int quantity;
    private Object item;
}
