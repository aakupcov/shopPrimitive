package by.anjei.shop.db.daomodel;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: xxxx
 * Date: 17.04.18
 * Time: 11:48
 * To change this template use File | Settings | File Templates.
 */
public class OrderedItem implements Serializable {
    private Integer id;
    private Integer orderId;
    private Integer itemId;

    public OrderedItem() {
    }

    public OrderedItem(Integer id, Integer orderId, Integer itemId) {
        this.id = id;
        this.orderId = orderId;
        this.itemId = itemId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderedItem that = (OrderedItem) o;

        if (!id.equals(that.id)) return false;
        if (!itemId.equals(that.itemId)) return false;
        if (!orderId.equals(that.orderId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + orderId.hashCode();
        result = 31 * result + itemId.hashCode();
        return result;
    }
}
