package fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

public class FileUtil {

	//파일 업로드(multipart/form-data 요청) 처리
	public static MultipartRequest uploadFile(HttpServletRequest req, String saveDirectory, int maxPostSize) {
		
		try {
			
			return new MultipartRequest(req, saveDirectory, maxPostSize, "UTF-8");
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//파일 다운로드
	public static void download(HttpServletRequest req, HttpServletResponse resp, String directory, String sfileName, String ofileName) {
		
		String sDirectory = req.getServletContext().getRealPath(directory);
		try {
			
			//파일 찾아 입력 스트림 생성
			File file = new File(sDirectory, sfileName);
			InputStream iStream = new FileInputStream(file);
			
			//한글 파일명 깨짐 방지
			String client = req.getHeader("User-Agent");
			if (client.indexOf("WOW64")==-1) {
				// 크롬 사용 시
				ofileName = new String(ofileName.getBytes("UTF-8"), "ISO-8859-1");
			} else {
				
				//인터넷 익스플로러 사용 시 -> 현재는 필요 없음
				ofileName = new String(ofileName.getBytes("KSC5601"), "ISO-8859-1");
			}
			
			//파일 다운로드용 응답 헤더 설정
			resp.reset();
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", "attachment; filename=\"" + ofileName + "\"");
			resp.setHeader("Content-Length", "" + file.length());
			
			//response내장객체로부터 새로운 출력 스트림 생성
			OutputStream oStream = resp.getOutputStream();

			//출력 스트림에 파일 내용 출력
			byte b[] = new byte[(int)file.length()];
			int readBuffer = 0;
			while((readBuffer = iStream.read(b))>0) {
				oStream.write(b, 0, readBuffer);
			}
			
			//입출력 스트림 닫음
			iStream.close();
			oStream.close();
			
		} catch(FileNotFoundException e){
			System.out.println("파일을 찾을 수 없습니다.");
			e.printStackTrace();			
			
		} catch(Exception e) {
			System.out.println("예외가 발생하였습니다.");
			e.printStackTrace();
		}
		
	}

	//파일 삭제
	public static void deleteFile(HttpServletRequest req, String directory, String filename) {
		
		String sDirectory = req.getServletContext().getRealPath(directory);
		File file = new File(sDirectory + File.separator + filename);
		if (file.exists()) {
			file.delete();
		}
	}

}
