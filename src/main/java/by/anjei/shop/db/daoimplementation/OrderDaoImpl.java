package by.anjei.shop.db.daoimplementation;

import by.anjei.shop.db.dao.OrderDao;
import by.anjei.shop.db.daomodel.Order;
import by.anjei.shop.db.daomodel.User;
import by.anjei.shop.db.util.DBManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: xxxx
 * Date: 16.04.18
 * Time: 19:51
 * To change this template use File | Settings | File Templates.
 */
public class OrderDaoImpl implements OrderDao {
    private DBManager dbm;

    public OrderDaoImpl(DBManager dbm) {
        this.dbm = dbm;
    }


    @Override
    public Set<Order> getAllUserOrders(User user) {
        Set<Order> orders = new LinkedHashSet<>();
        String query = "select * from orders where user_id = ? order by order_id";
        ResultSet allUserOrders = null;
        try {
            PreparedStatement ps = dbm.executeQuery(query);
            ps.setInt(1, user.getId());
            allUserOrders = ps.executeQuery();
            while (allUserOrders.next()) {
                Integer orderId = allUserOrders.getInt("order_id");
                Integer userId = allUserOrders.getInt("user_id");
                Double totalPrice = allUserOrders.getDouble("total_price");
                Order order = new Order(orderId, userId, totalPrice);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Order getOrderById(int orderId) {
        String query = "select * from orders where order_id = ?";
        ResultSet orderById = null;
        try {
            PreparedStatement ps = dbm.executeQuery(query);
            ps.setInt(1, orderId);
            orderById = ps.executeQuery();
            if (orderById.next()) {
                Integer id = orderById.getInt("order_id");
                Integer userId = orderById.getInt("user_id");
                Double totalPrice = orderById.getDouble("total_price");
                Order order = new Order(id, userId, totalPrice);
                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Order getLastUserOrder(User user) {
        String query = "select * from orders where order_id = (select max(order_id) from orders) and user_id = ?";
        ResultSet orderById = null;
        try {
            PreparedStatement ps = dbm.executeQuery(query);
            ps.setInt(1, user.getId());
            orderById = ps.executeQuery();
            if (orderById.next()) {
                Integer id = orderById.getInt("order_id");
                Integer userId = orderById.getInt("user_id");
                Double totalPrice = orderById.getDouble("total_price");
                Order order = new Order(id, userId, totalPrice);
                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveOrder(Order order) {
        String query = "insert into orders (user_id, total_price) values (?, ?)";
        try {
            PreparedStatement ps = dbm.executeQuery(query);
            ps.setInt(1, order.getUserId());
            ps.setDouble(2, order.getTotalPrice());
            int flag = ps.executeUpdate();
            if (flag == -1) return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deleteOrder(Order order) {
        String query = "delete from orders where order_id = ?";
        try {
            PreparedStatement ps = dbm.executeQuery(query);
            ps.setInt(1, order.getOrderId());
            int flag = ps.executeUpdate();
            if (flag == -1) return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
