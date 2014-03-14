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
			st= connection.prepareStatement("INSERT INTO informe VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			st.setInt(1, s.getId());
			st.setDate(2, (Date) s.getDate());
			st.setInt(3, s.getnSuccess());
			st.setInt(4, s.getnFailure());
			st.setInt(5, s.getIdPatient());
			st.setInt(6, s.getSuccessSpree());
			st.setInt(7, s.getFailSpree());
			st.setInt(8, s.getSuccessBonusSpree());
			st.setInt(9, s.getFailBonusSpree());
			st.setInt(10, s.getInitialDifficult());
			st.setInt(11, s.isReadState() ? 1:0);
		}catch(SQLException e){
			e.getStackTrace();
		}
		return st;
	}

	@Override
	protected PersistentObj selectObject(ResultSet r){
		Report s = null;
		try {
			int id=r.getInt("idinforme");
			boolean readState=false;
			if(r.getInt("readstate")==1) readState=true;
			s=new Report(id,r.getDate("fecha"),r.getInt("numero_aciertos"),
					r.getInt("numero_fallos"),r.getInt("id_paciente"),r.getInt("successspree"),
					r.getInt("failspree"),r.getInt("successbonusspree"),r.getInt("failbonusspree"),
					r.getInt("initialdifficult"),readState,selectAllTriesByReport(id));
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
					+ "id_paciente = ?,successspree = ?,failspree = ?,successbonusspree = ?,failbonusspree = ? "
					+ "initialdificult = ?,readstate = ? WHERE idinforme=?");
			st.setDate(1, (Date) s.getDate());
			st.setInt(2, s.getnSuccess());
			st.setInt(3, s.getnFailure());
			st.setInt(4, idPatient);
			st.setInt(5, s.getSuccessSpree());
			st.setInt(6, s.getFailSpree());
			st.setInt(7, s.getSuccessBonusSpree());
			st.setInt(8, s.getFailBonusSpree());
			st.setInt(9, s.getInitialDifficult());
			st.setInt(10, s.isReadState() ? 1:0);
			st.setInt(11, s.getId());
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
	public int GetNumberOfIds(){
		int res=0;
		PreparedStatement st=null;
		ResultSet aux=null;
		try {
			st=connection.prepareStatement("Select idinforme from informe");
			aux=st.executeQuery();
			if(aux!=null){
				aux.last();
				res=aux.getInt("idinforme");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res+1;
	}
}
