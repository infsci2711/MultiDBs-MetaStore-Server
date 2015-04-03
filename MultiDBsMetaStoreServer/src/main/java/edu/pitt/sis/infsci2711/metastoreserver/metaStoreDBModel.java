package edu.pitt.sis.infsci2711.metastoreserver;

public class metaStoreDBModel {
	private int id;
	private String DBtype;
	private String  IPAddress;
	private int port;
	private String username;
	private String password;
	private String DBname;
	
	public metaStoreDBModel(){
		
	}
	
	public metaStoreDBModel(final String DBtype, final String IPAdress, final int port,
			final String username, final String password, final String DBname, final String Label){
		this.setDBtype(DBtype);
		this.setIPAddress(IPAdress);
		this.setPort(port);
		this.setUsername(username);
		this.setPassword(password);
		this.setDBname(DBname);
	}
	
	public metaStoreDBModel(final int id, final String DBtype, final String IPAdress, 
			final int port, final String username, final String password, final String DBname, final String Label){		
		this.setId(id);
		this.setDBtype(DBtype);
		this.setIPAddress(IPAdress);
		this.setPort(port);
		this.setUsername(username);
		this.setPassword(password);
		this.setDBname(DBname);
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDBtype() {
		return DBtype;
	}

	public void setDBtype(String dBtype) {
		DBtype = dBtype;
	}

	public String getIPAddress() {
		return IPAddress;
	}

	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
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

	public String getDBname() {
		return DBname;
	}

	public void setDBname(String dBname) {
		DBname = dBname;
	}
	
	public String getLabel(){
		return this.id + this.DBname;
	}
	
}
