package edu.pitt.sis.infsci2711.metastoreserver.rest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXB;

import edu.pitt.sis.infsci2711.metastoreserver.business.DatasourcesService;
import edu.pitt.sis.infsci2711.metastoreserver.models.ColumnModel;
import edu.pitt.sis.infsci2711.metastoreserver.models.DatasourceDBModel;
import edu.pitt.sis.infsci2711.metastoreserver.models.TableModel;
import edu.pitt.sis.infsci2711.metastoreserver.viewmodels.CatalogViewModel;
import edu.pitt.sis.infsci2711.metastoreserver.viewmodels.ColumnViewModel;
import edu.pitt.sis.infsci2711.metastoreserver.viewmodels.DatasourceIdsViewModel;
import edu.pitt.sis.infsci2711.metastoreserver.viewmodels.DatasourceViewModel;
import edu.pitt.sis.infsci2711.metastoreserver.viewmodels.TableViewModel;

@Path("datasources/")
public class DatasourcesRestService {

	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public Response getDatasource() {
		
		DatasourcesService datasourcesService = new DatasourcesService();
		
		try {
			List<DatasourceDBModel> allDatasources = datasourcesService.getAll();
			
			List<DatasourceViewModel> fullDatasources = fillDatasourceWithTablesAndColumns(allDatasources);
			
			GenericEntity<List<DatasourceViewModel>> entity = new GenericEntity<List<DatasourceViewModel>>(fullDatasources) {};
			
			return Response.status(200).entity(entity).build();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return Response.status(500).build();
		}
	}
	
	/**
	 * 
	 * @param allDatasources
	 * @return Json object containing datasource with tables and columns
	 * @throws SQLException
	 * @throws Exception
	 */
	private List<DatasourceViewModel> fillDatasourceWithTablesAndColumns(
			List<DatasourceDBModel> allDatasources) throws SQLException, Exception {
		
		List<DatasourceViewModel> result = new ArrayList<DatasourceViewModel>();
		
		DatasourcesService datasourcesService = new DatasourcesService();
		
		for (DatasourceDBModel dbDatasource : allDatasources) {
			DatasourceViewModel datasourceVM = convertDbToViewModel(dbDatasource);
			
			List<TableModel> tables = datasourcesService.findTables(dbDatasource.getId());
			
			List<TableViewModel> tablesVM = new ArrayList<TableViewModel>();
			
			for (TableModel table : tables) {
				List<ColumnModel> columnsDbModel = datasourcesService.findSchema(dbDatasource.getId(), table.getTableName());
				
				List<ColumnViewModel> columnsVM = new ArrayList<ColumnViewModel>();
				
				for (ColumnModel columnDbModel : columnsDbModel) {
					columnsVM.add(new ColumnViewModel(columnDbModel.getTBfield()));
				}
				
				TableViewModel tableVM = new TableViewModel(table.getTableName(), columnsVM);
				
				tablesVM.add(tableVM);
			}
			
			datasourceVM.setTables(tablesVM);
			
			result.add(datasourceVM);
		} 
		
		return result;
	}


	@Path("add")
	@PUT
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addDatasource(final DatasourceViewModel datasource) {
		
		DatasourcesService datasourcesService = new DatasourcesService();
		
		DatasourceDBModel dbModel = convertToDbModel(datasource);
		
		try {
			DatasourceDBModel addedDbModel = datasourcesService.add(dbModel);
			
			
			DatasourceViewModel addedDatasource = convertDbToViewModel(addedDbModel);
			
			//TODO: Send requests to Presto groups
			
			CatalogViewModel prestoCatalog = new CatalogViewModel((addedDatasource.getId()+""),
					addedDatasource.getIpAddress(),addedDatasource.getPort()+"",addedDatasource.getDbType(),
					addedDatasource.getUsername(),addedDatasource.getPassword(),addedDatasource.getDbName());
			
			Client client = ClientBuilder.newClient();
			WebTarget targetPresto = client.target("http://54.174.80.167:7654").path("Catalog/add");
			Response responsePresto = targetPresto.request(MediaType.APPLICATION_JSON)
		             .put(Entity.entity(prestoCatalog, MediaType.APPLICATION_JSON),Response.class);
			
//			CatalogViewModel dataVMPresto = targetPresto.request(MediaType.APPLICATION_JSON_TYPE)
//			    .put(Entity.entity(prestoCatalog,MediaType.APPLICATION_JSON),
//			    		CatalogViewModel.class);
			
			//TODO: Send requests to keyword groups
			DatasourceDBModel dbDatasource = datasourcesService.findById(addedDatasource.getId());
			DatasourceViewModel dbDatasourceVM = convertDbToViewModel(dbDatasource);
			List<TableModel> tables = datasourcesService.findTables(dbDatasourceVM.getId());
			
			List<TableViewModel> tablesVM = new ArrayList<TableViewModel>();
			
			for (TableModel table : tables) {
				List<ColumnModel> columnsDbModel = datasourcesService.findSchema(dbDatasource.getId(), table.getTableName());
				
				List<ColumnViewModel> columnsVM = new ArrayList<ColumnViewModel>();
				
				for (ColumnModel columnDbModel : columnsDbModel) {
					columnsVM.add(new ColumnViewModel(columnDbModel.getTBfield()));
				}
				
				TableViewModel tableVM = new TableViewModel(table.getTableName(), columnsVM);
				
				tablesVM.add(tableVM);
			}
			
			dbDatasourceVM.setTables(tablesVM);
			
			WebTarget targetKeyWord = client.target("http://52.1.107.126:7654").path("Index");
			
			Response responseKeyWord = targetKeyWord.request(MediaType.APPLICATION_JSON)
		             .put(Entity.entity(dbDatasourceVM, MediaType.APPLICATION_JSON),Response.class);
			
			return Response.status(200).entity(addedDatasource).build();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return Response.status(500).build();
		}
	}
	
