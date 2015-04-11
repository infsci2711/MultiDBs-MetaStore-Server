package edu.pitt.sis.infsci2711.metastoreserver.models;

public class metaStoreDatabaseModel {
	private String database;
	
	public metaStoreDatabaseModel(){
		
	}
	
	public metaStoreDatabaseModel( final String database){
		this.setDatabase(database);
	}
	
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
}
