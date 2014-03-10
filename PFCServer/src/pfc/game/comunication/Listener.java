package pfc.game.comunication;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pfc.game.domain.Patient;
import pfc.game.domain.Report;
import pfc.game.domain.Try;
import pfc.game.persistence.Agent;
import pfc.game.persistence.PatientDAO;
import pfc.game.persistence.ReportDAO;
import pfc.game.persistence.TryDAO;

/**ARP 05/03/2014: This class receives the JSON from the client and parse the data properly*/ 
@SuppressWarnings("serial")
public class Listener extends HttpServlet{
	private String code,name,surname,mode;
	private int idTest,initialDifficult,nSuccess,nFails,successSpree,failSpree,successBonusSpree,
				failBonusSpree,difficult;
	private boolean result;
	private List<Try> tryList;
	private Agent agent;
	private PatientDAO pDAO;
	private ReportDAO rDAO;
	private TryDAO tDAO;
	private int idPatient,idRep;
	private ArrayList<Token> nameValuePairs;
	
	public Listener(){
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response){
		Enumeration<?> aux=request.getParameterNames();
		nameValuePairs=new ArrayList<Token>();
		while(aux.hasMoreElements()){
			Object obj=aux.nextElement();
			String param=(String)obj;
			nameValuePairs.add(new Token(param,request.getParameter(param)));
		}
		for(Token token:nameValuePairs){
			ParseData(token);
		}
		
	}
	public void ParseData(Token token){
		String value=token.getValue();
		switch(Code.ReturnCode(token.getCode())){
		case 1:
			//Code
			code=value;
			Initialice();
			break;
		case 2:
			//Name
			name=value;
			break;
		case 3:
			//Surname
			surname=value;
			break;
		case 4:
			idTest=Integer.parseInt(value);
			break;
		case 5:
			initialDifficult=Integer.parseInt(value);
			break;
		case 6:
			nSuccess=Integer.parseInt(value);
			break;
		case 7:
			nFails=Integer.parseInt(value);
			break;
		case 8:
			successSpree=Integer.parseInt(value);
			break;
		case 9:
			failSpree=Integer.parseInt(value);
			break;
		case 10:
			successBonusSpree=Integer.parseInt(value);
			break;
		case 11:
			failBonusSpree=Integer.parseInt(value);
			break;
		case 12:
			mode=value;
			break;
		case 13:
			if(value.equals("true"))
				result=true;
			else
				result =false;
			break;
		case 14:
			difficult=Integer.parseInt(value);
			insertListTry();
			break;
		}
	}
	private void SaveReport(){
		Report rep=null;
		Patient pat=new Patient(idPatient,name,code,surname,null);
		if(pDAO.select(pat)==null)
			pDAO.insert(pat);
		rep=new Report(idRep,new Date(),nSuccess,nFails,idPatient,successSpree,failSpree,
				successBonusSpree,failBonusSpree,initialDifficult,null);
		rDAO.insert(rep);
		for(Try mTry:tryList)
			tDAO.insert(mTry);
	}
	private void insertListTry(){
		boolean bonus_ready=false;
		boolean bonus_on=false;
		if(mode.startsWith("BonusOn"))
			bonus_ready=true;
		else if(mode.startsWith("BonusTime"))
			bonus_on=true;
		tryList.add(new Try(0,result,bonus_ready,bonus_on,idRep,difficult));
	}
	private void Initialice(){
		agent=new Agent().getMyInstance();
		try {
			pDAO=new PatientDAO(agent.getConnection(),code);
			idPatient=pDAO.GetNumberOfIds();
			rDAO=new ReportDAO(agent.getConnection(),idPatient);
			idRep=rDAO.GetNumberOfIds();
			tDAO=new TryDAO(agent.getConnection(),idRep);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
