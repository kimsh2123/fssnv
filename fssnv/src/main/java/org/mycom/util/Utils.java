package org.mycom.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * 2016.03.04 by sung
 */

public class Utils {
	
	// today 구하기
	public static String today(int style) throws Exception {
		SimpleDateFormat format = null;
		
		switch(style){
		case 1:
			format = new SimpleDateFormat("yyyyMMdd");
			break;
		case 2:
			format = new SimpleDateFormat("yyyy-MM-dd");
			break;
		case 3:
			format = new SimpleDateFormat("yyyy/MM/dd");
			break;	
		}
		Date currenttime = new Date();
		
		return format.format(currenttime);
	}
	
	// 8859_1 -> MS949 변환
	public static String e2k(String str) throws Exception {
		return new String(str.getBytes("KSC5601"),"8859_1");
	}
	
	// MS949 -> 8859_1 변환
	public static String e2kR(String str) throws Exception {
		return new String(str.getBytes("8859_1"),"KSC5601");
	}
	
	// 공백 0으로 채우기 왼쪽
	public static String appendZero(String str, int cou) throws Exception {
		StringBuffer sb = new StringBuffer();
		int len = cou - str.length();
		for(; len > 0; len--){
			sb.append("0");
		}
		sb.append(str);
		return sb.toString();
	}
	
	// 공백 채우기 오른쪽
	public static String appendRString(String str, int cou) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(str);
		int len = cou - str.getBytes().length;
		for(; len > 0; len--){
			sb.append(" ");
		}
		return sb.toString();
	}
	
}
