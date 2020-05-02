package dynamic_beat_Refactoring;

public class Beat {
	private int time;				// 시간
	private String noteName;	// 노트 종류

	public Beat(int time, String noteName) {
		this.time = time;
		this.noteName = noteName;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getNoteName() {
		return noteName;
	}

	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}

}
