package edu.pitt.sis.infsci2711.metastoreserver.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pitt.sis.infsci2711.metastoreserver.JdbcUtil;
import edu.pitt.sis.infsci2711.metastoreserver.models.metaStoreDatabaseModel;

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
