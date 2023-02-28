package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class JDBCClientMySQL2 {
	
	//필드명 출력 메서드
	public int printColumnName(ResultSet rs) throws Exception {
		ResultSetMetaData meta = rs.getMetaData();
		int count = meta.getColumnCount();
		
		StringBuilder sb = new StringBuilder("#");
		for (int i = 1 ; i <= count ; i++) {
			sb.append("," + meta.getColumnName(i));
		}
		System.out.println(sb);
		System.out.println("-".repeat(sb.length()));
		
		return count;
	}
	
	//Statement : 변수없이 SQL문 자체로 끝남
	public void StudyStatement(Connection con) throws Exception {
		
		//질의를 위한 객체 생성
		Statement st = con.createStatement();	
		// SQL 질의 (쿼리실행) -> ResultSet rs 에 return값 저장
		ResultSet rs = st.executeQuery("select * from country limit 10");	
		
		int colCount = printColumnName(rs);
		int rowCount = 1;
		while(rs.next()) {
			for(int i = 1 ; i <= colCount ; i++) {
				if (i == 1)	System.out.print((rowCount++) + ",");
				else		System.out.print(",");
				System.out.print(rs.getString(i));
			}
			System.out.println();
		}
		rs.close();
		st.close();
	}
	
	//PrepareStatement : where절에 ?를 넣어(변수 형식으로) 조건 전달할때
	public void StudyPrepareStatement(Connection con) throws Exception {
		
		PreparedStatement st = con.prepareStatement("select * from country where code=?");
		
		//code가 kor인 것을 찾음
		//setString이 자동으로 comma를 찍어줌 ""
		//setInt, setDate... 메서드를 이용해서 전달 
		st.setString(1, "KOR");
		ResultSet rs = st.executeQuery();
		
		int colCount = printColumnName(rs);
		int rowCount = 1;
		while(rs.next()) {
			for(int i = 1 ; i <= colCount ; i++) {
				if (i == 1)	System.out.print((rowCount++) + ",");
				else		System.out.print(",");
				System.out.print(rs.getString(i));
			}
			System.out.println();
		}
		rs.close();
		st.close();
	}	
	
	public static void main(String[] args) throws Exception  {

		// 인스턴스 생성
		JDBCClientMySQL2 cli = new JDBCClientMySQL2();

		Class.forName("com.mysql.cj.jdbc.Driver");

		// 드라이버 로드
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "musthave", "tiger");) {
			
			System.out.println("<=== StudyStatement ===>");
			cli.StudyStatement(con);
			System.out.println();

			System.out.println("<=== StudyPrepareStatement ===>");
			cli.StudyPrepareStatement(con);
		}		
	}
}
