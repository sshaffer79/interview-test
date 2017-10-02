package com.ultrafake.pos.service;

import com.ultrafake.pos.dao.Items;
import com.ultrafake.pos.dao.OrdersDAO;
import com.ultrafake.pos.model.Item;
import com.ultrafake.pos.model.Order;
import com.ultrafake.pos.model.OrderLineItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrdersDAO ordersDAO;

    @Autowired
    private Items items;

    public OrderService(OrdersDAO ordersDAO) {
        this.ordersDAO = ordersDAO;
    }

    public Order addOrUpdateLineItem(String orderId, String itemName) {
        Order order = ordersDAO.getOrder(orderId);
        Item item = items.itemFor(itemName);

        OrderLineItem lineItem = order.getLineItems()
                .stream()
                .filter(i -> i.getName() == itemName)
                .findFirst()
                .get();
        if (lineItem == null) {
            lineItem = new OrderLineItem(itemName, 1, item.getPrice());
            order.getLineItems().add(lineItem);
        } else {
            lineItem.setNumberOfItems(lineItem.getNumberOfItems() + 1);
            lineItem.getTotalPrice().add(item.getPrice());
        }
        ordersDAO.update(order);

        return order;
    }

    public Order removeLineItem(String orderId, String itemName) {
        Order order = ordersDAO.getOrder(orderId);

        order.getLineItems().removeIf(i -> i.getName() == itemName);
        ordersDAO.update(order);

        return order;
    }

    public void clear(String orderId) {
        ordersDAO.clear(orderId);
    }
}
