<%@page import="java.net.URLEncoder"%>
<%@page import="fileupload.MyFileDTO"%>
<%@page import="java.util.List"%>
<%@page import="fileupload.MyFileDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FileList</title>
</head>
<body>
	<h2>DB에 등록된 파일 목록 보기</h2>
	<a href="FileUploadMain.jsp">파일등록1</a>
	<a href="MultiUploadMain.jsp">파일등록2</a>
	<%
		MyFileDAO dao = new MyFileDAO();
		List<MyFileDTO> fileLists = dao.myFileList();
		dao.close();
	%>
	<table border="1">
		<tr>
			<th>No</th><th>제목</th><th>카테고리</th>
			<th>원본 파일명</th><th>저장된 파일명</th><th>작성일</th>
		</tr>
		<!-- JSTL을 사용하지 않고 반복 출력 -->
		<% for(MyFileDTO f : fileLists){ %>
			<tr>
				<td><%= f.getIdx() %></td>
				<td><%= f.getTitle() %></td>
				<td><%= f.getCate() %></td>
				<td><%= f.getOfile() %></td>
				<td><%= f.getSfile() %></td>
				<td><%= f.getPostdate() %></td>
				<!-- URL은 아스키코드로만 표현되므로 한글을 쿼리스트링으로 전달하기 위해서는 인코딩을 따로 해주어야 한다. -->
				<td><a href="Download.jsp?oName=<%= URLEncoder.encode(f.getOfile(), "UTF-8") %>
								&sName=<%= URLEncoder.encode(f.getSfile(),"UTF-8")%>">[다운로드]</a></td>
			</tr>
		<% }%>
	</table>
</body>
</html>