package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import base.DB;
import base.UserData;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @packageName	: InhaTable.main
 * @fileName	: OrderList.java
 * @author		: SeoJeong Kim
 * @date		: 2024.05.23
 * @description	: 사용자의 지난 예약 내역을 모두 보여주는 화면
 * ==============================================================
 * DATE				AUTHOR				NOTE
 * --------------------------------------------------------------
 * 2024.05.30		SeoJeong Kim		최초 생성
 * 2024.05.30		SeoJeong Kim		기능 구현 완료
 */ 

public class OrderList {

	private JFrame frame;
	private JPanel pan;
	private JButton btnNewButton;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderList window = new OrderList();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public OrderList() throws SQLException {
		initialize();
		DB.init();
	}

	/**pla
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setSize(850,623);
		frame.setBounds(100, 100, 799, 623);
		frame.getContentPane().setBackground(new Color(216, 67, 67));
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 66, 789, 470);
		scrollPane.setToolTipText("");
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scrollPane);
		
		pan = new JPanel();
		pan.setBounds(0, 0, 0, 0);
		pan.setLayout(new GridLayout(0, 1, 0, 0));
		scrollPane.setViewportView(pan);
		
		UserData userdata = new UserData();
		
		String placeName = null, selectedDate = null, 
				selectedTime = null, countHead = null;
		
		ResultSet rs = DB.getResult("SELECT * FROM ORDERS WHERE ORDERS.USER_ID = '" + userdata.getUserId() +"'");
		
		if (rs.next() == false) 
		{
			frame.setSize(850,623);
			JLabel noOrder = new JLabel("예약 내역이 존재하지 않습니다.");
			noOrder.setForeground(Color.gray);
			noOrder.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 16));
			pan.add(noOrder, BorderLayout.CENTER);
			noOrder.setHorizontalAlignment(JLabel.CENTER);
			noOrder.setBackground(Color.gray);
		}
		
		else 
		{ 
			while (true) 
			{
				frame.setSize(850,623);
				placeName = rs.getNString("PLACE");
				selectedDate = rs.getNString("ORDER_DATE");
				selectedTime = rs.getNString("ORDER_TIME");
				countHead = rs.getString("COUNT_HEAD");
			
				String myOrder = "<HTML><p style='font-size: 1.3em;'>예약 상세 정보</p><br>"
						+ "--------------------------------------------------------"
						+ "------------------------------------------------<br><br>"
						+ "[예약 장소] "+ placeName +"<br>"
						+ "[예약 날짜] "+ selectedDate +"<br>"
						+ "[예약 시간] "+ selectedTime +"<br>"
						+ "[예약 인원] "+ countHead +" 인</HTML>";
				JLabel orderLabel = new JLabel(myOrder);
				orderLabel.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 16));
				pan.add(orderLabel, BorderLayout.CENTER);
				orderLabel.setHorizontalAlignment(JLabel.RIGHT);
				orderLabel.setBackground(Color.white);
			
				orderLabel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, 
						new Color(255, 255, 255), new Color(160, 160, 160))));
				
				if(!rs.next())
					break;
			}
		}
		
		pan.setPreferredSize(new Dimension(pan.getWidth(), 
        		pan.getComponentCount() * 250)); 
		
		btnNewButton = new JButton("뒤로가기");
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(Color.white);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				Main main;
				try {
					main = new Main();
					main.getFrame().setVisible(true);
					frame.setVisible(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 13));
		btnNewButton.setBounds(28, 33, 91, 23);
		frame.getContentPane().add(btnNewButton);
		
		rs.close();
	}
	
	

	public JFrame getFrame() {
		return frame;
	}
}
