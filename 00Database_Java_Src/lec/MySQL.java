package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class MySQL {
	
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
	
	//문제1
	public void StudyPrepareStatement(Connection con) throws Exception {
		
		PreparedStatement st = con.prepareStatement("select count(*) from countrylanguage where Language=?");
		
		//setString이 자동으로 comma를 찍어줌 ""
		//setInt, setDate... 메서드를 이용해서 전달 
		st.setString(1, "English");
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
	
	//문제2
	public void StudyPrepareStatement2(Connection con) throws Exception {
		
		PreparedStatement st = con.prepareStatement("select Language\r\n"
				+ "from countrylanguage\r\n"
				+ "where CountryCode=?");
		
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
	
	//문제3	
	public void StudyPrepareStatement3(Connection con) throws Exception {
		
		PreparedStatement st = con.prepareStatement("select Continent, Name "
					+ "from country, countrylanguage "
					+ "where countrylanguage.CountryCode = country.Code and Language=? and IsOfficial=?");	
		
		//setString 여러개 사용 
		st.setString(1, "English");
		st.setString(2, "T");
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
	
	//문제4
	public void StudyPrepareStatement4(Connection con) throws Exception {
		
		PreparedStatement st = con.prepareStatement("select Continent, count(Name) "
				+ "from country, countrylanguage "
				+ "where countrylanguage.CountryCode = country.Code and Language=? "
				+ "group by Continent;");	
		
		st.setString(1, "English");
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
	
	public void StudyStatement4(Connection con) throws Exception {
		
		//질의를 위한 객체 생성
		Statement st = con.createStatement();	
		// SQL 질의 (쿼리실행) -> ResultSet rs 에 return값 저장
		ResultSet rs = st.executeQuery("select Continent, count(Name) "
				+ "from country, countrylanguage "
				+ "where countrylanguage.CountryCode = country.Code and Language='English' "
				+ "group by Continent;");	
		
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

	public static void main(String[] args) throws Exception {
		// 인스턴스 생성
		MySQL cli = new MySQL();

		Class.forName("com.mysql.cj.jdbc.Driver");

		// 드라이버 로드
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "musthave", "tiger");) {

			System.out.println("<=== 문제1 ===>");
			cli.StudyPrepareStatement(con);
			System.out.println();
			
			System.out.println("<=== 문제2 ===>");
			cli.StudyPrepareStatement2(con);
			System.out.println();
			
			System.out.println("<=== 문제3 ===>");
			cli.StudyPrepareStatement3(con);
			System.out.println();
			
			System.out.println("<=== 문제4 ===>");
			cli.StudyPrepareStatement4(con);
			System.out.println();
		}	
	}

}
