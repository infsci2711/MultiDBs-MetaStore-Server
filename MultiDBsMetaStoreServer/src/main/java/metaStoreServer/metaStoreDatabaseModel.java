package metaStoreServer;

public class metaStoreDatabaseModel {
	private int id;
	private String database;
	
	public metaStoreDatabaseModel(final int id, final String database){
		this.setId(id);
		this.setDatabase(database);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
}
