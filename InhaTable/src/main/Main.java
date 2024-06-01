package main;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.sql.SQLException;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import java.awt.Frame;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import base.DB;
import base.UserData;
import login.Login;
import reservation.Bar;
import reservation.Cafe;
import reservation.Restaurant;
import main.OrderList;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @packageName		: InhaTable.main
 * @fileName		: Main.java
 * @author			: SeoJeong Kim
 * @date			: 2024.05.23
 * @description	: 메인 화면 (로그인 성공시 가장 먼저 나타나는 화면)
 * ============================================================== 
 * DATE				AUTHOR				NOTE 
 * --------------------------------------------------------------
 * 2024.05.23		SeoJeong Kim		최초 생성
 * 2024.05.27		SeoJeong Kim		로그아웃 버튼 기능 구현
 * 2024.05.27		SeoJeong Kim		회원 정보 라벨 생성
 * 2024.05.28		SeoJeong Kim		예약하기 버튼 기능 구현 완료
 */

public class Main {

	private JFrame frame; 
	
	protected JPanel pnPlace;
	private String SelectFrame;

	public JFrame getFrame() {
		return frame;
	}
	
	public String getSelectFrame() {
		return SelectFrame;
	}
	
	protected void makeMenuList() {
		
		String category;
		pnPlace = new JPanel();
		pnPlace.setBackground(new Color(255, 236, 198));
		pnPlace.setLayout(null);
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() throws SQLException {
		MakeMainPanel();
		DB.init();
	}

	private void MakeMainPanel() throws SQLException {
		frame = new JFrame();
		frame.setTitle("예약하기");
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 799, 623);
		frame.setSize(850,623);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JButton btnCafe = new JButton("\n카페 예약하기");
		btnCafe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectFrame = "cafe";
				Cafe cafe = null;
				try {
					cafe = new Cafe();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cafe.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		
		JButton btnOrderList = new JButton("예약내역");
		btnOrderList.setOpaque(true);
		btnOrderList.setForeground(Color.GRAY);
		btnOrderList.setFont(new Font("G마켓 산스 TTF Light", Font.BOLD, 13));
		btnOrderList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnOrderList.setBackground(Color.WHITE);
		btnOrderList.setBounds(619, 33, 84, 50);
		frame.getContentPane().add(btnOrderList);
		btnOrderList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				OrderList orderlist;
				try {
					orderlist = new OrderList();
					orderlist.getFrame().setVisible(true);
					frame.setVisible(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		btnCafe.setForeground(new Color(92, 92, 92));
		btnCafe.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 16));
		btnCafe.setBackground(new Color(255, 255, 255));
		btnCafe.setIcon(new ImageIcon("images\\Main_Button\\donut.png"));
		btnCafe.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnCafe.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCafe.setBounds(296, 138, 237, 415);
		frame.getContentPane().add(btnCafe);
		
		JButton btnBar = new JButton("주점 예약하기");
		btnBar.setForeground(new Color(92, 92, 92));
		btnBar.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 16));
		btnBar.setBackground(new Color(255, 255, 255));
		btnBar.setIcon(new ImageIcon("images\\Main_Button\\champagne.png"));
		btnBar.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnBar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnBar.setBounds(563, 138, 237, 415);
		frame.getContentPane().add(btnBar);
		btnBar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectFrame = "bar";
				Bar bar = null;
				try {
					bar = new Bar();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				bar.getFrame().setVisible(true);
				frame.setVisible(false);
				
			}
		});
		
		JButton btnRest = new JButton("식당 예약하기");
		btnRest.setForeground(new Color(92, 92, 92));
		btnRest.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 16));
		btnRest.setBackground(new Color(255, 255, 255));
		btnRest.setIcon(new ImageIcon("images\\Main_Button\\rice.png"));
		btnRest.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnRest.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRest.setBounds(34, 138, 237, 415);
		frame.getContentPane().add(btnRest);
		btnRest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectFrame = "restaurant";
				Restaurant rest = null;
				try {
					rest = new Restaurant();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				rest.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		
		JLabel labelUserName = new JLabel("회원이름");
		labelUserName.setHorizontalAlignment(SwingConstants.CENTER);
		labelUserName.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 15));
		labelUserName.setBounds(510, 33, 106, 27);
		labelUserName.setText(Login.getUserName()+ " 님");
		frame.getContentPane().add(labelUserName);
		
		JButton btnLogOut = new JButton("로그아웃");
		btnLogOut.setFont(new Font("G마켓 산스 TTF Light", Font.BOLD, 13));
		btnLogOut.setBackground(new Color(255, 255, 255));
		btnLogOut.setForeground(new Color(128, 128, 128));
		btnLogOut.setOpaque(true);
		btnLogOut.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnLogOut.setBounds(711, 32, 89, 50);
		frame.getContentPane().add(btnLogOut);
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActionLogOut();
			}
		});
		
		JLabel labelUserId = new JLabel("회원아이디");
		labelUserId.setHorizontalAlignment(SwingConstants.CENTER);
		labelUserId.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 15));
		labelUserId.setText(Login.getUserId());
		labelUserId.setBounds(510, 56, 106, 27);
		frame.getContentPane().add(labelUserId);
		
		JLabel labelUserBg = new JLabel("");
		labelUserBg.setBackground(new Color(255, 255, 255));
		labelUserBg.setOpaque(true);
		labelUserBg.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		labelUserBg.setBounds(502, 20, 311, 70);
		frame.getContentPane().add(labelUserBg);
		
		JLabel labelName = new JLabel("  INHA TABLE");
		labelName.setForeground(new Color(255, 255, 255));
		labelName.setFont(new Font("G마켓 산스 TTF Bold", Font.PLAIN, 24));
		labelName.setBackground(new Color(241, 67, 67));
		labelName.setOpaque(true);
		labelName.setBounds(0, 0, 836, 107);
		frame.getContentPane().add(labelName);
	}
	
	private void ActionLogOut() {
		int result = JOptionPane.showConfirmDialog(frame, "정말 로그아웃할까요?", "로그아웃", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
            
            JOptionPane.showMessageDialog(frame,"로그아웃 되었습니다.","로그아웃됨",JOptionPane.OK_OPTION);
            try {
				Login login = new Login();
				login.getFrame().setVisible(true);
				frame.setVisible(false);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        } 
	}
}
