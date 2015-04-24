package edu.pitt.sis.infsci2711.metastoreserver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.pitt.sis.infsci2711.multidbs.utils.PropertiesManager;

public class JdbcUtil	{
		
	final static Logger logger = LogManager.getLogger(JdbcUtil.class.getName());
	
//	public static final String DEFAULT_HOST = "localhost";//
//	
//	public static final int DEFAULT_PORT = 3306;
//	
//	public static final String DEFAULT_USER = "root"; //CHANGE TO YOUR MYSQL USER NAME
//	
//	public static final String DEFAULT_PASSWOD = "hao"; // CHANGE TO YOUR MYSQL PASSWORD
//	
//	public static final String DEFAULT_DATABASE = "metastore";
	
	public static Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		
		try {
			String connectionString = getConnectionString();
			String username = PropertiesManager.getInstance().getStringProperty("db.username");
			String password = PropertiesManager.getInstance().getStringProperty("db.password");
			
			logger.info(String.format("Going to get connection to '%s' with username = '%s' and password length %d", 
					connectionString, username, password.length()));
			
			return DriverManager.getConnection(connectionString, username, password);
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			
			throw e;
		}
	}
	
	public static String getConnectionString() {
		
		String host = PropertiesManager.getInstance().getStringProperty("db.host");
		int port = PropertiesManager.getInstance().getIntProperty("db.port");
		String database = PropertiesManager.getInstance().getStringProperty("db.database");
		
		String connectionString = String.format("jdbc:mysql://%s:%d/%s", host, port, database);
		
		logger.info("Construciton this connection string: " + connectionString);
		
		return connectionString;
	}
}
