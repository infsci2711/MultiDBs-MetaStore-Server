package edu.pitt.sis.infsci2711.metastoreserver.business;

import java.sql.SQLException;
import java.util.List;

import edu.pitt.sis.infsci2711.metastoreserver.dao.DatasourcesDAO;
import edu.pitt.sis.infsci2711.metastoreserver.dao.metaStoreDBDAO;
import edu.pitt.sis.infsci2711.metastoreserver.dao.SchemaDAO;
import edu.pitt.sis.infsci2711.metastoreserver.dao.TableDAO;
import edu.pitt.sis.infsci2711.metastoreserver.models.DatasourceDBModel;
import edu.pitt.sis.infsci2711.metastoreserver.models.metaStoreDatabaseModel;
import edu.pitt.sis.infsci2711.metastoreserver.models.ColumnModel;
import edu.pitt.sis.infsci2711.metastoreserver.models.TableModel;

public class DatasourcesService {

	/**
	 * Adds new datasource.
	 * @param metaStore
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public DatasourceDBModel add(final DatasourceDBModel metaStore) throws SQLException, Exception {
		DatasourcesDAO.save(metaStore);
		
		return metaStore;
	}
	
	/**
	 * get all datasources
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<DatasourceDBModel> getAll() throws SQLException, Exception {
		List<DatasourceDBModel> result = DatasourcesDAO.findAll();
		
		return result;
	}
	
	/**
	 * find all table in a certain database
	 * @param DBname
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<TableModel> findTables(int id) throws SQLException, Exception {
		List<TableModel> result = TableDAO.findTables(id);
		
		return result;
	}
	
	/**
	 * find all columns in a table
	 * @param TBname
	 * @param DBname
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<ColumnModel> findSchema(int datasourceId, final String tableName) throws SQLException, Exception {
		List<ColumnModel> result = SchemaDAO.findSchema(datasourceId, tableName);
		
		return result;
	}
	
	public DatasourceDBModel findById(final int id) throws SQLException, Exception {
		DatasourceDBModel result = DatasourcesDAO.findById(id);
		
		return result;
	}
	//by DBname
	public DatasourceDBModel findByDBname(final String DBname) throws SQLException, Exception {
		DatasourceDBModel result = DatasourcesDAO.findByDBname(DBname);
		
		return result;
	}
	
	// check DBname
	public boolean checkDBname(String DBname)throws SQLException, Exception {
		
		return DatasourcesDAO.checkByDBname(DBname) ;
	}
	
//	//by Label
//	public DatasourceDBModel findByLabel(final String Label) throws SQLException, Exception {
//		DatasourceDBModel result = DatasourcesDAO.findByLabel(Label);
//		
//		return result;
//	}
	
	public List<metaStoreDatabaseModel> findDB() throws SQLException, Exception {
		List<metaStoreDatabaseModel> result = metaStoreDBDAO.findDB();
		return result;
	}
}
