package pfc.game.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class CloseSession extends ActionSupport{
	public String execute(){
		try {
			ActionContext.getContext().getSession().remove("psicologo");
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

}
