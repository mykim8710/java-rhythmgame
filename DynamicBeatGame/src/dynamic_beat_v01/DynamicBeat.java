package dynamic_beat_v01;

import javax.swing.JFrame;
// jFrame : GUI기반의 프로그램 제작 시 필요한 라이브러리(클래스)

public class DynamicBeat extends JFrame {
	public DynamicBeat() {
		setTitle("Dynamic Beat");												// 게임창 타이틀
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);		// 게임창 사이즈
		setResizable(false);														// 게임창 크기 조절불가
		setLocationRelativeTo(null); 											// 실행했을때 게임창이 정중앙에 위치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			// 게임창 종료시 전체가 종료됨
		setVisible(true);															// 게임창이 정상적으로 출력
	}
}
