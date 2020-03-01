package com.kh.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	// JDBC드라이버 등록
	// DB 연결 (Connection)
	// 트랜잭션
	// 자원반납
	
	private static Connection conn = null;
	
	public static Connection getConnection() {
		
		if(conn == null) { // 연결되어 있는 정보가 없으면
			try {
//				Class.forName("oracle.jdbc.driver.OracleDriver");
//				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MEMBER","MEMBER");

// 				Properties로 연결하는방법
				Properties prop = new Properties();
				prop.load(new FileReader("driver.properties"));
				
				
				Class.forName(prop.getProperty("driver"));
				conn = DriverManager.getConnection(prop.getProperty("url"),
													prop.getProperty("user"),
													prop.getProperty("password"));
				
				conn.setAutoCommit(false); // 자동으로 커밋되는걸 막겠다!
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return conn;
	}
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) { // 커넥션이 존재하고 커넥션이 열려있을때(닫혀져있지않을때)
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// PrepareStatement는 Statement의 자식이여서 다형성을 통해서 Statement만 생성해도 처리가능
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
