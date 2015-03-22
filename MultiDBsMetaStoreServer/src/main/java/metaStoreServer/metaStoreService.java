package metaStoreServer;

import java.sql.SQLException;
import java.util.List;

import metaStoreServer.metaStoreDAO;
import metaStoreServer.metaStoreDBModel;

public class metaStoreService {

	public List<metaStoreDBModel> getAll() throws SQLException, Exception {
		List<metaStoreDBModel> result = metaStoreDAO.findAll();
		
		return result;
	}
	
	public metaStoreDBModel findById(final int id) throws SQLException, Exception {
		metaStoreDBModel result = metaStoreDAO.findById(id);
		
		return result;
	}
	
	public metaStoreDBModel add(final metaStoreDBModel metaStore) throws SQLException, Exception {
		metaStoreDAO.save(metaStore);
		
		return metaStore;
	}
	
	public List<String> findDB(final String DBname) throws SQLException, Exception {
		List<String> result = metaStoreDBDAO.findDB(DBname);
		
		return result;
	}
	
	
	public List<metaStoreTableModel> findTables(final String DBname) throws SQLException, Exception {
		List<metaStoreTableModel> result = metaStoreTableDAO.findTables(DBname);
		
		return result;
	}
	
	public metaStoreTBModel findSchema(final String TBname) throws SQLException, Exception {
		metaStoreTBModel result = metaStoreSchemaDAO.findSchema(TBname);
		
		return result;
	}
}
