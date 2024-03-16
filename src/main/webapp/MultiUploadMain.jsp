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
	<h3>멀티 파일 업로드(multiple 속성 추가)</h3>
	<!-- 파일 업로드에 실패했으면 UploadProcess서블릿에서 가져온 에러메시지를 출력 -->
	<span style="color: red;">${errorMessage}</span>
	<!-- onsubmit = "return false"일 경우 submit버튼을 눌러도 action이 가리키는 곳으로 이동할 수 없다. -->
	<form name="fileForm" method="post" enctype="multipart/form-data"
		action="MultiProcess.do" onsubmit = "return validateForm(this);">
		제목 : <input type="text" name="title" /><br />
		카테고리(선택사항) : 
			<input type="checkbox" name="cate" value="사진" checked /> 사진
			<input type="checkbox" name="cate" value="과제" checked /> 과제
			<input type="checkbox" name="cate" value="워드" checked /> 워드
			<input type="checkbox" name="cate" value="음원" checked /> 음원 <br />
			<!--
				input file에 multiple 속성을 추가하면
				ctrl을 누르거나 마우스를 드래그 해서 여러 파일을 선택할 수 있다.
			-->
		첨부파일(다중선택가능) : <input type="file" name="attachedFile" multiple/> <br />
		<input type="submit" value="전송하기" />
	</form>
</body>
</html>