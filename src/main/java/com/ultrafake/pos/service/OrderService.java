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

    public OrderService(OrdersDAO ordersDAO, Items items) {
        this.ordersDAO = ordersDAO;
        this.items = items;
    }

    /**
     * Get the Order by id
     * @param orderId The order id
     * @return The order
     */
    public Order getOrder(String orderId) {
        return ordersDAO.getOrder(orderId);
    }

    /**
     * Adds or updates a line item for a given order. If a line item is already found for this order, that item has
     * its number of items incremented and its total price updated.
     * @param orderId The order id
     * @param itemId The id of the item
     * @return The update order
     */
    public Order addOrUpdateLineItem(String orderId, int itemId) {
        Order order = ordersDAO.getOrder(orderId);
        Item item = items.itemFor(itemId);

        OrderLineItem lineItem = order.getLineItems()
                .stream()
                .filter(i -> i.getItemId() == itemId)
                .findFirst()
                .orElse(null);
        if (lineItem == null) {
            lineItem = new OrderLineItem(itemId, item.getName(), 1, item.getPrice());
            order.getLineItems().add(lineItem);
        } else {
            lineItem.setNumberOfItems(lineItem.getNumberOfItems() + 1);
            lineItem.setTotalPrice(lineItem.getTotalPrice().add(item.getPrice()));
        }

        calculateTaxAndTotals(order);

        ordersDAO.update(order);

        return order;
    }

    /**
     * Remove a line item value from an order if found
     * @param orderId The id of the order
     * @param itemId The item id of the line item to be removed from the order
     * @return The updated order
     */
    public Order removeLineItem(String orderId, int itemId) {
        Order order = ordersDAO.getOrder(orderId);

        order.getLineItems().removeIf(i -> i.getItemId() == itemId);

        calculateTaxAndTotals(order);

        ordersDAO.update(order);

        return order;
    }

    /**
     * Clears out an order
     * @param orderId The order id to be cleared
     * @return A new empty order
     */
    public Order clear(String orderId) {
        ordersDAO.clear(orderId);
        return new Order(orderId);
    }

    /**
     * Takes a provided order and calculates the sub total, tax, and total values for the order based off its
     * order line items.
     * @param order The order that needs totals calculated
     */
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
