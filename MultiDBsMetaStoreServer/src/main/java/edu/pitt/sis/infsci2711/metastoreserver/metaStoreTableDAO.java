package edu.pitt.sis.infsci2711.metastoreserver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class metaStoreTableDAO {
	
	public static List<metaStoreTableModel> findTables( final String DBname ) throws SQLException, Exception{
		
		try (Connection connection = JdbcUtil.getConnection()) {
			String sql = String.format("show tables from "+DBname);
			try (Statement statement = connection.createStatement()){
				ResultSet resultSet = statement.executeQuery(sql);
				
				List<metaStoreTableModel> result = new ArrayList<metaStoreTableModel>();
				
				while (resultSet.next()) {
					result.add(new metaStoreTableModel(resultSet.getString(1)));
				}
				
				return result;
			
			}
		}
	}

}
