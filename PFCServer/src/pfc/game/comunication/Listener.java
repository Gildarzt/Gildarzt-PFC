package pfc.game.comunication;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Enumeration;

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
	private Agent agent;
	private PatientDAO pDAO;
	private ReportDAO rDAO;
	private TryDAO tDAO;
	private int idPatient,idRep;
	private ArrayList<Token> patient;
	private ArrayList<Token> report;
	private ArrayList<Token> modeTryList;
	private ArrayList<Token> resultTryList;
	private ArrayList<Token> difficultTryList;
	
	public Listener(){
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response){
		Enumeration<?> aux=request.getParameterNames();
		patient=new ArrayList<Token>();
		report=new ArrayList<Token>();
		modeTryList=new ArrayList<Token>();
		resultTryList=new ArrayList<Token>();
		difficultTryList=new ArrayList<Token>();
		while(aux.hasMoreElements()){
			Object obj=aux.nextElement();
			String param=(String)obj;
			SelectTokenList(param,request.getParameter(param));
		}
		SortLists();
		Initialice();
		SaveReport();
	}
	private void SortLists(){
		Collections.sort(patient);
		Collections.sort(report);
		Collections.sort(modeTryList);
		Collections.sort(resultTryList);
		Collections.sort(difficultTryList);
	}
	private void CreatePatient(){
		Patient pat=new Patient(idPatient,patient.get(1).getValue(),patient.get(2).getValue(),
				patient.get(0).getValue(),null);
		Patient aux;
		if((aux=(Patient)pDAO.select(pat))==null)
			pDAO.insert(pat);
		else
			idPatient=aux.getId();
	}
	private void CreateReport(){
		int nSuccess=Integer.parseInt(report.get(2).getValue());
		int nFailure=Integer.parseInt(report.get(3).getValue());
		int successSpree=Integer.parseInt(report.get(4).getValue());
		int failSpree=Integer.parseInt(report.get(5).getValue());
		int successBonusSpree=Integer.parseInt(report.get(6).getValue());
		int failBonusSpree=Integer.parseInt(report.get(7).getValue());
		int initialDifficult=(int) Double.parseDouble(report.get(1).getValue());
		Calendar currenttime = Calendar.getInstance();
	    Date sqlDate = new Date((currenttime.getTime()).getTime());

		Report rep=new Report(idRep,sqlDate,nSuccess,nFailure,idPatient,successSpree
				,failSpree,successBonusSpree,failBonusSpree,initialDifficult,false,null);
		rDAO.insert(rep);
	}
	private void CreateListTry(){
		if(modeTryList.size()==resultTryList.size() && modeTryList.size()==difficultTryList.size())
			for(int i=0;i<modeTryList.size();i++)
				insertListTry(i);
		else{
			//Ha pasado algo en el envío.
		}
	}
	//Select one list or another to save the token.
	public void SelectTokenList(String param, String value){
		int pos;
		switch(Code.ReturnCode(param)){
		case 1:
			//Code
			patient.add(new Token(param,value,0));
			break;
		case 2:
			//Name
			patient.add(new Token(param,value,1));
			break;
		case 3:
			//Surname
			patient.add(new Token(param,value,2));
			break;
		case 4:
			//IdTest
			report.add(new Token(param,value,0));
			break;
		case 5:
			//InitialDifficult
			report.add(new Token(param,value,1));
			break;
		case 6:
			//nSuccess
			report.add(new Token(param,value,2));
			break;
		case 7:
			//nFails
			report.add(new Token(param,value,3));
			break;
		case 8:
			//successSpree
			report.add(new Token(param,value,4));
			break;
		case 9:
			//failSpree
			report.add(new Token(param,value,5));
			break;
		case 10:
			//sucessBonusSpree
			report.add(new Token(param,value,6));
			break;
		case 11:
			//failBonusSpree
			report.add(new Token(param,value,7));
			break;
		case 12:
			//mode
			pos=Character.digit(param.charAt(param.length()-1),10);
			modeTryList.add(new Token(param,value,pos));
			break;
		case 13:
			//result
			pos=Character.digit(param.charAt(param.length()-1),10);
			resultTryList.add(new Token(param,value,pos));
			break;
		case 14:
			//difficult
			pos=Character.digit(param.charAt(param.length()-1),10);
			difficultTryList.add(new Token(param,value,pos));
			break;
		}
	}
	private void SaveReport(){
		CreatePatient();
		CreateReport();
		CreateListTry();
		
	}
	private void insertListTry(int i){
		boolean bonus_ready=false;
		boolean bonus_on=false;
		boolean result=false;
		if(modeTryList.get(i).getValue().startsWith("BonusOn"))
			bonus_ready=true;
		else if(modeTryList.get(i).getValue().startsWith("BonusTime"))
			bonus_on=true;
		if(resultTryList.get(i).getValue().contains("true"))
			result=true;
		tDAO.insert(new Try(tDAO.GetNumberOfIds(),result,bonus_ready,bonus_on,idRep
				,Double.parseDouble(difficultTryList.get(i).getValue().trim())));
	}
	private void Initialice(){
		agent=new Agent().getMyInstance();
		try {
			pDAO=new PatientDAO(agent.getConnection(),patient.get(0).getValue());
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
