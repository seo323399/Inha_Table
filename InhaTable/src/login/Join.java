package login;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import base.DB;
import base.boilerPlate;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * @packageName	: InhaTable.login
 * @fileName	: Join.java
 * @author		: SeoJeong Kim 
 * @date		: 2024.05.23
 * @description	: 회원가입 화면
 * =====================================================================
 * DATE				AUTHOR				NOTE
 * ---------------------------------------------------------------------
 * 2024.05.23		SeoJeong Kim		최초 생성
 * 2024.05.23		SeoJeong Kim		레이아웃 구현 완료
 * 2024.05.24		SeoJeong Kim		아이디 중복확인 버튼 기능 구현 완료
 * 2024.05.26		SeoJeong Kim		비밀번호 확인 버튼 기능 구현 완료
 * 2024.05.26		SeoJeong Kim		인증번호 받기 버튼 기능 구현 완료
 * 2024.05.26		SeoJeong Kim		인증번호 확인 버튼 기능 구현 완료
 * 2024.05.26		SeoJeong Kim		아이디와 비밀번호 유효성 검사 기능 구현 완료
 * 2024.05.27		SeoJeong Kim		가입하기 버튼 기능 구현 완료
 * 2024.05.27		SeoJeong Kim		뒤로가기 버튼, 입력 초기화 버튼 추가
 */ 

public class Join {

	private JFrame frame;
	private JTextField tfName = null;
	
	private String userInId;				//사용자에게 입력 받은 아이디
	private String userInPw;				//사용자에게 입력 받은 비밀번호
	private String userInName; 			// 사용자에게 입력 받은 이름
	private String userInBirthday; 		// 사용자에게 입력 받은 생년월일
	private String userInTel; 			// 사용자에게 입력 받은 전화번호
	private String userInGender;
	private String placeHolder = "전송받은 인증번호를 입력하세요...";
	
	private JTextField tfMakeId;		// 아이디 입력 텍스트 필드
	private JTextField tfMakePw;		// 비밀번호 입력 텍스트 필드
	private JTextField tfCheckPw;		// 비밀번호 확인 텍스트 필드
	private JRadioButton radioF;		// 성별 라디오 버튼 (남)
	private JRadioButton radioM;		// 성별 라디오 버튼 (여)
	private JButton backToLogin_1;
	private JComboBox <String> tel1;				// 전화번호 입력 콤보박스와 텍스트 필드
	private JTextField tel2;			
	private JTextField tel3;
	private JTextField tfCertifiTel;	// 인증번호 입력 텍스트 필드
	
	private JComboBox <Integer> cb_y;				// 생년 콤보박스
	private JComboBox <Integer> cb_m;				// 생월 콤보박스	
	private JComboBox <Integer> cb_d;				// 생일 콤보박스
	
