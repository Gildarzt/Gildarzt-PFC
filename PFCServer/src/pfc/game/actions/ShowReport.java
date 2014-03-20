package pfc.game.actions;

import java.sql.SQLException;
import java.util.List;

import pfc.game.domain.Patient;
import pfc.game.domain.Psicologo;
import pfc.game.domain.Report;
import pfc.game.domain.Try;
import pfc.game.persistence.Agent;
import pfc.game.persistence.ReportDAO;

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
				return ERROR;
			
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
					/*Now I chance the state field on database to indicate that this report
					has been read*/
					try {
						ReportDAO aux=new ReportDAO(new Agent().getMyInstance().getConnection(),report.getIdPatient());
						aux.update(new Report(report.getId(),report.getDate(),report.getnSuccess()
								,report.getnFailure(),report.getIdPatient(),report.getSuccessSpree()
								,report.getFailSpree(),report.getSuccessBonusSpree(),report.getFailBonusSpree()
								,report.getInitialDifficult(),true,null));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
