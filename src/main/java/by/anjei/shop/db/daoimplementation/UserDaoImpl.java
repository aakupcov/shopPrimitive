package by.anjei.shop.db.daoimplementation;

import by.anjei.shop.db.dao.UserDao;
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
 * Time: 19:50
 * To change this template use File | Settings | File Templates.
 */
public class UserDaoImpl implements UserDao {
    private DBManager dbm;

    public UserDaoImpl(DBManager dbm) {
        this.dbm = dbm;
    }

    @Override
    public Set<User> getAllUsers() {
        Set<User> users = new LinkedHashSet<>();
        String query = "select * from users order by user_id";
        ResultSet allUsers = null;
        try {
            allUsers = dbm.executeResultSet(query);
            while (allUsers.next()) {
                Integer userId = allUsers.getInt("user_id");
                String userLogin = allUsers.getString("user_login");
                String userPassword = allUsers.getString("user_password");
                User user = new User(userId, userLogin, userPassword);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User getUserByLogin(String login) {
        String query = "select * from users where user_login = ?";
        ResultSet allUsers = null;
        try {
            PreparedStatement ps = dbm.executeQuery(query);
            ps.setString(1, login);
            allUsers = ps.executeQuery();
            if (allUsers.next()) {
                Integer userId = allUsers.getInt("user_id");
                String userLogin = allUsers.getString("user_login");
                String userPassword = allUsers.getString("user_password");
                User user = new User(userId, userLogin, userPassword);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveUser(User user) {
        String query = "insert into users (user_login, user_password) values (?, ?)";
        try {
            PreparedStatement ps = dbm.executeQuery(query);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            int flag = ps.executeUpdate();
            if (flag == -1) return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deleteUser(User user) {
        String query = "delete from users where user_login = ?";
        try {
            PreparedStatement ps = dbm.executeQuery(query);
            ps.setString(1, user.getLogin());
            int flag = ps.executeUpdate();
            if (flag == -1) return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
