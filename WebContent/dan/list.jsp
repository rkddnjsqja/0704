<%@ page  contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<%String dan=request.getParameter("dan");

	if(dan == null){
		dan="2";
	}
	out.print(dan);
%>
<html>
<head>
<script>
window.addEventListener("load",function(){
	var sel=document.querySelector("select");
	sel.addEventListener("change",function(){
		if(this.value !="0"){
			send();
		}else{
			alert("단을 선택하세요");
		}
	});
});
//서버전송
function send(){
	var form=document.querySelector("form");
	form.action="list.jsp";
	form.method="get";
	form.submit();
}
</script>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form>
<select name="dan">
			
			<option name="dan" value="0">단선택</option>
			<%
				for(int i=2;i<=9;i++){
					%>
					<option <%if(i==Integer.parseInt(dan)){%>selected<%} %> value="<%=i%>"><%=i%>단</option>
					<% 
				}
			%>
			
</select>
</form>
		<p>
		<%
			for(int i=2;i<=9;i++){
					out.print(dan+"*"+i+"="+(Integer.parseInt(dan)*i)+"<br>");
			}	
		%>
</body>
</html>