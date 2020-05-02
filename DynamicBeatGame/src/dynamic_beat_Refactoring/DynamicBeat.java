package dynamic_beat_Refactoring;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame {
	
	/* 변수선언 */
	
	private Image screenImage;			// 실행화면 이미지 객체
	private Graphics screenGraphic;		// 이미지를 그려주기 위한 그래픽 객체
	
	// 메뉴바 종료 버튼 이미지아이콘 객체
	private ImageIcon closeBtnEnterImage = new ImageIcon(Main.class.getResource("../images/closeBtnEnter.png"));
	private ImageIcon closeBtnBasicImage = new ImageIcon(Main.class.getResource("../images/closeBtnBasic.png"));
	
	// 시작하기 버튼 이미지아이콘 객체
	private ImageIcon startBtnBasicImage = new ImageIcon(Main.class.getResource("../images/startBtnBasic.png"));
	private ImageIcon startBtnEnterImage = new ImageIcon(Main.class.getResource("../images/startBtnEnter.png"));
	
	// 종료하기 버튼 이미지아이콘 객체
	private ImageIcon quitBtnBasicImage = new ImageIcon(Main.class.getResource("../images/quitBtnBasic.png"));
	private ImageIcon quitBtnEnterImage = new ImageIcon(Main.class.getResource("../images/quitBtnEnter.png"));
	
	// 곡선택 왼쪽 버튼 이미지아이콘 객체
	private ImageIcon leftBasicBtnImage = new ImageIcon(Main.class.getResource("../images/leftBasicBtn.png"));
	private ImageIcon leftEnterBtnImage = new ImageIcon(Main.class.getResource("../images/leftEnterBtn.png"));
	
	// 곡선택 오른쪽 버튼 이미지아이콘 객체
	private ImageIcon rightBasicBtnImage = new ImageIcon(Main.class.getResource("../images/rightBasicBtn.png"));
	private ImageIcon rightEnterBtnImage = new ImageIcon(Main.class.getResource("../images/rightEnterBtn.png"));
	
	// 난이도선택 Easy 버튼 이미지아이콘 객체
	private ImageIcon easyBasicBtnImage = new ImageIcon(Main.class.getResource("../images/easyBasicBtn.png"));
	private ImageIcon easyEnterBtnImage = new ImageIcon(Main.class.getResource("../images/easyEnterBtn.png"));
	
	// 난이도선택 Hard 버튼 이미지아이콘 객체
	private ImageIcon hardBasicBtnImage = new ImageIcon(Main.class.getResource("../images/hardBasicBtn.png"));
	private ImageIcon hardEnterBtnImage = new ImageIcon(Main.class.getResource("../images/hardEnterBtn.png"));
	
	// 게임화면에서 곡선택화면으로 Back 버튼 이미지아이콘 객체
	private ImageIcon backBasicBtnImage = new ImageIcon(Main.class.getResource("../images/backBtnBasic.png"));
	private ImageIcon backEnterBtnImage = new ImageIcon(Main.class.getResource("../images/backBtnEnter.png"));

	// 인트로 화면배경 이미지 객체
	private Image backGround = new ImageIcon(Main.class.getResource("../images/introBackGround.png")).getImage();
	
	// 메뉴바 객체
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

	// 각 버튼 객체
	private JButton closeBtn = new JButton(closeBtnBasicImage);			// 메뉴바 종료버튼
	private JButton startBtn = new JButton(startBtnBasicImage);				// 시작하기 버튼
	private JButton quitBtn = new JButton(quitBtnBasicImage);				// 종료하기 버튼
	private JButton leftBtn = new JButton(leftBasicBtnImage);				// 곡선택 왼쪽으로 버튼
	private JButton rightBtn = new JButton(rightBasicBtnImage);			// 곡선택 오른쪽으로 버튼
	private JButton easyBtn = new JButton(easyBasicBtnImage);				// 난이도 선택 Easy으로 버튼
	private JButton hardBtn = new JButton(hardBasicBtnImage);			// 난이도 선택 Hard으로 버튼
	private JButton backBtn = new JButton(backBasicBtnImage);			// 게임화면에서 곡선택화면으로 Back 버튼

	private int mouseX, mouseY; 														// 화면창 내에서 마우스의 x, y좌표

	private boolean isMainScreen = false; 		// 메인화면인지 확인하는 변수
	private boolean isGameScreen = false; 	// 현재 게임화면으로 넘어왔는지 확인하는 변수

	private ArrayList<Track> trackList = new ArrayList<Track>(); // 각 음악을 담는 리스트

	private Image selectedImage;		// 선택 곡 이미지	 객체
	private Music selectedMusic;		// 선택 곡 음악 객체
	
	private Music introMusic = new Music("introMusic.mp3", true); // 뮤직객체 생성, 시작화면(인트로화면)에서 음악이 무한반복(쓰레드)
	
	private int nowSeleted = 0;	// 트랙리스트(arrayList)에서 곡의 순번 

	public static Game game; 		// 프로젝트 전체에서 이용되는 게임객체 변수
	
	/* 변수선언 끝 */
	
	
	/* DynamicBeat() 생성자 */
	
	public DynamicBeat() {
		
		// 트랙리스트에 각각 트랙객체 추가 : 프로그램이 실행된다음 곡이 추가되기 전에 버튼이벤트가 발생하면 프로그램이 오류가 발생하기때문에 제일 먼저 실행
		trackList.add(new Track("MightyLove_selectImage.png", "MightyLove_gameImage.png", "Mighty Love_selected.mp3", "Joakim Karud - Mighty Love.mp3", "Joakim Karud - Mighty Love"));	 	// index 0
		trackList.add(new Track("energy_selectImage.png", "energy_gameImage.png", "Energy_selected.mp3", "Bensound - Energy.mp3", "Bensound - Energy"));													// index 1
		trackList.add(new Track("wildFlower_selectImage.png",  "wildFlower_gameImage.png", "Wild Flower_selected.mp3", "Joakim Karud - Wild Flower.mp3", "Joakim Karud - Wild Flower")); 		// index 2

		setUndecorated(true); 													// 실행했을때 기본 메뉴바가 안보임
		setTitle("Dynamic Beat Game"); 										// 게임창 타이틀
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); 	// 게임창 사이즈
		setResizable(false);														// 게임창 크기 조절불가
		setLocationRelativeTo(null); 											// 실행했을때 게임창이 정중앙에 위치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 			// 게임창 종료 시 전체가 종료됨
		setVisible(true);															// 게임창이 정상적으로 출력
		setBackground(new Color(0, 0, 0, 0)); 								// paintComponents(); 했을때 배경이 white
		setLayout(null); 															// 버튼, JLabel을 그 위치에 위치하도록

		addKeyListener(new KeyListener());									// 키보드이벤트를 위해 키리스너 객체 생성

		introMusic.start();															// 인트로 뮤직을 시작 (쓰레드, 무한반복으로)
		
		
		/* 각 버튼 Setting */
		
		// 1. 메뉴바 종료버튼
		closeBtn.setBounds(1245, 0, 30, 30);;	// 위치 x, y좌표 , 이미지 크기
		closeBtn.setBorderPainted(false);
		closeBtn.setContentAreaFilled(false);
		closeBtn.setFocusPainted(false);
		closeBtn.addMouseListener(new MouseAdapter() {
			
			@Override	// 마우스가 버튼 위에 올라갔을때 
			public void mouseEntered(MouseEvent e) {
				closeBtn.setIcon(closeBtnEnterImage);
				closeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서 위치 시 손가락모양으로 바뀜
			}

			@Override	// 마우스가 버튼을 벗어났을때
			public void mouseExited(MouseEvent e) {
				closeBtn.setIcon(closeBtnBasicImage);
				closeBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 원래 커서로 바뀜
			}

			@Override // 마우스로 버튼 누를때
			public void mousePressed(MouseEvent e) {
				Music btnPressMusic = new Music("menuEnter.mp3", false); // 버튼 누를 때 효과음 삽입
				btnPressMusic.start();
				try {
					Thread.sleep(500); // 0.5초 뒤에 프로그램이 종료
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});

		add(closeBtn);

		// 2. 시작하기 버튼
		startBtn.setBounds(950, 350, 300, 80);;	// 위치 x, y좌표 , 이미지 크기
		startBtn.setBorderPainted(false);
		startBtn.setContentAreaFilled(false);
		startBtn.setFocusPainted(false);
		startBtn.addMouseListener(new MouseAdapter() { 
			
			@Override
			public void mouseEntered(MouseEvent e) {
				startBtn.setIcon(startBtnEnterImage);
				startBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서 위치시 손가락모양으로 바뀜
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startBtn.setIcon(startBtnBasicImage);
				startBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 원래 커서로 바뀜
			}

			@Override // 버튼 누를때
			public void mousePressed(MouseEvent e) {
				Music btnPressMusic = new Music("menuEnter.mp3", false); // 버튼 누를 때 효과음 삽입
				btnPressMusic.start();
				enterMain();
			}
		});

		add(startBtn);

		// 3. 종료하기 버튼
		quitBtn.setBounds(950, 450, 300, 80);;	// 위치 x, y좌표 , 이미지 크기
		quitBtn.setBorderPainted(false);
		quitBtn.setContentAreaFilled(false);
		quitBtn.setFocusPainted(false);
		quitBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				quitBtn.setIcon(quitBtnEnterImage);
				quitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서 위치시 손가락모양으로 바뀜
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quitBtn.setIcon(quitBtnBasicImage);
				quitBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 원래 커서로 바뀜
			}

			@Override // 버튼 누를때
			public void mousePressed(MouseEvent e) {
				Music btnPressMusic = new Music("menuEnter.mp3", false); // 버튼 누를 때 효과음 삽입
				btnPressMusic.start();
				
				try {
					Thread.sleep(500); // 0.5초 뒤에 프로그램이 종료
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});

		add(quitBtn);
	
		// 4. 곡선택 왼쪽버튼
		leftBtn.setVisible(false);
		leftBtn.setBounds(140, 310, 60, 60);;	// 위치 x, y좌표 , 이미지 크기
		leftBtn.setBorderPainted(false);
		leftBtn.setContentAreaFilled(false);
		leftBtn.setFocusPainted(false);
		leftBtn.addMouseListener(new MouseAdapter() { 
			
			@Override
			public void mouseEntered(MouseEvent e) {
				leftBtn.setIcon(leftEnterBtnImage);
				leftBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서 위치시 손가락모양으로 바뀜
			}

			@Override
			public void mouseExited(MouseEvent e) {
				leftBtn.setIcon(leftBasicBtnImage);
				leftBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 원래 커서로 바뀜
			}

			@Override // 버튼 누를때
			public void mousePressed(MouseEvent e) {
				Music btnPressMusic = new Music("menuEnter.mp3", false); // 버튼 누를 때 효과음 삽입
				btnPressMusic.start();
				selectLeft();
			}
		});

		add(leftBtn);
		
		// 5. 곡선택 오른쪽버튼
		rightBtn.setVisible(false);
		rightBtn.setBounds(1080, 310, 60, 60);;	// 위치 x, y좌표 , 이미지 크기
		rightBtn.setBorderPainted(false);
		rightBtn.setContentAreaFilled(false);
		rightBtn.setFocusPainted(false);
		rightBtn.addMouseListener(new MouseAdapter() { // 마우스 오버시 이미지 아이콘 변경
			@Override
			public void mouseEntered(MouseEvent e) {
				rightBtn.setIcon(rightEnterBtnImage);
				rightBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서 위치시 손가락모양으로 바뀜
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rightBtn.setIcon(rightBasicBtnImage);
				rightBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 원래 커서로 바뀜
			}

			@Override // 버튼 누를때
			public void mousePressed(MouseEvent e) {
				Music btnPressMusic = new Music("menuEnter.mp3", false); // 버튼 누를 때 효과음 삽입
				btnPressMusic.start();
				selectRight();
			}
		});

		add(rightBtn);	

		// 6. 난이도 선택 Easy 버튼
		easyBtn.setVisible(false);
		easyBtn.setBounds(375, 580, 250, 67);	// 위치 x, y좌표 , 이미지 크기
		easyBtn.setBorderPainted(false);
		easyBtn.setContentAreaFilled(false);
		easyBtn.setFocusPainted(false);
		easyBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyBtn.setIcon(easyEnterBtnImage);
				easyBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서 위치시 손가락모양으로 바뀜
			}

			@Override
			public void mouseExited(MouseEvent e) {
				easyBtn.setIcon(easyBasicBtnImage);
				easyBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 원래 커서로 바뀜
			}

			@Override // 버튼 누를때
			public void mousePressed(MouseEvent e) {
				Music btnPressMusic = new Music("menuEnter.mp3", false);	// 버튼 누를 때 효과음 삽입
				btnPressMusic.start();
				gameStart(nowSeleted, "Easy");
			}
		});

		add(easyBtn);
		
		// 7. 난이도 선택 Hard 버튼
		hardBtn.setVisible(false);
		hardBtn.setBounds(655, 580, 250, 67);;	// 위치 x, y좌표 , 이미지 크기
		hardBtn.setBorderPainted(false);
		hardBtn.setContentAreaFilled(false);
		hardBtn.setFocusPainted(false);
		hardBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardBtn.setIcon(hardEnterBtnImage);
				hardBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서 위치시 손가락모양으로 바뀜
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hardBtn.setIcon(hardBasicBtnImage);
				hardBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 원래 커서로 바뀜
			}

			@Override // 버튼 누를때
			public void mousePressed(MouseEvent e) {
				Music btnPressMusic = new Music("menuEnter.mp3", false); // 버튼 누를 때 효과음 삽입
				btnPressMusic.start();
				gameStart(nowSeleted, "Hard");
			}
		});

		add(hardBtn);

		// 8. 게임화면에서 곡선택 화면으로 back 버튼
		backBtn.setVisible(false);
		backBtn.setBounds(20, 50, 60, 60);;	// 위치 x, y좌표 , 이미지 크기
		backBtn.setBorderPainted(false);
		backBtn.setContentAreaFilled(false);
		backBtn.setFocusPainted(false);
		backBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				backBtn.setIcon(backEnterBtnImage);
				backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서 위치시 손가락모양으로 바뀜
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backBtn.setIcon(backBasicBtnImage);
				backBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 원래 커서로 바뀜
			}

			@Override // 버튼 누를때
			public void mousePressed(MouseEvent e) {
				Music btnPressMusic = new Music("menuEnter.mp3", false); // 버튼누룰 때 효과음 삽입
				btnPressMusic.start();
				goMain();
			}
		});

		add(backBtn);
		
		/* 메뉴바 setting */
		menuBar.setBounds(0, 0, 1280, 30); // 메뉴바의 위치, 크기 지정

		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});

		menuBar.addMouseMotionListener(new MouseMotionAdapter() { // 메뉴바를 잡고 화면창을 이동할 수 있게 함
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});

		add(menuBar); // JFrame에 메뉴바를 추가
	}
	
	/* 생성자 끝 */

	
	/* 이미지를 그려주는 메서드 : 프로그램 화면 크기만 이미지를 생성해서 그려줌(전체화면 이미지를 매순간마다 반복하면서 그려줌) */
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D) screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(backGround, 0, 0, null); // 단순하게 이미지를 그려줄때 사용

		if (isMainScreen == true) {				   // 메인화면(곡선택 화면)이면 다음의 이미지를 그려줘라
			g.drawImage(selectedImage, 340, 100, null);
		}

		if (isGameScreen == true) {			  // 게임화면이면 다음의 이미지를 그려줘라
			game.screenDraw(g);
		}

		paintComponents(g); // 이미지 그리는 방법 - 항상존재, 고정된 이미지를 그릴때 사용
		
		try {
			Thread.sleep(10);		// 시간차를 두면서 실행되게끔, 노트가 안정적으로 떨어지게끔 하기위함
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.repaint();
	}
	/* 이미지를 그려주는 메서드 끝 */
	
	
	/* 각 음악들이 담겨져있는 트랙에서 음악을 선택할때 발생되는 이벤트를 정리한 메서드 */
	public void selectTrack(int nowSeleted) {
		if (selectedMusic != null) {
			selectedMusic.close();
		}

		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSeleted).getSelectImage())).getImage();
		selectedMusic = new Music(trackList.get(nowSeleted).getStartMusic(), true);
		selectedMusic.start();
	}

	public void selectLeft() {
		if (nowSeleted == 0) {
			nowSeleted = trackList.size() - 1; // 가장 마지막 곡
		} else {
			nowSeleted--;
		}

		selectTrack(nowSeleted);
	}

	public void selectRight() {
		if (nowSeleted == trackList.size() - 1) {
			nowSeleted = 0;
		} else {
			nowSeleted++;
		}

		selectTrack(nowSeleted);
	}
	/* 각 음악들이 담겨져있는 트랙에서 음악을 선택할때 발생되는 이벤트를 정리한 메서드 끝 */
	
	
	/* 곡 선택 후 게임을 실행하는 메서드 */
	public void gameStart(int nowSelected, String difficulty) {
		if (selectedMusic != null) {
			selectedMusic.close();
		}

		isMainScreen = false;
		leftBtn.setVisible(false);
		rightBtn.setVisible(false);
		easyBtn.setVisible(false);
		hardBtn.setVisible(false);
		backGround = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage())).getImage();
		backBtn.setVisible(true);
		isGameScreen = true;

		game = new Game(trackList.get(nowSelected).getTitleName(), difficulty, trackList.get(nowSelected).getGameMusic());
		game.start();
		
		setFocusable(true); // 키보드 이벤트가 오류없이 정확히 캐치할수 있도록, 화면이 바뀜에 따라 포커스를 맞추어야함
	}
	/* 곡 선택 후 게임을 실행하는 메서드 끝 */
	
	
	/* 게임화면에서 메인화면(곡선택화면)으로 돌아가는 메서드 */
	public void goMain() {
		isMainScreen = true;
		backGround = new ImageIcon(Main.class.getResource("../images/mainBackGround.png")).getImage();
		
		leftBtn.setVisible(true);
		rightBtn.setVisible(true);
		easyBtn.setVisible(true);
		hardBtn.setVisible(true);	
		backBtn.setVisible(false);	
		
		selectTrack(nowSeleted);
		
		isGameScreen = false;
		game.close();
	}
	
	
	/* 시작화면(인트로화면)에서 메인화면(곡선택화면)으로 가는 메서드 */
	public void enterMain() {
		isMainScreen = true;
		backGround = new ImageIcon(Main.class.getResource("../images/mainBackGround.png")).getImage();
		
		startBtn.setVisible(false);
		quitBtn.setVisible(false);
		
		leftBtn.setVisible(true);
		rightBtn.setVisible(true);
		easyBtn.setVisible(true);
		hardBtn.setVisible(true);
		backBtn.setVisible(false);
		
		introMusic.close();
		selectTrack(0);		// 0 : nowselected , trackList에서 첫번째 곡
	}
}
