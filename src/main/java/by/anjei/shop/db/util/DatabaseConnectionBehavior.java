package by.anjei.shop.db.util;

import java.sql.Connection;

public interface DatabaseConnectionBehavior {
	Connection getConnection();
	String getConnectionURL();
	String getConnectionDetails();
	String getTablesSchemaQuery();

}
