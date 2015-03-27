package edu.pitt.sis.infsci2711.metastoreserver;


	import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//capital J!
	public class JdbcUtil {
		public static final String DEFAULT_HOST = "localhost";//
		
		public static final int DEFAULT_PORT = 3306;
		
		public static final String DEFAULT_USER = "root"; //CHANGE TO YOUR MYSQL USER NAME
		
		public static final String DEFAULT_PASSWOD = "hao"; // CHANGE TO YOUR MYSQL PASSWORD
		
		public static final String DEFAULT_DATABASE = "group1";
		
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

