package dynamic_beat_Refactoring;

public class Main {
	public static final int SCREEN_WIDTH = 1280;		// 스크린 : 게임창 너비
	public static final int SCREEN_HEIGHT = 720;		// 스크린 : 게임창 높이
	public static final int NOTE_SPEED = 3;				// 노트 떨어지는 스피드
	public static final int SLEEP_TIME = 10;				// 노트가 떨어지는 주기	0.01초
	public static final int REACH_TIME = 2;				// 노트가 생성된 후 판정바에 도달할때까지의 시간  2초
	
	public static void main(String[] args) {
		
		new DynamicBeat();			// DynamicBeat 객체 생성

	}
}
