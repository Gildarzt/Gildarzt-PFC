package pfc.game.persistence;

import android.content.Context;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**ARP-06/01/2014: This class creates the database when the applications runs for first time*/
public class DBGoal extends SQLiteOpenHelper{

	private static int version = 1;
	private static String name = "Goals" ;
	private static CursorFactory factory = null;
	
    public DBGoal(Context contexto) {
        super(contexto, name, factory, version);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
    	StringBuilder sb = new StringBuilder();
    	/**Create the goal table*/
    	sb.append ("CREATE TABLE Goals ("
		   			+"id INTEGER PRIMARY KEY autoincrement,"
		   			+"name TEXT not null,"
		   			+"idRew INTEGER,"
		   			+"descp TEXT not null"
		   			+");");
        db.execSQL(sb.toString());
        
        InsertData(db);
    }
    /**In this method I will insert the initials data that I need*/
    private void InsertData(SQLiteDatabase db){
    	if(db!=null){
    		/**Goals table*/
    		db.execSQL("INSERT INTO Goals(id,name,idRew,descp) VALUES(1,'Nivel medio',1,'Desbloquear nivel medio');");
    		db.execSQL("INSERT INTO Goals(id,name,idRew,descp) VALUES(2,'Nivel difícil',2,'Desbloquear nivel difícil');");
    		db.execSQL("INSERT INTO Goals(id,name,idRew,descp) VALUES(3,'Aprendiz',3,'Lograr 5 aciertos consecutivos');");
    		db.execSQL("INSERT INTO Goals(id,name,idRew,descp) VALUES(4,'Profesional',4,'Lograr 10 aciertos consecutivos');");
    		db.execSQL("INSERT INTO Goals(id,name,idRew,descp) VALUES(5,'Maestro',5,'Lograr 15 aciertos consecutivos');");
    		db.execSQL("INSERT INTO Goals(id,name,idRew,descp) VALUES(6,'Pequeño fallo',6,'Fallar 5 veces seguidas');");
    		db.execSQL("INSERT INTO Goals(id,name,idRew,descp) VALUES(7,'Fallón',7,'Fallar 10 veces seguidas');");
    		db.execSQL("INSERT INTO Goals(id,name,idRew,descp) VALUES(8,'Desastroso',8,'Fallar 15 veces seguidas');");
    		db.execSQL("INSERT INTO Goals(id,name,idRew,descp) VALUES(9,'Estratega',9,'Activar el modo bonus tras 5 aciertos');");
    		db.execSQL("INSERT INTO Goals(id,name,idRew,descp) VALUES(10,'Buen estratega',10,'Activar el modo bonus tras 10 aciertos');");
    		db.execSQL("INSERT INTO Goals(id,name,idRew,descp) VALUES(11,'Napoleón',11,'Activar el modo bonus tras 15 aciertos');");
    		db.execSQL("INSERT INTO Goals(id,name,idRew,descp) VALUES(12,'Inmortal',12,'Conseguir el máximo número de vidas');");
    		db.execSQL("INSERT INTO Goals(id,name,idRew,descp) VALUES(13,'Aprendiz del bonus',13,'Conseguir 5 aciertos consecutivos en modo bonus');");
    		db.execSQL("INSERT INTO Goals(id,name,idRew,descp) VALUES(14,'Profesional del bonus',14,'Conseguir 10 aciertos consecutivos en modo bonus');");
    		db.execSQL("INSERT INTO Goals(id,name,idRew,descp) VALUES(15,'Maestro del bonus',15,'Conseguir 15 aciertos consecutivos en modo bonus');");
    		db.execSQL("INSERT INTO Goals(id,name,idRew,descp) VALUES(16,'Despistado',16,'Fallar 5 veces seguidas en modo bonus');");
    		db.execSQL("INSERT INTO Goals(id,name,idRew,descp) VALUES(17,'Muy despistado',17,'Fallar 10 veces seguidas en modo bonus');");
    		db.execSQL("INSERT INTO Goals(id,name,idRew,descp) VALUES(18,'Peor imposible',18,'Fallar 15 veces seguidas en modo bonus');");
    	
    		//db.close();
    	}
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
    	
    }
    
}
