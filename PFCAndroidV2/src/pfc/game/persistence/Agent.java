package pfc.game.persistence;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.backends.android.AndroidApplication;

import pfc.game.domain.Goal;
import pfc.game.domain.Player;
import pfc.game.domain.Record;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class Agent extends AndroidApplication{
	
	private DBPlayer dbPlayerHelper;
	private DBGoal dbGoalHelper;
	private DBRecord dbRecordHelper;
	private DBPlayerGoal dbPGolHelper;
	private SQLiteDatabase db;
	
	public Agent(){
	}
	/**<-------------------------------------READ FROM DATABASE---------------------------------------->*/
	public int GetLastIdPlayer(Context context){
		int res=0;
		dbPlayerHelper=new DBPlayer(context);
		db=dbPlayerHelper.getReadableDatabase();
		String[] valores_recuperar = {"id"};
		Cursor c = db.query("Player", valores_recuperar, 
                null, null, null, null, null, null);
		if(c!=null){
			res=c.getCount();
		}
		
		return res;
	}
	public int GetLastIdRecord(Context context){
		int res=0;
		dbRecordHelper=new DBRecord(context);
		db=dbRecordHelper.getReadableDatabase();
		String[] valores_recuperar = {"id"};
		Cursor c = db.query("Record", valores_recuperar, 
                null, null, null, null, null, null);
		if(c!=null){
			res=c.getCount();
		}
		
		return res;
	}
	
	public List<Goal> ReadGoalListDB(Context context){
		List<Goal> goal_list=new ArrayList<Goal>();
		dbGoalHelper=new DBGoal(context);
		db=dbGoalHelper.getReadableDatabase();
		String[] valores_recuperar = {"id","name","idRew","descp"};
        
		Cursor c = db.query("Goals", valores_recuperar, 
                null, null, null, null, null, null);
		
		if(c!=null){
			c.moveToFirst();
			do {
				Goal goal = new Goal(c.getInt(0), c.getString(1),c.getInt(2),c.getString(3));
				goal_list.add(goal);
			} while (c.moveToNext());
			c.close();
		}
		db.close();
		dbGoalHelper.close();
		return goal_list;
		
	}
	
	public Goal ReadGoalDB(int id,Context context){
		Goal goal;
		dbGoalHelper=new DBGoal(context);
		db=dbGoalHelper.getReadableDatabase();
		String[] valores_recuperar={"id","name","idRew","descp"};
		
		Cursor c=db.query("Goals", valores_recuperar,"id="+id,null,null,null,null,null);
		
		if(c!=null){
			c.moveToFirst();
		}
		goal=new Goal(c.getInt(0),c.getString(1),c.getInt(2),c.getString(3));
		c.close();
		db.close();
		dbGoalHelper.close();
		return goal;
	}
	
	public List<Record> ReadRecordListDB(Context context){
		List<Record> record_list=new ArrayList<Record>();
		dbRecordHelper=new DBRecord(context);
		db=dbPlayerHelper.getReadableDatabase();
		String[] valores_recuperar = {"id","name","value","idPlayer"};
        
		Cursor c = db.query("Record", valores_recuperar, 
                null, null, null, null, null, null);
		
		if(c!=null){
			c.moveToFirst();
			do {
				Record record = new Record(c.getInt(0), c.getString(1),c.getInt(2),c.getInt(3));
				record_list.add(record);
			} while (c.moveToNext());
			c.close();
		}
		db.close();
		dbPlayerHelper.close();
		return record_list;
	}
	
	public Record ReadRecordDB(int id,Context context){
		Record rec;
		dbRecordHelper=new DBRecord(context);
		db=dbRecordHelper.getReadableDatabase();
		String[] valores_recuperar={"id","name","value","idPlayer"};
		
		Cursor c=db.query("Record", valores_recuperar,"id="+id,null,null,null,null,null);
		
		if(c!=null){
			c.moveToFirst();
		}
		rec=new Record(c.getInt(0),c.getString(1),c.getInt(2),c.getInt(3));
		c.close();
		db.close();
		dbPlayerHelper.close();
		return rec;
	}
	
	public List<Player> ReadPlayerListDB(Context context){
		List<Player> player_list=new ArrayList<Player>();
		dbPlayerHelper=new DBPlayer(context);
		db=dbPlayerHelper.getReadableDatabase();
		String[] valores_recuperar = {"id","name","surname","nick","password","psiCode"};
        
		Cursor c = db.query("Player", valores_recuperar, 
                null, null, null, null, null, null);
		
		if(c!=null){
			c.moveToFirst();
			do {
				Player player = new Player(c.getInt(0), c.getString(1),c.getString(2),
										   c.getString(3),c.getString(4),c.getString(5));
				player_list.add(player);
			} while (c.moveToNext());
			c.close();
		}
		db.close();
		dbPlayerHelper.close();
		return player_list;
	}
	
	public Player ReadPlayerlDB(int id,Context context){
		Player player;
		dbPlayerHelper=new DBPlayer(context);
		db=dbPlayerHelper.getReadableDatabase();
		String[] valores_recuperar={"id","name","surname","nick","password","psiCode"};
		
		Cursor c=db.query("Player", valores_recuperar,"id="+id,null,null,null,null,null);
		if(c!=null)c.moveToFirst();
		
		player=new Player(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5));
		c.close();
		db.close();
		dbPlayerHelper.close();
		return player;
	}
	
	public List<Goal> ReadGoalFromPlayer(int idPlayer,Context context){
		List<Goal> goal_listPlayer=new ArrayList<Goal>();
		/**I will get the list of goals from the current player*/
		dbPGolHelper=new DBPlayerGoal(context);
		db=dbPGolHelper.getReadableDatabase();
		String[] valores_recuperar={"idPlayer","idGoal"};
		Cursor c=db.query("PlayerGoal", valores_recuperar,"idPlayer="+idPlayer,null,null,null,null,null);
		if(c!=null) c.moveToFirst();
		if(c.getCount()>0){
			/**Now I look in the Goal table to recover the goal*/
			dbGoalHelper=new DBGoal(context);
			SQLiteDatabase db2=dbGoalHelper.getReadableDatabase();
			do{	
				String[] valores_recuperarGoal={"id","name","idRew","descp"};
				Cursor cGoal=db2.query("Goals", valores_recuperarGoal,"id="+c.getInt(1),null,null,null,null,null);
				if(cGoal!=null){
					cGoal.moveToFirst();
					Goal goal=new Goal(cGoal.getInt(0),cGoal.getString(1),cGoal.getInt(2),cGoal.getString(3));
					goal_listPlayer.add(goal);
				}
				cGoal.close();
			}while(c.moveToNext());
			db2.close();
			dbGoalHelper.close();
		}
		db.close();
		dbPGolHelper.close();
		c.close();
		return goal_listPlayer;
	}
	
	public List<Record> ReadRecFromPlayer(int idPlayer,Context context){
		List<Record> record_listPlayer=new ArrayList<Record>();
		dbRecordHelper=new DBRecord(context);
		SQLiteDatabase db=dbRecordHelper.getReadableDatabase();
		String[] valores_recuperarRecord={"id","name","value","idPlayer"};
		Cursor cRec=db.query("Record", valores_recuperarRecord,"idPlayer="+idPlayer,null,null,null,null,null);
		if(cRec!=null){
			cRec.moveToFirst();
			do{
				Record rec=new Record(cRec.getInt(0),cRec.getString(1),cRec.getInt(2),cRec.getInt(3));
				record_listPlayer.add(rec);
			}while(cRec.moveToNext());
		}
		cRec.close();
		db.close();
		dbRecordHelper.close();
		return record_listPlayer;
	}
	
	public int playerExist(String nick,String password,Context context){
		int id=0;
		dbPlayerHelper=new DBPlayer(context);
		db=dbPlayerHelper.getReadableDatabase();
		String[] valores_recuperar={"id,nick"};
		try{
			Cursor c=db.query("Player", valores_recuperar,"nick='"+nick+"' AND password='"+password+"'",
					null,null,null,null,null);
			if(c!=null){
				c.moveToFirst();
				id=c.getInt(0);
			}
			c.close();
		} catch(Exception e){
			
		}
		dbPlayerHelper.close();
		db.close();
		return id;
	}
	
	
	/**<--------------------------------------INSERT INTO DATABASE-------------------------------->*/
	public boolean insertPlayer(Player pla,Context context){
		boolean res=false;
		dbPlayerHelper=new DBPlayer(context);
		db = dbPlayerHelper.getWritableDatabase();
		Toast.makeText(context, "Base de datos de jugadores preparada", Toast.LENGTH_LONG).show();
		
		StringBuilder sb=new StringBuilder();
		sb.append("INSERT INTO Player (id,name,surname,nick,password,psiCode) VALUES ("
				+ pla.getId()+","
				+ "'"+pla.getName()+"',"
				+ "'"+pla.getSurname()+"',"
				+ "'"+pla.getNick()+"',"
				+ "'"+pla.getPassword()+"',"
				+ "'"+pla.getPsiCode()+"');");
		try{
			db.execSQL(sb.toString());
			res=true;
			
		} catch(Exception e){
			Toast.makeText(context, "Error al introducir el jugador.", Toast.LENGTH_LONG).show();
		}
		db.close();
		dbPlayerHelper.close();
		return res;
	}
	public boolean insertRecord(Record rec,Context context){
		boolean res=false;
		dbRecordHelper=new DBRecord(context);
		db = dbRecordHelper.getWritableDatabase();
		Toast.makeText(context, "Base de datos de records preparada", Toast.LENGTH_LONG).show();
		
		StringBuilder sb=new StringBuilder();
		sb.append("INSERT INTO Record (id,name,value) VALUES ("
				+ rec.getId()
				+ "'"+rec.getName()+"',"
				+rec.getValue()+");");
		try{
			db.execSQL(sb.toString());
			res=true;
			
		} catch(Exception e){
			Toast.makeText(context, "Error al introducir el record.", Toast.LENGTH_LONG).show();
		}
		db.close();
		dbRecordHelper.close();
		return res;
	}
	public boolean insertPlayerGoal(int idPlayer,int idGoal,Context context){
		boolean res=false;
		dbPGolHelper=new DBPlayerGoal(context);
		db=dbPGolHelper.getWritableDatabase();
		Toast.makeText(context, "Base de datos de Jugador-Logro preparada", Toast.LENGTH_LONG).show();
		
		StringBuilder sb=new StringBuilder();
		sb.append("INSERT INTO PlayerGoal(idPlayer,idGoal) VALUES ("
				+ idPlayer+","+idGoal+");");
		
		try{
			db.execSQL(sb.toString());
		} catch(Exception e){
			Toast.makeText(context, "Error al introducir el logro del jugador.", Toast.LENGTH_LONG).show();
		}
		db.close();
		dbPGolHelper.close();
		return res;
	}
	public boolean insertPlayerRecord(int idPlayer,int idRecord,int value,Context context){
		boolean res=false;
		dbRecordHelper=new DBRecord(context);
		db=dbRecordHelper.getWritableDatabase();
		ContentValues valores=new ContentValues();
		valores.put("id", idRecord);
		valores.put("value",value);
		valores.put("idPlayer", idPlayer);
		try{
			db.update("Record", valores, "id="+idRecord, null);
			res=true;
		}catch(Exception e){
			res=false;
		}
		db.close();
		dbRecordHelper.close();
		return res;
	}
	
	public void InsertRecordInitialData(int idPlayer,Context context){
		dbRecordHelper=new DBRecord(context);
		db=dbRecordHelper.getWritableDatabase();
		int lastIdRec=GetLastIdRecord(context);
    	if(db!=null){
    		lastIdRec++;
    		db.execSQL("INSERT INTO Record(id,name,value,idPlayer) VALUES("+lastIdRec+",'Mayor racha de aciertos',0,"+idPlayer+");");
    		lastIdRec++;
    		db.execSQL("INSERT INTO Record(id,name,value,idPlayer) VALUES("+lastIdRec+",'Mayor racha de fallos',0,"+idPlayer+");");
    		lastIdRec++;
    		db.execSQL("INSERT INTO Record(id,name,value,idPlayer) VALUES("+lastIdRec+",'Mayor racha de aciertos en bonus',0,"+idPlayer+");");
    		lastIdRec++;
    		db.execSQL("INSERT INTO Record(id,name,value,idPlayer) VALUES("+lastIdRec+",'Mayor racha de fallos en bonus',0,"+idPlayer+");");
    		db.close();
    	}
    	dbRecordHelper.close();
    }

	
	/**<-------------------------------------------UPDATE DATABASES-------------------------------------------->*/
	public boolean UpdateRecordDB(Record rec,Context context){
		boolean res;
		dbRecordHelper=new DBRecord(context);
		db=dbRecordHelper.getWritableDatabase();
		ContentValues valores=new ContentValues();
		valores.put("id", rec.getId());
		valores.put("name", rec.getName());
		valores.put("value",rec.getValue());
		valores.put("idPlayer", rec.getIdPlayer());
		try{
			db.update("Record", valores, "id="+rec.getId(), null);
			res=true;
		}catch(Exception e){
			res=false;
		}
		db.close();
		dbRecordHelper.close();
		return res;
	}
	
	public boolean UpdatePlayerDB(Player pla,Context context){
		boolean res;
		dbPlayerHelper=new DBPlayer(context);
		db=dbPlayerHelper.getWritableDatabase();
		ContentValues valores=new ContentValues();
		valores.put("id", pla.getId());
		valores.put("name", pla.getName());
		valores.put("surname",pla.getSurname());
		valores.put("nick", pla.getNick());
		valores.put("password",pla.getPassword());
		valores.put("psiCode", pla.getPsiCode());
		try{
			db.update("Record", valores, "id="+pla.getId(), null);
			res=true;
		}catch(Exception e){
			res=false;
		}
		db.close();
		dbPlayerHelper.close();
		return res;
	}
	
	public boolean UpdateGoalDB(Goal gol,Context context){
		boolean res;
		dbGoalHelper=new DBGoal(context);
		db=dbGoalHelper.getWritableDatabase();
		ContentValues valores=new ContentValues();
		valores.put("id", gol.getId());
		valores.put("name", gol.getName());
		valores.put("idRew",gol.getIdRew());
		valores.put("descp", gol.getDescription());
		try{
			db.update("Goal", valores, "id="+gol.getId(), null);
			res=true;
		}catch(Exception e){
			res=false;
		}
		db.close();
		dbGoalHelper.close();
		return res;
	}
	
	/**<----------------------------------------GETTERS AND SETTERS-------------------------------------------->*/

	public DBPlayer getDbPlayerHelper() {
		return dbPlayerHelper;
	}

	public void setDbPlayerHelper(DBPlayer dbPlayerHelper) {
		this.dbPlayerHelper = dbPlayerHelper;
	}

	public DBGoal getDbGoalHelper() {
		return dbGoalHelper;
	}

	public void setDbGoalHelper(DBGoal dbGoalHelper) {
		this.dbGoalHelper = dbGoalHelper;
	}

	public DBRecord getDbRecordHelper() {
		return dbRecordHelper;
	}

	public void setDbRecordHelper(DBRecord dbRecordHelper) {
		this.dbRecordHelper = dbRecordHelper;
	}

	public DBPlayerGoal getDbPGolHelper() {
		return dbPGolHelper;
	}

	public void setDbPGolHelper(DBPlayerGoal dbPGolHelper) {
		this.dbPGolHelper = dbPGolHelper;
	}
}
