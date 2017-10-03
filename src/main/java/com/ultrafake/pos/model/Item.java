package com.ultrafake.pos.model;


import java.math.BigDecimal;

public class Item {

    private int id;

    private String name;

    private BigDecimal price;

    public Item() {}

    public Item(int id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDisplayPrice() {
        return price == null ? "null" : price.toString();
    }
}
