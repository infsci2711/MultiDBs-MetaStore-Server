package edu.pitt.sis.infsci2711.metastoreserver.models;

public class ColumnModel {

	private String TBfield;
	private String TBtype;
	private String TBnull;
	private String TBkey;
	private String TBdefault;
	private String TBextra;
	
	public ColumnModel(){
		
	}
	
	public ColumnModel(final String TBfield, final String TBtype, final String TBnull,
			final String TBkey, final String TBdefault, final String TBextra){
		this.setTBfield(TBfield);
		this.setTBtype(TBtype);
		this.setTBnull(TBnull);
		this.setTBkey(TBkey);
		this.setTBdefault(TBdefault);
		this.setTBextra(TBextra);
	}
	
	public String getTBfield() {
		return TBfield;
	}

	public void setTBfield(String tBfield) {
		TBfield = tBfield;
	}

	public String getTBtype() {
		return TBtype;
	}

	public void setTBtype(String tBtype) {
		TBtype = tBtype;
	}
	
	public String getTBnull() {
		return TBnull;
	}

	public void setTBnull(String tBnull) {
		TBnull = tBnull;
	}
	
	public String getTBkey() {
		return TBnull;
	}

	public void setTBkey(String tBkey) {
		TBkey = tBkey;
	}

	public String getTBdefault() {
		return TBdefault;
	}

	public void setTBdefault(String tBdefault) {
		TBdefault = tBdefault;
	}
	
	public String getTBextra() {
		return TBextra;
	}

	public void setTBextra(String tBextra) {
		TBextra = tBextra;
	}

}
