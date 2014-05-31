package pfc.game.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Arcade implements Parcelable{
	private int dificult;
	private int tries;
	private boolean incSpeed;
	private boolean bonus;
	private boolean sound;
	
	public Arcade(int dificult, int tries, boolean incSpeed, boolean bonus,boolean sound) {
		this.dificult = dificult;
		this.tries = tries;
		this.incSpeed = incSpeed;
		this.bonus = bonus;
		this.sound=sound;
	}
	/**<------------------------------------------------------------PARCELABLE METHODS----------------------------------------------------------->*/
	public Arcade (Parcel in){
		readFromParcel(in);
	}
	 @Override
     public int describeContents() {
             return 0;
     }
	 @Override
     public void writeToParcel(Parcel dest, int flags) {
		 dest.writeInt(dificult);
         dest.writeInt(tries);
         dest.writeBooleanArray(new boolean[]{incSpeed,bonus,sound});;
     }	
	
	 private void readFromParcel(Parcel in) {
         dificult = in.readInt();
         tries = in.readInt();
         boolean aux[]=new boolean[3];
         in.readBooleanArray(aux);
         incSpeed=aux[0];
         bonus=aux[1];
         sound=aux[2];
	 }
	 
	 public static final Parcelable.Creator<Arcade> CREATOR =new Parcelable.Creator<Arcade>() {
		 public Arcade createFromParcel(Parcel in){
			 return new Arcade(in);
		 }
		 public Arcade[] newArray(int size){
			 return new Arcade[size];
		 }
	 };
	 
	/**<------------------------------------------------------------GETTERS AND SETTERS---------------------------------------------------------->*/
	public int getDificult() {
		return dificult;
	}

	public void setDificult(int dificult) {
		this.dificult = dificult;
	}

	public int getTries() {
		return tries;
	}

	public void setTries(int tries) {
		this.tries = tries;
	}

	public boolean isIncSpeed() {
		return incSpeed;
	}

	public void setIncSpeed(boolean incSpeed) {
		this.incSpeed = incSpeed;
	}

	public boolean isBonus() {
		return bonus;
	}

	public void setBonus(boolean bonus) {
		this.bonus = bonus;
	}
	public boolean isSound() {
		return sound;
	}
	public void setSound(boolean sound) {
		this.sound = sound;
	}
}
