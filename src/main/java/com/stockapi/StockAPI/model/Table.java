package com.stockapi.StockAPI.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

// put lombok annotations here
@SuperBuilder
@Getter
public class Table extends Product{
    final int length;
    final int width;
    final int height;
    final String color;
}
