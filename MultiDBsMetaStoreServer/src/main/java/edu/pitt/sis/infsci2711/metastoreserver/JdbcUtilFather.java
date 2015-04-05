package edu.pitt.sis.infsci2711.metastoreserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtilFather {
	public static String DEFAULT_HOST = "";//
	
	public static int DEFAULT_PORT = 0;
	
	public static String DEFAULT_USER = ""; //CHANGE TO YOUR MYSQL USER NAME
	
	public static String DEFAULT_PASSWOD = ""; // CHANGE TO YOUR MYSQL PASSWORD
	
	public static String DEFAULT_DATABASE = "";	
	
	
	public JdbcUtilFather() {
	}

	public JdbcUtilFather(String dEFAULT_HOST, int dEFAULT_PORT,
			String dEFAULT_USER, String dEFAULT_PASSWOD, String dEFAULT_DATABASE) {
		DEFAULT_HOST = dEFAULT_HOST;
		DEFAULT_PORT = dEFAULT_PORT;
		DEFAULT_USER = dEFAULT_USER;
		DEFAULT_PASSWOD = dEFAULT_PASSWOD;
		DEFAULT_DATABASE = dEFAULT_DATABASE;
	}

	public static String getDEFAULT_HOST() {
		return DEFAULT_HOST;
	}

	public static void setDEFAULT_HOST(String dEFAULT_HOST) {
		DEFAULT_HOST = dEFAULT_HOST;
	}

	public static int getDEFAULT_PORT() {
		return DEFAULT_PORT;
	}

	public static void setDEFAULT_PORT(int dEFAULT_PORT) {
		DEFAULT_PORT = dEFAULT_PORT;
	}

	public static String getDEFAULT_USER() {
		return DEFAULT_USER;
	}

	public static void setDEFAULT_USER(String dEFAULT_USER) {
		DEFAULT_USER = dEFAULT_USER;
	}

	public static String getDEFAULT_PASSWOD() {
		return DEFAULT_PASSWOD;
	}

	public static void setDEFAULT_PASSWOD(String dEFAULT_PASSWOD) {
		DEFAULT_PASSWOD = dEFAULT_PASSWOD;
	}

	public static String getDEFAULT_DATABASE() {
		return DEFAULT_DATABASE;
	}

	public static void setDEFAULT_DATABASE(String dEFAULT_DATABASE) {
		DEFAULT_DATABASE = dEFAULT_DATABASE;
	}

	public static Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		
		try {
			return DriverManager.getConnection(getConnectionString(), DEFAULT_USER, DEFAULT_PASSWOD);
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			throw e;
		}
	}
	
	public static String getConnectionString() {
		return String.format("jdbc:mysql://%s:%d/%s", DEFAULT_HOST, DEFAULT_PORT, DEFAULT_DATABASE);
	}
}
