package pfc.game.domain;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import pfc.game.persistence.Agent;
import pfc.game.persistence.FileResult;

public class Player implements Parcelable{
	private int id;
	private String name;
	private String surname;
	private String nick;
	private String password;
	private String psiCode;
	private List<Goal> goalList;
	private List<Record> recList;
	private Agent agent;
	
	public Player(int id, String name, String surname, String nick,
			String password, String psiCode) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.nick = nick;
		this.password = password;
		this.psiCode = psiCode;
		goalList=new ArrayList<Goal>();
		recList=new ArrayList<Record>();
	}
	/**<------------------------------------------------------------PARCELABLE METHODS----------------------------------------------------------->*/
	public Player (Parcel in){
		
		goalList=new ArrayList<Goal>();
		recList=new ArrayList<Record>();
		readFromParcel(in);
		
	}
	 @Override
     public int describeContents() {
             return 0;
     }
	 @Override
     public void writeToParcel(Parcel dest, int flags) {
             dest.writeInt(id);
             dest.writeString(name);
             dest.writeString(surname);
             dest.writeString(nick);
             dest.writeString(password);
             dest.writeString(psiCode);
             dest.writeList(goalList);
             dest.writeList(recList);
     }	
	
	 private void readFromParcel(Parcel in) {
         id = in.readInt();
         name = in.readString();
         surname=in.readString();
         nick=in.readString();
         password=in.readString();
         psiCode=in.readString();
         in.readList(goalList, Goal.class.getClassLoader());
         in.readList(recList, Record.class.getClassLoader());
	 }
	 
	 public static final Parcelable.Creator<Player> CREATOR =new Parcelable.Creator<Player>() {
		 public Player createFromParcel(Parcel in){
			 return new Player(in);
		 }
		 public Player[] newArray(int size){
			 return new Player[size];
		 }
	 };
	 
	/**<------------------------------------------------------------GETTERS AND SETTERS---------------------------------------------------------->*/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPsiCode() {
		return psiCode;
	}

	public void setPsiCode(String psiCode) {
		this.psiCode = psiCode;
	}

	public List<Goal> getGoalList() {
		return goalList;
	}

	public void setGoalList(List<Goal> goalList) {
		this.goalList = goalList;
	}

	public List<Record> getRecList() {
		return recList;
	}

	public void setRecList(List<Record> recList) {
		this.recList = recList;
	}
	public boolean playerDAO(Context context){
		return agent.insertPlayer(this,context);
	}
	public boolean createResultFile(Activity activity){
		FileResult aux=new FileResult(activity,this);
		return aux.createFile();
	}
	public void sendReport(Activity activity){
		FileResult aux=new FileResult(activity,this);
		aux.SendAllReports();
	}
}
