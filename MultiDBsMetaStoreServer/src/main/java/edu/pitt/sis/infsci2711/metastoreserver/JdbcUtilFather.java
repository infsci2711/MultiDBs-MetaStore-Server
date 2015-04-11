package edu.pitt.sis.infsci2711.metastoreserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtilFather {
	public final String host;
	
	public final int port;
	
	public final String username;
	
	public final String password;
	
	public final String databaseName;	
	
	public JdbcUtilFather(String hostP, int portP,
			String usernameP, String passwordP, String databaseNameP) {
		host = hostP;
		port = portP;
		username = usernameP;
		password = passwordP;
		databaseName = databaseNameP;
	}

	public Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		
		try {
			return DriverManager.getConnection(getConnectionString(), username, password);
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			throw e;
		}
	}
	
	public String getConnectionString() {
		return String.format("jdbc:mysql://%s:%d/%s", host, port, databaseName);
	}
}
