package pfc.game.actions;

import java.util.List;

import pfc.game.domain.Patient;
import pfc.game.domain.Psicologo;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ShowPatient extends ActionSupport{
	private Patient patient;
	private int id;
	private List<Patient> reportList;
	public String execute() throws Exception {
		try {
			Psicologo ad = (Psicologo) ActionContext.getContext().getSession().get("psicologo");
			if (ad == null)
				throw new Exception("No está usted logueado");
			if(FindPatient(ad))
				return SUCCESS;
			else 
				return "ERROR";
			
		} catch (Exception e) {
			return ERROR;
		}
	}
	public boolean FindPatient(Psicologo ad){
		boolean res=false;
		for(Patient pat:ad.getPatientList())
			if(pat.getId()==id){
				patient=pat;
				ActionContext.getContext().getSession().put("Patient", patient);
				res=true;
			}
		return res;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Patient> getReportList() {
		return reportList;
	}
	public void setReportList(List<Patient> reportList) {
		this.reportList = reportList;
	}
}
