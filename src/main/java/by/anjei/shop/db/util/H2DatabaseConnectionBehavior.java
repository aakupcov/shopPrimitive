package by.anjei.shop.db.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class H2DatabaseConnectionBehavior extends DBInfo implements DatabaseConnectionBehavior {
	
	

	public H2DatabaseConnectionBehavior() {
		super();
		// TODO Auto-generated constructor stub
	}

	public H2DatabaseConnectionBehavior(String dbUser, String dbPassword, String dbUrl, String dbDriver) {
		super(dbUser, dbPassword, dbUrl, dbDriver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Connection getConnection() {
		try
		{
			Class.forName(getDbDriver());
			Connection cn = DriverManager.getConnection(getConnectionURL());
			return cn;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getConnectionURL() {
		return String.format("%s?user=%s&password=%s",
				getDbUrl(), getDbUser(), getDbPassword());
	}

	@Override
	public String getConnectionDetails() {
		return "Database Connection to "
				+ getDbUrl();
	}

	@Override
	public String getTablesSchemaQuery() {
		return "select table_name from information_schema.tables "
				+ "where table_schema = " + getDbUrl();
	}

}
