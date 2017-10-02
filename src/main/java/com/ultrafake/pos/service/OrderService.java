package com.ultrafake.pos.service;

import com.ultrafake.pos.dao.Items;
import com.ultrafake.pos.dao.OrdersDAO;
import com.ultrafake.pos.model.Item;
import com.ultrafake.pos.model.Order;
import com.ultrafake.pos.model.OrderLineItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class OrderService {
    @Autowired
    private OrdersDAO ordersDAO;

    @Autowired
    private Items items;

    private static BigDecimal SALES_TAX = new BigDecimal(0.06);

    public OrderService(OrdersDAO ordersDAO) {
        this.ordersDAO = ordersDAO;
    }

    public Order getOrder(String orderId) {
        return ordersDAO.getOrder(orderId);
    }

    public Order addOrUpdateLineItem(String orderId, String itemName) {
        Order order = ordersDAO.getOrder(orderId);
        Item item = items.itemFor(itemName);

        OrderLineItem lineItem = order.getLineItems()
                .stream()
                .filter(i -> i.getName().equals(itemName))
                .findFirst()
                .orElse(null);
        if (lineItem == null) {
            lineItem = new OrderLineItem(itemName, 1, item.getPrice());
            order.getLineItems().add(lineItem);
        } else {
            lineItem.setNumberOfItems(lineItem.getNumberOfItems() + 1);
            lineItem.setTotalPrice(lineItem.getTotalPrice().add(item.getPrice()));
        }

        calculateTaxAndTotals(order);

        ordersDAO.update(order);

        return order;
    }

    public Order removeLineItem(String orderId, String itemName) {
        Order order = ordersDAO.getOrder(orderId);

        order.getLineItems().removeIf(i -> i.getName().equals(itemName));

        calculateTaxAndTotals(order);

        ordersDAO.update(order);

        return order;
    }

    public Order clear(String orderId) {
        ordersDAO.clear(orderId);
        return new Order(orderId);
    }

    public void calculateTaxAndTotals(Order order) {
        BigDecimal subTotal = new BigDecimal(0.00).setScale(2, RoundingMode.CEILING);
        for (OrderLineItem lineItem : order.getLineItems()) {
            subTotal = subTotal.add(lineItem.getTotalPrice());
        }
        order.setSubTotal(subTotal);

        BigDecimal salesTax = subTotal.multiply(SALES_TAX).setScale(2, RoundingMode.CEILING);
        order.setSalesTax(salesTax);

        BigDecimal total = new BigDecimal(0.00).setScale(2, RoundingMode.CEILING);
        total = total.add(order.getSubTotal());
        total = total.add(order.getSalesTax());
        order.setTotal(total);
    }
}
