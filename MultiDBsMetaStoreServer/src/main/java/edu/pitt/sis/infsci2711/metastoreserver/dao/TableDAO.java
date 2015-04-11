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
import edu.pitt.sis.infsci2711.metastoreserver.models.TableModel;

public class TableDAO {
	
	public static List<TableModel> findTables(final int id) throws SQLException, Exception{
		
		DatasourceDBModel datasource = DatasourcesDAO.findById(id);
		
		JdbcUtilFather jdbcUtil = new JdbcUtilFather(datasource.getIpAddress(), datasource.getPort(), 
				datasource.getUsername(), datasource.getPassword(), datasource.getDbName());
		
		try (Connection connection = jdbcUtil.getConnection()) {
			String sql = String.format("show tables from "+ datasource.getDbName());
			
			try (Statement statement = connection.createStatement()){
				ResultSet resultSet = statement.executeQuery(sql);
				
				List<TableModel> result = new ArrayList<TableModel>();
				
				while (resultSet.next()) {
					result.add(new TableModel(resultSet.getString(1)));
				}
				
				return result;
			}
		}
	}
}
