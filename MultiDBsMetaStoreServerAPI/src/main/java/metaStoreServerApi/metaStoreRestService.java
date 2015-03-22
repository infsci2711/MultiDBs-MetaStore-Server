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
@Path("metaStore/")
public class metaStoreRestService {

	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public Response allmetaStores() {
		
		metaStoreService metaStoreService = new metaStoreService();
		
		List<metaStoreDBModel> metaStoresDB;
		try {
			metaStoresDB = metaStoreService.getAll();
		
			List<metaStore> metaStores = convertDbToViewModel(metaStoresDB);
			
			GenericEntity<List<metaStore>> entity = new GenericEntity<List<metaStore>>(metaStores) {};
			
			return Response.status(200).entity(entity).build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.status(500).build();
		}
		
	}
	
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


@Path("{TBname}")
@GET
@Produces(MediaType.APPLICATION_JSON)
//DB_Table_Schema...
public Response Tables_Schema(@PathParam("TBname") String TBname) {
	
	metaStoreService metaStoreService = new metaStoreService();
	
	metaStoreSchemaModel table_schema;
	try {
		table_schema = metaStoreService.findSchema(TBname);
		
		Table_Schema schema=convertSchemaToViewModel(table_schema);
	
		GenericEntity<Table_Schema> entity = new GenericEntity<Table_Schema>(schema) {};
		
		return Response.status(200).entity(entity).build();
	} catch (Exception e) {
		System.out.println(e.getMessage());
		return Response.status(500).build();
	}
	
}


//Data base...
public Response Data_Base() {
	
	metaStoreService metaStoreService = new metaStoreService();
	
	List<metaStoreDatabaseModel> data_base;
	try {
		data_base = metaStoreService.findDB();
		List<DataBase> bases = convertDatabaseToViewModel(data_base);
	
		GenericEntity<List<DataBase>> entity = new GenericEntity<List<DataBase>>(bases) {};
		
		return Response.status(200).entity(entity).build();
	} catch (Exception e) {
		System.out.println(e.getMessage());
		return Response.status(500).build();
	}
	
}



	
	
	
	
	@Path("{id}")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public Response metaStoreById(@PathParam("id") final int id) {
		
		metaStoreService metaStoreService = new metaStoreService();
		
		try {
			metaStoreDBModel metaStoresDB = metaStoreService.findById(id);
		 
			if (metaStoresDB != null) {
				metaStore metaStore = convertDbToViewModel(metaStoresDB);
			
				return Response.status(200).entity(metaStore).build();
			}
			return Response.status(404).entity("metaStore not found").build();
		} catch (Exception e) {
			return Response.status(500).build();
		}
		
	}
	
	@PUT
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addmetaStore(final metaStore metaStore) {
		
		metaStoreService metaStoreService = new metaStoreService();
		
		try {
			metaStoreDBModel metaStoresDB = metaStoreService.add(convertViewModelToDB(metaStore));
		
			metaStore metaStoreInserted = convertDbToViewModel(metaStoresDB);
			
			return Response.status(200).entity(metaStoreInserted).build();
		} catch (Exception e) {
			return Response.status(500).build();
		}
		
	}

	private metaStoreDBModel convertViewModelToDB(final metaStore metaStore) {
		return new metaStoreDBModel(metaStore.getDBtype(), metaStore.getIPAddress(), 
				metaStore.getPort(),metaStore.getUsername(),metaStore.getPassword(),metaStore.getDBname());
	}

	private List<metaStore> convertDbToViewModel(final List<metaStoreDBModel> metaStoresDB) {
		List<metaStore> result = new ArrayList<metaStore>();
		for(metaStoreDBModel metaStoreDB : metaStoresDB) {
			result.add(convertDbToViewModel(metaStoreDB));
		}
		
		return result;
	}
	
	private metaStore convertDbToViewModel(final metaStoreDBModel metaStoreDB) {
		return new metaStore(metaStoreDB.getId(), metaStoreDB.getDBtype(), metaStoreDB.getIPAddress(), metaStoreDB.getPort(),metaStoreDB.getUsername(),metaStoreDB.getPassword(),metaStoreDB.getDBname());
	}
	
	
	
	///tb---
	private List<DB_Table> convertTbToViewModel(final List<metaStoreTableModel> metaStoresTB) {
		List<DB_Table> result = new ArrayList<DB_Table>();
		for(metaStoreTableModel metaStoreTB : metaStoresTB) {
			result.add(convertTbToViewModel(metaStoreTB));
		}
		
		return result;
	}
	
	private DB_Table convertTbToViewModel(final metaStoreTableModel metaStoreTB) {
		return new DB_Table(metaStoreTB.getTable());
	}
	
  ////data base..
	private List<DataBase> convertDatabaseToViewModel(final List<metaStoreDatabaseModel> Data_Bases) {
		List<DataBase> result = new ArrayList<DataBase>();
		for(metaStoreDatabaseModel Data_Base : Data_Bases) {
			result.add(convertDatabaseToViewModel(Data_Base));
		}
		
		return result;
	}
	
	private DataBase convertDatabaseToViewModel(final metaStoreDatabaseModel Data_Base) {
		return new DataBase(Data_Base.getDatabase());
	}
	
	//schema
	private Table_Schema convertSchemaToViewModel(final metaStoreSchemaModel Schema) {
		return new Table_Schema(Schema.getId(),Schema.getTBfield(),Schema.getTBtype(),Schema.getTBnull(),Schema.getTBkey(),Schema.getTBdefault(),Schema.getTBextra());
	}
	
}
