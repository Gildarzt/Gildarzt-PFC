package pfc.game.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pfc.game.persistence.PersistentObj;

@SuppressWarnings("serial")
public class Psicologo implements Serializable, PersistentObj{
	private String id;
	private String name;
	private String password;
	private List<Patient> patientList;
	
	public Psicologo(String id, String name, String password,List<Patient>patientList) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.patientList=patientList;
	}
	public Psicologo(String id,String password){
		this.id=id;
		this.name=null;
		this.password=password;
		this.patientList=null;
	}
	public Psicologo(String id,String name,String password){
		this.id = id;
		this.name = name;
		this.password = password;
		this.patientList=new ArrayList<Patient>();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
