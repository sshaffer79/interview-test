package com.ultrafake.pos.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private List<OrderLineItem> lineItems = new ArrayList<>();
    private BigDecimal subTotal = new BigDecimal(0.00);
    private BigDecimal total = new BigDecimal(0.00);
    private BigDecimal salesTax = new BigDecimal(0.00);

    public Order() {
    }

    public Order(String orderId) {
        this.orderId = orderId;
    }

    public Order(String orderId, List<OrderLineItem> lineItems) {
        this.orderId = orderId;
        this.lineItems = lineItems;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<OrderLineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<OrderLineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(BigDecimal salesTax) {
        this.salesTax = salesTax;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Order{");
        sb.append("orderId='").append(orderId).append('\'');
        sb.append(", lineItems=").append(lineItems);
        sb.append(", subTotal=").append(subTotal);
        sb.append(", total=").append(total);
        sb.append(", salesTax=").append(salesTax);
        sb.append('}');
        return sb.toString();
    }
}
