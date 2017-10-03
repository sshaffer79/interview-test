package com.ultrafake.pos.dao;

import com.ultrafake.pos.model.Item;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

@Component
public class Items {
    private final static List<Item> itemList;

    static {
        LinkedList<Item> items = new LinkedList<>();
        String fields[] = {};

        try(InputStream itemsStream =
                Items.class.getClassLoader().getResourceAsStream("items.csv")) {

            Scanner scanner = new Scanner(itemsStream);

            int lineNum = 0;

            while(scanner.hasNextLine()) {

                lineNum++;

                fields = scanner.nextLine().split(",");

                if(fields.length < 2) {
                    continue;
                }

                // Skip the first line in an intentionally brittle way so format changes
                // will cause this to get re-examined
                if(lineNum == 1 && "Item Id".equals(fields[0].trim())
                        && "Item Name".equals(fields[1].trim()) && "Price".equals(fields[2].trim())) {
                    continue;
                }

                int id = Integer.valueOf(fields[0].trim());

                String name = fields[1].trim();

                BigDecimal price = new BigDecimal(fields[2].trim());

                items.add(new Item(id, name, price));
            }
        } catch(Exception e) {
            throw new Error(e);
        }

        items.sort(Comparator.comparing(Item::getName));

        itemList = Collections.unmodifiableList(items);
    }

    public List<Item> getAllItems() {
        return itemList;
    }

    public Item itemFor(String name) {
        for(Item item : itemList) {
            if(item.getName().equals(name)) {
                return item;
            }
        }

        throw new Error("Unknown item: " + name);
    }

    public Item itemFor(int id) {
        for(Item item : itemList) {
            if(item.getId() == id) {
                return item;
            }
        }

        throw new Error("Unknown item id: " + id);
    }
}
