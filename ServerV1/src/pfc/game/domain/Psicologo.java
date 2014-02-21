package pfc.game.domain;

import java.util.List;

public class Psicologo {
	private int id;
	private String name;
	private String password;
	private List<Patient> patientList;
	public Psicologo(int id, String name, String password,List<Patient>patientList) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.patientList=patientList;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Patient> getPatientList() {
		return patientList;
	}
	public void setPatientList(List<Patient> patientList) {
		this.patientList = patientList;
	}
}
