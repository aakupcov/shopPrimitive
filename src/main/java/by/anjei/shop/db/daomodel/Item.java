package by.anjei.shop.db.daomodel;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: xxxx
 * Date: 12.04.18
 * Time: 20:02
 * To change this template use File | Settings | File Templates.
 */
public class Item implements Serializable {
    private Integer id;
    private String itemName;
    private Double price;

    public Item() {

    }

    public Item(String itemName, Double price) {
        this.itemName = itemName;
        this.price = price;
    }

    public Item(Integer id, String itemName, Double price) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (!id.equals(item.id)) return false;
        if (!itemName.equals(item.itemName)) return false;
        if (!price.equals(item.price)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + itemName.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }
}
