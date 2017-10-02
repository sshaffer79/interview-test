package com.ultrafake.pos.api;

import com.ultrafake.pos.dao.OrdersDAO;
import com.ultrafake.pos.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrdersDAO ordersDAO;

    @RequestMapping(value = "/item/{itemName}", method = {RequestMethod.POST, RequestMethod.PUT})
    public Item addItemToOrder(@CookieValue("JSESSIONID") String orderId, @PathVariable String itemName) {
        return null;
    }

    @RequestMapping(value = "/item/{itemName}", method = RequestMethod.DELETE)
    public Item removeItemFromOrder(@CookieValue("JSESSIONID") String orderId, @PathVariable String itemName) {
        return null;
    }

    @RequestMapping(value = "/clear", method = RequestMethod.DELETE)
    public void clearOrder(@CookieValue("JSESSIONID") String orderId) {
        logger.info(orderId);
    }
}
