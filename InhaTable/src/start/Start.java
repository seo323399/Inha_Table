package start;

import javax.imageio.ImageIO;
import javax.swing.*;
import login.Login;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.Font;

/**
* @packageName	: InhaTable.start
* @fileName		: Start.java
* @date			: 2024.05.27
* @description	: 시작 로딩 화면   
* ================================================================
* DATE				AUTHOR				NOTE
* ----------------------------------------------------------------
* 2024.06.01		SeoJeong Kim		최초 생성
* 2022.06.01		SeoJeong Kim		기능 구현 완료
* 2022.06.01		SeoJeong Kim		로딩 시간 변경 

*/ 

public class Start {

    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Start window = new Start();
                    window.frame.setVisible(true);
                    window.fadeOutAndShowLogin(4000); // 2초 뒤에 로그인 프레임을 보여줌
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Start() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setUndecorated(true); // 테두리 없음
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(216, 67, 67));
        frame.getContentPane().setLayout(null);
        
        JLabel labelImage = new JLabel("");
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("images\\start\\logo.png"));
            // 이미지 크기 조정
            Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            labelImage.setIcon(new ImageIcon(scaledImg));
        } catch (IOException e) {
            e.printStackTrace();
        }
        labelImage.setBounds(170, 61, 106, 111); // 변경된 이미지 크기에 맞게 위치 및 크기 조정
        frame.getContentPane().add(labelImage);
        
        JLabel labelProgName = new JLabel("INHA TABLE");
        labelProgName.setForeground(new Color(255, 255, 255));
        labelProgName.setFont(new Font("G마켓 산스 TTF Bold", Font.BOLD, 20));
        labelProgName.setBounds(151, 165, 146, 49);
        frame.getContentPane().add(labelProgName);
        
        JLabel labelLoading = new JLabel("loading...");
        labelLoading.setForeground(new Color(255, 255, 255));
        labelLoading.setHorizontalAlignment(SwingConstants.CENTER);
        labelLoading.setVerticalAlignment(SwingConstants.BOTTOM);
        labelLoading.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        labelLoading.setBounds(179, 206, 84, 17);
        frame.getContentPane().add(labelLoading);
    }

    // 서서히 투명해지면서 로그인 프레임을 보여주는 메서드
    private void fadeOutAndShowLogin(int delay) {
        Timer timer = new Timer(delay, new ActionListener() {
            private float alpha = 2.0f;
            private long startTime = System.currentTimeMillis(); // 시작 시간

            @Override
            public void actionPerformed(ActionEvent e) {
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - startTime; // 경과 시간 계산
                float progress = (float) elapsedTime / delay; // 경과 시간 대비 전체 시간의 비율

                if (progress >= 1.0f) { // 경과 시간이 전체 시간을 초과하면
                    frame.dispose(); // 현재 프레임 파괴
                    try {
                        showLoginFrame(); // 로그인 프레임 보여줌
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    ((Timer) e.getSource()).stop(); // 타이머 중지
                } else {
                    alpha = 1.0f - progress; // 경과 시간에 따라 투명도 조절
                    frame.setOpacity(alpha); // 투명도 설정
                }
            }
        });
        timer.start();
    }

    // 로그인 프레임을 보여주는 메서드
    private void showLoginFrame() throws SQLException {
       Login login = new Login();
       login.getFrame().setVisible(true);
       frame.setVisible(false);
        
    }
}
