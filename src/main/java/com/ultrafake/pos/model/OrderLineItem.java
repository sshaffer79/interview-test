package com.ultrafake.pos.model;

import java.math.BigDecimal;

public class OrderLineItem {
    private String name;
    private int numberOfItems;
    private BigDecimal totalPrice;

    public OrderLineItem() {
    }

    public OrderLineItem(String name, int numberOfItems, BigDecimal totalPrice) {
        this.name = name;
        this.numberOfItems = numberOfItems;
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("OrderLineItem{");
        sb.append("name='").append(name).append('\'');
        sb.append(", numberOfItems=").append(numberOfItems);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append('}');
        return sb.toString();
    }
}
