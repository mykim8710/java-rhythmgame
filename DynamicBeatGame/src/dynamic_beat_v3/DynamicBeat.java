package dynamic_beat_v3;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class DynamicBeat extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;

	private Image introBackGround;

	public DynamicBeat() {
		setTitle("Dynamic Beat"); 											// 게임창 타이틀
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 게임창 사이즈
		setResizable(false);													// 게임창 크기 조절불가
		setLocationRelativeTo(null); 										// 실행했을때 게임창이 정중앙에 위치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// 게임창 종료시 전체가 종료됨
		setVisible(true); 														// 게임창이 정상적으로 출력

		introBackGround = new ImageIcon(Main.class.getResource("../images/introBackGround.jpg")).getImage();
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
		g.drawImage(introBackGround, 0, 0, null);
		this.repaint();
	}
}
