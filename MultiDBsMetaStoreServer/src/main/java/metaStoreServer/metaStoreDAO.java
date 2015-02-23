package metaStoreServer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import metaStoreServer.metaStoreDBModel;
import metaStoreServer.JdbcUtil;

public class metaStoreDAO {
public static List<metaStoreDBModel> findAll() throws SQLException, Exception {
		
		try (Connection connection = JdbcUtil.getConnection()) {
			String sql = "SELECT * FROM metaStore";
			try (Statement statement = connection.createStatement()){
				
				ResultSet resultSet = statement.executeQuery(sql);
				
				List<metaStoreDBModel> result = new ArrayList<metaStoreDBModel>();
				
				while (resultSet.next()) {
					result.add(new metaStoreDBModel(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),resultSet.getInt(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7)));					 
				}
				
				return result;
			}
		}
		
	}
	
	public static metaStoreDBModel findById(final int id) throws SQLException, Exception {
		
		try (Connection connection = JdbcUtil.getConnection()) {
			String sql = "SELECT * FROM metaStore where ID = " + id;
			try (Statement statement = connection.createStatement()){
				
				ResultSet resultSet = statement.executeQuery(sql);
				
				while (resultSet.next()) {
					return new metaStoreDBModel(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),resultSet.getInt(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7));					 
				}
				
				return null;
			}
		}
		
	}
	
	public static int save(final metaStoreDBModel metaStore) throws SQLException, Exception {
		
		try (Connection connection = JdbcUtil.getConnection()) {
			String sql = String.format("INSERT INTO metaStore (DBtype, IPAddress, port, username, password, DBname) VALUES ('%s', '%s', '%d', '%s', '%s', '%s')", metaStore.getDBtype(), metaStore.getIPAddress(), 
					metaStore.getPort(),metaStore.getUsername(),metaStore.getPassword(),metaStore.getDBname());
			try (Statement statement = connection.createStatement()){
				
				int res = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
				
				ResultSet rs = statement.getGeneratedKeys();
				if (rs.next()){
					metaStore.setId(rs.getInt(1));
				}
				
				return res;
			}
		}
		
	}
}
