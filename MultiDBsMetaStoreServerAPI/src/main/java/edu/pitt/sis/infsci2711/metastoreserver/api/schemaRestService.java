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

@Path("Schema/")
public class schemaRestService {

	@Path("{DBname}/{TBname}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	//DB_Table_Schema...
	public Response Tables_Schema(@PathParam("TBname") String TBname,@PathParam("DBname") String DBname) {
		
		metaStoreService metaStoreService = new metaStoreService();
		
		List<metaStoreSchemaModel> table_schemas;
		try {
			table_schemas = metaStoreService.findSchema(TBname,DBname);
			
			List<Table_Schema> schemas=convertSCToViewModel(table_schemas);
		
			GenericEntity<List<Table_Schema>> entity = new GenericEntity<List<Table_Schema>>(schemas) {};
			
			return Response.status(200).entity(entity).build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.status(500).build();
		}
		
	}

	//schema
	
	private Table_Schema convertSCToViewModel(final metaStoreSchemaModel metaStoreSC) {
		return new Table_Schema(metaStoreSC.getTBfield(),metaStoreSC.getTBtype(),metaStoreSC.getTBnull(),metaStoreSC.getTBkey(),metaStoreSC.getTBdefault(),metaStoreSC.getTBextra());
	}
	
	///tb---
	private List<Table_Schema> convertSCToViewModel(final List<metaStoreSchemaModel> metaStoresSC) {
		List<Table_Schema> result = new ArrayList<Table_Schema>();
		for(metaStoreSchemaModel metaStoreSC : metaStoresSC) {
			result.add(convertSCToViewModel(metaStoreSC));
		}
		
		return result;
	}
	


}
