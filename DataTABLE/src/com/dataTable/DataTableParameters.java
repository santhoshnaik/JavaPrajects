package com.dataTable;

import java.util.List;

import com.model.RevenueReport;

public class DataTableParameters {
	// Data table plug parameter
	int iTotalRecords;
	int iTotalDisplayRecords;
	String sEcho;
	String sColumns;
	List<RevenueReport> aaData;

	public int getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public String getsColumns() {
		return sColumns;
	}

	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}

	public List<RevenueReport> getAaData() {
		return aaData;
	}

	public void setAaData(List<RevenueReport> aaData) {
		this.aaData = aaData;
	}
}