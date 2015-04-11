package edu.pitt.sis.infsci2711.metastoreserver.models;

public class TableModel {

	private String tableName;
		
	public TableModel(){
		
	}
	
	public TableModel(final String table){
		this.setTableName(table);
	}
	
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String table) {
        this.tableName = table;
    }
}
