package edu.pitt.sis.infsci2711.metastoreserver.api;

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

import edu.pitt.sis.infsci2711.metastoreserver.metaStoreDBModel;
import edu.pitt.sis.infsci2711.metastoreserver.metaStoreDatabaseModel;
import edu.pitt.sis.infsci2711.metastoreserver.metaStoreSchemaModel;
import edu.pitt.sis.infsci2711.metastoreserver.metaStoreService;
import edu.pitt.sis.infsci2711.metastoreserver.metaStoreTableModel;
import edu.pitt.sis.infsci2711.metastoreserver.api.metaStore;
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
	

	

	
}
