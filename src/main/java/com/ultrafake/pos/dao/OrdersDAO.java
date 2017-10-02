package com.ultrafake.pos.dao;

import com.ultrafake.pos.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class OrdersDAO {

    @Autowired
    private Map<String, Order> orderCache;

    public OrdersDAO(Map<String, Order> orderCache) {
        this.orderCache = orderCache;
    }

    public Order getOrder(String orderId) {
        Order order;
        if (orderCache.containsKey(orderId)) {
            order = orderCache.get(orderId);
        } else {
            order = new Order(orderId);
            orderCache.put(orderId, order);
        }
        return order;
    }

    public void clear(String orderId) {
        orderCache.remove(orderId);
    }

    public Order update(Order order) {
        if (orderCache.containsKey(order.getOrderId())) {
            order = orderCache.put(order.getOrderId(), order);
        } else {
            //FIXME throw exception
        }
        return order;
    }
}
