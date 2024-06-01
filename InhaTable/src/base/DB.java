package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	
	public static Connection conn;
	public static Statement stmt;
	public ResultSet rs;
	
	
	/**
	 *  @packageName	: InhaTable.base
	 * @fileName		: DB.java
	 * @author		: SeoJeong Kim
	 * @date			: 2024.05.23
	 * @description	: DB 생성 클래스
	 * ==============================================================
	 * DATE				AUTHOR				NOTE
	 * --------------------------------------------------------------
	 * 2024.05.23		SeoJeong Kim		최초 생성
	 * 2024.05.23		SeoJeong Kim		DB 연결 메소드 구현 완료
	 */
	
	public static void init() throws SQLException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/inhaTable", 
					"root", 
					"1234");
			
			stmt = conn.createStatement();
			
			System.out.println("(DB) DB 연결 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("(DB) 예외 발생 : 해당 드라이버가 없습니다.");
			e.printStackTrace();
			
		} catch (SQLException e) {
			System.out.println("(DB) 예외 발생 : 접속 정보 확인이 필요합니다.");
			e.printStackTrace();
			
		}
	}
	  
		// 조회용
		public static ResultSet getResult(String sql) {
			try {
				stmt = conn.createStatement();
				System.out.println("(DB) Statement 객체 생성 성공");
				return stmt.executeQuery(sql);
			} catch (SQLException e) {
				System.out.println("(DB) 예외 발생 : DB 조회에 실패했습니다.");
				e.printStackTrace();
				return null;
			}
		} 
		
		// 수정용
		public static void executeSQL(String sql) {
			try {
				stmt = conn.createStatement();
				System.out.println("(DB) Statement 객체 생성 성공");
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				System.out.println("(DB) 예외 발생 : DB 수정에 실패했습니다.");
				e.printStackTrace();
				
			}
		} 
		
		// DB 연결 종료
		public static void closeDB(Connection conn, Statement stmt) {
			try {
				stmt.close();
				conn.close();
				System.out.println("(DB) DB 연결 종료 성공");
			} catch (SQLException e) {
				System.out.println("(DB) 예외 발생 : DB 연결 종료에 실패했습니다.");
				e.printStackTrace();
				
			}
		}
}
