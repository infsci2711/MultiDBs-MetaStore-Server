package metaStoreServerApi;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import metaStoreServer.metaStoreDatabaseModel;
import metaStoreServer.metaStoreSchemaModel;
import metaStoreServer.metaStoreService;
import metaStoreServer.metaStoreDBModel;
import metaStoreServer.metaStoreTableModel;
import metaStoreServerApi.metaStore;

@Path("DB_Table/")
public class tableRestService {
	
	@Path("{DBname}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	//DB_Table...
public Response allDB_Tables(@PathParam("DBname")String DBname) {
		
		metaStoreService metaStoreService = new metaStoreService();
		
		List<metaStoreTableModel> DB_Tables;
		try {
			DB_Tables = metaStoreService.findTables( DBname);
		
			List<DB_Table> metaStores_tables = convertTbToViewModel(DB_Tables);
			
			GenericEntity<List<DB_Table>> entity = new GenericEntity<List<DB_Table>>(metaStores_tables) {};
			
			return Response.status(200).entity(entity).build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.status(500).build();
		}
		
	}
	
	private DB_Table convertTbToViewModel(final metaStoreTableModel metaStoreTB) {
		return new DB_Table(metaStoreTB.getTable());
	}
	
	///tb---
	private List<DB_Table> convertTbToViewModel(final List<metaStoreTableModel> metaStoresTB) {
		List<DB_Table> result = new ArrayList<DB_Table>();
		for(metaStoreTableModel metaStoreTB : metaStoresTB) {
			result.add(convertTbToViewModel(metaStoreTB));
		}
		
		return result;
	}
}
