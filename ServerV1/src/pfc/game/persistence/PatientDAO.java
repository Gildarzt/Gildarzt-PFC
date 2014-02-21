package pfc.game.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pfc.game.domain.Patient;
import pfc.game.domain.Report;

public class PatientDAO extends DAO{
	private int idPsi;
	public PatientDAO(Connection conn,int idPsi){
		super(conn);
		this.idPsi=idPsi;
	}
	@Override
	protected PreparedStatement deleteSql(PersistentObj o){
		Patient s = (Patient) o;
		PreparedStatement st=null;
		try{
			st= connection
				.prepareStatement("DELETE FROM paciente WHERE idpaciente = ? and nombre = ? and codigo_psicologo = ? ");
			st.setInt(1, s.getId());
			st.setString(2, s.getName());
			st.setInt(3, s.getIdPsi());
		}catch(SQLException e){
			e.getStackTrace();
		}
		return st;
	}

	@Override
	protected PreparedStatement insertSql(PersistentObj o){
		Patient s = (Patient) o;
		PreparedStatement st =null;
		try{
			st= connection.prepareStatement("INSERT INTO paciente VALUES(?,?,?)");
			st.setInt(1, s.getId());
			st.setString(2, s.getName());
			st.setInt(3, s.getIdPsi());
		}catch(SQLException e){
			e.getStackTrace();
		}
		return st;
	}

	@Override
	protected PersistentObj selectObject(ResultSet r){
		Patient s = null;
		try {
			int id = r.getInt("idpaciente");
			String name = r.getString("nombre");
			int psiCode = r.getInt("codigo_psicologo");
			List<Report> patientList=selectAllReportsByPatient(id); 
			s = new Patient(id,name,psiCode,patientList);
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
			st= connection.prepareStatement("SELECT * FROM paciente WHERE idpaciente = ? and nombre = ? and codigo_psicologo = ?");
			st.setInt(1, ((Patient)o).getId());
			st.setString(2, ((Patient)o).getName());
			st.setInt(3, ((Patient)o).getIdPsi());
		}catch(SQLException e){
			e.getSQLState();
		}
		return st;
	}

	@Override
	protected PreparedStatement updateSql(PersistentObj o){
		// Actualiza los usos de un espia
		Patient s = (Patient) o;
		PreparedStatement st=null;
		try{
			st= connection.prepareStatement("UPDATE paciente SET nombre = ?,codigo_psicologo = ?"
					+ " WHERE idpaciente=? and nombre=?");
			st.setString(1, s.getName());
			st.setInt(2, s.getIdPsi());
			st.setInt(3,s.getId());
			st.setString(4, s.getName());
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
			st= connection.prepareStatement("Select * from paciente WHERE codigo_psicologo= ?");
			st.setInt(1, this.idPsi);
		}catch(SQLException e){
			e.getStackTrace();
		}
		return st;
	}
	private List<Report> selectAllReportsByPatient(int id) {
		ArrayList<Report> reportList = new ArrayList<Report>();
		ReportDAO sd = new ReportDAO(this.connection,id);
		ArrayList<PersistentObj> repList = sd.selectAll();
		for (Iterator<PersistentObj> iterador = repList.iterator(); iterador.hasNext();)
			reportList.add((Report)iterador.next());
		
		return reportList;
	}
}
