package edu.mission;

public class JDBCClientTest {

	public static void main(String[] args) throws Exception {

		String q1 = "select * from member";
		String q2 = "select * from board";
		
		System.out.println("<=== H2 data 출력 ===>");
		JDBCClient cli = new JDBCClientH2();
		cli.StudyStatement(q1);
		System.out.println();
		cli.StudyStatement(q2);
		System.out.println();
		
		System.out.println("<=== MySQL data 출력 ===>");
		JDBCClient cli2 = new JDBCClientMySQL();
		cli2.StudyStatement(q1);
		System.out.println();
		cli2.StudyStatement(q2);
		System.out.println();
		
	}
}
