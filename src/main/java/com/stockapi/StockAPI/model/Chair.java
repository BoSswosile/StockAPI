package com.stockapi.StockAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Chair {
    private int length;
    private int width;
    private int height;
    private int price;
}
