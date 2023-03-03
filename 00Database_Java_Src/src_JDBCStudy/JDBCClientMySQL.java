package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;	//referenced libraries 추가 (mysql-connector-j-8.0.32.jar)
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCClientMySQL {

	public static void main(String[] args) throws Exception {
		
		// MySQL 접속을 위한 JDBC 드라이버 로드 (라이브러리)
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// MySQL Database 연결 객체 생성 ("접속할 데이터베이스의 url", "id", "pw")
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "musthave", "tiger");
		
		// 질의를 위한 객체 생성
		// Statement
		// Preparestatement
		Statement st = con.createStatement();
		
		// SQL 질의 (쿼리실행) -> ResultSet rs 에 return값 저장
		ResultSet rs = st.executeQuery("select Name, Continent, Population, HeadOfState from country");
		
		// 질의 결과 Parsing (커서 프로세싱)
		// 클라이언트에서 질의를 하면 => 서버의 데이터베이스안에 질의결과가 테이블 형태로 만들어져 있음
		// rs가 원격에 있는 테이블을 가르키고 있음
		// 커서프로세싱 : 제일 첫 빈공간을 가르키고 있음. rs.next() 호출될때 마다 데이터 가져옴.
		// getString, getInt, getdouble  ... 해당하는 타입으로 rs의 데이터 읽어올 수 있음
		// 인덱스가 1부터 시작!
		while(rs.next()) {	//다음 결과 레코드로  이동
			
			for(int i=1; i<=4; i++) { 
				
				if (i!=1)	System.out.print(",");
				
				System.out.print(rs.getString(i));
			}
			System.out.println();
		}
		
		//생성된 객체 연결을 모두 해제
		//기본적으로 닫아주기
		//(자바 가비지컬렉터가 시간이 지난 후 자동으로 닫아주긴 함)
		rs.close();
		st.close();
		con.close();
	}
}
