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

    /**
     * Retrieves the order from the repository. If none is found, a new one is created and put in the repository.
     * @param orderId The id of the order to be retrieved
     * @return The order contents
     */
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

    /**
     * Remove the order from the repository
     * @param orderId The id of the order to be removed
     */
    public void clear(String orderId) {
        orderCache.remove(orderId);
    }

    /**
     * Update the repositories value with the latest order contents
     * @param order The order object to update
     * @return the Order that was passed in
     */
    public Order update(Order order) {
        return orderCache.put(order.getOrderId(), order);
    }
}
