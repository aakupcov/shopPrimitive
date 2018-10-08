package by.anjei.shop.db.daomodel;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: xxxx
 * Date: 17.04.18
 * Time: 11:45
 * To change this template use File | Settings | File Templates.
 */
public class Order implements Serializable {
    private Integer orderId;
    private Integer userId;
    private Double totalPrice;

    public Order() {
    }

    public Order(Integer userId, Double totalPrice) {
        this.userId = userId;
        this.totalPrice = totalPrice;
    }

    public Order(Integer orderId, Integer userId, Double totalPrice) {
        this.orderId = orderId;
        this.userId = userId;
        this.totalPrice = totalPrice;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (!orderId.equals(order.orderId)) return false;
        if (!totalPrice.equals(order.totalPrice)) return false;
        if (!userId.equals(order.userId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId.hashCode();
        result = 31 * result + userId.hashCode();
        result = 31 * result + totalPrice.hashCode();
        return result;
    }
}
