package login;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import base.DB;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * @packageName	: InhaTable.login
 * @fileName	: Find.java
 * @author		: SeoJeong Kim
 * @date		: 2024.05.23
 * @description	: 아이디와 비밀번호 찾기 화면
 * ============================================================
 * DATE				AUTHOR				NOTE
 * ------------------------------------------------------------
 * 2024.05.23		SeoJeong Kim		최초 생성
 * 2024.05.31		SeoJeong Kim		레이아웃 구현 완료
 * 2024.05.31		SeoJeong Kim		기능 구현 완료
 */ 
 
public class Find extends JFrame { 
	
	private JFrame frame;
	
	private String placeholder_Name = "사용자 이름 입력...";
	private String placeholder_Id = "사용자 아이디 입력...";
	private String userInName;
	private String userInTel;
	
	private String[] telIdentList = {"-", "010","011"}; // 식별번호 콤보박스 항목 리스트
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
	
	private JTextField tel_2;
	private JTextField tel_3;
	private JTextField tfUserName;
	private JTextField tfUserId;
	private JComboBox<String> tel_1;
	private JComboBox<Integer> cbd;
	private JComboBox<Integer> cbm;
	private JComboBox<Integer> cby;
	
	//getter와 setter 구현-------------------------------------------
	
		public JFrame getFrame() {
			return frame;
		}
	//=============================================================	

	public static void main(String[] arg) { 
		EventQueue.invokeLater(new Runnable() {
			public void run() { 
				try {
					Find window = new Find();
					window.frame.setVisible(true);
				} catch (Exception e) { 
					e.printStackTrace();
				}
			}
		});
	}
	
	public Find() throws SQLException {
		UIManager.put("OptionPane.messageFont", new Font("G마켓 산스 TTF Light", Font.BOLD, 16));
		DB.init();
		 makeInputField();
	}

