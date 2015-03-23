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

@Path("Table_Schema/")
public class schemaRestService {

	@Path("{DBname}/{TBname}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	//DB_Table_Schema...
	public Response Tables_Schema(@PathParam("TBname") String TBname,@PathParam("DBname") String DBname) {
		
		metaStoreService metaStoreService = new metaStoreService();
		
		metaStoreSchemaModel table_schema;
		try {
			table_schema = metaStoreService.findSchema(TBname,DBname);
			
			Table_Schema schema=convertSchemaToViewModel(table_schema);
		
			GenericEntity<Table_Schema> entity = new GenericEntity<Table_Schema>(schema) {};
			
			return Response.status(200).entity(entity).build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.status(500).build();
		}
		
	}

	//schema
	private Table_Schema convertSchemaToViewModel(final metaStoreSchemaModel Schema) {
		return new Table_Schema(Schema.getId(),Schema.getTBfield(),Schema.getTBtype(),Schema.getTBnull(),Schema.getTBkey(),Schema.getTBdefault(),Schema.getTBextra());
	}
	


}
