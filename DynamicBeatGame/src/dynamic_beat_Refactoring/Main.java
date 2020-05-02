package dynamic_beat_Refactoring;

public class Main {
	public static final int SCREEN_WIDTH = 1280;		// 스크린 : 게임창 너비
	public static final int SCREEN_HEIGHT = 720;		// 스크린 : 게임창 높이
	public static int note_speed;								// 노트 떨어지는 스피드(픽셀)  x픽셀씩 아래로 떨어짐
	public static final int SLEEP_TIME = 10;				// 노트가 떨어지는 시간주기	0.01초간격으로 아래로 떨어짐
	public static int reach_time;								// 노트가 생성된 후 판정바에 도달할때까지의 시간  x초
	
	public static void main(String[] args) {
		
		new DynamicBeat();			// DynamicBeat 객체 생성

	}
}
