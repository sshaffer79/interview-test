package com.ultrafake.pos.dao;

import com.ultrafake.pos.model.Order;
import com.ultrafake.pos.model.OrderLineItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class OrdersDAOTest {

    @Test
    public void testGetOnEmptyMap() {
        Map<String, Order> emptyOrderedList = new HashMap<>();
        String orderId = "test1";

        OrdersDAO dao = new OrdersDAO(emptyOrderedList);
        Order order = dao.getOrder(orderId);

        assertThat(emptyOrderedList.isEmpty(), is(false));
        assertThat(emptyOrderedList.containsKey(orderId), is(true));
        assertThat(emptyOrderedList.get(orderId).getOrderId(), is("test1"));
    }

    @Test
    public void testGetOnPopulatedMap() {
        Map<String, Order> populatedOrderedList = new HashMap<>();
        String orderId = "test1";
        OrderLineItem item = new OrderLineItem("Test Item", 1, new BigDecimal(10.00));
        Order order = new Order(orderId, Arrays.asList(item));
        populatedOrderedList.put(orderId, order);

        OrdersDAO dao = new OrdersDAO(populatedOrderedList);
        Order daoOrder = dao.getOrder(orderId);

        assertThat(daoOrder.getOrderId(), is("test1"));
        assertThat(daoOrder.getLineItems().size(), is(1));
        assertThat(daoOrder.getLineItems().get(0).getName(), is("Test Item"));
    }

    @Test
    public void testGetOnPopulatedMapWithDifferentId() {
        Map<String, Order> populatedOrderedList = new HashMap<>();
        String orderId = "test1";
        OrderLineItem item = new OrderLineItem("Test Item", 1, new BigDecimal(10.00));
        Order order = new Order(orderId, Arrays.asList(item));
        populatedOrderedList.put(orderId, order);

        OrdersDAO dao = new OrdersDAO(populatedOrderedList);
        Order daoOrder = dao.getOrder("test2");

        assertThat(daoOrder.getOrderId(), is("test2"));
        assertThat(daoOrder.getLineItems().isEmpty(), is(true));
    }

    @Test
    public void testClearWithEmptyMap() {
        Map<String, Order> emptyOrderList = new HashMap<>();
        String orderId = "test1";

        OrdersDAO dao = new OrdersDAO(emptyOrderList);
        dao.clear(orderId);

        assertThat(emptyOrderList.isEmpty(), is(true));
    }

    @Test
    public void testClearWithPopulatedMap() {
        Map<String, Order> populatedOrderedList = new HashMap<>();
        String orderId = "test1";
        OrderLineItem item = new OrderLineItem("Test Item", 1, new BigDecimal(10.00));
        Order order = new Order(orderId, Arrays.asList(item));
        populatedOrderedList.put(orderId, order);

        OrdersDAO dao = new OrdersDAO(populatedOrderedList);
        dao.clear(orderId);

        assertThat(populatedOrderedList.isEmpty(), is(true));
    }

    @Test
    public void testClearWithPopulatedMapAndWrongId() {
        Map<String, Order> populatedOrderedList = new HashMap<>();
        String orderId = "test1";
        OrderLineItem item = new OrderLineItem("Test Item", 1, new BigDecimal(10.00));
        Order order = new Order(orderId, Arrays.asList(item));
        populatedOrderedList.put(orderId, order);

        OrdersDAO dao = new OrdersDAO(populatedOrderedList);
        dao.clear("test2");

        assertThat(populatedOrderedList.isEmpty(), is(false));
        assertThat(populatedOrderedList.get(orderId).getLineItems().size(), is(1));
    }
}
