package pfc.game.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import pfc.game.persistence.PersistentObj;

@SuppressWarnings("serial")
public class Report implements Serializable, PersistentObj{
	private int id;
	private Date date;
	private int nSuccess;
	private int nFailure;
	private int idPatient;
	private int successSpree;
	private int failSpree;
	private int successBonusSpree;
	private int failBonusSpree;
	private int initialDifficult;
	private List<Try> listTries;
	public Report(int id, Date date, int nSuccess, int nFailure, int idPatient,
			int successSpree, int failSpree, int successBonusSpree,
			int failBonusSpree, int initialDifficult, List<Try> listTries) {
		super();
		this.id = id;
		this.date = date;
		this.nSuccess = nSuccess;
		this.nFailure = nFailure;
		this.idPatient = idPatient;
		this.successSpree = successSpree;
		this.failSpree = failSpree;
		this.successBonusSpree = successBonusSpree;
		this.failBonusSpree = failBonusSpree;
		this.initialDifficult = initialDifficult;
		this.listTries = listTries;
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
	public int getSuccessBonusSpree() {
		return successBonusSpree;
	}
	public void setSuccessBonusSpree(int successBonusSpree) {
		this.successBonusSpree = successBonusSpree;
	}
	public int getFailBonusSpree() {
		return failBonusSpree;
	}
	public void setFailBonusSpree(int failBonusSpree) {
		this.failBonusSpree = failBonusSpree;
	}
	public int getInitialDifficult() {
		return initialDifficult;
	}
	public void setInitialDifficult(int initialDifficult) {
		this.initialDifficult = initialDifficult;
	}
	public List<Try> getListTries() {
		return listTries;
	}
	public void setListTries(List<Try> listTries) {
		this.listTries = listTries;
	}
}
