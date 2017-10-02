package com.ultrafake.pos.dto;

public class ItemDTO {
    private String name;
    private int quantity;
    private String cost;

    public ItemDTO(String name, int quantity, String cost) {
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ItemDTO{");
        sb.append("name='").append(name).append('\'');
        sb.append(", quantity=").append(quantity);
        sb.append(", cost='").append(cost).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
