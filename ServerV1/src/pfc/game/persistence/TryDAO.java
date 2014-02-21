package pfc.game.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pfc.game.domain.Report;
import pfc.game.domain.Try;

public class TryDAO extends DAO{
	private int idReport;
	public TryDAO(Connection conn,int idReport){
		super(conn);
		this.idReport=idReport;
	}
	@Override
	protected PreparedStatement deleteSql(PersistentObj o){
		Try s = (Try) o;
		PreparedStatement st=null;
		try{
			st= connection
				.prepareStatement("DELETE FROM intento WHERE idintento = ?");
			st.setInt(1, s.getId());
		}catch(SQLException e){
			e.getStackTrace();
		}
		return st;
	}

	@Override
	protected PreparedStatement insertSql(PersistentObj o){
		Try s = (Try) o;
		PreparedStatement st =null;
		try{
			st= connection.prepareStatement("INSERT INTO report VALUES(?,?,?,?,?)");
			st.setInt(1, s.getId());
			st.setInt(2, s.isResult() ? 1 : 0);
			st.setInt(3, s.isBonus_ready() ? 1 : 0);
			st.setInt(4, s.isBonus_on() ? 1 : 0);
			st.setInt(5, s.getIdReport());
		}catch(SQLException e){
			e.getStackTrace();
		}
		return st;
	}

	@Override
	protected PersistentObj selectObject(ResultSet r){
		Try s = null;
		try {
			boolean nResult=false,nRBonus=false,nOnBonus=false;
			int id = r.getInt("idintento");
			if( r.getInt("resultado")==1)
				nResult=true;
			else if(r.getInt("resultado")==0)
				nResult=false;
			if( r.getInt("bonus_ready")==1)
				nRBonus=true;
			else if(r.getInt("bonus_ready")==0)
				nRBonus=false;
			if( r.getInt("bonus_on")==1)
				nOnBonus=true;
			else if(r.getInt("bonus_on")==0)
				nOnBonus=false;
			int idTest = r.getInt("id_test");
			s = new Try(id,nResult,nRBonus,nOnBonus,idTest);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (PersistentObj) s;
	}
	@Override
	protected PreparedStatement selectSql(PersistentObj o){
		PreparedStatement st =null;
		try{
			st= connection.prepareStatement("SELECT * FROM intento WHERE idintento = ?");
			st.setInt(1, ((Try)o).getId());
		}catch(SQLException e){
			e.getSQLState();
		}
		return st;
	}

	@Override
	protected PreparedStatement updateSql(PersistentObj o){
		Try s = (Try) o;
		PreparedStatement st=null;
		try{
			st= connection.prepareStatement("UPDATE intento SET resultado = ?,bonus_ready = ?,bonus_on = ?,"
					+ "id_test = ? WHERE idintento=?");
			st.setInt(1, s.isResult() ? 1 : 0);
			st.setInt(2, s.isBonus_ready() ? 1 : 0);
			st.setInt(3, s.isBonus_on() ? 1 : 0);
			st.setInt(4, s.getIdReport());
			st.setInt(5, s.getId());
		}catch(SQLException e){
			e.getStackTrace();
		}
		return st;
	}
	@Override
	protected PreparedStatement selectAllSql(){
		PreparedStatement st=null;
		try{
			st= connection.prepareStatement("Select * from intento WHERE id_test= ?");
			st.setInt(1, this.idReport);
		}catch(SQLException e){
			e.getStackTrace();
		}
		return st;
	}
}
