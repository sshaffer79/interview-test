package com.ultrafake.pos.api;

import com.ultrafake.pos.model.Order;
import com.ultrafake.pos.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/item/{itemName}", method = {RequestMethod.POST, RequestMethod.PUT})
    public Order addItemToOrder(@CookieValue("JSESSIONID") String orderId, @PathVariable String itemName) {
        return orderService.addOrUpdateLineItem(orderId, itemName);
    }

    @RequestMapping(value = "/item/{itemName}", method = RequestMethod.DELETE)
    public Order removeItemFromOrder(@CookieValue("JSESSIONID") String orderId, @PathVariable String itemName) {
        return orderService.removeLineItem(orderId, itemName);
    }

    @RequestMapping(value = "/clear", method = RequestMethod.DELETE)
    public void clearOrder(@CookieValue("JSESSIONID") String orderId) {
        logger.info(orderId);
        orderService.clear(orderId);
    }
}
