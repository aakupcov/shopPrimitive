package by.anjei.shop.db.dao;

import by.anjei.shop.db.daomodel.Order;
import by.anjei.shop.db.daomodel.User;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: xxxx
 * Date: 16.04.18
 * Time: 19:48
 * To change this template use File | Settings | File Templates.
 */
public interface OrderDao {
    Set<Order> getAllUserOrders(User user);
    Order getOrderById(int orderId);
    Order getLastUserOrder(User user);
    boolean saveOrder(Order order);
    boolean deleteOrder(Order order);
}
