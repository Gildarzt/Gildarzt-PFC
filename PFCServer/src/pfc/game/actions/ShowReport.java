package pfc.game.actions;

import java.util.List;

import pfc.game.domain.Patient;
import pfc.game.domain.Psicologo;
import pfc.game.domain.Report;
import pfc.game.domain.Try;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ShowReport extends ActionSupport{
	private Report report;
	private int id;
	private List<Try> tryList;
	public String execute() throws Exception {
		try {
			Psicologo ad = (Psicologo) ActionContext.getContext().getSession().get("psicologo");
			if (ad == null)
				throw new Exception("No está usted logueado");
			if(FindReport(ad))
				return SUCCESS;
			else 
				return "ERROR";
			
		} catch (Exception e) {
			return ERROR;
		}
	}
	public boolean FindReport(Psicologo ad){
		boolean res=false;
		for(Patient pat:ad.getPatientList())
			for(Report rep:pat.getReportList())
				if(rep.getId()==id){
					report=rep;
					res=true;
					tryList=report.getListTries();
				}
		return res;
	}
	public Report getReport() {
		return report;
	}
	public void setReport(Report report) {
		this.report = report;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Try> getTryList() {
		return tryList;
	}
	public void setTryList(List<Try> tryList) {
		this.tryList = tryList;
	}
}
