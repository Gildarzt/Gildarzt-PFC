package pfc.game.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Goal implements Parcelable{
	private int id;
	private String name;
	private int idRew;
	private String description;
	
	public Goal(int id, String name, int idRew, String description) {
		super();
		this.id = id;
		this.name = name;
		this.idRew = idRew;
		this.description = description;
	}
	/**<------------------------------------------------------------PARCELABLE METHODS----------------------------------------------------------->*/
	public Goal (Parcel in){
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
             dest.writeInt(idRew);
             dest.writeString(description);
     }	
	 private void readFromParcel(Parcel in) {
         id = in.readInt();
         name = in.readString();
         idRew=in.readInt();
         description=in.readString();
	 }
	 
	 public static final Parcelable.Creator<Goal> CREATOR = new Parcelable.Creator<Goal>() {
		 public Goal createFromParcel(Parcel in) {
                 return new Goal(in);
         }

         public Goal[] newArray(int size) {
                 return new Goal[size];
         }
	 };
	/**<---------------------------------------------------------------COMPARE TO---------------------------------------------------------------->*/
	 @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Goal other = (Goal) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (idRew != other.idRew)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + idRew;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
		
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

	public int getIdRew() {
		return idRew;
	}

	public void setIdRew(int idRew) {
		this.idRew = idRew;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
