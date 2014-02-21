package pfc.game.persistence;

import android.content.Context;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DBRecord extends SQLiteOpenHelper{
	
	private static int version = 1;
	private static String name = "Record" ;
	private static CursorFactory factory = null;
	public DBRecord(Context contexto) {
		super(contexto, name, factory, version);
	}
	 
	@Override
	public void onCreate(SQLiteDatabase db) {
		StringBuilder sb = new StringBuilder();
	    	
	    /**Create the record table*/
	    sb.append("CREATE TABLE Record ("
	    			+ "id INTEGER PRIMARY KEY autoincrement, "
	    			+ "name TEXT not null, "
	    			+ "value INTEGER not null, "
	    			+ "idPlayer not null,"
	    			+ "FOREIGN KEY (idPlayer) REFERENCES Player (id)"
	    			+ ");");
	    db.execSQL(sb.toString());
	} 
	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
	}
}
