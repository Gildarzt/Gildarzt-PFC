package pfc.game.domain;

import java.io.Serializable;
import java.util.List;

import pfc.game.persistence.PersistentObj;

@SuppressWarnings("serial")
public class Patient implements Serializable, PersistentObj {
	private int id;
	private String name;
	private String surname;
	private String idPsi;
	private List<Report> reportList;
	public Patient(int id, String name, String surname, String idPsi, List<Report> reportList) {
		super();
		this.id = id;
		this.name = name;
		this.idPsi = idPsi;
		this.reportList = reportList;
		this.surname=surname;
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
	public String getIdPsi() {
		return idPsi;
	}
	public void setIdPsi(String idPsi) {
		this.idPsi = idPsi;
	}
	public List<Report> getReportList() {
		return reportList;
	}
	public void setReportList(List<Report> reportList) {
		this.reportList = reportList;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
}
