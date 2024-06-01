package reservation;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import base.DB;
import base.UserData;
import main.Main;

/**
* @packageName	: InhaTable.reservation
* @fileName		: ReservScreen.java
* @author		: SeoJeong Kim
* @date			: 2024.05.28
* @description	: 장소 선택 후 예약 진행 화면
* ===========================================================
* DATE				AUTHOR				NOTE
* -----------------------------------------------------------
* 2024.05.28		SeoJeong Kim		최초 생성
* 2024.05.29		SeoJeong Kim		레이아웃 구현 완료 
* 2024.05.30		SeoJeong Kim		기능 구현 완료		
*/

public class ReservScreen {

	// 변수 선언 필드       
	private JFrame frame;
	private JFrame previousFrame;
	
	private int headCount;
	private String[] yearList = {"-", "2024", "2025"}; // 년 콤보박스 항목 리스트
	private Integer[] monthList = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}; // 월 콤보박스 항목 리스트
	private Integer[] dayList = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, // 일 콤보박스 항목 리스트
			17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
	private Integer[] hourList = {0, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24};
	private Integer[] minuteList = {0, 30};
	private String PlaceId; 	// 선택한 장소의 아이디 (DB 내의 아이디)
	private String PlaceName;		//선택한 장소의 이름
	private String PlaceAddress; 		// 선택한 장소의 주소 
	private String PlaceTel;		// 선택한 장소의 전화번호
	private String category;		// 장소의 카테고리
	private String selectedYear; 		// 사용자가 선택한 년도
	private String selectedMonth;		// 사용자가 선택한 월
	private String selectedDay;		// 사용자가 선택한 일
	private String selectedHour;		// 사용자가 선택한 시간
	private String selectedMinute;		// 사용자가 선택한 분
	private String selectedDate;		// 사용자가 선택한 년도, 월, 일을 모두 저장하는 날짜 변수
	private String selectTime;			// 사용자가 선택한 시간, 분을 입력받아 모두 저장하는 시간 변수
		
	private JLabel labelPlaceName;
	private JLabel labelPlaceAddress;
	private JLabel imageLabel;
	private JLabel selecterLayout;
	private JLabel labelSelectDate;
	private JLabel labelSelectTime;
	private JLabel labelSelectCountHead;
	private JLabel labelCountHead;
	private JLabel labelHour;
	private JLabel labelYear;
	private JLabel labelMonth;
	private JLabel labelDay;
	private JLabel lblBackGround;
	private JLabel labelMinute;
	
	private JButton backToMain;		
	private JButton btnCall;
	private JButton BtnMenu;
	private JButton btnFinal;
	private JComboBox<String> cb_year;
	private JComboBox<Integer> cb_month;
	private JComboBox<Integer> cb_day;
	private JComboBox<Integer> cb_hour;
	private JComboBox<Integer> cb_minute;
	private JSpinner spinnerCountHead;
	
	//getter와 setter 구현-----------------------------------------
	
	public void setPlaceId(String placeId) {
		this.PlaceId = placeId; 
	}

	public void setPlaceName(String placeName) {
		this.PlaceName = placeName;
		labelPlaceName.setText(PlaceName);
	}

	public void setPlaceAddress(String placeAddress) {
		this.PlaceAddress = placeAddress;
		labelPlaceAddress.setText(placeAddress);
	}

	public void setPlaceTel(String placeTel) {
		PlaceTel = placeTel;
	}

	public JFrame getFrame() {
		return frame;
	}
	
	//------------------------------------------------------------

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservScreen window = new ReservScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//기본 생성자
	public ReservScreen() throws SQLException {
		DB.init();
		UIManager.put("OptionPane.messageFont", new Font("G마켓 산스 TTF Light", Font.BOLD, 16));
		makeInputField();
		
	}
	
	//사용자가 입력한 정보들 가져오는 생성자
	public ReservScreen(JFrame prevFrame, String category, String id, String name, String address, String tel) throws SQLException {
		UIManager.put("OptionPane.messageFont", new Font("G마켓 산스 TTF Light", Font.BOLD, 16));
		this.previousFrame = prevFrame;
		PlaceId = id;
		PlaceName = name;
		PlaceAddress = address;
		PlaceTel = tel;
		this.category = category;
		DB.init();
		makeInputField();
		
	}

	//사용자 눈에 보이는 프레임 내 컴포넌트를 생성하는 메소드
	private void makeInputField() throws SQLException {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(216, 67, 67));
		frame.setBounds(100, 100, 852, 623);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		// 선택된 식당 이미지를 출력하는 라벨
		imageLabel = new JLabel(new ImageIcon("images/Place/"+ category+"/"+PlaceId+".jpg"));
		imageLabel.setBounds(53, 179, 365, 256);
		frame.getContentPane().add(imageLabel);
		
		// 선택된 식당 이름을 출력하는 라벨
		labelPlaceName = new JLabel("New label");
		labelPlaceName.setFont(new Font("G마켓 산스 TTF Bold", Font.BOLD, 25));
		labelPlaceName.setText(PlaceId);
		labelPlaceName.setBounds(57, 109, 278, 46);
		frame.getContentPane().add(labelPlaceName);
		
		// 선택된 식당 주소를 출력하는 라벨
		labelPlaceAddress = new JLabel("New label");
		labelPlaceAddress.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 15));
		labelPlaceAddress.setBounds(58, 142, 360, 36);
		frame.getContentPane().add(labelPlaceAddress);
		
		// 예약 날짜 라벨과 콤보박스
		labelSelectDate = new JLabel("예약날짜");
		labelSelectDate.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 16));
		labelSelectDate.setBounds(475, 134, 118, 23);
		frame.getContentPane().add(labelSelectDate);
		
		labelYear = new JLabel(" 년");
		labelYear.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 15));
		labelYear.setBounds(547, 167, 32, 43);
		frame.getContentPane().add(labelYear);
		
		cb_year = new JComboBox<String>(yearList);
		cb_year.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		cb_year.setBackground(new Color(255, 255, 255));
		cb_year.setBounds(485, 169, 62, 38);
		frame.getContentPane().add(cb_year);
		
		cb_month = new JComboBox<Integer>(monthList);
		cb_month.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		cb_month.setBackground(new Color(255, 255, 255));
		cb_month.setBounds(584, 169, 42, 38);
		frame.getContentPane().add(cb_month);
		cb_month.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				dayRestric();
			}
		});
		
		labelMonth = new JLabel("월");
		labelMonth.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 15));
		labelMonth.setBounds(634, 170, 24, 36);
		frame.getContentPane().add(labelMonth);
		
		cb_day = new JComboBox<Integer>(dayList);
		cb_day.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		cb_day.setBackground(new Color(255, 255, 255));
		cb_day.setBounds(674, 169, 42, 38);
		frame.getContentPane().add(cb_day);
		
		labelDay = new JLabel("일");
		labelDay.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 15));
		labelDay.setBounds(728, 169, 24, 38);
		frame.getContentPane().add(labelDay);
		
		// 예약 시간 라벨과 콤보박스, 텍스트 필드
		labelSelectTime = new JLabel("예약 시간");
		labelSelectTime.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 16));
		labelSelectTime.setBounds(475, 218, 118, 39);
		frame.getContentPane().add(labelSelectTime);
		
		cb_hour = new JComboBox<Integer>(hourList);
		cb_hour.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		cb_hour.setBackground(new Color(255, 255, 255));
		cb_hour.setBounds(485, 257, 94, 50);
		frame.getContentPane().add(cb_hour);
		cb_hour.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cb_minute.removeItem(cb_minute.getItemAt(1));
				if(!cb_hour.getSelectedItem().equals(24)) {
					cb_minute.addItem(30);
				}
					
			}
		});
		
		labelHour = new JLabel("  시");
		labelHour.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 15));
		labelHour.setBounds(584, 267, 24, 36);
		frame.getContentPane().add(labelHour);
		
		cb_minute = new JComboBox<Integer>(minuteList);
		cb_minute.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		cb_minute.setBackground(new Color(255, 255, 255));
		cb_minute.setBounds(620, 259, 96, 50);
		frame.getContentPane().add(cb_minute);
		
		labelMinute = new JLabel("분");
		labelMinute.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 15));
		labelMinute.setBounds(728, 267, 24, 36);
		frame.getContentPane().add(labelMinute);
		
		// 인원수 라벨과 스피너
		labelSelectCountHead = new JLabel("예약 인원");
		labelSelectCountHead.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 16));
		labelSelectCountHead.setBounds(475, 330, 118, 23);
		frame.getContentPane().add(labelSelectCountHead);
		
		SpinnerNumberModel spmodel = new SpinnerNumberModel(0, 0, 100, 1); // 최솟값 0, 최댓값 100으로 설정
		spinnerCountHead = new JSpinner(spmodel);
		spinnerCountHead.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		spinnerCountHead.setBounds(485, 364, 231, 43);
		frame.getContentPane().add(spinnerCountHead);
		
		labelCountHead = new JLabel("명");
		labelCountHead.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 15));
		labelCountHead.setBounds(728, 370, 24, 36);
		frame.getContentPane().add(labelCountHead);
		
		//뒤로가기 버튼
		backToMain = new JButton("뒤로가기");
		backToMain.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 14));
		backToMain.setBorder(null);
		backToMain.setBackground(new Color(255, 255, 255));
		backToMain.setBounds(22, 32, 91, 23);
		frame.getContentPane().add(backToMain);
		backToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				previousFrame.setVisible(true);
				frame.setVisible(false);
			}
		});
		
		// 대표메뉴 버튼
		BtnMenu = new JButton("대표메뉴");
		BtnMenu.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 17));
		BtnMenu.setForeground(new Color(255, 255, 255));
		BtnMenu.setBackground(new Color(94, 117, 210));
		BtnMenu.setBounds(273, 451, 222, 68);
		frame.getContentPane().add(BtnMenu);
		BtnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					showMenuAction();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		// 예약하기 버튼
		btnFinal = new JButton("예약하기 ");
		btnFinal.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 17));
		btnFinal.setForeground(new Color(255, 255, 255));
		btnFinal.setBackground(new Color(78, 185, 36));
		btnFinal.setBounds(507, 451, 266, 68);
		frame.getContentPane().add(btnFinal);
		btnFinal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					OKAction();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		// 매장 전화 버튼
		btnCall = new JButton("매장전화");
		btnCall.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 17));
		btnCall.setForeground(new Color(255, 255, 255));
		btnCall.setBackground(new Color(94, 117, 210));
		btnCall.setBounds(48, 451, 213, 68);
		frame.getContentPane().add(btnCall);
		btnCall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					showPlaceTel();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	
		selecterLayout = new JLabel("");
		selecterLayout.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", 
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		selecterLayout.setBackground(new Color(255, 255, 255));
		selecterLayout.setOpaque(true);
		selecterLayout.setBounds(451, 108, 329, 327);
		frame.getContentPane().add(selecterLayout);
		
		lblBackGround = new JLabel("");
		lblBackGround.setBackground(new Color(255, 255, 255));
		lblBackGround.setBounds(22, 65, 790, 472);
		frame.getContentPane().add(lblBackGround);
		lblBackGround.setOpaque(true);
		
	}
	
	//예약하기 버튼을 누르면 실행시킬 확인 메소드 구현
	private void OKAction() throws SQLException {
		selectedYear = (String) cb_year.getSelectedItem();
		selectedMonth = Integer.toString((int) cb_month.getSelectedItem());
		selectedDay =  Integer.toString((int) cb_day.getSelectedItem());
		selectedHour = Integer.toString((int) cb_hour.getSelectedItem());
		selectedMinute = Integer.toString((int) cb_minute.getSelectedItem());
		
		headCount = (int)spinnerCountHead.getValue();
		
		if(cb_year.getSelectedItem().equals("-") || cb_month.getSelectedItem().equals(0) || cb_day.getSelectedItem().equals(0) || 
				cb_day.getSelectedItem().equals(0) || cb_hour.getSelectedItem().equals(0) || spinnerCountHead.getValue().equals(0)) {
			JOptionPane.showMessageDialog(frame, "모든 항목을 입력해주세요.");
		}
		
		else { UserData userdata = new UserData();
			UIManager.put("OptionPane.messageFont", new Font("G마켓 산스 TTF Light", Font.BOLD, 16));
			int result = JOptionPane.showConfirmDialog(frame, 
				"예약 내용을 확인해주세요.\n"
					+ "-----------------------------------------------------------------\n\n"
					+ "예약자 성함: " + userdata.getUserName() + "\n"
					+ "예약자 전화번호: " + userdata.getUserTel() + "\n\n"
					+ "예약 장소: " + PlaceName + "\n"
					+ "예약 날짜: " + selectedYear + "년 " + selectedMonth + "월 " + selectedDay + "일" + "\n"
					+ "예약 시간: " + selectedHour + "시 " + selectedMinute + "분" + "\n"
					+ "예약 인원: " + headCount + "명" + "\n\n"
					+ "-----------------------------------------------------------------\n"
					+ "내용을 확인하신 후 [OK]를 눌러주세요.\n\n"
					,"예약 내용 확인",JOptionPane.OK_CANCEL_OPTION);
			
			makeSimpleBirth(); 		// 생일 형변환
			selectTime = selectedHour +":"+ selectedMinute;
			
			if(result == JOptionPane.OK_OPTION) {
				JOptionPane.showMessageDialog(frame, "예약이 완료되었습니다.", "예약 완료", JOptionPane.PLAIN_MESSAGE);
				
				
				String sqlInsert = "INSERT INTO orders (user_id, user_name, user_tel, place, order_date, order_time, count_head)"  
						+ "VALUES('"+userdata.getUserId()+"','"+userdata.getUserName()+"','"+userdata.getUserTel()+"',"
								+ "'"+PlaceName+"','"+selectedDate+"','"+selectTime+"','"+headCount+"')";
				DB.executeSQL(sqlInsert);
				Main main = new Main();
				main.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		}
	}
	
	// 선택된 장소의 대표 메뉴 다이얼로그를 보여주는 메소드
	private void showMenuAction() throws SQLException {
		
		String menu1 = null, menu2 = null , menu3 = null;
		ResultSet rs = DB.getResult("SELECT * FROM MENU WHERE PLACE_NAME = '" + PlaceName + "'");
		if(rs.next()) {
			menu1 = rs.getString("MENU1");
			menu2 = rs.getString("MENU2");
			menu3 = rs.getString("MENU3");
		}
		
		String menuList = PlaceName+" \t\t대표메뉴\n\t-------------------------------------\n1 : " + menu1+ "\n2 : " + menu2+ "\n3 : " + menu3;
		 
		
		JOptionPane.showMessageDialog(frame, menuList,"대표메뉴",JOptionPane.PLAIN_MESSAGE);
	}
	
	private void showPlaceTel() throws SQLException {
		
		JOptionPane.showMessageDialog(frame, PlaceTel ,PlaceName + " 전화번호", JOptionPane.PLAIN_MESSAGE);
	}
	
	// 선택된 생월에 따라 선택 가능한 생일 항목 수정하는 메소드
	private void dayRestric() {		
		
		int month = (int) cb_month.getSelectedItem();
		int daysInMonth;
		
		if (month == 2) {
            daysInMonth = 28;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            daysInMonth = 30;
        } else {
            daysInMonth = 31;
        }
		
		cb_day.removeAllItems(); // 일 콤보박스 내용 지우기
        for (int i = 1; i <= daysInMonth+1; i++) {
            cb_day.addItem(i-1);
        }
	}
	
	// 입력받은 생일을 YYMMDD 형태로 변환하는 메소드
	private void makeSimpleBirth() {
		if((Integer.parseInt(selectedMonth) < 10)) {
			selectedMonth = "0" + selectedMonth;
		}
		
		if((Integer.parseInt(selectedDay) < 10)) {
			selectedDay = "0" + selectedDay;
		}
		
		if((Integer.parseInt(selectedHour) < 10)) {
			selectedHour = "0" + selectedHour;
		}
		
		if((Integer.parseInt(selectedMinute) == 0)) {
			selectedMinute = "0" + selectedMinute;
		}
		
		
		selectedDate = selectedYear +"-" +  selectedMonth + "-" + selectedDay;
	}
}
