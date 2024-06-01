package login;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import base.DB;
import base.UserData;
import main.Main;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @packageName	: InhaTable.login
 * @fileName	: Login.java
 * @author		: SeoJeong Kim
 * @date		: 2024.05.22
 * @description	: 로그인 화면
 * =============================================================
 * DATE				AUTHOR				NOTE
 * -------------------------------------------------------------
 * 2024.05.22		SeoJeong Kim		최초 생성
 * 2024.05.23		SeoJeong Kim		레이아웃 구현 완료
 * 2024.05.23		SeoJeong Kim		로그인 버튼 기능 구현 완료
 * 2024.05.24		SeoJeong Kim		회원가입 버튼 기능 구현 완료
 * 2024.05.31		SeoJeong Kim		ID/PW 찾기 버튼 기능 구현 완료
 */

public class Login {

	// 변수 선언 필드
	private JFrame frame;
	
	private String usersId;				//회원 아이디
	private String usersPw;
	private static String userId;
	private static String userName;
	private String userGender;
	private String userTel;
	
	private JLabel IDLine;
	private JLabel PWLine;
	private JLabel loginTitle;
	private JLabel labelPW;
	private JLabel labelID;
	
	private JTextField tfID;
	private JPasswordField tfPW;
	private JLabel titleBT;
	
	private JButton btnJoin;
	private JButton btnLogin;
	private JButton btnFind;
	
	private UserData userdata;

	
	
	//getter와 setter 구현-----------------------------------------
	
	public static String getUserId() {
		return userId;
	}
	
	public static String getUserName() {
		return userName;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	//------------------------------------------------------------
	
	//============================================================
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() throws SQLException {
		
		DB.init();
		UIManager.put("OptionPane.messageFont", new Font("G마켓 산스 TTF Light", Font.BOLD, 16));
		makeLoginInput();
		
	}

	private void makeLoginInput() {		// 로그인 레이아웃 구현 메소드 
		
		frame = new JFrame();
		frame.setTitle("Login");
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setLayout(null);
		frame.setSize(850,623);
		frame.setBounds(100, 100, 799, 623);
		
		loginTitle = new JLabel("INHA TABLE"); 						// 타이틀 라벨 생성
		loginTitle.setFont(new Font("G마켓 산스 TTF Bold", Font.PLAIN, 62));
		loginTitle.setForeground(new Color(255, 255, 255));
		loginTitle.setBounds(192, 139, 473, 118);
		frame.getContentPane().add(loginTitle);
        
        // 로그인 버튼
        btnLogin = new JButton("로그인");
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnLogin.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 12));
		btnLogin.setBackground(new Color(128, 128, 128));
		btnLogin.setBounds(518, 267, 68, 76);
		frame.getContentPane().add(btnLogin);
		btnLogin.addActionListener(new ActionListener() { 		// 버튼을 누르면 loginAction() 메소드 실행
			public void actionPerformed(ActionEvent e) {
				loginAction();
			}
		});
		
