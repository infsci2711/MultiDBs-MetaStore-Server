package edu.pitt.sis.infsci2711.metastoreserver;

import java.sql.SQLException;
import java.util.List;

import edu.pitt.sis.infsci2711.metastoreserver.metaStoreDAO;
import edu.pitt.sis.infsci2711.metastoreserver.metaStoreDBModel;

public class metaStoreService {

	public List<metaStoreDBModel> getAll() throws SQLException, Exception {
		List<metaStoreDBModel> result = metaStoreDAO.findAll();
		
		return result;
	}
	
	
	public metaStoreDBModel findById(final int id) throws SQLException, Exception {
		metaStoreDBModel result = metaStoreDAO.findById(id);
		
		return result;
	}
	//by DBname
	public metaStoreDBModel findByDBname(final String DBname) throws SQLException, Exception {
		metaStoreDBModel result = metaStoreDAO.findByDBname(DBname);
		
		return result;
	}
	
	// check DBname
	public boolean checkDBname(String DBname)throws SQLException, Exception {
		
		return metaStoreDAO.checkByDBname(DBname) ;
	}
	
	
	//by Label
	public metaStoreDBModel findByLabel(final String Label) throws SQLException, Exception {
		metaStoreDBModel result = metaStoreDAO.findByLabel(Label);
		
		return result;
	}
	
	public metaStoreDBModel add(final metaStoreDBModel metaStore) throws SQLException, Exception {
		metaStoreDAO.save(metaStore);
		
		return metaStore;
	}
	
	public List<metaStoreDatabaseModel> findDB() throws SQLException, Exception {
		List<metaStoreDatabaseModel> result = metaStoreDBDAO.findDB();
		return result;
	}
	
	
	public List<metaStoreTableModel> findTables(final String DBname) throws SQLException, Exception {
		List<metaStoreTableModel> result = metaStoreTableDAO.findTables(DBname);
		
		return result;
	}
	
	public List<metaStoreSchemaModel> findSchema(final String TBname,final String DBname) throws SQLException, Exception {
		List<metaStoreSchemaModel> result = metaStoreSchemaDAO.findSchema(TBname,DBname);
		
		return result;
	}
}
