package reservation;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import base.DB;
import main.Main;
import java.awt.Font;

/**
* @packageName	: InhaTable.reservation
* @fileName		: Cafe.java
* @author		: SeoJeong Kim
* @date			: 2024.05.27
* @description	: 카페 예약 메인화면
* ================================================================
* DATE				AUTHOR				NOTE
* -----------------------------------------------------------
* 2024.05.27		SeoJeong Kim		최초 생성
* 2024.05.28		SeoJeong Kim		레이아웃 구현 완료
* 2022.05.28		SeoJeong Kim		기능 구현 완료
*/

public class Cafe {

    private JFrame frame;
    private JPanel cafePanel;
    public static String previousFrame;

    // getter와 setter 구현-----------------------------------------
    
    public JFrame getFrame() {
        return frame;
    }
    //------------------------------------------------------------

    // main 메서드: 애플리케이션 시작점
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Cafe window = new Cafe();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Cafe 생성자: DB 초기화 및 카페 목록 로드
    public Cafe() throws SQLException {
        DB.init();
        loadCafes();
    }

    // 카페 정보를 로드하고 UI를 설정하는 메서드
    private void loadCafes() {
        try {
            // 메인 프레임 설정
            frame = new JFrame();
            frame.setTitle("식당 예약하기");
            frame.getContentPane().setBackground(new Color(216, 67, 67));
            frame.getContentPane().setLayout(null);
            frame.setBounds(100, 100, 799, 623);
            frame.setSize(850, 623);
            frame.setLocationRelativeTo(null);

            // 스크롤 패널 설정
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setBounds(22, 67, 791, 474);
            frame.getContentPane().add(scrollPane);

            // 카페 패널 설정 (수직으로 배치)
            cafePanel = new JPanel();
            cafePanel.setBounds(0, 0, 0, 0);
            cafePanel.setLayout(new GridLayout(0, 1, 0, 0)); // 수직으로 배치
            scrollPane.setViewportView(cafePanel);

            // 뒤로가기 버튼 설정
            JButton backToMain = new JButton("뒤로가기");
            backToMain.setBackground(new Color(240, 240, 240));
            backToMain.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 14));
            backToMain.setBorder(null);
            backToMain.setBounds(25, 34, 91, 23);
            frame.getContentPane().add(backToMain);
            backToMain.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        Main main = new Main();
                        main.getFrame().setVisible(true);
                        frame.setVisible(false);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            });

            // 데이터베이스에서 카페 리스트 가져오기
            String SQL = "SELECT * FROM CAFELIST";
            ResultSet rs = DB.getResult(SQL);

            // 결과를 순회하며 UI 구성
            while (rs.next()) {
                String cafeId = rs.getString("CAFE_ID");
                String cafeName = rs.getString("CAFE_NAME");
                String cafeAddress = rs.getString("CAFE_ADDRESS");
                String cafeTel = rs.getString("CAFE_TEL");

                // 버튼 패널 생성
                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new BorderLayout());
                buttonPanel.setPreferredSize(new Dimension(400, 100));

                // 카페 정보 버튼 생성
                JButton cafeButton = new JButton("<HTML><p style='text-align:right; font-family: 맑은 고딕; text-color: grey; '>"
                		+ "<span style='font-size: 2em;'>"
                        + cafeName + "</span><br>" + cafeAddress + "<br><br> tel: " + cafeTel + "</p></html>");
                cafeButton.setHorizontalAlignment(JButton.RIGHT); // 텍스트 오른쪽 정렬
                cafeButton.setBorder(BorderFactory.createCompoundBorder(
                		cafeButton.getBorder(), // 기존 테두리 유지
                		BorderFactory.createEmptyBorder(0, 10, 5, 10) // 위, 왼쪽, 아래, 오른쪽 여백
                ));

                // 사진 추가
                String ImageLink = "images/Place/cafe/" + cafeId + ".jpg";
                JLabel imageLabel = new JLabel(new ImageIcon(ImageLink));
                imageLabel.setPreferredSize(new Dimension(400, 80)); // 사진 크기 설정
                buttonPanel.add(imageLabel, BorderLayout.WEST); // 사진을 왼쪽(WEST)에 추가
                buttonPanel.add(cafeButton, BorderLayout.CENTER);
                cafeButton.setBackground(Color.white);

                // 카페 버튼 클릭 시 예약 화면으로 이동
                cafeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            // 예약 화면 생성
                            ReservScreen reservSc = new ReservScreen(frame, "cafe", cafeId, cafeName, cafeAddress, cafeTel);

                            // 예약 화면에 카페 정보 설정
                            reservSc.setPlaceId(cafeId);
                            reservSc.setPlaceName(cafeName);
                            reservSc.setPlaceAddress(cafeAddress);
                            reservSc.setPlaceTel(cafeTel);

                            // 예약 화면 표시
                            reservSc.getFrame().setVisible(true);

                            // 현재 화면 숨기기
                            frame.setVisible(false);
                        } catch (SQLException e1) {
                            // SQLException 발생 시 처리
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(frame, "데이터베이스 오류가 발생했습니다.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                // cafePanel에 버튼 패널 추가
                cafePanel.add(buttonPanel);
            }

            // 패널 크기 조정
            cafePanel.setPreferredSize(new Dimension(cafePanel.getWidth(), 
                cafePanel.getComponentCount() * 250)); // 버튼 높이 250px로 가정
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}