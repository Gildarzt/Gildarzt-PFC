package pfc.game.actions;


import java.util.List;

import pfc.game.domain.Patient;
import pfc.game.domain.Psicologo;
import pfc.game.domain.Report;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class SearchPatient extends ActionSupport{
	private String name,surname;
	private Patient patient;
	private List<Report> reportList;
	private List<Patient> patientList;
	public String execute() throws Exception {
		try {
			Psicologo ad = (Psicologo) ActionContext.getContext().getSession().get("psicologo");
			if (ad == null)
				throw new Exception("No está usted logueado");
			FindPatient(ad);
			if(patientList.size()==1){
				patient = patientList.get(0);
				ActionContext.getContext().getSession().put("Patient", patient);
				GetReports();
				return SUCCESS;
			}
			else 
				return "moreThanOne";
			
		} catch (Exception e) {
			return ERROR;
		}
	}
	public void FindPatient(Psicologo ad){
		for(Patient pat:ad.getPatientList())
			if(pat.getName().equals(name) && pat.getSurname().equals(surname))
				patientList.add(pat);
	}
	public void GetReports(){
		for(Report rep:patient.getReportList())
			reportList.add(rep);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public List<Patient> getPatientList() {
		return patientList;
	}
	public void setPatientList(List<Patient> patientList) {
		this.patientList = patientList;
	}
	public List<Report> getReportList() {
		return reportList;
	}
	public void setReportList(List<Report> reportList) {
		this.reportList = reportList;
	}
}