	@Path("ids")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public Response getDatasourceIds() {
		
		DatasourcesService metaStoreService = new DatasourcesService();
		
		try {
			List<DatasourceDBModel> allDatasources = metaStoreService.getAll();
			List<DatasourceIdsViewModel> datasourceIds = convertDbToViewModel(allDatasources);
			
			GenericEntity<List<DatasourceIdsViewModel>> entity = new GenericEntity<List<DatasourceIdsViewModel>>(datasourceIds) {};
			
			return Response.status(200).entity(entity).build();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return Response.status(500).build();
		}
	}

	@Path("{id}")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public Response getDatasourcebyIds(@PathParam("id") final int id) {
		
		DatasourcesService datasourcesService = new DatasourcesService();
		
		try {
			DatasourceDBModel dbDatasource = datasourcesService.findById(id);
			DatasourceViewModel dbDatasourceVM = convertDbToViewModel(dbDatasource);
			List<TableModel> tables = datasourcesService.findTables(dbDatasourceVM.getId());
			
			List<TableViewModel> tablesVM = new ArrayList<TableViewModel>();
			
			for (TableModel table : tables) {
				List<ColumnModel> columnsDbModel = datasourcesService.findSchema(dbDatasource.getId(), table.getTableName());
				
				List<ColumnViewModel> columnsVM = new ArrayList<ColumnViewModel>();
				
				for (ColumnModel columnDbModel : columnsDbModel) {
					columnsVM.add(new ColumnViewModel(columnDbModel.getTBfield()));
				}
				
				TableViewModel tableVM = new TableViewModel(table.getTableName(), columnsVM);
				
				tablesVM.add(tableVM);
			}
			
			dbDatasourceVM.setTables(tablesVM);
			GenericEntity<DatasourceViewModel> entity = new GenericEntity<DatasourceViewModel>(dbDatasourceVM) {};
			
			return Response.status(200).entity(entity).build();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return Response.status(500).build();
		}
	}
	
	@Path("{id}/tables")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findTables(@PathParam("id")int id) {
		
		DatasourcesService metaStoreService = new DatasourcesService();
		
		try {
			List<TableModel> table = metaStoreService.findTables(id);
		
			List<TableViewModel> metaStores_tables = convertTableToViewModel(table);
			
			GenericEntity<List<TableViewModel>> entity = new GenericEntity<List<TableViewModel>>(metaStores_tables) {};
			
			return Response.status(200).entity(entity).build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.status(500).build();
		}
		
	}
	
	@Path("{id}/{tableName}/columns")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findTables(@PathParam("id")int id, @PathParam("tableName")String tableName) {
		
		DatasourcesService metaStoreService = new DatasourcesService();
		
		try {
			List<ColumnModel> column = metaStoreService.findSchema(id,tableName);
		
			List<ColumnViewModel> columnVM = convertColumnToViewModel(column);
			
			GenericEntity<List<ColumnViewModel>> entity = new GenericEntity<List<ColumnViewModel>>(columnVM) {};
			
			return Response.status(200).entity(entity).build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.status(500).build();
		}
		
	}
	
	private List<DatasourceIdsViewModel> convertDbToViewModel(
			List<DatasourceDBModel> allDatasources) {
		List<DatasourceIdsViewModel> result = new ArrayList<DatasourceIdsViewModel>();
		
		for(DatasourceDBModel datasource : allDatasources) {
			result.add(new DatasourceIdsViewModel(datasource.getId(), datasource.getDbName(), datasource.getTitle()));
		}
		
		return result;
	}

	private DatasourceViewModel convertDbToViewModel(
			DatasourceDBModel datasourceDbModel) {
		return new DatasourceViewModel(datasourceDbModel.getId(), datasourceDbModel.getDbType(), datasourceDbModel.getIpAddress(), datasourceDbModel.getPort(),
				datasourceDbModel.getUsername(), datasourceDbModel.getPassword(), datasourceDbModel.getDbName(),
				datasourceDbModel.getTitle(), datasourceDbModel.getDescription());
	}

	private DatasourceDBModel convertToDbModel(DatasourceViewModel datasource) {
		return new DatasourceDBModel(datasource.getDbType(), datasource.getIpAddress(), datasource.getPort(),
				datasource.getUsername(), datasource.getPassword(), datasource.getDbName(),
				datasource.getTitle(), datasource.getDescription());
	}
	
	private TableViewModel convertTableToViewModel(final TableModel metaStoreTB) {
	return new TableViewModel(metaStoreTB.getTableName());
}

	private List<TableViewModel> convertTableToViewModel(final List<TableModel> metaStoresTB) {
	List<TableViewModel> result = new ArrayList<TableViewModel>();
	for(TableModel metaStoreTB : metaStoresTB) {
		result.add(convertTableToViewModel(metaStoreTB));
	}
	
	return result;
}
	private ColumnViewModel convertColumnToViewModel(final ColumnModel column) {
		return new ColumnViewModel(column.getTBfield());
	}

	private List<ColumnViewModel> convertColumnToViewModel(final List<ColumnModel> columns) {
	List<ColumnViewModel> result = new ArrayList<ColumnViewModel>();
	for(ColumnModel column : columns) {
		result.add(convertColumnToViewModel(column));
	}
		
	return result;
	}
}
