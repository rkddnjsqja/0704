package util;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

//파일과 관련된 정보 추출에 사용할 공통 클래스!!
public class FileManager {

	//확장자 구하기
	public static String getExt(String path) {
		int index=path.lastIndexOf(".");
		System.out.println(index);
		return path.substring(index+1,path.length());
	}
	/*
	public static void main(String[] args) {
		System.out.println(getExt("dkdk....dskfd.png"));
	}
	*/
}
