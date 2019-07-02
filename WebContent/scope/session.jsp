<%@ page  contentType="text/html; charset=UTF-8"%>
<%
	//클라이언트가 최초의 접속이라면 세션객체가 생성되고
	//고유한 아이디가 발급된다.그것을 경험해 보자!!
	String id=session.getId();//톰켓이 현재 클라이언트에게 발급 고유 세션 ID
	out.print("당신이 발급받은 아이디는"+id);
	//클라이언트의 브라우저에 쿠키로 기록될것임..
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>