package pfc.game.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DBPlayer extends SQLiteOpenHelper {
	private static int version = 1;
	private static String name = "Player" ;
	private static CursorFactory factory = null;
	
	public DBPlayer(Context contexto) {
	        super(contexto, name, factory, version);
	}
	 
	@Override
	public void onCreate(SQLiteDatabase db) {
		StringBuilder sb = new StringBuilder();
	    /**Create the player table*/
	    sb.append("CREATE TABLE Player ("
	    			+ "id INTEGER PRIMARY KEY autoincrement, "
	    			+ "name TEXT not null, "
	    			+ "surname TEXT not null,"
	    			+ "nick TEXT not null, "
	    			+ "password TEXT not null, "
	    			+ "psiCode TEXT not null "
	    			+ ");");
	    db.execSQL(sb.toString());
	        
	}
	 
	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
	    	
	}

}
