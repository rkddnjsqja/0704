<%@ page  contentType="text/html; charset=UTF-8"%>
<%
/*
	jsp는 실행 직전에 서블릿 클래스로 변경된다...
	그렇다면 jsp란 왜 제공되는 것일까?
	-순수한 서블릿만을 이용하면 문서의 태그를 컨텐츠 내용 모두를
	out.print("<html>");이런식으로 개발해야한다..따라서 디자인 영역에 
	너무 효율성이 떨어짐..
	
	개발자는 jsp에서지원되는 내장객체라 불리는 객체들이 서블릿으로 변경될때
	어떤 자료형인지 알고 있어야한다..
	ex)request객체의 자료형:HttpServletRequest
		respnse 객체 : 응답정보를 보유한 객체 HttpServletResponse
		session 객체 : 클라이언트와 연결을 지속한 것처럼 효과를 내기위한 객체 HttpSesstion
		application 객체: 웹어플리케이션 전역적 정보를 보유한 객체 ServletContext
		
	내장객체중 request<sesstion<application 의 생명력을 이해..
*/
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