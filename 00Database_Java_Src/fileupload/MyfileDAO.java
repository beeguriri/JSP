package fileupload;


import java.util.List;
import java.util.Vector;

import javax.servlet.ServletContext;

import common.JDBConnect;

public class MyfileDAO extends JDBConnect {
	
	public MyfileDAO(ServletContext application) {
		super(application);
	}
	
	//새로운 게시물 입력
	public int insertFile(MyfileDTO dto) {
		
		int applyResult = 0;
		
		try {
			
			String query = "INSERT INTO myfile (name, title, cate, ofile, sfile) VALUES (?, ?, ?, ?, ?)";
			
			psmt = con.prepareStatement(query);
			
			psmt.setString(1,  dto.getName());
			psmt.setString(2,  dto.getTitle());
			psmt.setString(3,  dto.getCate());
			psmt.setString(4,  dto.getOfile());
			psmt.setString(5,  dto.getSfile());
			
			applyResult = psmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("INSERT 중 예외 발생");
			e.printStackTrace();
		}
		
		return applyResult;
		
	}

	//파일 목록 반환
	public List<MyfileDTO> myFileList() {
		
		List<MyfileDTO> fileList = new Vector<MyfileDTO>();
		
		String query = "SELECT * FROM myfile ORDER BY idx DESC";
		try {
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();
			
			//DTO에 저장
			while(rs.next()) {
				
				MyfileDTO dto = new MyfileDTO();
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setCate(rs.getString(4));
				dto.setOfile(rs.getString(5));
				dto.setSfile(rs.getString(6));
				dto.setPostdate(rs.getString(7));
				
				fileList.add(dto);
				
			}
		} catch (Exception e) {
			System.out.println("SELECT 시 예외 발생");
			e.printStackTrace();
		}
		
		
		return fileList;
		
	}
}
