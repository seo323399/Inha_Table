package base;

/**
* @packageName	: InhaTable.base
* @fileName		: UserData.java
* @author		: SeoJeong Kim
* @date			: 2022.05.27
* @description	: 로그인 회원 정보를 담고 있는 클래스
* ===========================================================
* DATE				AUTHOR				NOTE
* -----------------------------------------------------------
* 2024.05.27		SeoJeong Kim		최초 생성
* 2024.05.27		SeoJeong Kim		필드 생성 완료
* 2024.05.27		SeoJeong Kim		메소드 구현 완료 
*/

public class UserData {	

	private static String userName;
	private static String userId;
	private static String userTel;
	
	public UserData() {
		this(userName,userId,userTel);
	}
	
	public String getUserName() {
		return userName;
	}

	public String getUserId() {
		return userId;
	}
	
	public String getUserTel() {
		return userTel;
	}
	
	public UserData(String userName, String userId, String userTel) {
		UserData.userName = userName;
		UserData.userId = userId;
		UserData.userTel = userTel;
		
		System.out.println(userName);
	}
}
