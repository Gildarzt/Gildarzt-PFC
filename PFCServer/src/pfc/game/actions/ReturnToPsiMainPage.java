package pfc.game.actions;

import java.util.ArrayList;
import java.util.List;

import pfc.game.domain.Patient;
import pfc.game.domain.Psicologo;
import pfc.game.domain.Report;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ReturnToPsiMainPage  extends ActionSupport {
	private String password;
	private String id;
	private String name;
	private List<Patient> patientList;
	private List<Report> reportList;
	public String execute(){
		try {
			Psicologo ad = (Psicologo) ActionContext.getContext().getSession().get("psicologo");
			if (ad == null)
				throw new Exception("No está usted logueado");
			
			patientList=new ArrayList<Patient>();
			patientList=ad.getPatientList();
			password=ad.getPassword();
			id=ad.getId();
			name=ad.getName();
			getUnreadReports();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	private void getUnreadReports(){
		reportList=new ArrayList<Report>();
		for(Patient pat:patientList)
			for(Report rep:pat.getReportList())
				if(!rep.isReadState())
					reportList.add(rep);
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
