package pfc.game.actions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pfc.game.domain.Patient;
import pfc.game.domain.Psicologo;
import pfc.game.domain.Report;
import pfc.game.persistence.Agent;
import pfc.game.persistence.PersistentObj;
import pfc.game.persistence.PsicologoDAO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Login extends ActionSupport{
	private String password;
	private String id;
	private String name;
	private List<Patient> patientList;
	private Psicologo psi;
	private List<Report> reportList;
	public String execute(){
		psi = checkPsiAccount();
		if(psi!=null){
			ActionContext.getContext().getSession().put("psicologo", psi);
			patientList=new ArrayList<Patient>();
			patientList=psi.getPatientList();
			password=psi.getPassword();
			id=psi.getId();
			name=psi.getName();
			getUnreadReports();
			return SUCCESS;
		}else
			return ERROR;
	}
	private void getUnreadReports(){
		reportList=new ArrayList<Report>();
		for(Patient pat:patientList)
			for(Report rep:pat.getReportList())
				if(!rep.isReadState())
					reportList.add(rep);
	}
	private Psicologo checkPsiAccount(){
		try {
			PsicologoDAO psiDAO=new PsicologoDAO(new Agent().getMyInstance().getConnection());
			return psiDAO.checkPsiAccount((PersistentObj) new Psicologo(id,password));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
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
	public Psicologo getPsi() {
		return psi;
	}
	public void setPsi(Psicologo psi) {
		this.psi = psi;
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
	public List<Report> getReportList() {
		return reportList;
	}
	public void setReportList(List<Report> reportList) {
		this.reportList = reportList;
	}
}
