package metaStoreServer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class metaStoreSchemaDAO {
	
	public static metaStoreTBModel findSchema( final String TBname ) throws SQLException, Exception{
		
		try (Connection connection = JdbcUtil.getConnection()) {
			String sql = String.format("show columns from "+TBname);
			try (Statement statement = connection.createStatement()){
				//re
				 ResultSet resultSet = statement.executeQuery(sql);
				
				while (resultSet.next()) {
					return new metaStoreTBModel(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7));					 
				}
				
				return null;
			}
		}
	}

}
