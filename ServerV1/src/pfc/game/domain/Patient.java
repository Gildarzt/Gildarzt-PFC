package pfc.game.domain;

import java.util.List;

public class Patient {
	private int id;
	private String name;
	private int idPsi;
	private List<Report> reportList;
	public Patient(int id, String name, int idPsi, List<Report> reportList) {
		super();
		this.id = id;
		this.name = name;
		this.idPsi = idPsi;
		this.reportList = reportList;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIdPsi() {
		return idPsi;
	}
	public void setIdPsi(int idPsi) {
		this.idPsi = idPsi;
	}
	public List<Report> getReportList() {
		return reportList;
	}
	public void setReportList(List<Report> reportList) {
		this.reportList = reportList;
	}
}
