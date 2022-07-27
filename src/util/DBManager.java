package util;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBManager {
	// DB 연결
	public static Connection getConnection() {
		
		Connection conn = null;
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	// r
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
	try {
		rs.close();
		stmt.close();
		conn.close();

	} catch (Exception e) {
		e.printStackTrace();
	}
	}
	// cud
	public static void close(Connection conn, Statement stmt) {
		try {
			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
