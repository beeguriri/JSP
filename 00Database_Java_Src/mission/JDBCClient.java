package edu.mission;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public abstract class JDBCClient {
	
	Connection con;
	
	public JDBCClient(String driverName) throws ClassNotFoundException {
		Class.forName(driverName);
	}
	
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
		
		
		public abstract void StudyStatement(String q) throws Exception; 		
		public abstract void StudyPrepareStatement(String q)throws Exception; 		

}
