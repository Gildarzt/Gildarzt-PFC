package pfc.game.persistence;

import java.io.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.os.Environment;
import pfc.game.domain.Records;
import pfc.game.domain.widgets.Click;

/**ARP-18/11/13: this class creates patient roles that store personal data, results of test
 * and send them to a web page.
 * 
 * @author Antonio
 *
 */
public class Patient {
	private int Id;
	private String name;
	private List<Result> ResultList;
	private int tries;
	private int bTimes;
	private int lifes;
	private int cTimes;
	private List<Integer>GoalList;
	private List<Records>RecordList;
	
	public Patient(String name,int Id,int tries){
		this.Id=Id;
		this.name=name;
		ResultList= new ArrayList<Result>();
		GoalList=new ArrayList<Integer>();
		RecordList=new ArrayList<Records>();
		initialiceRecords();
		this.tries=tries;
		bTimes=0;
		if(tries==0)
			lifes=3;
		else
			lifes=0;
		cTimes=0; /**This variable is used to count the number of rights patient gets on a
					 bonus combo. If he gets 5 he wins an extra life*/
	}
	
	public boolean saveResults(Click click){
		boolean res=false;
		FileWriter file=null;
		String name=getName()+getId()+".txt";
		File root=Environment.getExternalStorageDirectory();
		if(root.canWrite()){
			File dir=new File(root+"/pfc");
			File datafile=new File(dir,name);
			
			if(!dir.exists())
				dir.mkdir();
			if(!datafile.exists())
				try {
					datafile.createNewFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			try{
				file = new FileWriter(datafile,true);
				BufferedWriter out=new BufferedWriter(file);
 
				out.write("IdTest "+getResultList().get(0).getIdTest()+"\n");
				out.write("InitialDifficult "+getResultList().get(0).getDifficult()+"\n");
				out.write("nSuccess "+click.getTotalSuccessClick()+"\n");
				out.write("nFails "+click.getTotalFailClick()+"\n");
				out.write("SuccessSpree "+click.getSuccessNormalClick()+"\n");
				out.write("FailSpree "+click.getFailNormalClick()+"\n");
				out.write("SuccessBonusSpree "+click.getSuccessBonusClick()+"\n");
				out.write("FailBonusSpree "+click.getFailBonusClick()+"\n");
        	
				DecimalFormat df = new DecimalFormat("##.##");
				df.setRoundingMode(RoundingMode.DOWN);
				for (int i = 0; i <getResultList().size(); i++)
					if(getResultList().size()>i){
						out.write("Mode_"+i+" "+getResultList().get(i).getDescrp()+" ");
						out.write("Result_"+i+" "+getResultList().get(i).isRes()+" ");
						out.write("Difficult_"+i+" "+df.format(getResultList().get(i).getDifficult())+"\n");
					}
					else{
						out.write("Mode_"+i+" "+getResultList().get(i).getDescrp()+" ");
						out.write("Result_"+i+" "+false+" ");
						out.write("Difficult_"+i+" "+df.format(getResultList().get(i).getDifficult())+"\n");
					}
				res=true;
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (null != file)
						file.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
        return res;
	}

	public boolean saveGoalsAndRecords(){
		boolean res=false;
		FileWriter file=null;
		List<Integer> aux=new ArrayList<Integer>();
		
		String name=getId()+"GaR.txt";
		File root=Environment.getExternalStorageDirectory();
		
		if(root.canWrite()){
			File dir=new File(root+"/pfc");
			File datafile=new File(dir,name);
			
			if(!dir.exists())
				dir.mkdir();
			if(!datafile.exists())
				try {
					datafile.createNewFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			try{
				file=new FileWriter(datafile);
				BufferedWriter out=new BufferedWriter(file);
				out.write("Goal\n");
				for(int a=0;a<GoalList.size();a++){
					if(aux.size()<=0 || !aux.contains(GoalList.get(a))){
						aux.add(GoalList.get(a));
						out.write(GoalList.get(a)+"\n");
					}
				}
				out.write("Record\n");
				for(int a=0;a<RecordList.size();a++){
					out.write(RecordList.get(a).getId()+","+RecordList.get(a).getValue()+"\n");
				}
				out.close();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					if(null!=file)
						file.close();
				}catch(Exception e2){
					e2.printStackTrace();
				}
			}
		}
		return res;
	}
	private void initialiceRecords(){
		for(int i=1;i<5;i++)
			RecordList.add(new Records(i,0));
	}
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public List<Result> getResultList() {
		return ResultList;
	}

	public void setResultList(List<Result> resultList) {
		ResultList = resultList;
	}

	public int getTries() {
		return tries;
	}

	public void setTries(int tries) {
		this.tries = tries;
	}

	public int getbTimes() {
		return bTimes;
	}

	public void setbTimes(int bTimes) {
		this.bTimes = bTimes;
	}

	public int getLifes() {
		return lifes;
	}

	public void setLifes(int lifes) {
		this.lifes = lifes;
	}

	public int getcTimes() {
		return cTimes;
	}

	public void setcTimes(int cTimes) {
		this.cTimes = cTimes;
	}

	public List<Integer> getGoalList() {
		return GoalList;
	}

	public void setGoalList(List<Integer> goalList) {
		GoalList = goalList;
	}

	public List<Records> getRecordList() {
		return RecordList;
	}

	public void setRecordList(List<Records> recordList) {
		RecordList = recordList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
