package by.anjei.shop.db.daoimplementation;

import by.anjei.shop.db.dao.ItemDao;
import by.anjei.shop.db.daomodel.Item;
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
public class ItemDaoImpl implements ItemDao {
    private DBManager dbm;

    public ItemDaoImpl(DBManager dbm) {
        this.dbm = dbm;
    }

    @Override
    public Set<Item> getAllItems() {
        Set<Item> items = new LinkedHashSet<>();
        String query = "select * from items order by item_id";
        ResultSet allItems = null;
        try {
            allItems = dbm.executeResultSet(query);
            while (allItems.next()) {
                Integer itemId = allItems.getInt("item_id");
                String itemName = allItems.getString("item_name");
                Double itemPrice = allItems.getDouble("item_price");
                Item item = new Item(itemId, itemName, itemPrice);
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public Item getItemByName(String name) {
        String query = "select * from items where item_name = ?";
        ResultSet allItems = null;
        try {
            PreparedStatement ps = dbm.executeQuery(query);
            ps.setString(1, name);
            allItems = ps.executeQuery();
            if (allItems.next()) {
                Integer itemId = allItems.getInt("item_id");
                String itemName = allItems.getString("item_name");
                Double itemPrice = allItems.getDouble("item_price");
                Item item = new Item(itemId, itemName, itemPrice);
                return item;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveItem(Item item) {
        String query = "insert into items (item_name, item_price) values (?, ?)";
        try {
            PreparedStatement ps = dbm.executeQuery(query);
            ps.setString(1, item.getItemName());
            ps.setDouble(2, item.getPrice());
            int flag = ps.executeUpdate();
            if (flag == -1) return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deleteItem(Item item) {
        String query = "delete from items where item_name = ?";
        try {
            PreparedStatement ps = dbm.executeQuery(query);
            ps.setString(1, item.getItemName());
            int flag = ps.executeUpdate();
            if (flag == -1) return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