		frame.setFocusable(true);
        frame.requestFocusInWindow();
        frame.addKeyListener(new KeyAdapter() {			// JFrame에 키 리스너를 추가해 포커스 없이도 키 이벤트가 실행되도록 함
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginAction();
                }
            }
        });
        
		//아이디 라벨과 텍스트 필드
		labelID = new JLabel("아이디 ");
		labelID.setForeground(new Color(255, 255, 255));
		labelID.setHorizontalAlignment(SwingConstants.CENTER);
		labelID.setOpaque(true);
		labelID.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 13));
		labelID.setBackground(new Color(128, 128, 128));
		labelID.setBounds(239, 267, 68, 31);
		frame.getContentPane().add(labelID);
		
		tfID = new JTextField();
		tfID.setToolTipText(" ");
		tfID.setBorder(new CompoundBorder());
		tfID.setBounds(319, 271, 192, 20);
		frame.getContentPane().add(tfID);
		tfID.setColumns(10);
		
		tfID.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginAction();
                }
            }
        });
		
		IDLine = new JLabel("_______________________________________"); // 
		IDLine.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		IDLine.setBounds(319, 271, 233, 38);
		frame.getContentPane().add(IDLine);
		
		//비밀번호 라벨과 텍스트 필드
		labelPW = new JLabel("비밀번호");
		labelPW.setForeground(new Color(255, 255, 255));
		labelPW.setHorizontalAlignment(SwingConstants.CENTER);
		labelPW.setOpaque(true);
		labelPW.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 13));
		labelPW.setBackground(new Color(128, 128, 128));
		labelPW.setBounds(239, 308, 68, 31);
		frame.getContentPane().add(labelPW);
		
		tfPW = new JPasswordField();
		tfPW.setBorder(new CompoundBorder());
		tfPW.setBounds(319, 308, 192, 20);
		frame.getContentPane().add(tfPW);
		
		 tfPW.addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyPressed(KeyEvent e) {
	                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	                    loginAction();
	                }
	            }
	        });
		
		PWLine = new JLabel("_______________________________________"); // 
		PWLine.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		PWLine.setBounds(319, 308, 233, 44);
		frame.getContentPane().add(PWLine);
		
		// 가입 버튼
		btnJoin = new JButton("회원가입");
		btnJoin.setForeground(new Color(97, 97, 97));
		btnJoin.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnJoin.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 12));
		btnJoin.setBackground(new Color(255, 255, 255));
		btnJoin.setBounds(390, 352, 68, 38);
		frame.getContentPane().add(btnJoin);
		btnJoin.addActionListener(new ActionListener() { 		// 회원가입 버튼 클릭시 발생하는 이벤트
			public void actionPerformed(ActionEvent e) {
				try {
					Join join = new Join();						// 회원가입 버튼 클릭시 현재 프레임을 닫고 Join.java의 프레임을 보임
					join.getFrame().setVisible(true);
					frame.setVisible(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
			}
			
		}
	});
		
		// 아이디와 비밀번호 찾기 버튼
		btnFind = new JButton("아이디/비밀번호 찾기");
		btnFind.setForeground(new Color(97, 97, 97));
		btnFind.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnFind.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 12));
		btnFind.setBackground(new Color(255, 255, 255));
		btnFind.setBounds(460, 352, 126, 38);
		frame.getContentPane().add(btnFind);
		btnFind.addActionListener(new ActionListener() {		// 아이디/비밀번호 찾기 버튼 클릭시 발생하는 이벤트
			public void actionPerformed(ActionEvent e) {
				try {
					Find find = new Find();
					find.getFrame().setVisible(true);
					frame.setVisible(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JLabel whiteLine = new JLabel(""); // 상단 흰색 바 생성
		whiteLine.setOpaque(true); // 배경색이 보이게 설정
		whiteLine.setBorder(null); // 테두리 없음으로 변경
		whiteLine.setBackground(new Color(255, 255, 255)); 
		whiteLine.setBounds(193, 227, 431, 186);
		frame.getContentPane().add(whiteLine);
		
		titleBT = new JLabel("");
		titleBT.setBorder(null);
		titleBT.setBackground(new Color(216, 67, 67));
		titleBT.setOpaque(true);
		titleBT.setBounds(0, 0, 836, 586);
		frame.getContentPane().add(titleBT);
		frame.setBounds(100, 100, 784, 529);
		frame.setSize(850,623);
		frame.setLocationRelativeTo(null);
		
	}
	
	private void loginAction() { 		// 로그인 버튼 클릭시 실행할 액션을 구현하는 메소드
		String userInId = tfID.getText();
		String userInPw = new String(tfPW.getPassword());
		ResultSet rs = DB.getResult("SELECT * FROM USER WHERE ID = '" + userInId + "'");
		
		try {
			
			if(rs.next()) {
				usersId = rs.getString("ID");			//조회 결과 데이터(회원 아이디) 저장
				usersPw = rs.getString("PASSWORD");			//조회 결과 데이터(회원 비밀번호) 저장
				
				if(usersId.equals(userInId) && usersPw.equals(userInPw)) {
					System.out.println("(Login) 로그인 성공");
					
					userId = usersId;
					userGender = rs.getString("GENDER");
					userTel = rs.getString("TEL");
					userName = rs.getString("NAME");
					
					JOptionPane.showMessageDialog(frame, "로그인 되었습니다.");
					Main main = new Main();
					userdata = new UserData(userName, userId, userTel); //  입력받은 사용자 정보를 UserData에 저장
					main.getFrame().setVisible(true);
					frame.setVisible(false);
					
				} else {
					tfID.setText(""); 		// 텍스트 필드에 적힌 내용 초기화
					tfPW.setText("");
					JOptionPane.showMessageDialog(frame, "아이디 또는 비밀번호가 일치하지 않습니다.\n 다시 확인해주세요.");
				}
			}
			
			else {
				tfID.setText("");		// 텍스트 필드에 적힌 내용 초기화
				tfPW.setText("");
				JOptionPane.showMessageDialog(frame, "아이디와 비밀번호를 확인하세요.");
			}
			
		} catch (SQLException e) {
				System.out.println("(Login) 예외발생 : 회원 조회에 실패했습니다.");
			e.printStackTrace();
		}
	}
}
