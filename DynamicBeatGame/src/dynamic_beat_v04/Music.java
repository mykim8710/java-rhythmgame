package dynamic_beat_v04;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

// 음악재생은 쓰레드를 이용하여 게임실행과 동시에 플레이
public class Music extends Thread {
	private Player player;
	private boolean isLoop;					// 음악의 반복재생여부
	private File file;							
	private FileInputStream fis;			
	private BufferedInputStream bis;		
	
	public Music(String musicName, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/" +musicName).toURI());	// 해당파일을 가져옴
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);	// 해당파일을 버퍼에 담아 읽어옴
			player = new Player(bis);					// 플레이어에 해당 파일을 담음
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// 현재 실행되는 음악이 어떤 위치인지, 0.001(1ms)초 단위
	public int getTime() {
		if(player == null) {
			return 0;
		}
		
		return player.getPosition();
	}
	
	// 해당음악을 항상 실행할 수 있도록 하는 메서드
	public void close() {
		isLoop = false;
		player.close();
		this.interrupt();		// 해당쓰레드를 중지상태로 만드는 것(게임과 별도로 음악재생부분)
	}
	
	// 쓰레드실행을 위해 오버라이딩
	@Override
	public void run() {
		try {
			// isLoop가 true면 무한반복
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			} while(isLoop);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
