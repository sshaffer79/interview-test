package com.ultrafake.pos.api;

import com.ultrafake.pos.dto.ItemDTO;
import com.ultrafake.pos.dto.OrderDTO;
import com.ultrafake.pos.model.Order;
import com.ultrafake.pos.model.OrderLineItem;
import com.ultrafake.pos.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private DecimalFormat decimalFormat;

    public OrderController(OrderService orderService, DecimalFormat decimalFormat) {
        this.orderService = orderService;
        this.decimalFormat = decimalFormat;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public OrderDTO getOrder(@CookieValue("JSESSIONID") String orderId) {
        return createFrom(orderService.getOrder(orderId));
    }

    @RequestMapping(value = "/item/{itemId}", method = {RequestMethod.POST, RequestMethod.PUT})
    @ResponseBody
    public OrderDTO addItemToOrder(@CookieValue("JSESSIONID") String orderId, @PathVariable int itemId) {
        return createFrom(orderService.addOrUpdateLineItem(orderId, itemId));
    }

    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.DELETE)
    @ResponseBody
    public OrderDTO removeItemFromOrder(@CookieValue("JSESSIONID") String orderId, @PathVariable int itemId) {
        return createFrom(orderService.removeLineItem(orderId, itemId));
    }

    @RequestMapping(value = "/clear", method = RequestMethod.DELETE)
    @ResponseBody
    public OrderDTO clearOrder(@CookieValue("JSESSIONID") String orderId) {
        return createFrom(orderService.clear(orderId));
    }

    /**
     * Converts a Order object to a OrderDTO object. It will convert BigDecimal values into String for proper
     * decimal formatting
     * @param order The order
     * @return The OrderDTO representing that transformed contents of the order
     */
    protected OrderDTO createFrom(Order order) {
        List<ItemDTO> items = new ArrayList<>();
        for (OrderLineItem lineItem : order.getLineItems()) {
            items.add(new ItemDTO(lineItem.getItemId(),
                    lineItem.getName(),
                    lineItem.getNumberOfItems(),
                    decimalFormat.format(lineItem.getTotalPrice())));
        }

        OrderDTO orderDTO = new OrderDTO(order.getOrderId(),
                items,
                decimalFormat.format(order.getSubTotal()),
                decimalFormat.format(order.getTotal()),
                decimalFormat.format(order.getSalesTax()));

        return orderDTO;
    }
}
