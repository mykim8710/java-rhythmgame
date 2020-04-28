package dynamic_beat_v11;

// 하나의 곡에 대한 정보를 담는 클래스
public class Track {
	private String titleImage; // 처음부분 이미지
	private String startImage; // 음악선택창 이미지
	private String gameImage; // 해당곡을 실행했을때 이미지
	private String startMusic; // 게임선택창 음악
	private String gameMusic; // 해당곡을 실행했을때 음악

	public Track(String titleImage, String startImage, String gameImage, String startMusic, String gameMusic) {
		this.titleImage = titleImage;
		this.startImage = startImage;
		this.gameImage = gameImage;
		this.startMusic = startMusic;
		this.gameMusic = gameMusic;
	}

	public String getTitleImage() {
		return titleImage;
	}

	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}

	public String getStartImage() {
		return startImage;
	}

	public void setStartImage(String startImage) {
		this.startImage = startImage;
	}

	public String getGameImage() {
		return gameImage;
	}

	public void setGameImage(String gameImage) {
		this.gameImage = gameImage;
	}

	public String getStartMusic() {
		return startMusic;
	}

	public void setStartMusic(String startMusic) {
		this.startMusic = startMusic;
	}

	public String getGameMusic() {
		return gameMusic;
	}

	public void setGameMusic(String gameMusic) {
		this.gameMusic = gameMusic;
	}

}
