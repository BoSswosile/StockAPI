package com.stockapi.StockAPI.model;

import lombok.*;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Getter
public class Chair extends Product {
    private int length;
    private int width;
    private int height;
    private String color;
}