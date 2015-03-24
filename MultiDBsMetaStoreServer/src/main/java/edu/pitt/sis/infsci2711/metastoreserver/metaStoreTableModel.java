package edu.pitt.sis.infsci2711.metastoreserver;

public class metaStoreTableModel {

	 private String table;
		
		public metaStoreTableModel(){
			
		}
		
		public metaStoreTableModel(final String table){
			this.setTable(table);
		}
		
	    public String getTable() {
	        return table;
	    }

	    public void setTable(String table) {
	        this.table = table;
	    }
		
		
}
