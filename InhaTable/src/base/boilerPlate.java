package base;

import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

/**
* @packageName	: orderhere.common
* @fileName		: Boilerplate.java
* @author		: TaeJeong Park
* @date			: 2022.05.21
* @description	: 유효성 검사를 위한 클래스
* ===========================================================
* DATE				AUTHOR				NOTE
* -----------------------------------------------------------
* 2022.05.26		SeoJeong Kim		최초 생성
* 2022.05.27		SeoJeong Kim		기능 구현 완료  
* 2022.05.27		SeoJeong Kim		클래스 설명 수정  
*/

public class boilerPlate {

	// ID 유효성 검사 : 영문, 숫자 조합의 5자리 이상 12자리 이하로 사용 가능하며, 첫 자리에 숫자 사용 불가능
	public static boolean idValidation(String str) {
		
		return Pattern.matches("^[a-zA-Z0-9]{5,12}$", str) && !Pattern.matches("^[0-9]$", str.substring(0, 1));
		
	}
	
	// PW 유효성 검사 : 영문, 숫자, 특수문자 조합의 8자리 이상 15자리 이하로 사용 가능
	public static boolean pwValidation(String str) {
		
		return Pattern.matches("^[a-zA-Z0-9!@#$]{8,15}$", str);
		
	}
	
	// 이름 유효성 검사 : 한글 2자리 이상 10자리 이하로 사용 가능
	public static boolean nameValidation(String str) {
		
		return Pattern.matches("^[가-하]{2,10}$", str);
		
	}

	// 생년월일 유효성 검사 : 숫자 8자리 사용 가능
	public static boolean birthdayValidation(String str) {
		
		return Pattern.matches("^[0-9]{8,8}$", str);
		
	}
	
	// 숫자 6자리 인증번호 생성
	public static int certificationNum() {
		
        return ThreadLocalRandom.current().nextInt(100000, 1000000);
        
    }
	
	// 인증번호 유효성 검사 : 숫자 6자리
	public static boolean certifiNumValidation(String str) {
			
			return Pattern.matches("^[0-9]{6}$", str);
			
		}
	
	// 전화번호 자릿수 유효성 검사
	public static boolean telValidation(String tel2, String tel3) { 	
		
		if(Pattern.matches("^[0-9]{3,4}$", tel2) && (Pattern.matches("^[0-9]{4}$", tel3))) { 
			return true;
		}
		
		else return false;
	}
	
	
	
}