package edu.pitt.sis.infsci2711.metastoreserver.viewmodels;

import java.util.List;

public class TableViewModel {
	private String tableName;
	
	private List<ColumnViewModel> columns;
	
	public TableViewModel() {
		
	}
	
	public TableViewModel(String tableName) {
		this.setTableName(tableName);
	}
	
	public TableViewModel(String tableName, List<ColumnViewModel> columns) {
		this.setTableName(tableName);
		this.setColumns(columns);
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<ColumnViewModel> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnViewModel> columns) {
		this.columns = columns;
	}
}
