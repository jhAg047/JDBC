package com.kh.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.model.vo.Employee;

public class EmployeeDAO {

	public ArrayList<Employee> selectAll() {
	
		Connection conn = null; // Connection = 연결정보를 담은 객체 
		
		Statement stmt = null; // Connection객체를 통해 DB에 sql문을 전달하여 실행시키고 결과 값을 반환 받는 역할
		
		ResultSet rset = null; // SELECT문을 사용한 질의 성공 시 반환되는 객체
		
		ArrayList<Employee> empList = null;
		try {
			// 오라클 드라이버
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "SCOTT");
			// jdbc:oracle:thin	JDBC드라이버가 thin타입
			// @localhost	오라클이 설치된 서버의 ip가 자신의 컴퓨터임 == @127.0.0.1
			// 1521	오라클 listener 포트번호 
			// xe	접속할 오라클 명 
			
//			System.out.println(conn); // 접속 실패 시 null
			
			String query = "SELECT * FROM EMP"; // 세미콜론 생략
			
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query); // select * from의 결과 
			
			empList = new ArrayList<Employee>(); // 조회 결과를 저장할 ArrayList
			Employee emp = null; // 조회 결과의 한 행의 값을 저장할 임시 vo 선언
			
			while(rset.next()) {
				int empNo = rset.getInt("empno");
				String empName = rset.getString("ename");
				String job = rset.getString("job");
				int mgr = rset.getInt("mgr");
				Date hireDate = rset.getDate("hiredate");
				int sal = rset.getInt("sal");
				int comm = rset.getInt("comm");
				int deptNo = rset.getInt("deptNo");
				
				emp = new Employee(empNo,empName,job,mgr,hireDate,sal,comm,deptNo);
				empList.add(emp);
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return empList;
	}

	public Employee selectEmployee(int empNo) {
		Connection conn = null;
//		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Employee emp = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","SCOTT","SCOTT");
			
//			String query = "SELECT * FROM EMP WHERE EMPNO = " + empNo;
			String query = "SELECT * FROM EMP WHERE EMPNO = ?";
			
//			stmt = conn.createStatement();
//			rset = stmt.executeQuery(query);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, empNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				String empName = rset.getString("ename");
				String job = rset.getString("job");
				int mgr = rset.getInt("mgr");
				Date hireDate = rset.getDate("hireDate");
				int sal = rset.getInt("sal");
				int comm = rset.getInt("comm");
				int deptNo = rset.getInt("deptNo");
				
				emp = new Employee(empNo, empName,job,mgr, hireDate,sal,comm,deptNo);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
//				stmt.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return emp;
	}

	public int insertEmployee(Employee emp) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SCOTT","SCOTT");
			String query = "INSERT INTO EMP VALUES(?,?,?,?,SYSDATE,?,?,?)";
			pstmt = conn.prepareStatement(query);
			// db에 있는 컬럼 순서로 들어가야함
			pstmt.setInt(1, emp.empNogetter());
			pstmt.setString(2, emp.empNamegetter());
			pstmt.setString(3, emp.jobgetter());
			pstmt.setInt(4, emp.mgrgetter());
			pstmt.setInt(5, emp.salgetter());
			pstmt.setInt(6, emp.commgetter());
			pstmt.setInt(7, emp.deptNogetter());
			
			result = pstmt.executeUpdate();
			
			if(result > 0 ) { //insert 성공적으로 수행
				conn.commit();
			} else { // insert 안됐을때
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int updateEmployee(Employee emp) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SCOTT","SCOTT");
			String query = "UPDATE EMP SET JOB = ?, SAL = ?,COMM = ? WHERE EMPNO = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, emp.jobgetter());
			pstmt.setInt(2, emp.salgetter());
			pstmt.setInt(3,emp.commgetter());
			pstmt.setInt(4,emp.empNogetter());
			
			result = pstmt.executeUpdate();
			if(result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public int deleteEmployee(int empNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
//		Statement stmt = null;
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SCOTT","SCOTT");
		String query = "DELETE FROM EMP WHERE EMPNO = ?";
//		String query = "DELETE FROM EMP WHERE EMPNO = " + empNo;
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, empNo);
		result = pstmt.executeUpdate();
//		stmt = conn.createStatement();
//		result = stmt.executeUpdate(query);
		if(result > 0) {
			conn.commit();
		} else {
			conn.rollback();
		}
		
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
//				stmt.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
}
