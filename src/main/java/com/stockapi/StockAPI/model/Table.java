package com.stockapi.StockAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;


public class Table extends Product{
    private int length;
    private int width;
    private int height;
    private String color;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Table(int id, int price, int quantity, int l, int w, int h, String c) {
        super(id, price, quantity);
        this.color = c;
        this.length = l;
        this.width = w;
        this.height = h;
    }
}
