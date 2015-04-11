package edu.pitt.sis.infsci2711.metastoreserver.models;

public class DatasourceDBModel {
	
	private int id;
	private String dbType;
	private String  ipAddress;
	private int port;
	private String username;
	private String password;
	private String dbName;
	private String title;
	private String description;
	
	public DatasourceDBModel() {
		
	}
	
	public DatasourceDBModel(final String dbType, final String ipAdress, final int port,
			final String username, final String password, final String dbName,
			String title, String description){
		this.setDbType(dbType);
		this.setIpAddress(ipAdress);
		this.setPort(port);
		this.setUsername(username);
		this.setPassword(password);
		this.setDbName(dbName);
		this.setTitle(title);
		this.setDescription(description);
	}
	
	public DatasourceDBModel(int id, final String dbType, final String ipAdress, final int port,
			final String username, final String password, final String dbName,
			String title, String description){
		this.setId(id);
		this.setDbType(dbType);
		this.setIpAddress(ipAdress);
		this.setPort(port);
		this.setUsername(username);
		this.setPassword(password);
		this.setDbName(dbName);
		this.setTitle(title);
		this.setDescription(description);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String iPAddress) {
		ipAddress = iPAddress;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dBname) {
		dbName = dBname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
