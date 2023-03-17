package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;

public class JDBConnect {

	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	//기본생성자
	public JDBConnect() {
		
		try {
			
			// JDBC 드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// DB연결
			String url = "jdbc:mysql://localhost:3306/musthave";
			String id = "musthave";
			String pw = "tiger";
			con = DriverManager.getConnection(url, id, pw);
			
			System.out.println("DB연결성공(기본생성자)");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public JDBConnect(String database) {
		
		try {
			
			// JDBC 드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// DB연결
			String url = "jdbc:mysql://localhost:3306/"+database;
			String id = "musthave";
			String pw = "tiger";
			con = DriverManager.getConnection(url, id, pw);
			
			System.out.println("DB연결성공(기본생성자)");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//두번째생성자
	public JDBConnect(String driver, String url, String id, String pwd) {
		
		try {
			
			// JDBC 드라이버 로드
			Class.forName(driver);
			
			// DB연결
			con = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB연결성공(인수 생성자 1)");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//세번째생성자
	public JDBConnect(ServletContext application) {
		
		try {
			
			// JDBC 드라이버 로드
			String driver = application.getInitParameter("MySQLDriver");
			Class.forName(driver);
			
			// DB연결
			String url = application.getInitParameter("MySQLURL");
			String id = application.getInitParameter("MySQLId");
			String pwd = application.getInitParameter("MySQLPwd");
			con = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB연결성공(인수 생성자 2)");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		JDBConnect jdbconnect = new JDBConnect();
		
	}
	
	//연결 해제(자원 반납)
	public void close() {
		
		try {
			 
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (psmt != null) psmt.close();
			if (con != null) con.close();
			
			System.err.println("JDBC 자원 해제");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
