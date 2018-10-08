package by.anjei.shop.db.dao;



import by.anjei.shop.db.daomodel.Item;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: xxxx
 * Date: 16.04.18
 * Time: 19:49
 * To change this template use File | Settings | File Templates.
 */
public interface ItemDao {
    Set<Item> getAllItems();
    Item getItemByName(String itemName);
    boolean saveItem(Item item);
    boolean deleteItem(Item item);
}
