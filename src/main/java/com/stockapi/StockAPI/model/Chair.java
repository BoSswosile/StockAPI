package com.stockapi.StockAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;



public class Chair extends Product{
    private int length;
    private int width;
    private int height;
    private String color;

    public Chair(int id, int price, int quantity, int l, int w, int h, String c) {
        super(id, price, quantity);
        this.length = l;
        this.width = w;
        this.height = h;
        this.color = c;
    }

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
}
