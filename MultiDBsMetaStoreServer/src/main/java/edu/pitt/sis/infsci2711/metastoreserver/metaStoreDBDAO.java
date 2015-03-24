package edu.pitt.sis.infsci2711.metastoreserver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class metaStoreDBDAO {
		public static List<metaStoreDatabaseModel> findDB(  ) throws SQLException, Exception{
		
		try (Connection connection = JdbcUtil.getConnection()) {
			String sql = String.format("show databases");
			try (Statement statement = connection.createStatement()){
				ResultSet resultSet = statement.executeQuery(sql);
				
				 List<metaStoreDatabaseModel> result = new ArrayList<metaStoreDatabaseModel>();
				
				while (resultSet.next()) {
					result.add(new metaStoreDatabaseModel(resultSet.getString(1)));
				}
				
				return result;
			
			}
		}
	}

}
