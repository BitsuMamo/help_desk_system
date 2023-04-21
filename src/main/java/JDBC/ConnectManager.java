package JDBC;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class ConnectManager implements Closeable {
	
//	private static final String DB_URL 
//		= "jdbc:derby://localhost:1527/MPP_DB;create=true";
//	private static final String USERNAME = "app";
//	private static final String PASSWORD = "app";
	
	private static final String DB_URL = "jdbc:sqlite:identifier.sqlite";

	/* replace with new Optional pattern
	private static Connection conn = null;
	public Connection getConnection() throws SQLException {
		if(conn == null) {	
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			System.out.println("Got connection...");
		}
		return conn;
	}
	*/
	
	private Connection conn = null;
	private Connection myGetConn() {
		try {
			conn = DriverManager.getConnection(DB_URL);
			return conn;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public Connection getConnection() {
		return Optional.ofNullable(conn).orElseGet(this::myGetConn);
	}
	
	public void closeConnection(Connection con)  throws SQLException {

	}

	@Override
	public void close() throws IOException {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
