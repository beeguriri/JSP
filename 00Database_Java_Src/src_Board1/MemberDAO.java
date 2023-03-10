package membership;

import javax.servlet.ServletContext;
import common.JDBConnect;

public class MemberDAO extends JDBConnect{
	
	//데이터베이스 연결 생성자
	public MemberDAO (String drv, String url, String id, String pw) {
		super(drv, url, id, pw);
	}
	
	public MemberDAO(ServletContext application) {
		super(application);
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

	//회원정보 추가
		public int regUser(MemberDTO dto) {
			int result = 0;
			
			try {
				
				String query = "INSERT INTO member (id,pass,name) VALUES (?, ?, ?)";
				
				psmt = con.prepareStatement(query);
				
				psmt.setString(1, dto.getId());
				psmt.setString(2, dto.getPass());
				psmt.setString(3, dto.getName());
				
				result = psmt.executeUpdate();
				
			} catch(Exception e) {
				
				System.out.println("게시물 입력 중 예외 발생");
				e.printStackTrace();
			}
			
			return result;
		}


}
