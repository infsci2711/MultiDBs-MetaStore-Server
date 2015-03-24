package edu.pitt.sis.infsci2711.metastoreserver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class metaStoreSchemaDAO {
	
	public static List<metaStoreSchemaModel> findSchema( final String TBname ,final String DBname) throws SQLException, Exception{
		
		try (Connection connection = JdbcUtil.getConnection()) {
			String sql = String.format("show columns from "+TBname+" from "+DBname);
			try (Statement statement = connection.createStatement()){
				//re
				 ResultSet resultSet = statement.executeQuery(sql);
				 List<metaStoreSchemaModel> result = new ArrayList<metaStoreSchemaModel>();
				while (resultSet.next()) {
					result.add( new metaStoreSchemaModel(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6)));					 
				}
				
				return result;
			}
		}
	}

}
