package com.ultrafake.pos.api;

import com.ultrafake.pos.dao.Items;
import com.ultrafake.pos.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemsController {
    private Logger logger = LoggerFactory.getLogger(ItemsController.class);

    @Autowired
    Items items;

    @RequestMapping("/api/items/getAllItems")
    public List<Item> getAllItems() {
        return items.getAllItems();
    }
}
