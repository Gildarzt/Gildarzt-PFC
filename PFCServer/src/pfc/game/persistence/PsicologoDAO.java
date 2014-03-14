package pfc.game.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pfc.game.domain.Patient;
import pfc.game.domain.Psicologo;

public class PsicologoDAO extends DAO{
	public PsicologoDAO(Connection conn){
		super(conn);
	}
	@Override
	protected PreparedStatement deleteSql(PersistentObj o){
		Psicologo p = (Psicologo) o;
		PreparedStatement st=null;
		try {
			st = connection.prepareStatement("DELETE FROM psicologo WHERE idpsicologo=?");
			st.setString(1,p.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return st;
	}

	@Override
	public PreparedStatement insertSql(PersistentObj o){
		Psicologo p = (Psicologo) o;
		PreparedStatement st=null;
		try{
			st= connection.prepareStatement("INSERT INTO psicologo VALUES(?,?,?)");
			st.setString(1,p.getId());
			st.setString(2, p.getName());
			st.setString(3, p.getPassword());
		}catch (SQLException e){
			e.printStackTrace();
		}
		return st; 
	}

	@Override
	protected PreparedStatement selectSql(PersistentObj o){
		Psicologo p = (Psicologo) o;
		PreparedStatement st=null;
		try{
			st = connection.prepareStatement("SELECT * FROM psicologo WHERE idpsicologo=? and contraseña = ?");
			st.setString(1, p.getId());
			st.setString(2, p.getPassword());
		}catch(SQLException e){
			e.printStackTrace();
		}
		return st;
	}

	@Override
	protected PreparedStatement updateSql(PersistentObj o){
		Psicologo p = (Psicologo) o;
		PreparedStatement st =null;
		try{
			st= connection.prepareStatement("UPDATE psicologo SET idpsicologo=?,nombre=?, contraseña=? WHERE idpsicologo=?");
			st.setString(1, p.getId());
			st.setString(2, p.getName());
			st.setString(3, p.getPassword());
			st.setString(4, p.getId());
		}catch(SQLException e){
			e.printStackTrace();
		}
		return st; 
	}

	@Override
	protected PersistentObj selectObject(ResultSet r){
		Psicologo p = null;
		try {
			String id = r.getString("idpsicologo");
			String name = r.getString("nombre");
			String contraseña = r.getString("contraseña");
			List<Patient> patientList=selectAllPatientsByPsi(id); 
			p = new Psicologo(id,name, contraseña,patientList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (PersistentObj) p;
	}

	@Override
	protected PreparedStatement selectAllSql(){
		PreparedStatement st=null;
		try {
			st = connection
					.prepareStatement("Select * from psicologo");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return st;
	}
	public ArrayList<Psicologo> selectAllPsicologos(String idGame){
		ArrayList<Psicologo> psicologos = new ArrayList<Psicologo>();
		ArrayList<PersistentObj> psiAux = this.selectAll();
		for (Iterator<PersistentObj> iterador = psiAux.iterator(); iterador.hasNext();) {
			Psicologo pAux = (Psicologo) iterador.next();
			psicologos.add(pAux);
		}
		return psicologos;
	}
	private ArrayList<Patient> selectAllPatientsByPsi(String id){
		ArrayList<Patient> patients = new ArrayList<Patient>();
		PatientDAO sd = new PatientDAO(this.connection,id);
		ArrayList<PersistentObj> patientList = sd.selectAll();
		for (Iterator<PersistentObj> iterador = patientList.iterator(); iterador.hasNext();)
			patients.add((Patient) iterador.next());
		return patients;
	}
	public Psicologo checkPsiAccount(PersistentObj o){
		return (Psicologo) this.select(o);
	}
}
