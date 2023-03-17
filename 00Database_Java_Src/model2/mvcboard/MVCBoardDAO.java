package model2.mvcboard;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;

import common.JDBConnect;

public class MVCBoardDAO extends JDBConnect {


	public MVCBoardDAO() {
		super();
	}
	//DB 접속
	public MVCBoardDAO(ServletContext application) {
		super(application);
	}
	
	//검색조건에 맞는 게시물의 개수 반환
	public int selectCount(Map<String, Object> map) {
		
		int totalCount=0;
		
		//게시물 수 얻어오는 쿼리문
		String query = "SELECT COUNT(*) FROM mvcboard";
		//검색조건
		if(map.get("searchWord")!=null) {
			
			query += " WHERE " + map.get("searchField") + " "
					+ " LIKE '%" + map.get("searchWord") + "%'";
		}
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);	//첫번째 컬럼값 가져옴
		} catch (Exception e) {
			System.out.println("게시물 카운트 중 예외 발생");
			e.printStackTrace();
		}
		
		return totalCount; //게시물 수 서블릿으로 반환
		
		
		
		
	}
	
	//검색 조건에 맞는 게시물 목록 반환 (페이징 기능 지원)
	public List<MVCBoardDTO> selectListPage(Map<String, Object> map) {
		
		List<MVCBoardDTO> board = new Vector<MVCBoardDTO>();
		
		//쿼리문
		String query = "SELECT * FROM mvcboard ";
		
		if(map.get("searchWord")!=null) {
			query += " WHERE " + map.get("searchField") + " "
					+ " LIKE '%" + map.get("searchWord") + "%' ";
		}
		
		query += " ORDER BY idx DESC LIMIT ?,?"; //from to
		
		try {
			
			//쿼리문 완성
			psmt = con.prepareStatement(query);
			psmt.setInt(1, (int)map.get("start")-1);	//시작인덱스 
			psmt.setInt(2, (int)map.get("pageSize"));	//페이지수(application -> ListController에서 받아옴)
			
			//쿼리문 실행
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				MVCBoardDTO dto = new MVCBoardDTO();
				
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));
				
				board.add(dto);
				
			}
		} catch(Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
							
		return board;
	}

	//게시글 데이터를 받아 DB에 추가(파일 업로드지원)
	public int insertWrite(MVCBoardDTO dto) {
		int result = 0;
		
		try {
			
			String query = "INSERT INTO mvcboard (name, title, content, ofile, sfile, pass) VALUES (?, ?, ?, ?, ?, ?)";
			
			psmt = con.prepareStatement(query);
			
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());			
			psmt.setString(6, dto.getPass());
			
			result = psmt.executeUpdate();
			
		} catch(Exception e) {
			
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		
		return result;
	}

	//게시물 반환
	public MVCBoardDTO selectView(String idx) {
		
		MVCBoardDTO dto = new MVCBoardDTO();
		
		//쿼리문
		String query = "SELECT * FROM mvcboard WHERE idx=?";
		
		try {
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			rs = psmt.executeQuery();
			
			//결과처리
			if (rs.next()) {
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));
			}
			
		} catch(Exception e) {
			System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		}
		return dto;
	}

	//게시물 조회수 증가
	public void updateVisitCount(String idx) {
		
		String query = "UPDATE mvcboard SET visitcount=visitcount+1 WHERE idx=?";
		
		try {
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			psmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
	}

	//다운로드 횟수 증가
	public void downCountPlus(String idx) {
		
		String query = "UPDATE mvcboard SET downcount=downcount+1 WHERE idx=?";
		
		try {
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			psmt.executeUpdate();
			
		} catch(Exception e) {

		}
	}

	//비밀번호 일치여부 확인
	public boolean confirmPassword(String pass, String idx) {
		
		boolean isCorr = true;
		
		try {
			
			String query = "SELECT COUNT(*) FROM mvcboard WHERE pass=? AND idx=?";
			
			psmt = con.prepareStatement(query);
			
			psmt.setString(1, pass);
			psmt.setString(2, idx);
			rs = psmt.executeQuery();
			rs.next();
			if(rs.getInt(1)==0) {
				isCorr = false;
			}
			
		} catch (Exception e) {
			isCorr = false;
			e.printStackTrace();
		}
		
		return isCorr;
	}
	
	//게시물 삭제
	public int deletePost(String idx) {
		
		int result = 0;
		
		try {
			
			String query = "DELETE FROM mvcboard WHERE idx=?";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			result = psmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("게시물 삭제 중 예외 발생");
			e.printStackTrace();
		}
		
		return result;
	}

	//게시물 갱신(파일 업로드 지원)
	public int updatePost(MVCBoardDTO dto) {
		
		int result = 0;
		
		try {
			
			String query = "UPDATE mvcboard SET title=?, name=?, content=?, ofile=?, sfile=? WHERE idx=? and pass=?";
			
			psmt = con.prepareStatement(query);
			
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setString(6, dto.getIdx());
			psmt.setString(7, dto.getPass());
			
			result = psmt.executeUpdate();
			
		} catch(Exception e) {
			
			System.out.println("게시물 수정 중 예외 발생");
			e.printStackTrace();
		}
		
		return result;
		
	}
}
