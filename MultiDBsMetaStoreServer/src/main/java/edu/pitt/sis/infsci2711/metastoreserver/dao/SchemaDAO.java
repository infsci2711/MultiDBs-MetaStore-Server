package edu.pitt.sis.infsci2711.metastoreserver.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pitt.sis.infsci2711.metastoreserver.JdbcUtilFather;
import edu.pitt.sis.infsci2711.metastoreserver.business.DatasourcesService;
import edu.pitt.sis.infsci2711.metastoreserver.models.DatasourceDBModel;
import edu.pitt.sis.infsci2711.metastoreserver.models.ColumnModel;

public class SchemaDAO {
	
	public static List<ColumnModel> findSchema(int datasourceId, final String tableName) throws SQLException, Exception{
		
		DatasourceDBModel datasource = DatasourcesDAO.findById(datasourceId);
		
		JdbcUtilFather jdbcUtil = new JdbcUtilFather(datasource.getIpAddress(), datasource.getPort(), 
				datasource.getUsername(), datasource.getPassword(), datasource.getDbName());
		
		
		try (Connection connection = jdbcUtil.getConnection()) {
			String sql = String.format("show columns from `%s` from `%s`", tableName, datasource.getDbName());
			try (Statement statement = connection.createStatement()){
				//re
				 ResultSet resultSet = statement.executeQuery(sql);
				 List<ColumnModel> result = new ArrayList<ColumnModel>();
				while (resultSet.next()) {
					result.add( new ColumnModel(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6)));					 
				}
				
				return result;
			}
		}
	}

}
