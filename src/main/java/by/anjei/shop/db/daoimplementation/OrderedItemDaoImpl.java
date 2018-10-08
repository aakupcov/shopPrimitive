package by.anjei.shop.db.daoimplementation;

import by.anjei.shop.db.dao.OrderedItemDao;
import by.anjei.shop.db.daomodel.Item;
import by.anjei.shop.db.daomodel.Order;
import by.anjei.shop.db.daomodel.OrderedItem;
import by.anjei.shop.db.util.DBManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: xxxx
 * Date: 21.04.18
 * Time: 13:54
 * To change this template use File | Settings | File Templates.
 */
public class OrderedItemDaoImpl implements OrderedItemDao {
    private DBManager dbm;

    public OrderedItemDaoImpl(DBManager dbm) {
        this.dbm = dbm;
    }


    @Override
    public List<Integer>  getItemsByOrderId(Order order) {
        List<Integer> items = new ArrayList<>();
        String query = "select item_id from ordered_items where order_id = ?";
        ResultSet allItems = null;
        try {
            PreparedStatement ps = dbm.executeQuery(query);
            ps.setInt(1, order.getOrderId());
            allItems = ps.executeQuery();
            while (allItems.next()) {
                Integer itemId = allItems.getInt("item_id");
                items.add(itemId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public boolean saveItem(Integer orderId, Integer itemId) {
        String query = "insert into ordered_items (order_id, item_id) values (?, ?)";
        try {
            PreparedStatement ps = dbm.executeQuery(query);
            ps.setInt(1, orderId);
            ps.setInt(2, itemId);
            int flag = ps.executeUpdate();
            if (flag == -1) return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void saveItems(List<Item> currentItems, Order order) {
        for (Item item : currentItems) {
            saveItem(order.getOrderId(), item.getId());
        }
    }


    @Override
    public boolean deleteItem(OrderedItem orderedItem) {
        String query = "delete from ordered_items where ordered_item_id = ?";
        try {
            PreparedStatement ps = dbm.executeQuery(query);
            ps.setInt(1, orderedItem.getId());
            int flag = ps.executeUpdate();
            if (flag == -1) return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