	private JButton btnCkId;			// 아이디 중복확인 버튼
	private JButton btnCerifiTel;		// 인증번호 받기 버튼
	private JButton btnCkPw;			// 비밀번호 확인 버튼
	private JButton btnCktel;			// 인증번호 확인 버튼
	private JLabel labelName;			// "이름"
	private JLabel labelId;				// "아이디"
	private JLabel labelPw;				// "비밀번호"
	private JLabel labelCkPw;			// "비밀번호 확인"
	private JLabel labelBirth;			// "생년월일"
	private JLabel labelTel;			// "전화번호"
	private String[] telIdentList = {"010","011"}; // 식별번호 콤보박스 항목 리스트
	private Integer[] yearList = {0, 2005, 2006, 2005, 2004, 2003, 2002, 2001, 2000, 1999, 1998, // 생년 콤보박스 항목 리스트
			1997, 1996, 1995, 1994, 1993, 1992, 1991, 1990, 1989, 1988, 1987, 1986, 1985, 
			1984, 1983, 1982, 1981, 1980, 1979, 1978, 1977, 1976, 1975, 1974, 1973, 1972, 
			1971, 1970, 1969, 1968, 1967, 1966, 1965, 1964, 1963, 1962, 1961, 1960, 1959, 
			1958, 1957, 1956, 1955, 1954, 1953, 1952, 1951, 1950, 1949, 1948, 1947, 1946, 
			1945, 1944, 1943, 1942, 1941, 1940, 1939, 1938, 1937, 1936, 1935, 1934, 1933, 
			1932, 1931, 1930, 1929, 1928, 1927, 1926, 1925, 1924, 1923, 1922, 1921, 1920, 
			1919, 1918, 1917, 1916, 1915, 1914, 1913, 1912, 1911, 1910, 1909, 1908, 1907, 
			1906, 1905, 1904, 1903};
	private Integer[] monthList = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}; // 생월 콤보박스 항목 리스트
	private Integer[] dayList = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, // 생일 콤보박스 항목 리스트
			17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31}; 
	
	private boolean overlapFlag = false;
	private boolean checkPwFlag = false;
	private boolean telValidationflag = false;
	private int year;
	private int month;
	private int day;
	private int certifiNum;
	
	//getter와 setter 구현-----------------------------------------


	public JFrame getFrame() {
		return frame;
	}
	
	//------------------------------------------------------------
	
	//============================================================
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Join window = new Join();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Join() throws SQLException {
		
		DB.init(); // 데이터베이스 연결
		UIManager.put("OptionPane.messageFont", new Font("G마켓 산스 TTF Light", Font.BOLD, 16));
		makeInputField();
	}
	
	private void makeInputField() throws SQLException {
		
		frame = new JFrame();
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(new Color(216, 67, 67));
		frame.getContentPane().setLayout(null);
		frame.setSize(850,623);
		frame.setBounds(100, 100, 799, 623);
		
		
		btnCktel = new JButton("인증");
		btnCktel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (boilerPlate.certifiNumValidation(tfCertifiTel.getText()) == true) {
					JOptionPane.showMessageDialog(frame, "인증이 완료되었습니다.","",JOptionPane.PLAIN_MESSAGE);
					tel1.setEnabled(false);
					tel2.setEditable(false);
					tel3.setEditable(false);
					btnCktel.setEnabled(false);
					btnCktel.setText("인증완료");
					btnCerifiTel.setEnabled(false);;
					btnCerifiTel.setText("전송완료");
					tfCertifiTel.setEditable(false);
					
					telValidationflag = true;
				}
				
				else {
					JOptionPane.showMessageDialog(frame, "인증번호가 일치하지 않습니다.\n 다시 시도해주세요.");
					tfCertifiTel.setText("");
				}
			}
		});
		
		JButton backToLogin = new JButton("뒤로 가기");
		
		
		backToLogin.getBorder();
		backToLogin.setBorder(null);
		
		backToLogin.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 11));
		backToLogin.setBackground(new Color(255, 255, 255));
		backToLogin.setBounds(73, 483, 87, 21);
		frame.getContentPane().add(backToLogin);
		backToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Login login = new Login();
					login.getFrame().setVisible(true);
					frame.setVisible(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		backToLogin_1 = new JButton("<html><u>입력 초기화</u><html>");
		
		
		backToLogin_1.setForeground(new Color(219, 64, 64));
		backToLogin_1.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 13));
		backToLogin_1.setBorder(null);
		backToLogin_1.setBackground(Color.WHITE);
		backToLogin_1.setBounds(710, 55, 104, 21);
		frame.getContentPane().add(backToLogin_1);
		backToLogin_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					resetAll();
			}
		});
		
		btnCktel.setForeground(Color.WHITE);
		btnCktel.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 12));
		btnCktel.setBackground(Color.GRAY);
		btnCktel.setBounds(681, 396, 120, 41);
		frame.getContentPane().add(btnCktel);
		
		
		//이름 텍스트 필드 생성
		tfName = new JTextField();
		tfName.setToolTipText("이름");
		tfName.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		tfName.setBounds(385, 89, 416, 37);
		frame.getContentPane().add(tfName);
		tfName.setColumns(10);
		
		labelName = new JLabel("이름");
		labelName.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		labelName.setBounds(326, 99, 40, 15);
		frame.getContentPane().add(labelName);
		
		
		// 아이디 텍스트 필드 생성
		tfMakeId = new JTextField();
		tfMakeId.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		tfMakeId.setColumns(10);
		tfMakeId.setBounds(385, 136, 284, 37);
		frame.getContentPane().add(tfMakeId);
		
		labelId = new JLabel("아이디");
		labelId.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		labelId.setBounds(312, 146, 50, 15);
		frame.getContentPane().add(labelId);
		
		
		//아이디 중복확인 버튼 생성
		btnCkId = new JButton("중복확인");
		btnCkId.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					idValidation();							// 아이디 중복 여부를 확인하는 idValidation() 함수 호출
			}
		});
		btnCkId.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 12));
		btnCkId.setForeground(new Color(255, 255, 255));
		btnCkId.setBackground(new Color(128, 128, 128));
		btnCkId.setBounds(681, 136, 120, 37);
		frame.getContentPane().add(btnCkId);

		
		// 비밀번호 텍스트 필드 생성
		tfMakePw = new JTextField();
		tfMakePw.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		tfMakePw.setColumns(10);
		tfMakePw.setBounds(385, 183, 416, 43);
		frame.getContentPane().add(tfMakePw);
		
		labelPw = new JLabel("비밀번호");
		labelPw.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		labelPw.setBounds(299, 196, 67, 15);
		frame.getContentPane().add(labelPw);
		
		
		// 비밀번호 확인 텍스트 필드 생성
		tfCheckPw = new JTextField();
		tfCheckPw.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		tfCheckPw.setColumns(10);
		tfCheckPw.setBounds(385, 236, 284, 37);
		frame.getContentPane().add(tfCheckPw);
		
		labelCkPw = new JLabel("비밀번호확인");
		labelCkPw.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		labelCkPw.setBounds(265, 246, 101, 15);
		frame.getContentPane().add(labelCkPw);
		
		
		// 비밀번호 확인 버튼 생성
		btnCkPw = new JButton("확인");
		btnCkPw.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 12));
		btnCkPw.setForeground(new Color(255, 255, 255));
		btnCkPw.setBackground(new Color(128, 128, 128));
		btnCkPw.setBounds(681, 236, 120, 37);
		frame.getContentPane().add(btnCkPw);
		btnCkPw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		// 비밀번호 일치 여부를 확인하는 pwValidation() 함수 호출
				pwValidation();
			}
		});
		
		//생년월일 콤보박스 생성
		cb_y = new JComboBox<Integer>(yearList);
		cb_y.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		cb_y.setBackground(new Color(255, 255, 255));
		cb_y.setSelectedItem(0);
		cb_y.setBounds(385, 292, 125, 39);
		frame.getContentPane().add(cb_y);
		
		cb_m = new JComboBox<Integer>(monthList);
		cb_m.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		cb_m.setBackground(new Color(255, 255, 255));
		cb_m.setSelectedItem(0);
		cb_m.setBounds(522, 292, 134, 39);
		frame.getContentPane().add(cb_m);
		cb_m.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				dayRestric();
			}
		});
		
		cb_d = new JComboBox<Integer>(dayList);
		cb_d.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		cb_d.setBackground(new Color(255, 255, 255));
		cb_d.setSelectedItem(0);
		cb_d.setBounds(667, 292, 134, 39);
		frame.getContentPane().add(cb_d);
		
		labelBirth = new JLabel("생년월일");
		labelBirth.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		labelBirth.setBounds(295, 303, 67, 15);
		frame.getContentPane().add(labelBirth);
		
		// 전화번호 콤보박스와 텍스트 필드 생성
		tel1 = new JComboBox<String>(telIdentList);
		tel1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		tel1.setBackground(Color.WHITE);
		tel1.setBounds(383, 349, 87, 37);
		frame.getContentPane().add(tel1);
		
		tel2 = new JTextField();
		tel2.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		tel2.setBounds(482, 349, 87, 37);
		frame.getContentPane().add(tel2);
		tel2.setColumns(10);
		
		tel3 = new JTextField();
		tel3.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		tel3.setColumns(10);
		tel3.setBounds(581, 349, 88, 37);
		frame.getContentPane().add(tel3);
		
		labelTel = new JLabel("전화번호");					
		labelTel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		labelTel.setBounds(295, 359, 67, 15);
		frame.getContentPane().add(labelTel);
		
		// 전화번호 인증 텍스트 필드 생성
		
		tfCertifiTel = new JTextField(placeHolder, 20);
		tfCertifiTel.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(tfCertifiTel.getText().equals(placeHolder))
					tfCertifiTel.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(tfCertifiTel.getText().equals(""))
					tfCertifiTel.setText(placeHolder);
			}
		});
		
		tfCertifiTel.setToolTipText("");
		tfCertifiTel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		tfCertifiTel.setColumns(10);
		tfCertifiTel.setBounds(385, 397, 284, 40);
		frame.getContentPane().add(tfCertifiTel);
		
		// 전화번호 인증 버튼 생성
		btnCerifiTel = new JButton("인증번호 받기");
		btnCerifiTel.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 12));
		btnCerifiTel.setForeground(new Color(255, 255, 255));
		btnCerifiTel.setBackground(new Color(128, 128, 128));
		btnCerifiTel.setBounds(681, 349, 120, 37);
		frame.getContentPane().add(btnCerifiTel);
		btnCerifiTel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telValidation();
			}
		});
		
		// 성별 선택 라디오 버튼 생성
		radioM = new JRadioButton("남");
		radioM.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		radioM.setBackground(new Color(255, 255, 255));
		radioM.setBounds(469, 467, 113, 23);
		frame.getContentPane().add(radioM);
		radioM.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			radioF.setSelected(false);
		}
	});
		
		radioF = new JRadioButton("여");
		radioF.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		radioF.setBackground(new Color(255, 255, 255));
		radioF.setBounds(584, 467, 74, 23);
		frame.getContentPane().add(radioF);
		radioF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioM.setSelected(false);
		}
	});
		
		// 가입하기 버튼 생성
		JButton btnJoin = new JButton("가입하기");
	
		btnJoin.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 12));
		btnJoin.setForeground(new Color(255, 255, 255));
		btnJoin.setBackground(new Color(128, 128, 128));
		btnJoin.setBounds(676, 457, 125, 47);
		frame.getContentPane().add(btnJoin);
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnOK();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JLabel whiteLine = new JLabel("");
		whiteLine.setOpaque(true);
		whiteLine.setBorder(null);
		whiteLine.setBackground(Color.WHITE);
		whiteLine.setBounds(243, 0, 593, 586);
		frame.getContentPane().add(whiteLine);
		
		JLabel labelJoin = new JLabel("SIGN UP");
		labelJoin.setForeground(new Color(255, 255, 255));
		labelJoin.setFont(new Font("G마켓 산스 TTF Bold", Font.PLAIN, 35));
		labelJoin.setBounds(34, 31, 208, 142);
		frame.getContentPane().add(labelJoin);
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(850,623);
		frame.setLocationRelativeTo(null);
	}
	
	private void idValidation() { 				// 아이디 중복 제한 기능 메소드
		
		userInId = tfMakeId.getText();
		if(!boilerPlate.idValidation(userInId)) {
			JOptionPane.showMessageDialog(frame," 아이디를 다시 확인해주세요.\n영문, 숫자 조합의 5자리 이상 12자리 이하로 사용 가능하며,\n첫 자리에 숫자 사용이 불가능합니다.");
			tfMakeId.setText("");
		} 
		else {
		ResultSet rs = DB.getResult("select * from USER WHERE ID like '" + userInId + "'");
		
		try {
			
			if(rs.next()) {
				
				System.out.println("(Join) 아이디 중복");
				JOptionPane.showMessageDialog(frame, "이미 사용중인 아이디입니다.\n다시 시도해주세요.", "아이디 중복", JOptionPane.ERROR_MESSAGE);
				
			} else {
				
					System.out.println("(Join) 아이디 사용가능");
					int result = JOptionPane.showConfirmDialog(frame, "사용 가능한 아이디입니다.\n" + userInId + "로 사용하시겠습니까?", "아이디 사용 가능", JOptionPane.YES_NO_OPTION);
					
					if(result == JOptionPane.YES_OPTION) {
						
						tfMakeId.setEnabled(false);			// 아이디 텍스트 필드 비활성화
						btnCkId.setEnabled(false);		// 중복확인 버튼 비활성화
						overlapFlag = true;					// 아이디 중복확인 여부 저장
						
					} else {
						
						tfMakeId.setText("");
						
					}
				}	
			
		} catch (SQLException e) {
			e.printStackTrace();
			}
		
		}

	}
	
	private void pwValidation() { 				// 비밀번호 일치 확인 메소드
		userInPw = tfMakePw.getText();
		
		if(!boilerPlate.pwValidation(userInPw)) {
			JOptionPane.showMessageDialog(frame," 비밀번호를 다시 확인해주세요.\n영문, 숫자, 특수문자 조합의 8자리 이상 15자리 이하로만 사용이 가능합니다.");
			tfMakePw.setText("");
			tfCheckPw.setText("");
		}
		else {
		if(userInPw.equals(tfCheckPw.getText())){
			JOptionPane.showMessageDialog(frame, "비밀번호가 일치합니다.");
			checkPwFlag = true;
			tfMakePw.setEnabled(false);
			tfCheckPw.setEnabled(false);
			btnCkPw.setEnabled(false);
		} else {
			JOptionPane.showMessageDialog(frame, "비밀번호가 일치하지 않습니다.\n다시 입력해주세요.");
			}
		}
	}
	
	private void dayRestric() {		// 선택된 생월에 따라 선택 가능한 생일 항목 수정하는 메소드
			
			month = (int) cb_m.getSelectedItem();
			
			int daysInMonth;
			
			if (month == 2) {
	            daysInMonth = 28;
	        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
	            daysInMonth = 30;
	        } else {
	            daysInMonth = 31;
	        }
			
			cb_d.removeAllItems(); // 일 콤보박스 내용 지우기
	        for (int i = 1; i <= daysInMonth+1; i++) {
	            cb_d.addItem(i-1);
	        }
	}
	
	private void telValidation() { // 인증번호 발송 메소드
		
		if(boilerPlate.telValidation(tel2.getText(), tel3.getText()) == true) {
			makeSimpleTel();
			certifiNum = boilerPlate.certificationNum();
			JOptionPane.showMessageDialog(frame, "인증번호는 ["+certifiNum+"] 입니다.", "( "+userInTel+" )",JOptionPane.PLAIN_MESSAGE);
			}
		
		else {
			JOptionPane.showMessageDialog(frame, "유효하지 않은 전화번호입니다.\n 다시 확인해주세요.");
			tel2.setText("");
			tel3.setText("");
		}
	}
	
	private void makeSimpleTel() { // 전화번호를 이어붙여 한번에 저장하는 메소드
		userInTel = (String) tel1.getSelectedItem() + "-" + tel2.getText() + "-" + tel3.getText();
		
	}
	
	private void btnOK() throws SQLException { // 가입하기 버튼 기능 수행 메소드
		
		//모든 항목이 입력되는지 확인
		if((tfName.getText().equals(""))) JOptionPane.showMessageDialog(frame, "이름 항목을 입력해주세요.");
		else {
			if(overlapFlag != true) JOptionPane.showMessageDialog(frame, "아이디 중복 확인이 필요합니다.");
			else {
				if(checkPwFlag != true) JOptionPane.showMessageDialog(frame, "비밀번호 확인이 필요합니다.");
				else {
					if(((cb_y.getSelectedIndex() == 0) || (cb_m.getSelectedIndex() == 0) || (cb_d.getSelectedIndex() == 0))) JOptionPane.showMessageDialog(frame, "생년월일을 정확히 입력해주세요.");
					else {
						if(telValidationflag != true) JOptionPane.showMessageDialog(frame, "전화번호 인증이 필요합니다.");
						else {
							
							//입력받은 정보 저장
							userInId = tfMakeId.getText();			//입력받은 아이디 저장
							userInPw = tfMakePw.getText();			//입력받은 비밀번호 저장
							userInName = tfName.getText();	
							
							// userInTel의 경우 makeSimpleTel()에서 저장 수행
							
							// 라디오 버튼을 통해 입력받은 성별 정보 저장
							if(radioM.isSelected() == true && radioF.isSelected() == false) {
								userInGender = "남";
							} else userInGender = "여";
							
							String strYear = Integer.toString((int)cb_y.getSelectedItem());
							String strMonth = Integer.toString((int)cb_m.getSelectedItem());
							String strDay = Integer.toString((int)cb_d.getSelectedItem());
							
							//입력받은 생년월일 YYYYMMDD 형태로 저장
							userInBirthday = strYear;
							if (month < 10) {
							    userInBirthday += "0" + strMonth;
							} else {
							    userInBirthday += strMonth;
							}

							if (day < 10) {
							    userInBirthday += "0" + strDay;
							} 
							else if (day == 10){
								userInBirthday += "10";
							}
							else {
								userInBirthday += strDay;
							}
							
							String sqlInsert = "INSERT INTO USER (NAME, ID, PASSWORD, TEL, GENDER, BIRTH)"  // insert문 선언
									+ " VALUES('" + userInName + "', '" + userInId + "', '" + userInPw + "', '" + userInTel + "', '" + userInGender + "', '" + userInBirthday + "')";	//회원정보 Insert문 생성
							DB.executeSQL(sqlInsert);		//DB로 insert문 삽입
							
							JOptionPane.showMessageDialog(frame, "회원가입 되었습니다.\n다시 로그인 해주세요.");
							Login login = new Login();  		
							login.getFrame().setVisible(true);		// Login 폼 띄우기
							frame.setVisible(false);
							
							UIManager.put("OptionPane.messageFont", new Font("G마켓 산스 TTF Light", Font.BOLD, 16));
						}
					}
				}
			}
		}
	}
	
	private void resetAll() {
		tfName.setText("");	//이름 입력 텍스트 필드
		tfMakeId.setText(""); // 아이디 입력 텍스트 필드
		tfMakeId.setEnabled(true);
		btnCkId.setEnabled(true);
		tfMakePw.setText("");	// 비밀번호 입력 텍스트 필드
		tfCheckPw.setText("");	// 비밀번호 확인 텍스트 필드
		tfMakePw.setEnabled(true);
		tfCheckPw.setEnabled(true);
		btnCkPw.setEnabled(true);
		cb_y.setSelectedIndex(0);			// 생년 콤보박스
		cb_m.setSelectedIndex(0);			// 생월 콤보박스	
		cb_d.setSelectedIndex(0);			// 생일 콤보박스
		tel1.setSelectedIndex(0);			// 전화번호 입력 콤보박스와 텍스트 필드
		tel2.setText("");
		tel3.setText("");
		tel1.setEnabled(true);
		tel2.setEnabled(true);
		tel2.setEditable(true);
		tel3.setEnabled(true);
		tel3.setEditable(true);
		btnCerifiTel.setEnabled(true);	// 인증번호 입력 텍스트 필드
		btnCerifiTel.setText("인증번호 받기");
		tfCertifiTel.setEnabled(true);
		tfCertifiTel.setEditable(true);
		tfCertifiTel.setText(placeHolder);
		btnCktel.setEnabled(true);
		btnCktel.setText("인증");
		radioF.setSelected(false);		// 성별 라디오 버튼 (남)
		radioM.setSelected(false);
	}
}
			
