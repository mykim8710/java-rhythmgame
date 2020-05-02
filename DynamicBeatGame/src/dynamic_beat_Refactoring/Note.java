package dynamic_beat_Refactoring;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

// 하나의 떨어지는 노트를 객체화, 각 노트들이 떨어져야 하는 역할을 해야하기때문에 쓰레드로 구현
public class Note extends Thread {

	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();	// 노트 이미지 객체
	private int x;																																		// 노트가 생성될 때 x좌표 위치
	private int y = 580 - (1000 / Main.SLEEP_TIME  * Main.note_speed) * Main.reach_time;									// 노트가 생성될 때 y좌표 위치, 노트는 REACH_TIME 후에 580위치에 가야한다
	
	private String noteType;				// 각 노트의 종류 : S D F Space J K L
	
	private boolean proceeded = true;	// 현재 노트의 진행여부를 판별하는 변수

	public String getNoteType() {
		return noteType;
	}

	public boolean isProceeded() {
		return proceeded;
	}

	public void close() {				// 현재노트가 움직이지 않도록해주는 메서드 : 노트를 성공적으로 입력해서 해당노트가 이동하지않도록 해주는 메서드 
		proceeded = false;
	}

	public Note(String noteType) {
		if (noteType.equals("S")) {
			x = 228;
		} else if (noteType.equals("D")) {
			x = 332;
		} else if (noteType.equals("F")) {
			x = 436;
		} else if (noteType.equals("Space")) {
			x = 540;
		} else if (noteType.equals("J")) {
			x = 744;
		} else if (noteType.equals("K")) {
			x = 848;
		} else if (noteType.equals("L")) {
			x = 952;
		}

		this.noteType = noteType;
	}
	
	// 하나의 노트에 대한 이미지를 그리기 위한 메서드
	public void screenDraw(Graphics2D g) {
		if (!noteType.equals("Space")) {
			g.drawImage(noteBasicImage, x, y, null);

		} else {
			g.drawImage(noteBasicImage, x, y, null);
			g.drawImage(noteBasicImage, x + 100, y, null);

		}
	}
	
	
	/* 각 노트가 떨어지는 메서드 */
	public void drop() {
		y += Main.note_speed;		// 노트의 y좌표가 NOTE_SPEED만큼 아래로 이동(Sleep_Time 후에)
		if (y > 620) {					// 620 : 노트가 판정바를 벗어나는 지점
			System.out.println("Miss  score : 0");
			close();
		}
	}
	
	
	/* 쓰레드 실행되는 메서드 */
	@Override
	public void run() {
		try {
			while (true) {
				drop();				// 1초에 NOTE_SPEED * 100픽셀만큼 노트가 아래로 떨어짐
				if (proceeded) {	// 만약 노트가 현재 떨어지는 상황이라면
					Thread.sleep(Main.SLEEP_TIME);	// 노트가 아래로 한번 이동하고 0.01초만큼 쉬었다가 다시 떨어지게 끔 하기위함
				} else {
					interrupt();		// 해당 노트가 다떨어졌다면 쓰레드 종료하고 반복문 종료하면서 해당 노트는 움직이지 않음
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getJudgement() {
		if (y >= 613) {
			System.out.println("Late  score : 0");
			close();
			return "Late";
		} else if (y >= 600) {
			System.out.println("Good  score : 10");
			close();
			return "Good";
		} else if (y >= 587) {
			System.out.println("Great  score : 20");
			close();
			return "Great";
		} else if (y >= 573) {
			System.out.println("Perfect  score : 30");
			close();
			return "Perfect";
		} else if (y >= 565) {
			System.out.println("Great  score : 20");
			close();
			return "Great";
		} else if (y >= 550) {
			System.out.println("Good  score : 10");
			close();
			return "Good";
		} else if (y >= 535) {
			System.out.println("Early  score : 5");
			close();
			return "Early";
		} else {
			return "None";
		}
	}
	
	public int getY() {
		return y;
	}
	
}
