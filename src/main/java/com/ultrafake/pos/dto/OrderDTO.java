package com.ultrafake.pos.dto;

import java.util.List;

public class OrderDTO {
    private String orderId;
    private List<ItemDTO> items;
    private String subTotal;
    private String total;
    private String salesTax;

    public OrderDTO(String orderId, List<ItemDTO> items, String subTotal, String total, String salesTax) {
        this.orderId = orderId;
        this.items = items;
        this.subTotal = subTotal;
        this.total = total;
        this.salesTax = salesTax;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(String tax) {
        this.salesTax = tax;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("OrderDTO{");
        sb.append("orderId='").append(orderId).append('\'');
        sb.append(", items=").append(items);
        sb.append(", subTotal='").append(subTotal).append('\'');
        sb.append(", total='").append(total).append('\'');
        sb.append(", salesTax='").append(salesTax).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
