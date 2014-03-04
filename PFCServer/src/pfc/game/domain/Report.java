package pfc.game.domain;

import java.sql.Date;
import java.util.List;

public class Report {
	private int id;
	private Date date;
	private int nSuccess;
	private int nFailure;
	private int nBonus;
	private int idPatient;
	private int successSpree;
	private int failSpree;
	private List<Try> listTries;
	private boolean state;
	
	public Report(int id, Date date, int nSuccess, int nFailure, int nBonus,
			int idPatient, int successSpree, int failSpree,
			List<Try> listTries, boolean state) {
		super();
		this.id = id;
		this.date = date;
		this.nSuccess = nSuccess;
		this.nFailure = nFailure;
		this.nBonus = nBonus;
		this.idPatient = idPatient;
		this.successSpree = successSpree;
		this.failSpree = failSpree;
		this.listTries = listTries;
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getnSuccess() {
		return nSuccess;
	}

	public void setnSuccess(int nSuccess) {
		this.nSuccess = nSuccess;
	}

	public int getnFailure() {
		return nFailure;
	}

	public void setnFailure(int nFailure) {
		this.nFailure = nFailure;
	}

	public int getnBonus() {
		return nBonus;
	}

	public void setnBonus(int nBonus) {
		this.nBonus = nBonus;
	}

	public int getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
	}

	public int getSuccessSpree() {
		return successSpree;
	}

	public void setSuccessSpree(int successSpree) {
		this.successSpree = successSpree;
	}

	public int getFailSpree() {
		return failSpree;
	}

	public void setFailSpree(int failSpree) {
		this.failSpree = failSpree;
	}

	public List<Try> getListTries() {
		return listTries;
	}

	public void setListTries(List<Try> listTries) {
		this.listTries = listTries;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
}