	private void makeInputField()  {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(850,623);
		frame.getContentPane().setBackground(new Color(216, 67, 67));
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
	//아이디 찾기		
		JButton btnFindId = new JButton("확인");
		btnFindId.setForeground(new Color(255, 255, 255));
		btnFindId.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 12));
		btnFindId.setBackground(new Color(111, 111, 111));
		btnFindId.setBounds(96, 408, 266, 32);
		frame.getContentPane().add(btnFindId);
		btnFindId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					OKForFindId();
				} catch (HeadlessException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		// 이름 입력 텍스트 필드
		JLabel labelUserName = new JLabel("이름");
		labelUserName.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 13));
		labelUserName.setBounds(124, 252, 211, 32);
		frame.getContentPane().add(labelUserName);
		
		tfUserName = new JTextField();
		tfUserName.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		tfUserName.setText(placeholder_Name);
		tfUserName.setBounds(124, 278, 211, 35);
		frame.getContentPane().add(tfUserName);
		tfUserName.setColumns(10);
		tfUserName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(tfUserName.getText().equals(""))
					tfUserName.setText(placeholder_Name);
			}
			@Override
			public void focusGained(FocusEvent e) {
				if(tfUserName.getText().equals(placeholder_Name))
					tfUserName.setText("");
			}
		});
		
		//전화번호 입력 라벨과 콤보박스, 텍스트 필드
		JLabel labelUserTel = new JLabel("전화번호");
		labelUserTel.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 13));
		labelUserTel.setBounds(124, 313, 211, 32);
		frame.getContentPane().add(labelUserTel);
		
		tel_1 = new JComboBox<String>(telIdentList);
		tel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		tel_1.setBackground(new Color(255, 255, 255));
		tel_1.setBounds(124, 340, 67, 32);
		frame.getContentPane().add(tel_1);
		
		tel_2 = new JTextField();
		tel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		tel_2.setBounds(195, 340, 67, 32);
		frame.getContentPane().add(tel_2);
		tel_2.setColumns(10);
		
		tel_3 = new JTextField();
		tel_3.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		tel_3.setColumns(10);
		tel_3.setBounds(268, 340, 67, 32);
		frame.getContentPane().add(tel_3);
		
	// 비밀번호 찾기
		JButton btnFindPw = new JButton("확인");
		btnFindPw.setForeground(new Color(255, 255, 255));
		btnFindPw.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 12));
		btnFindPw.setBackground(new Color(111, 111, 111));
		btnFindPw.setBounds(481, 408, 266, 32);
		frame.getContentPane().add(btnFindPw);
		btnFindPw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					OKForFindPw();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JLabel labelUserId = new JLabel("아이디");
		labelUserId.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 13));
		labelUserId.setBounds(509, 252, 211, 32);
		frame.getContentPane().add(labelUserId);
		
		// 아이디 텍스트 필드
		tfUserId = new JTextField();
		tfUserId.setText("사용자 아이디 입력...");
		tfUserId.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		tfUserId.setColumns(10);
		tfUserId.setBounds(509, 278, 211, 35);
		frame.getContentPane().add(tfUserId);
		// 포커스 리스너 : placeholder 삭제와 추가 이벤트
		tfUserId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(tfUserId.getText().equals(""))
					tfUserId.setText(placeholder_Id);
			}
			@Override
			public void focusGained(FocusEvent e) {
				if(tfUserId.getText().equals(placeholder_Id))
					tfUserId.setText("");
			}
		});
		
		// 생년월일 입력 라벨과 콤보박스
		JLabel labelUserBirth = new JLabel("생년월일");
		labelUserBirth.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 13));
		labelUserBirth.setBounds(509, 313, 211, 32);
		frame.getContentPane().add(labelUserBirth);
				
		cby = new JComboBox<Integer>(yearList);
		cby.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		cby.setBackground(new Color(255, 255, 255));
		cby.setBounds(509, 340, 67, 32);
		frame.getContentPane().add(cby);
				
		cbm = new JComboBox<Integer>(monthList);
		cbm.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		cbm.setBackground(new Color(255, 255, 255));
		cbm.setBounds(580, 340, 67, 32);
		frame.getContentPane().add(cbm);
		cbm.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				dayRestric();
			}
		});
				
		cbd = new JComboBox<Integer>(dayList);
		cbd.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		cbd.setBackground(new Color(255, 255, 255));
		cbd.setBounds(653, 340, 67, 32);
		frame.getContentPane().add(cbd);		
		
	// 텍스트 라벨
		JLabel labelGroupFindPw = new JLabel("");
		labelGroupFindPw.setOpaque(true);
		labelGroupFindPw.setBounds(481, 239, 266, 171);
		frame.getContentPane().add(labelGroupFindPw);
		
		JLabel labelGroupFindId = new JLabel("");
		labelGroupFindId.setOpaque(true);
		labelGroupFindId.setBounds(96, 239, 266, 171);
		frame.getContentPane().add(labelGroupFindId);
		
		JLabel labelFindId_1_1 = new JLabel("사용자 아이디와 생년월일을 입력하세요.");
		labelFindId_1_1.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 14));
		labelFindId_1_1.setBounds(491, 169, 256, 32);
		frame.getContentPane().add(labelFindId_1_1);
		
		JLabel labelFindId_1 = new JLabel("사용자 이름과 전화번호를 입력하세요.");
		labelFindId_1.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 14));
		labelFindId_1.setBounds(118, 169, 244, 32);
		frame.getContentPane().add(labelFindId_1);
		
		JLabel labelFindPw = new JLabel("비밀번호를 잊어버리셨나요?");
		labelFindPw.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 17));
		labelFindPw.setBounds(509, 145, 211, 32);
		frame.getContentPane().add(labelFindPw);
		
		JLabel labelFindId = new JLabel("아이디를 잊어버리셨나요?");
		labelFindId.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 17));
		labelFindId.setBounds(134, 145, 197, 32);
		frame.getContentPane().add(labelFindId);
		
		JLabel labelBgPw = new JLabel("");
		labelBgPw.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		labelBgPw.setBounds(421, 77, 378, 438);
		frame.getContentPane().add(labelBgPw);
		
		JLabel labelBgId = new JLabel("");
		labelBgId.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		labelBgId.setBounds(40, 77, 373, 438);
		frame.getContentPane().add(labelBgId);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(23, 61, 790, 471);
		frame.getContentPane().add(lblNewLabel);
		
		// 뒤로가기 버튼
		JButton btnNewButton = new JButton("뒤로가기");
		btnNewButton.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 13));
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(26, 28, 91, 23);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
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
	}
	
	// 비밀번호 찾기 확인 버튼 
	private void OKForFindPw() throws SQLException {
		if (tfUserId.getText().equals(placeholder_Id)) {
			JOptionPane.showMessageDialog(frame, "아이디를 입력해주세요.");
		}
		
		else {
			
			if(cby.getSelectedItem().equals(0) || cbm.getSelectedItem().equals(0) || cbd.getSelectedItem().equals(0))
				JOptionPane.showMessageDialog(frame, "생년월일을 정확히 입력해주세요.");
			
			else {
				String userInId = tfUserId.getText();
				String strYear = Integer.toString((int)cby.getSelectedItem());
				String strMonth = Integer.toString((int)cbm.getSelectedItem());
				String strDay = Integer.toString((int)cbd.getSelectedItem());
			
				int month = Integer.parseInt(strMonth);
				int day = Integer.parseInt(strDay);
			
				String userInBirthday = strYear;
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
				String userInBirth = (String) userInBirthday;
				String sqlSelect = "SELECT * FROM USER WHERE ID = '"+userInId+"' AND BIRTH = '"+userInBirth+"'";
				ResultSet rs = DB.getResult(sqlSelect);
				
				String userId, userPw;
				if(rs.next()) {
					userId = rs.getString("ID");
					userPw = rs.getString("PASSWORD");
					
					JOptionPane.showMessageDialog(frame, userId + "님의 비밀번호는\n["+ userPw +"] 입니다.", "회원 정보 조회 성공", JOptionPane.PLAIN_MESSAGE);
				}
				
				else {
					JOptionPane.showMessageDialog(frame, "입력하신 내용과 일치하는 회원정보가 존재하지 않습니다.");
				}
			}
		}
	}
		
	private void OKForFindId() throws HeadlessException, SQLException {
		
		if (tfUserName.getText().equals(placeholder_Name)) {
			JOptionPane.showMessageDialog(frame, "이름을 입력해주세요.");
		}
		
		else {
			
				if(tel_1.getSelectedItem().equals("-") || tel_2.getText().equals("") || tel_3.getText().equals(""))
					JOptionPane.showMessageDialog(frame, "전화번호를 정확히 입력해주세요.");
			
				else {
					userInName = tfUserName.getText();
					userInTel = (String) tel_1.getSelectedItem() + "-" + tel_2.getText() + "-" + tel_3.getText();
					
					String sqlSelect = "SELECT * FROM USER WHERE NAME = '"+ userInName +"' AND TEL = '"+ userInTel +"'";
					ResultSet rs = DB.getResult(sqlSelect);
					
					String userName, userId, userTel;
					if(rs.next()) {
						userName = rs.getString("NAME");
						userId = rs.getString("ID");
						userTel = rs.getString("TEL");
						
						JOptionPane.showMessageDialog(frame, userName+"("+userTel+") 님의 아이디는\n["+ userId +"] 입니다.", "회원 정보 조회 성공", JOptionPane.PLAIN_MESSAGE);
					}
					
					else {
						JOptionPane.showMessageDialog(frame, "입력하신 내용과 일치하는 회원정보가 존재하지 않습니다.");
				}
			}
		}
	}
	
	private void dayRestric() {		// 선택된 생월에 따라 선택 가능한 생일 항목 수정하는 메소드
		
		int month = (int) cbm.getSelectedItem();
		
		int daysInMonth;
		
		if (month == 2) {
            daysInMonth = 28;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            daysInMonth = 30;
        } else {
            daysInMonth = 31;
        }
		
		cbd.removeAllItems(); // 일 콤보박스 내용 지우기
        for (int i = 1; i <= daysInMonth+1; i++) {
            cbd.addItem(i-1);
        }
	}
}