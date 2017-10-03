package com.ultrafake.pos.api;

import com.ultrafake.pos.dto.OrderDTO;
import com.ultrafake.pos.model.Order;
import com.ultrafake.pos.model.OrderLineItem;
import com.ultrafake.pos.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class OrderControllerTest {

    OrderController controller;

    @Mock
    OrderService service;

    DecimalFormat decimalFormat;

    @Before
    public void setup() {
        this.decimalFormat = new DecimalFormat("#,###,##0.00");
    }

    @Test
    public void testOrderToOrderDTOWithNoLineItems() {
        Order order = new Order("test");

        controller = new OrderController(service, decimalFormat);

        OrderDTO dto = controller.createFrom(order);

        assertThat(dto.getOrderId(), is("test"));
        assertThat(dto.getItems().size(), is(0));
        assertThat(dto.getSubTotal(), is("0.00"));
        assertThat(dto.getTotal(), is("0.00"));
    }

    @Test
    public void testOrderToOrderDTOWithLineItems() {
        List<OrderLineItem> items = Arrays.asList(
                new OrderLineItem(1, "Test1", 2, new BigDecimal(2.00)),
                new OrderLineItem(2, "Test2", 1, new BigDecimal(8.00))
        );
        Order order = new Order("test", items);
        order.setSalesTax(new BigDecimal(0.60));
        order.setSubTotal(new BigDecimal(10.00));
        order.setTotal(new BigDecimal(10.60));

        controller = new OrderController(service, decimalFormat);

        OrderDTO dto = controller.createFrom(order);

        assertThat(dto.getOrderId(), is("test"));
        assertThat(dto.getItems().size(), is(2));
        assertThat(dto.getItems().get(0).getCost(), is("2.00"));
        assertThat(dto.getItems().get(1).getCost(), is("8.00"));
        assertThat(dto.getSubTotal(), is("10.00"));
        assertThat(dto.getSalesTax(), is("0.60"));
        assertThat(dto.getTotal(), is("10.60"));
    }
}
