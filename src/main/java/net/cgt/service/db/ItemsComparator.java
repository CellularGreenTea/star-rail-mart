package net.cgt.service.db;

import net.cgt.service.item.Item;

import java.util.Comparator;

public class ItemsComparator implements Comparator<Item> {
    @Override
    public int compare(Item item1, Item item2) {
        char item1CodeCategory = item1.getCode().charAt(0);
        char item2CodeCategory = item2.getCode().charAt(0);

        String item1Code = item1.getCode().substring(1);
        String item2Code = item2.getCode().substring(1);

        if (item1CodeCategory != item2CodeCategory) {
            return item1CodeCategory - item2CodeCategory;
        }

        else {
            return Integer.parseInt(item1Code) - Integer.parseInt(item2Code);
        }
    }
}
