package dynamic_beat_v4;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;

	private ImageIcon closeBtnEnterImage = new ImageIcon(Main.class.getResource("../images/closeBtnEnter.png"));
	private ImageIcon closeBtnBasicImage = new ImageIcon(Main.class.getResource("../images/closeBtnBasic.png"));
	
	private Image introBackGround = new ImageIcon(Main.class.getResource("../images/introBackGround.jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	
	private JButton closeBtn = new JButton(closeBtnBasicImage);
	
	private int mouseX, mouseY;		// 화면창 내에서 마우스의 x,y좌표
	
	public DynamicBeat() {
		setUndecorated(true);												// 실행했을때 기본 메뉴바가 안보임
		setTitle("Dynamic Beat"); 											// 게임창 타이틀
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 게임창 사이즈
		setResizable(false);													// 게임창 크기 조절불가
		setLocationRelativeTo(null); 										// 실행했을때 게임창이 정중앙에 위치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// 게임창 종료시 전체가 종료됨
		setVisible(true); 														// 게임창이 정상적으로 출력
		setBackground(new Color(0,0,0,0));								// paintComponents(); 했을때 배경이 white
		setLayout(null);														// 버튼, JLabel을 그 위치에 위치하도록
		
		closeBtn.setBounds(1245, 0, 30 ,30);
		closeBtn.setBorderPainted(false);
		closeBtn.setContentAreaFilled(false);
		closeBtn.setFocusPainted(false);
		closeBtn.addMouseListener(new MouseAdapter() {		// 마우스 오버시 이미지 아이콘 변경
			@Override
			public void mouseEntered(MouseEvent e) {
				closeBtn.setIcon(closeBtnEnterImage);
				closeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));	// 커서 위치시 손가락모양으로 바뀜
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				closeBtn.setIcon(closeBtnBasicImage);
				closeBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	// 원래 커서로 바뀜
			}
			
			@Override													// 버튼 누를때
			public void mousePressed(MouseEvent e) {
				Music btnPressMusic = new Music("btnPressMusic.mp3", false);	// 버큰누룰때 효과음 삽입
				btnPressMusic.start();
				try {
					Thread.sleep(1000);					// 1초뒤에 프로그램이 종료
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		
		add(closeBtn);	
		
		menuBar.setBounds(0,0,1280,30);								// 메뉴바의 위치, 크기 지정
		
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		
		// 메뉴바를 잡고 화면창을 이동할 수 있게 함
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		
		add(menuBar);														// JFrame에 메뉴바를 추가
		 
		Music introMusic = new Music("introMusic.mp3", true);		// 시작화면에서 음악이 무한반복(쓰레드)
		introMusic.start();

	}

	// 게임창에 이미지를 그려주는 메서드 : 프로그램 화면 크기만 이미지를 생성해서 그려줌(전체화면 이미지를 매순간마다 반복하면서 그려줌)
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics g) {
		g.drawImage(introBackGround, 0, 0, null);	//	단순하게 이미지를 그려줄때 사용
		paintComponents(g);								// 이미지 그리는 방법 - 항상존재, 고정된 이미지를 그릴때 사용
		this.repaint();
	}
}
