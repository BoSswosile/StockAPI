package com.stockapi.StockAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Table {
    private int length;
    private int width;
    private int height;
    private String color;

}
