package com.ultrafake.pos.service;

import com.ultrafake.pos.dao.Items;
import com.ultrafake.pos.dao.OrdersDAO;
import com.ultrafake.pos.model.Item;
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
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
    private OrderService service;

    @Mock
    private OrdersDAO ordersDAO;

    @Mock
    private Items items;

    @Test
    public void testCalculateTaxAndTotalsWithEmptyOrder() {
        service = new OrderService(new OrdersDAO(null), new Items());
        Order order = new Order();

        service.calculateTaxAndTotals(order);

        assertThat(order.getSalesTax().doubleValue(), is(0.00));
        assertThat(order.getSubTotal().doubleValue(), is(0.00));
        assertThat(order.getTotal().doubleValue(), is(0.00));
    }

    @Test
    public void testCalculateTaxAndTotalsWithOneItem() {
        service = new OrderService(new OrdersDAO(null), new Items());
        Order order = new Order();
        order.setLineItems(Arrays.asList(new OrderLineItem(1, "test", 1, new BigDecimal(1.00))));

        service.calculateTaxAndTotals(order);

        assertThat(order.getSalesTax().doubleValue(), is(0.06));
        assertThat(order.getSubTotal().doubleValue(), is(1.00));
        assertThat(order.getTotal().doubleValue(), is(1.06));
    }

    @Test
    public void testCalculateTaxAndTotalsWithMultipleItems() {
        service = new OrderService(new OrdersDAO(null), new Items());
        Order order = new Order();
        order.setLineItems(Arrays.asList(
                new OrderLineItem(1, "test", 3, new BigDecimal(3.00)),
                new OrderLineItem(2, "test2", 1, new BigDecimal(8.00))));

        service.calculateTaxAndTotals(order);

        assertThat(order.getSalesTax().doubleValue(), is(0.66));
        assertThat(order.getSubTotal().doubleValue(), is(11.00));
        assertThat(order.getTotal().doubleValue(), is(11.66));
    }

    @Test
    public void testAddOrUpdateLineItemOnEmptyOrder() {
        Order order = new Order("test");
        Item item = new Item(1, "Test", new BigDecimal(1.00));

        when(ordersDAO.getOrder(any(String.class))).thenReturn(order);
        when(items.itemFor(anyInt())).thenReturn(item);

        service = new OrderService(ordersDAO, items);

        Order serviceOrder = service.addOrUpdateLineItem("test", 1);

        assertThat(serviceOrder.getLineItems().size(), is(1));
        assertThat(serviceOrder.getSubTotal().doubleValue(), is(1.00));
        assertThat(serviceOrder.getLineItems().get(0).getItemId(), is(1));
        assertThat(serviceOrder.getLineItems().get(0).getNumberOfItems(), is(1));
    }


    @Test
    public void testAddOrUpdateLineItemOnPopulatedOrder() {
        Order order = new Order("test");
        Item item = new Item(1, "Test", new BigDecimal(1.00));
        order.getLineItems().add(new OrderLineItem(item.getId(), item.getName(), 1, new BigDecimal(1.00)));

        when(ordersDAO.getOrder(any(String.class))).thenReturn(order);
        when(items.itemFor(anyInt())).thenReturn(item);

        service = new OrderService(ordersDAO, items);

        Order serviceOrder = service.addOrUpdateLineItem("test", 1);

        assertThat(serviceOrder.getLineItems().size(), is(1));
        assertThat(serviceOrder.getSubTotal().doubleValue(), is(2.00));
        assertThat(serviceOrder.getLineItems().get(0).getItemId(), is(1));
        assertThat(serviceOrder.getLineItems().get(0).getNumberOfItems(), is(2));
    }

    @Test
    public void testRemoveLineItemOnEmptyOrder() {
        Order order = new Order("test");

        when(ordersDAO.getOrder(any(String.class))).thenReturn(order);

        service = new OrderService(ordersDAO, items);

        Order serviceOrder = service.removeLineItem("test", 1);

        assertThat(serviceOrder.getLineItems().size(), is(0));
    }

    @Test
    public void testRemoveLineItemPopulatedOrder() {
        Order order = new Order("test");
        order.getLineItems().add(new OrderLineItem(1, "test", 2, new BigDecimal(2.00)));
        order.getLineItems().add(new OrderLineItem(2, "test2", 1, new BigDecimal(1.00)));

        when(ordersDAO.getOrder(any(String.class))).thenReturn(order);

        service = new OrderService(ordersDAO, items);

        Order serviceOrder = service.removeLineItem("test", 1);

        assertThat(serviceOrder.getLineItems().size(), is(1));
        assertThat(serviceOrder.getSubTotal().doubleValue(), is(1.00));
        assertThat(serviceOrder.getLineItems().get(0).getItemId(), is(2));
        assertThat(serviceOrder.getLineItems().get(0).getNumberOfItems(), is(1));
    }
}
