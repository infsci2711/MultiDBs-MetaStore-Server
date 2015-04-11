package edu.pitt.sis.infsci2711.metastoreserver.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pitt.sis.infsci2711.metastoreserver.JdbcUtil;
import edu.pitt.sis.infsci2711.metastoreserver.models.DatasourceDBModel;

public class DatasourcesDAO {
	
	public static List<DatasourceDBModel> findAll() throws SQLException, Exception {
		
		try (Connection connection = JdbcUtil.getConnection()) {
			String sql = "SELECT * FROM metaStore";
			try (Statement statement = connection.createStatement()){
				
				ResultSet resultSet = statement.executeQuery(sql);
				
				List<DatasourceDBModel> result = new ArrayList<DatasourceDBModel>();
				
				while (resultSet.next()) {
					result.add(new DatasourceDBModel(resultSet.getInt(1), resultSet.getString(2), 
							resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5), 
							resultSet.getString(6), resultSet.getString(7),
							resultSet.getString(8), resultSet.getString(9)));					 
				}
				
				return result;
			}
		}
		
	}
	
	public static DatasourceDBModel findById(final int id) throws SQLException, Exception {
		
		try (Connection connection = JdbcUtil.getConnection()) {
			String sql = "SELECT * FROM metaStore where id = " + id;
			try (Statement statement = connection.createStatement()){
				
				ResultSet resultSet = statement.executeQuery(sql);
				
				while (resultSet.next()) {
					return new DatasourceDBModel(resultSet.getInt(1), resultSet.getString(2), 
							resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5), 
							resultSet.getString(6), resultSet.getString(7),
							resultSet.getString(8), resultSet.getString(9));					 
				}
				
				return null;
			}
		}
		
	}
	
	public static int save(final DatasourceDBModel metaStore) throws SQLException, Exception {
		
		try (Connection connection = JdbcUtil.getConnection()) {
			String sql = String.format("INSERT INTO metaStore (DBtype, IPAddress, port, username, password, DBname, title, description) "
					+ "VALUES ('%s', '%s', '%d', '%s', '%s', '%s', '%s', '%s')", 
					metaStore.getDbType(), metaStore.getIpAddress(), 
					metaStore.getPort(), metaStore.getUsername(), 
					metaStore.getPassword(), metaStore.getDbName(), metaStore.getTitle(), metaStore.getDescription());
			
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
	
	//FIND by NAME
    public static DatasourceDBModel findByDBname(final String  DBname) throws SQLException, Exception {
		
		try (Connection connection = JdbcUtil.getConnection()) {
			String sql = "SELECT * FROM metaStore where DBname = '" + DBname +"'";
			try (Statement statement = connection.createStatement()){
				
				ResultSet resultSet = statement.executeQuery(sql);
				
				while (resultSet.next()) {
					return new DatasourceDBModel(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), 
							resultSet.getInt(4), resultSet.getString(5), 
							resultSet.getString(6), resultSet.getString(7), 
							resultSet.getString(8), resultSet.getString(9));					 
				}
				
				return null;
			}
		}
		
	}
    public static boolean checkByDBname(final String  DBname) throws SQLException, Exception {
		
 		try (Connection connection = JdbcUtil.getConnection()) {
 			String sql = "SELECT * FROM metaStore where DBname = '" + DBname +"'";
 			try (Statement statement = connection.createStatement()){
 				
 				ResultSet resultSet = statement.executeQuery(sql);
 				
 				while (resultSet.next()) {
 					return false;
 				} 			
 				return true;
 			}
 		} 		
 	}		
}
