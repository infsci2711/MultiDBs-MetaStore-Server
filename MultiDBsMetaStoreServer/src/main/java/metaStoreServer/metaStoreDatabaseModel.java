package metaStoreServer;

public class metaStoreDatabaseModel {
	private String database;
	
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
