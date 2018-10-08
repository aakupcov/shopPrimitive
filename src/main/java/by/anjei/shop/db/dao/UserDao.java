package by.anjei.shop.db.dao;

import by.anjei.shop.db.daomodel.User;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: xxxx
 * Date: 16.04.18
 * Time: 19:48
 * To change this template use File | Settings | File Templates.
 */
public interface UserDao {
    public Set<User> getAllUsers();
    User getUserByLogin(String login);
    boolean saveUser(User user);
    boolean deleteUser(User user);
}
