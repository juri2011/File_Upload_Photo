<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FileUpload</title>
</head>
<script>
	function validateForm(form){
		if(form.title.value == ""){
			alert("제목을 입력하세요.");
			form.title.focus();
			return false;
		}
		if(form.attachedFile.value == ""){
			alert("첨부 파일은 필수 입력입니다.");
			return false;
		}
	}
</script>
<body>
	<h3>파일 업로드</h3>
	<span style="color: red;">${errorMessage}</span>
	<!-- onsubmit = "return false"일 경우 submit버튼을 눌러도 action이 가리키는 곳으로 이동할 수 없다. -->
	<form name="fileForm" method="post" enctype="multipart/form-data"
		action="uploadProcess.do" onsubmit = "return validateForm(this);">
		제목 : <input type="text" name="title" /><br />
		카테고리(선택사항) : 
			<input type="checkbox" name="cate" value="사진" chekced /> 사진
			<input type="checkbox" name="cate" value="과제" chekced /> 과제
			<input type="checkbox" name="cate" value="워드" chekced /> 워드
			<input type="checkbox" name="cate" value="음원" chekced /> 음원 <br />
		첨부파일 : <input type="file" name="attachedFile" /> <br />
		<input type="submit" value="전송하기" />
	</form>
</body>
</html>