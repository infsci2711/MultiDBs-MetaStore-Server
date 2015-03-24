package edu.pitt.sis.infsci2711.metastoreserver.api;

public class DB_Table {

	 private String table;
		
		public DB_Table(){
			
		}
		
		public DB_Table(final String table){
			this.setTable(table);
		}
		
	    public String getTable() {
	        return table;
	    }

	    public void setTable(String table) {
	        this.table = table;
	    }
		
		
}
