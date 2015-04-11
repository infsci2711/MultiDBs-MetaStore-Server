package edu.pitt.sis.infsci2711.metastoreserver.viewmodels;

public class ColumnViewModel {
	private String columnName;
	
	public ColumnViewModel() {
		
	}
	
	public ColumnViewModel(String columnName) {
		this.setColumnName(columnName);
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
}
