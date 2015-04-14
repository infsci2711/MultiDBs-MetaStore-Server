package edu.pitt.sis.infsci2711.metastoreserver.viewmodels;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement
public class CatalogViewModel {
	@XmlElement
	private String id;
	@XmlElement
	private String ip;
	@XmlElement
	private String port;
	@XmlElement
	private String dbtype;
	@XmlElement
	private String username;
	@XmlElement
	private String password;
	@XmlElement
	private String dbname;
	
	public CatalogViewModel()
	{}
	public CatalogViewModel(String mid,String mIP, String mPort,String mdbType, String mUsername, String mPassword, String mDBname)
	{
		this.id=mid;
		this.dbtype=mdbType;
		this.ip=mIP;
		this.port=mPort;
		this.username=mUsername;
		this.password=mPassword;
		this.dbname=mDBname;
	}
	public String getId(){return this.id;}
	public String getIP(){return this.ip;}
	public String getPort(){return this.port;}
	public String getUsername(){return this.username;}
	public String getPassword(){return this.password;}
	public String getDBname(){return this.dbname;}
	public String getDBType(){return this.dbtype;}
}