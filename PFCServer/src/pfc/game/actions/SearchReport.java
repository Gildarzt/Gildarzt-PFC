package pfc.game.actions;

import java.sql.Date;
import java.util.List;

import pfc.game.domain.Patient;
import pfc.game.domain.Psicologo;
import pfc.game.domain.Report;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class SearchReport extends ActionSupport{
	private Date date;
	private Report report;
	List<Report> reportList;
	public String execute() throws Exception {
		try {
			Psicologo ad = (Psicologo) ActionContext.getContext().getSession().get("psicologo");
			if (ad == null)
				throw new Exception("No está usted logueado");
			FindReport(ad);
			if(reportList.size()==1){
				report = reportList.get(0);
				return SUCCESS;
			}
			else 
				return "moreThanOne";
			
		} catch (Exception e) {
			return ERROR;
		}
	}
	public void FindReport(Psicologo ad){
		for(Patient pat:ad.getPatientList())
			for(Report rep:pat.getReportList())
				if(rep.getDate().equals(date))
					reportList.add(rep);
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Report getReport() {
		return report;
	}
	public void setReport(Report report) {
		this.report = report;
	}
	public List<Report> getReportList() {
		return reportList;
	}
	public void setReportList(List<Report> reportList) {
		this.reportList = reportList;
	}
}
