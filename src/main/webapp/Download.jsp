<%@page import="utils.JSFunction"%>
<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//getServletContext.getRealPath()와 같은 결과
	String saveDirectory = application.getRealPath("/Uploads");
	String saveFilename = request.getParameter("sName");
	String originalFilename = request.getParameter("oName");
	
	try{
		File file = new File(saveDirectory, saveFilename);
		InputStream inStream = new FileInputStream(file);
		
		
		//웹브라우저 종류 알아내기
		String client = request.getHeader("User-Agent");
		
		//Internet Explorer가 아니면
		if(client.indexOf("WOW64") == -1){
			originalFilename = new String(originalFilename.getBytes("UTF-8"),
											"ISO-8859-1");
		}else{
			/*
				익스플로러는 KSC5601을 이용하여 바이트 배열로 변환하고 문자열을 재생성하는 방식으로
				한글을 처리한다. (웹브라우저에 따라 한글 처리 방식이 다르다.)
			*/
			originalFilename = new String(originalFilename.getBytes("KSC5601"),
											"ISO-8859-1");
		}
		//response 헤더 초기화 -> 왜 하는데?
		response.reset();
		//파일 다운로드 창을 띄우기 위한 콘텐트 타입 지정(octet-stream: 8비트 단위의 바이너리 데이터)
		//응답헤더로 설정하면 파일의 종류의 관계없이 다운로드 창이 나온다.
		response.setContentType("application/octet-stream");
		//다운로드 창이 뜰 때 원본 파일명을 기본으로 입력되어 있도록 설정
		response.setHeader("Content-Disposition",
							"attachment; filename=\"" + originalFilename + "\"");
		response.setHeader("Content-Length", "" + file.length() );
		
		// 출력 스트림 초기화
		// 왜 하는데? -> JSP가 열린 상태에서 다운로드를 위해 또 다른 JSP를 열면
		// 출력 스트림이 중복으로 생성되기 때문에 이 부분이 없으면 예외가 발생함
		out.clear();
		
		// response 내장 객체로부터 새로운 출력 스트림 생성
		OutputStream outStream = response.getOutputStream();
		
		// 출력 스트림에 파일 내용 출력
		byte b[] = new byte[(int)file.length()];
		int readBuffer = 0;
		while(	(readBuffer = inStream.read(b)) > 0){
			outStream.write(b, 0, readBuffer);
		}
	
		// 입출력 스트림 close
		inStream.close();
		outStream.close();
	}catch(FileNotFoundException e){
		JSFunction.alertBack("파일을 찾을 수 없습니다.", out);
	}catch(Exception e){
		JSFunction.alertBack("예외가 발생하였습니다.", out);
	}
%>