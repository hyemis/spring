<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
</head>
<body>
<h1>내정보 보기</h1>
<form id="frmInfo" method="get">
	<input name="id" value="${membervo.id }" type="text" readonly="readonly"><br>
	<input value="${membervo.passwd }" type="password" readonly="readonly"><br>
	<input value="${membervo.name }"  type="text" readonly="readonly"><br>
	<input value="${membervo.email }" type="text" readonly="readonly"><br>
	<button type="button" onclick="frmSubmit('update')">수정</button>
	<button type="button" onclick="frmSubmit('delete')">탈퇴</button>
	
	<button type="button">수 정</button>
	<button type="button">탈 퇴</button>
</form>

<script>
	function frmSubmit(targetEle) {
		frmInfo.action = targetEle;
		frmInfo.submit();
	}
	
	$("button").eq(2).click('update', frmSubmit2);
	$("button").eq(3).click('delete', frmSubmit2);
	
	function frmSubmit2(event){
		console.log(this); // this : click 이벤트가 발생한 시점의 element
		frmInfo.action = event.data;
		frmInfo.submit();
	}
	

</script>
</body>
</html>