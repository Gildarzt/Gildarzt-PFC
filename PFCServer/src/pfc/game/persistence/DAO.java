package pfc.game.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class DAO {
	protected Connection connection;

	abstract protected PreparedStatement insertSql(PersistentObj o)
			throws SQLException;

	abstract protected PreparedStatement deleteSql(PersistentObj o)
			throws SQLException;

	abstract protected PreparedStatement updateSql(PersistentObj o)
			throws SQLException;

	abstract protected PreparedStatement selectSql(PersistentObj o)
			throws SQLException;

	abstract protected PreparedStatement selectAllSql() throws SQLException;

	abstract protected PersistentObj selectObject(ResultSet r)
			throws SQLException;

	public DAO(Connection conn) {
		connection = conn;
	}

	public void insert(PersistentObj o){
		try {
			PreparedStatement st;
			st = insertSql(o);
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void delete(PersistentObj o){
		try {
			PreparedStatement st;
			st = deleteSql(o);
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void update(PersistentObj o){
		try {
			PreparedStatement st;
			st = updateSql(o);
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/* Object "o" es la clave primaria */
	public PersistentObj select(PersistentObj o){
		PreparedStatement st = null;
		PersistentObj obj = null;
		try {
			st = selectSql(o);
			ResultSet r = st.executeQuery();
			if (!r.next())
			{
				throw new SQLException("No object found in select query");
			}
			obj = selectObject(r);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	public ArrayList<PersistentObj> selectAll(){
		PreparedStatement st = null;
		ArrayList<PersistentObj> obs = new ArrayList<PersistentObj>();
		try {
			st = selectAllSql();
			ResultSet r = st.executeQuery();
			while (r.next()) {
				obs.add(selectObject(r));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obs;
	}
}
