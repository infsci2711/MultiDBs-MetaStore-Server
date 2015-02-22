package metaStoreServer;

public class jdbcUtil{
	import java.sql.Connection;
	import java.sql.DriverManager;

	public class JdbcUtil {
		public static final String DEFAULT_HOST = "localhost";
		
		public static final int DEFAULT_PORT = 3306;
		
		public static final String DEFAULT_USER = "hao"; //CHANGE TO YOUR MYSQL USER NAME
		
		public static final String DEFAULT_PASSWOD = "hao"; // CHANGE TO YOUR MYSQL PASSWORD
		
		public static final String DEFAULT_DATABASE = "metaStore";
		
		public static Connection getConnection() throws Exception {
			Class.forName("com.mysql.jdbc.Driver");
			
			return DriverManager.getConnection(getConnectionString(), DEFAULT_USER, DEFAULT_PASSWOD);
		}
		
		public static String getConnectionString() {
			return String.format("jdbc:mysql://%s:%d/%s", DEFAULT_HOST, DEFAULT_PORT, DEFAULT_DATABASE);
		}
	}
}
