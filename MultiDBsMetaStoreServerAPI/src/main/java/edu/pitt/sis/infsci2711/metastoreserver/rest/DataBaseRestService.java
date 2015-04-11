package edu.pitt.sis.infsci2711.metastoreserver.rest;
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

import edu.pitt.sis.infsci2711.metastoreserver.business.DatasourcesService;
import edu.pitt.sis.infsci2711.metastoreserver.models.metaStoreDatabaseModel;
import edu.pitt.sis.infsci2711.metastoreserver.models.ColumnModel;
import edu.pitt.sis.infsci2711.metastoreserver.viewmodels.DataBase;

@Path("Database/")
public class DataBaseRestService {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	//Data base...
	public Response Data_Base() {
		
		DatasourcesService metaStoreService = new DatasourcesService();
		
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
	private DataBase convertDatabaseToViewModel(final metaStoreDatabaseModel Data_Base) {
		return new DataBase(Data_Base.getDatabase());
	}
	  ////data base..
		private List<DataBase> convertDatabaseToViewModel(final List<metaStoreDatabaseModel> Data_Bases) {
			List<DataBase> result = new ArrayList<DataBase>();
			for(metaStoreDatabaseModel Data_Base : Data_Bases) {
				result.add(convertDatabaseToViewModel(Data_Base));
			}
			
			return result;
		}
}
