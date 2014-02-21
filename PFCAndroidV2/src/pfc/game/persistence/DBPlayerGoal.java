package pfc.game.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DBPlayerGoal extends SQLiteOpenHelper{
	
	private static int version = 1;
	private static String name = "PlayerGoal" ;
	private static CursorFactory factory = null;
	
	public DBPlayerGoal(Context contexto) {
        super(contexto, name, factory, version);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
    	StringBuilder sb = new StringBuilder();
        /**Auxiliary tables that I need to join two tables*/
        sb.append("CREATE TABLE PlayerGoal ("
    				+ "idPlayer INTEGER,"
    				+ "idGoal INTEGER,"
    				+ "FOREIGN KEY (idPlayer) REFERENCES Player (id),"
    				+ "FOREIGN KEY (idGoal) REFERENCES Goals (id),"
    				+ "PRIMARY KEY (idPlayer,idGoal)"
    				+ ");");
        db.execSQL(sb.toString());
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
    	
    }
    
}
