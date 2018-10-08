package by.anjei.shop.servlets;

import by.anjei.shop.db.daoimplementation.OrderDaoImpl;
import by.anjei.shop.db.daoimplementation.OrderedItemDaoImpl;
import by.anjei.shop.db.daoimplementation.UserDaoImpl;
import by.anjei.shop.db.daomodel.Item;
import by.anjei.shop.db.daomodel.Order;
import by.anjei.shop.db.daomodel.User;
import by.anjei.shop.db.util.DBManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: xxxx
 * Date: 10.04.18
 * Time: 21:00
 * To change this template use File | Settings | File Templates.
 */

public class HomePageServlet extends HttpServlet {
    DBManager dbManager;
    UserDaoImpl userDao;
    OrderDaoImpl orderDao;
    OrderedItemDaoImpl orderedItemDao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dbManager  = (DBManager) this.getServletConfig().getServletContext().getAttribute("dbmanager");
        userDao = new UserDaoImpl(dbManager);
        orderDao = new OrderDaoImpl(dbManager);
        orderedItemDao = new OrderedItemDaoImpl(dbManager);

        Set<Item> items = (Set<Item>) this.getServletConfig().getServletContext().getAttribute("items");

        String login = (String) request.getParameter("login");
        String agreement = (String) request.getParameter("agreement");
        if (agreement == null || !isValidLogin(login)) {
            request.getRequestDispatcher("/jsp/invalidlogindata.jsp").forward(request, response);
        } else {

            User userFromDatabase = userDao.getUserByLogin(login);
            if (userFromDatabase == null) {
                User user = new User(login);
                userDao.saveUser(user);

                user = userDao.getUserByLogin(login);

                List<Item> orderedItems = new ArrayList<>();
                List<Item> currentItems = new ArrayList<>();

                request.getSession().invalidate();
                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("orderedItems", orderedItems);
                request.getSession().setAttribute("currentItems", currentItems);
            } else {
                Set<Order> userOrders = new LinkedHashSet<>(orderDao.getAllUserOrders(userFromDatabase));
                List<Item> orderedItems = new ArrayList<>();
                for (Order order : userOrders) {
                    List<Integer> orderedItemIds = new ArrayList<>(orderedItemDao.getItemsByOrderId(order));
                    for (Integer id : orderedItemIds) {
                        for (Item item : items) {
                            if (id.equals(item.getId())) {
                                orderedItems.add(item);
                                break;
                            }
                        }
                    }
                }
                List<Item> currentItems = new ArrayList<>();

                request.getSession().invalidate();
                request.getSession().setAttribute("user", userFromDatabase);
                request.getSession().setAttribute("orderedItems", orderedItems);
                request.getSession().setAttribute("currentItems", currentItems);
            }
            request.getRequestDispatcher("/jsp/order.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private boolean isValidLogin(String login) {
        if (login == null || login.equals("")) {
            return false;
        }

        return true;
    }
}
