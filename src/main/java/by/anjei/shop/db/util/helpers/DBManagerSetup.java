package by.anjei.shop.db.util.helpers;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.anjei.shop.db.daoimplementation.ItemDaoImpl;
import by.anjei.shop.db.daomodel.Item;
import by.anjei.shop.db.util.DBManager;
import by.anjei.shop.db.util.DatabaseConnectionBehavior;
import by.anjei.shop.db.util.H2DatabaseConnectionBehavior;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;

public class DBManagerSetup implements ServletContextListener {
	
	private DBManager dbm = null;

	@Override
	public void contextInitialized(ServletContextEvent sce) {

        ServletContext context = sce.getServletContext();
        Properties properties = new Properties();
        InputStream input = null;

        try{
            input = new FileInputStream("src/main/resources/db.properties");
            properties.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String dbUser = properties.getProperty("dbUser");
		String dbPassword = properties.getProperty("dbPassword");
		String dbUrl = properties.getProperty("dbUrl");
        String dbDriver = properties.getProperty("dbDriver");
		
		DatabaseConnectionBehavior conB = new H2DatabaseConnectionBehavior(dbUser, dbPassword, dbUrl, dbDriver);
		DBManager dbm = new DBManager(conB);
        if (!dbm.isConnected()) {
            if (!dbm.openConnection())
            {
                try {
                    throw new IOException("Could not connect to database and open connection");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            DBSQLQueries.createDefaultTables(dbm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ItemDaoImpl itemDao = new ItemDaoImpl(dbm);
        Set<Item> items = new LinkedHashSet<>(itemDao.getAllItems());

        context.setAttribute("dbmanager", dbm);
        context.setAttribute("items", items);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// cleanup the connection when the context is destroyed
		if (dbm != null) {
			if (dbm.isConnected()) {
				dbm.closeConnection(false);
			}
		}
		dbm = null;
        System.out.println("!!!!!!!!!!!!!!Context is destroyed.!!!!!!!!!!!!!!!!!");
	}
}
