package dynamic_beat_Refactoring;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {

	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();	// 노트 이미지 객체
	private int x;																																		// 노트 x좌표 위치
	private int y = 580 - (1000 / Main.SLEEP_TIME  * Main.NOTE_SPEED) * Main.REACH_TIME;							// 노트 y좌표 위치
	
	private String noteType;
	private boolean proceeded = true;

	public String getNoteType() {
		return noteType;
	}

	public boolean isProceeded() {
		return proceeded;
	}

	public void close() {
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

	public void screenDraw(Graphics2D g) {
		if (!noteType.equals("Space")) {
			g.drawImage(noteBasicImage, x, y, null);

		} else {
			g.drawImage(noteBasicImage, x, y, null);
			g.drawImage(noteBasicImage, x + 100, y, null);

		}
	}

	public void drop() {
		y += Main.NOTE_SPEED;
		if (y > 620) {
			System.out.println("Miss  score : 0");
			close();
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				drop();
				if (proceeded) {
					Thread.sleep(Main.SLEEP_TIME);
				} else {
					interrupt();
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
