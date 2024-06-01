package main;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import reservation.Restaurant;

/**
* @packageName	: InhaTable.main
* @fileName		: PlaceListPanel.java
* @author		: SeoJeong Kim
* @date			: 2024.05.27
* @description	: 예약 아이템 리스트를 만들기 위한 클래스
* ===========================================================
* DATE				AUTHOR				NOTE 
* -----------------------------------------------------------
* 2024.05.27		SeoJeong Kim		최초 생성
* 2022.05.28		SeoJeong Kim		기능 구현 완료
*/

public class PlaceListPanel extends JPanel {
	
	private static JButton btn;

	public PlaceListPanel(String Id, String Name, String Address, String tel, String category, Restaurant restaurant) {
		
		setSize(286, 100);
		setBackground(new Color(255, 236, 198));
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		setLayout(null);
		
		// 식당 이름
		JLabel lblPlaceName = new JLabel(Name);
		lblPlaceName.setFont(new Font("맑은고딕", Font.BOLD, 15));	//콤보박스 폰트 설정
		lblPlaceName.setSize(164, 20);
		lblPlaceName.setLocation(99, 12);
		
		add(lblPlaceName);
		
		//메뉴 가격
		JLabel lblPlaceAddress = new JLabel(Address);
		lblPlaceAddress.setFont(new Font("맑은고딕", Font.BOLD, 15));	//콤보박스 폰트 설정
		lblPlaceAddress.setSize(164, 20);
		lblPlaceAddress.setLocation(90, 68);
		
		add(lblPlaceAddress);
		
		
		//메뉴 이미지
		//JLabel lblMenuImg = new JLabel(new ImageIcon("images/menu/" + category + "/" + memuImage));
		//lblMenuImg.setSize(75, 75);
		//lblMenuImg.setLocation(12, 12);
		
		//add(lblMenuImg);
		
		btn = new JButton(new ImageIcon("images/main/Btn_Menu_EnabledTrue.png"));
		btn.setRolloverIcon(new ImageIcon("images/main/Btn_Menu_Rollover.png"));
		btn.setPressedIcon(new ImageIcon("images/main/Btn_Menu_Pressed.png"));
		btn.setName(Name);
		btn.setFont(new Font(null, Font.PLAIN, 0));
		btn.setSize(286, 100);
		btn.setLocation(0, 0);
		btn.setBorderPainted(false);		//버튼 테두리 설정
		btn.setContentAreaFilled(false);	//버튼 배경 표시 설정
		btn.setFocusPainted(false);			//포커스 테두리 표시 설정
		btn.setOpaque(false);				//투명하게 설정
		//btn.addActionListener();
		
	}

	public static JButton getBtn() {
		
		return btn;
	}
}