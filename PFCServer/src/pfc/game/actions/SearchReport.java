package pfc.game.actions;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import pfc.game.domain.Patient;
import pfc.game.domain.Psicologo;
import pfc.game.domain.Report;
import pfc.game.domain.Try;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class SearchReport extends ActionSupport{
	private Date date;
	private Report report;
	List<Report> reportList;
	private List<Try> tryList;
	public String execute() throws Exception {
		try {
			Psicologo ad = (Psicologo) ActionContext.getContext().getSession().get("psicologo");
			if (ad == null)
				throw new Exception("No está usted logueado");
			reportList=new ArrayList<Report>();
			FindReport();
			if(reportList.size()==1){
				report = reportList.get(0);
				setTryList(report.getListTries());
				return SUCCESS;
			}
			else 
				return "moreThanOne";
			
		} catch (Exception e) {
			return ERROR;
		}
	}
	public void FindReport(){
		Patient pat=(Patient) ActionContext.getContext().getSession().get("Patient");
		if(pat!=null){
			for(Report rep:pat.getReportList())
				if(rep.getDate().equals(date))
					reportList.add(rep);
		}
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
	public List<Try> getTryList() {
		return tryList;
	}
	public void setTryList(List<Try> tryList) {
		this.tryList = tryList;
	}
}
