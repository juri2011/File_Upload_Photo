package fileupload;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class FileUtil {
	
	//파일 업로드
	public static String uploadFile(HttpServletRequest req, String sDirectory) 
					throws ServletException, IOException{
		//ofile이라는 name 속성값을 가진 Part 객체를 가져온다.
		Part part = req.getPart("attachedFile");
		//Part 객체에서 content-disposition 헤더값 읽어오기(name 속성과 파일명이 포함되어있음)
		String partHeader = part.getHeader("content-disposition");
		System.out.println(partHeader);
		//헤더 내용으로부터 파일명을 추출함
		String[] phArr = partHeader.split("filename=");
		//쌍따옴표 제거
		String originalFileName = phArr[1].trim().replace("\"","");
		//파일명이 비어있지 않다면 디렉터리에 파일을 저장한다.
		if(!originalFileName.isEmpty()) {
			//지정한 디렉토리에 파일을 저장한다.(이 프로젝트에선 webapp 안의 upload 디렉토리)
			//File.separator는 사용하고 있는 OS의 디렉토리 구분자를 쓸 수 있다.(Windows는 \\ 리눅스는 /)
			part.write(sDirectory + File.separator + originalFileName);
		}
		//원본파일명 반환
		return originalFileName;
	}
	
	//파일명 변경
	//파일명 변경하는 이유? -> 웹 서버의 인코딩 방식에 따라 한글이 깨질 수 있으므로
	// 인코딩 방식에 상관없이 제대로 표시하려면 파일명을 영문+숫자의 조합으로 하는 것이 안전하다.
	public static String renameFile(String sDirectory, String fileName) {
		//확장자 추출
		//lastIndexOf 쓴 이유? -> 파일명에 점(.)이 2개 이상 포함될 수 있음
		String ext = fileName.substring(fileName.lastIndexOf("."));
		//현재날짜_시간
		String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
		//현재날짜_시간 + 확장자로 파일 이름을 정한다.
		String newFileName = now + ext;
		//원본파일과 새 파일에 대해 File 객체를 생성하고 파일 이름을 바꾼다.
		File oldFile = new File(sDirectory + File.separator + fileName);
		File newFile = new File(sDirectory + File.separator + newFileName);
		//기존의 파일 이름을 새 파일명으로 바꾼다.
		oldFile.renameTo(newFile);
		
		//새 파일의 이름을 반환한다.
		return newFileName;
	}
	
}
