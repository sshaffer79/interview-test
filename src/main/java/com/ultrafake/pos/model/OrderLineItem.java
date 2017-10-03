package com.ultrafake.pos.model;

import java.math.BigDecimal;

public class OrderLineItem {
    private int itemId;
    private String name;
    private int numberOfItems;
    private BigDecimal totalPrice;

    public OrderLineItem() {
    }

    public OrderLineItem(int itemId, String name, int numberOfItems, BigDecimal totalPrice) {
        this.itemId = itemId;
        this.name = name;
        this.numberOfItems = numberOfItems;
        this.totalPrice = totalPrice;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
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
        sb.append("itemId=").append(itemId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", numberOfItems=").append(numberOfItems);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append('}');
        return sb.toString();
    }
}
