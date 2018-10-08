package by.anjei.shop.db.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager implements Serializable {
	private static final long serialVersionUID = 1L;

    Connection cn = null;
	DatabaseConnectionBehavior dcb = null;
	
	public DBManager() {
	}
	
	public DBManager(DatabaseConnectionBehavior conBehavior) {
		dcb = conBehavior;
	}
	
	public boolean setConnectionBehavior(DatabaseConnectionBehavior value) {
		if (value == null) {
			throw new IllegalArgumentException("Please use a valid connection behavior");
		}
		dcb = value;
		return true;
	}
	
	public boolean openConnection() {
		try {
			if (dcb == null) {
				throw new IllegalArgumentException("Define "
						+ "a connection behavior");
			}
			if (cn != null) closeConnection(false);
			cn = dcb.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if (cn == null) return false;
		return true;
	}

	public boolean closeConnection(boolean keepAlive) {
		try {
			if (cn != null) {
				if (!cn.isClosed()) {
					cn.close();
				}
			}
			if (!keepAlive) {
				cn = null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean isConnected() {
		return cn != null; 
	}
	
	public PreparedStatement executeQuery(String query) throws SQLException {
		return cn.prepareStatement(query);
	}

	public ResultSet executeResultSet(String query) throws SQLException {
		PreparedStatement st = cn.prepareStatement(query);
		ResultSet rs = st.executeQuery();
		return rs;
	}
	
	public Connection getConnection() {
		return cn;
	}

	public String getConnectionURL() {
		return dcb.getConnectionURL();
	}
	
	public String getTablesSchemaQuery() {
		return dcb.getTablesSchemaQuery();
	}
}
