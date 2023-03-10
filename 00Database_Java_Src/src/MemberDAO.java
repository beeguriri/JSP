package membership;

import common.JDBConnect;

public class MemberDAO extends JDBConnect{
	
	//데이터베이스 연결 생성자
	public MemberDAO (String drv, String url, String id, String pw) {
		super(drv, url, id, pw);
	}
	
	//회원정보 반환
	public MemberDTO getMemberDTO(String uid, String upass) {
		MemberDTO dto = new MemberDTO();
		String query = "SELECT id, pass, name, regidate FROM member WHERE id=? AND pass=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, uid);
			psmt.setString(2, upass);
			rs = psmt.executeQuery();
			
			//결과처리
			if(rs.next()) {
				
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString(3));
				dto.setRegidate(rs.getString(4));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return dto;
		
	}
}
