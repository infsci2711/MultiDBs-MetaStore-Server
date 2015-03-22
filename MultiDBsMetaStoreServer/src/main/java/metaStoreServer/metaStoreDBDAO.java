package metaStoreServer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class metaStoreDBDAO {
		public static List<String> findDB( final String DBname ) throws SQLException, Exception{
		
		try (Connection connection = JdbcUtil.getConnection()) {
			String sql = String.format("show databases");
			try (Statement statement = connection.createStatement()){
				ResultSet resultSet = statement.executeQuery(sql);
				
				 List<String> result = new ArrayList<String>();
				//re
				while (resultSet.next()) {
					result.add(resultSet.getString(1));
				}
				
				return result;
			
			}
		}
	}

}
