package pfc.game.actions;

import java.sql.SQLException;

import pfc.game.domain.Psicologo;
import pfc.game.persistence.Agent;
import pfc.game.persistence.PersistentObj;
import pfc.game.persistence.PsicologoDAO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Login extends ActionSupport{
	private String password;
	private String idPsi;

	public String execute(){
		Psicologo s = checkPsiAccount();
		if(s!=null){
			ActionContext.getContext().getSession().put("psicologo", s);
			return SUCCESS;
		}else
			return ERROR;
	}
	private Psicologo checkPsiAccount(){
		try {
			PsicologoDAO psiDAO=new PsicologoDAO(new Agent().getMyInstance().getConnection());
			return psiDAO.checkPsiAccount((PersistentObj) new Psicologo(idPsi,password));
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
	public String getIdPsi() {
		return idPsi;
	}
	public void setIdPsi(String idPsi) {
		this.idPsi = idPsi;
	}
}
