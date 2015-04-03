package edu.pitt.sis.infsci2711.metastoreserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtilFather {
	public String DEFAULT_HOST = "";//
	
	public int DEFAULT_PORT = 0;
	
	public String DEFAULT_USER = ""; //CHANGE TO YOUR MYSQL USER NAME
	
	public String DEFAULT_PASSWOD = ""; // CHANGE TO YOUR MYSQL PASSWORD
	
	public String DEFAULT_DATABASE = "";
	
	
	
	public JdbcUtilFather() {
	}

	public JdbcUtilFather(String dEFAULT_HOST, int dEFAULT_PORT,
			String dEFAULT_USER, String dEFAULT_PASSWOD, String dEFAULT_DATABASE) {
		super();
		DEFAULT_HOST = dEFAULT_HOST;
		DEFAULT_PORT = dEFAULT_PORT;
		DEFAULT_USER = dEFAULT_USER;
		DEFAULT_PASSWOD = dEFAULT_PASSWOD;
		DEFAULT_DATABASE = dEFAULT_DATABASE;
	}

	public String getDEFAULT_HOST() {
		return DEFAULT_HOST;
	}

	public void setDEFAULT_HOST(String dEFAULT_HOST) {
		DEFAULT_HOST = dEFAULT_HOST;
	}

	public int getDEFAULT_PORT() {
		return DEFAULT_PORT;
	}

	public void setDEFAULT_PORT(int dEFAULT_PORT) {
		DEFAULT_PORT = dEFAULT_PORT;
	}

	public String getDEFAULT_USER() {
		return DEFAULT_USER;
	}

	public void setDEFAULT_USER(String dEFAULT_USER) {
		DEFAULT_USER = dEFAULT_USER;
	}

	public String getDEFAULT_PASSWOD() {
		return DEFAULT_PASSWOD;
	}

	public void setDEFAULT_PASSWOD(String dEFAULT_PASSWOD) {
		DEFAULT_PASSWOD = dEFAULT_PASSWOD;
	}

	public String getDEFAULT_DATABASE() {
		return DEFAULT_DATABASE;
	}

	public void setDEFAULT_DATABASE(String dEFAULT_DATABASE) {
		DEFAULT_DATABASE = dEFAULT_DATABASE;
	}

	public Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		
		try {
			return DriverManager.getConnection(getConnectionString(), this.DEFAULT_USER, this.DEFAULT_PASSWOD);
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			throw e;
		}
	}
	
	public String getConnectionString() {
		return String.format("jdbc:mysql://%s:%d/%s", this.DEFAULT_HOST, this.DEFAULT_PORT, this.DEFAULT_DATABASE);
	}
}
