package com.kvest.mvvm_research.common.datamodel;

/**
 * Created by kvest on 17.06.16.
 */
public class Item {
    public long id;
    public String name;

    public Item(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return id == item.id;

    }

    @Override
    public int hashCode() {
        return (int) id;
    }
}
