package by.anjei.shop.db.util;

import java.io.Serializable;

public abstract class DBInfo {
	private String dbUser;
	private String dbPassword;
	private String dbUrl;
    private String dbDriver;

	public DBInfo() {
	}

	public DBInfo(String dbUser, String dbPassword, String dbUrl, String dbDriver) {
		this.dbUser = dbUser;
		this.dbPassword = dbPassword;
		this.dbUrl = dbUrl;
        this.dbDriver = dbDriver;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

    public String getDbDriver() {
        return dbDriver;
    }

    public void setDbDriver(String dbDriver) {
        this.dbDriver = dbDriver;
    }
}
