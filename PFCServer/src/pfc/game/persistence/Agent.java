package pfc.game.persistence;

import java.sql.*;
import java.util.Vector;
/**ARP-11/02/2014: This class implements the methods that i need to use the database in the webpage*/
public class Agent {
	
	private Vector<Connection> connections;
	private Agent myInstance;
	private String server;
	private String dbName;
	private String login;
	private String password;
	
	public Agent(Vector<Connection> connections, Agent myInstance,
			String server, String dbName, String login, String password) {
		super();
		this.connections = connections;
		this.myInstance = myInstance;
		this.server = server;
		this.dbName = dbName;
		this.login = login;
		this.password = password;
	}
	public Agent(){
		this.server="localhost";
		this.dbName="pfc_ddbb";
		this.login="root";
		this.password="MeMphis";
		connections = new Vector<Connection>();
	}
	public Vector<Connection> getConnections() {
		return connections;
	}

	public void setConnections(Vector<Connection> connections) {
		this.connections = connections;
	}

	public Agent getMyInstance() {
		if (myInstance == null) {
			myInstance = new Agent();
		}
		return myInstance;
	}

	public void setMyInstance(Agent myInstance) {
		this.myInstance = myInstance;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Connection getConnection() throws SQLException {
		return newConnection();
	}
	private Connection newConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			throw new SQLException("Mysql driver not found");
		}

		conn = DriverManager.getConnection("jdbc:mysql://" + server + "/"
				+ dbName, login, getPassword());
		connections.add(conn);

		return conn;
	}
}
