package edu.mission;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCClientH2 extends JDBCClient {
		
	public JDBCClientH2() throws Exception {
		super("org.h2.Driver");
		con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/musthave", "sa", "");
	}
	
	@Override
	public void StudyStatement(String q) throws Exception {

		//질의를 위한 객체 생성
		Statement st = con.createStatement();	
		
		// SQL 질의 (쿼리실행) -> ResultSet rs 에 return값 저장
		ResultSet rs = st.executeQuery(q);	
		
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

	@Override
	public void StudyPrepareStatement(String q) throws Exception {

		PreparedStatement st = con.prepareStatement(q);
		
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

}
