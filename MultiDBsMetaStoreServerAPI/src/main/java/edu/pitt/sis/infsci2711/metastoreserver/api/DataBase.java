package edu.pitt.sis.infsci2711.metastoreserver.api;

public class DataBase {

	private String database;
	
	public DataBase(){
		
	}
	
	public DataBase( final String database){
		this.setDatabase(database);
	}
	
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
		
	}

