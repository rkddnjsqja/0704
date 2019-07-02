<%@page import="shop.Member"%>
<%@page import="shop.Cart"%>
<%@ page  contentType="text/html; charset=UTF-8"%>


<!DOCTYPE html>
<%
request.setCharacterEncoding("utf-8");
String name=request.getParameter("name");
String ea=request.getParameter("ea");
String price=request.getParameter("price");
//낱개로된 데이터를 하나의 인스턴스에 넣자!!
Cart cart = new Cart();

cart.setName(name);
cart.setEa(Integer.parseInt(ea));
cart.setPrice(Integer.parseInt(price));

//현재 클라이언트가 사용가능한 세션에 cart를 담는다!1
session.setAttribute("cart", cart);
%>
<%
	Member member=(Member)session.getAttribute("member");
	
%>
<%=member.getUname() %>님 로그인중
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="yellow">
당신이 입력한 상품은  <%=cart.getName() %><br>
당신이 입력한 갯수는  <%=cart.getEa()%><br>
당신이 입력한 가격은  <%=cart.getPrice()%><br>
<p>
<a href="c.jsp">장바구니 담기
</a>
</body>
</html>