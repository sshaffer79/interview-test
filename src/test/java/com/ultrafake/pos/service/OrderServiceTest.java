package com.ultrafake.pos.service;

import com.ultrafake.pos.dao.OrdersDAO;
import com.ultrafake.pos.model.Order;
import com.ultrafake.pos.model.OrderLineItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
    private OrderService service;

    @Mock
    private OrdersDAO ordersDAO;

    @Test
    public void testCalculateTaxAndTotalsWithEmptyOrder() {
        service = new OrderService(new OrdersDAO(null));
        Order order = new Order();

        service.calculateTaxAndTotals(order);

        assertThat(order.getSalesTax().doubleValue(), is(0.00));
        assertThat(order.getSubTotal().doubleValue(), is(0.00));
        assertThat(order.getTotal().doubleValue(), is(0.00));
    }

    @Test
    public void testCalculateTaxAndTotalsWithOneItem() {
        service = new OrderService(new OrdersDAO(null));
        Order order = new Order();
        order.setLineItems(Arrays.asList(new OrderLineItem(1, "test", 1, new BigDecimal(1.00))));

        service.calculateTaxAndTotals(order);

        assertThat(order.getSalesTax().doubleValue(), is(0.06));
        assertThat(order.getSubTotal().doubleValue(), is(1.00));
        assertThat(order.getTotal().doubleValue(), is(1.06));
    }

    @Test
    public void testCalculateTaxAndTotalsWithMultipleItems() {
        service = new OrderService(new OrdersDAO(null));
        Order order = new Order();
        order.setLineItems(Arrays.asList(
                new OrderLineItem(1, "test", 3, new BigDecimal(3.00)),
                new OrderLineItem(2, "test2", 1, new BigDecimal(8.00))));

        service.calculateTaxAndTotals(order);

        assertThat(order.getSalesTax().doubleValue(), is(0.66));
        assertThat(order.getSubTotal().doubleValue(), is(11.00));
        assertThat(order.getTotal().doubleValue(), is(11.66));
    }
}
