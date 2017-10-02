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

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public Order getOrder(@CookieValue("JSESSIONID") String orderId) {
        return orderService.getOrder(orderId);
    }

    @RequestMapping(value = "/item/{itemName}", method = {RequestMethod.POST, RequestMethod.PUT})
    @ResponseBody
    public Order addItemToOrder(@CookieValue("JSESSIONID") String orderId, @PathVariable String itemName) {
        return orderService.addOrUpdateLineItem(orderId, itemName);
    }

    @RequestMapping(value = "/item/{itemName}", method = RequestMethod.DELETE)
    @ResponseBody
    public Order removeItemFromOrder(@CookieValue("JSESSIONID") String orderId, @PathVariable String itemName) {
        return orderService.removeLineItem(orderId, itemName);
    }

    @RequestMapping(value = "/clear", method = RequestMethod.DELETE)
    @ResponseBody
    public Order clearOrder(@CookieValue("JSESSIONID") String orderId) {
        logger.info(orderId);
        return orderService.clear(orderId);
    }
}
