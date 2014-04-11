package pfc.game.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Record implements Parcelable{
	private int id;
	private String name;
	private int value;
	private int idPlayer;
	
	public Record(int id, String name, int value,int idPlayer) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
		this.idPlayer=idPlayer;
	}

	/**<------------------------------------------------------------PARCELABLE METHODS----------------------------------------------------------->*/
	public Record (Parcel in){
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
             dest.writeInt(value);
             dest.writeInt(idPlayer);
     }	
	
	 private void readFromParcel(Parcel in) {
         id = in.readInt();
         name = in.readString();
         value= in.readInt();
         idPlayer=in.readInt();
	 }
	 
	 public static final Parcelable.Creator<Record> CREATOR = new Parcelable.Creator<Record>() {
		 public Record createFromParcel(Parcel in) {
                 return new Record(in);
         }

         public Record[] newArray(int size) {
                 return new Record[size];
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

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getIdPlayer() {
		return idPlayer;
	}

	public void setIdPlayer(int idPlayer) {
		this.idPlayer = idPlayer;
	}
}
