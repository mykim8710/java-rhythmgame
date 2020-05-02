package dynamic_beat_Refactoring;

// 하나의 곡에 대한 정보를 담는 클래스
public class Track {
	private String selectImage; // 음악선택창 이미지
	private String gameImage; // 해당곡을 실행했을때 게임화면 배경 이미지
	
	private String startMusic; 	// 음악 선택창에서 나오는 음악
	private String gameMusic; // 해당곡을 실행했을때 나오는 게임화면에서의 음악

	private String titleName; // 곡제목

	public Track(String selectImage, String gameImage, String startMusic, String gameMusic, String titleName) {
		this.selectImage = selectImage;
		this.gameImage = gameImage;
		this.startMusic = startMusic;
		this.gameMusic = gameMusic;
		this.titleName = titleName;
	}

	public String getSelectImage() {
		return selectImage;
	}

	public void setSelectImage(String selectImage) {
		this.selectImage = selectImage;
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

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

}
