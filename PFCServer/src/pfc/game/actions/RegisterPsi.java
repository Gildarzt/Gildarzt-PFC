package pfc.game.actions;

import java.sql.SQLException;

import pfc.game.domain.Psicologo;
import pfc.game.persistence.Agent;
import pfc.game.persistence.PersistentObj;
import pfc.game.persistence.PsicologoDAO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class RegisterPsi extends ActionSupport {
	private String id,name, password;	
	public String execute() throws Exception {
		Psicologo s=new Psicologo(id,name,password);
		ActionContext.getContext().getSession().put("psicologo", s);
		if(CreatePsiAccount(s))
			return SUCCESS;
		else
			return ERROR;
	}
	private boolean CreatePsiAccount(Psicologo s){
		boolean res=false;
		try {
			PsicologoDAO psiDAO=new PsicologoDAO(new Agent().getMyInstance().getConnection());
			psiDAO.insert((PersistentObj) s);
			res=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
