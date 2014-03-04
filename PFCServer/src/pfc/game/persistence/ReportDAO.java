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

public class ReportDAO extends DAO{
	private int idPatient;
	public ReportDAO(Connection conn,int idPatient){
		super(conn);
		this.idPatient=idPatient;
	}
	@Override
	protected PreparedStatement deleteSql(PersistentObj o){
		Report s = (Report) o;
		PreparedStatement st=null;
		try{
			st= connection
				.prepareStatement("DELETE FROM informe WHERE idinforme = ?");
			st.setInt(1, s.getId());
		}catch(SQLException e){
			e.getStackTrace();
		}
		return st;
	}

	@Override
	protected PreparedStatement insertSql(PersistentObj o){
		Report s = (Report) o;
		PreparedStatement st =null;
		try{
			st= connection.prepareStatement("INSERT INTO report VALUES(?,?,?,?,?,?,?,?)");
			st.setInt(1, s.getId());
			st.setDate(2, s.getDate());
			st.setInt(3, s.getnSuccess());
			st.setInt(4, s.getnFailure());
			st.setInt(5, s.getnBonus());
			st.setInt(6, idPatient);
			st.setInt(7, s.isState() ? 1 : 0);
			st.setInt(8, s.getSuccessSpree());
			st.setInt(9, s.getFailSpree());
		}catch(SQLException e){
			e.getStackTrace();
		}
		return st;
	}

	@Override
	protected PersistentObj selectObject(ResultSet r){
		Report s = null;
		try {
			int id = r.getInt("idinforme");
			Date date = r.getDate("fecha");
			int nSuccess = r.getInt("numero_aciertos");
			int nFailures = r.getInt("numero_fallos");
			int nBonus = r.getInt("numero_bonus");
			int idPatient = r.getInt("id_paciente");
			List<Try> TryList=selectAllTriesByReport(id); 
			boolean state=false;
			if(r.getInt("state")==1)
				state=true;
			int successSpree=r.getInt("successspree");
			int failSpree=r.getInt("failspree");
			s = new Report(id,date,nSuccess,nFailures,nBonus,idPatient,successSpree,failSpree,TryList,state);
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
			st= connection.prepareStatement("SELECT * FROM informe WHERE idinforme = ?");
			st.setInt(1, ((Report)o).getId());
		}catch(SQLException e){
			e.getSQLState();
		}
		return st;
	}

	@Override
	protected PreparedStatement updateSql(PersistentObj o){
		Report s = (Report) o;
		PreparedStatement st=null;
		try{
			st= connection.prepareStatement("UPDATE informe SET fecha = ?,numero_aciertos = ?,numero_fallos = ?,"
					+ "numero_bonus = ?,id_paciente = ?,state = ?, successspree = ?,failspree = ? WHERE idinforme=?");
			st.setDate(1,s.getDate());
			st.setInt(2, s.getnSuccess());
			st.setInt(3, s.getnFailure());
			st.setInt(4, s.getnBonus());
			st.setInt(5, s.getIdPatient());
			st.setInt(6, s.getId());
			st.setInt(7, s.isState() ? 1 : 0);
			st.setInt(8, s.getSuccessSpree());
			st.setInt(9, s.getFailSpree());
		}catch(SQLException e){
			e.getStackTrace();
		}
		return st;
	}
	@Override
	protected PreparedStatement selectAllSql(){
		// Select todos los espias de un usuario
		PreparedStatement st=null;
		try{
			st= connection.prepareStatement("Select * from informe WHERE id_paciente= ?");
			st.setInt(1, this.idPatient);
		}catch(SQLException e){
			e.getStackTrace();
		}
		return st;
	}
	private List<Try> selectAllTriesByReport(int id) {
		ArrayList<Try> tryList = new ArrayList<Try>();
		TryDAO sd = new TryDAO(this.connection,id);
		ArrayList<PersistentObj> auxList = sd.selectAll();
		for (Iterator<PersistentObj> iterador = auxList.iterator(); iterador.hasNext();)
			tryList.add((Try)iterador.next());
		
		return tryList;
	}
}
