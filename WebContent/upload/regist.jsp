<%@page import="util.FileManager"%>
<%@page import="java.io.File"%>
<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%
	//업로드란?클라이언트가 전송한 파일 데이터를 서버의 특정 위치에 
	//저장하는것(네트워크+스트림 등 많은 기술이 필요)
	//우리가 선택한 라이브러리는 oreilly 사에서 제작한 책의 예제였던 cos.jar를
	//사용해본다!!
	//request.setCharacterEncoding("utf-8");//파라미터에대한 인코딩 처리
	//String title=request.getParameter("title");
	//out.println("전송한 제목은"+title);
	//아래의 코드는 윈도우에서만 실행되므로 환겨에 지배를 받음..
	//해결책=모든 플랫폼에서 실행되려면 예)리눅스,유닉스,맥 등..
	//경로를 수정해야한다..
	//방법
	//개발자가 경로를 하드코딩하지말자!!!
	//jsp의 내장 객체중 application 내장객체를 이용하면 웹어플리케이션 내의 전역적 정보를 가지고있음으로, 특정 디렉토리의 하드디스크상 풀경로로 구해준다
	//aplication 내장객체의 자료형은? ServletContext
	ServletContext context=request.getServletContext();
	String saveDirectory=context.getRealPath("/data");
	out.print(saveDirectory);
	//String saveDirectory=application.getRealPath("/data");
	int maxSize=5*1024*1024;
	String encoding="UTF-8";
	MultipartRequest multi = new MultipartRequest(request, saveDirectory,maxSize,encoding);
	//방금만들어진 이미지명을 현재날짜를 이용하여 바꾸기!!
	long time=System.currentTimeMillis();
	
	File file=multi.getFile("myfile");
	String ext= FileManager.getExt(file.getName());//업로드된 파일로부터 확장자 구하기
	File destFile = new File(saveDirectory+"/"+time+"."+ext);//바뀐 이름으로앞으로 생성될 파일!!
	
	file.renameTo(destFile);
	//파일 제어능력..
	out.print(destFile.getName());
	
	//제목받기
	String title=multi.getParameter("title");
	out.print("<br>제목은:"+title);
	
	
%>