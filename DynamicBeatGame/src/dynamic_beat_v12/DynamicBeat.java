package dynamic_beat_v12;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;

	private ImageIcon closeBtnEnterImage = new ImageIcon(Main.class.getResource("../images/closeBtnEnter.png"));
	private ImageIcon closeBtnBasicImage = new ImageIcon(Main.class.getResource("../images/closeBtnBasic.png"));
	private ImageIcon startBtnBasicImage = new ImageIcon(Main.class.getResource("../images/startBtnBasic.png"));
	private ImageIcon startBtnEnterImage = new ImageIcon(Main.class.getResource("../images/startBtnEnter.png"));
	private ImageIcon quitBtnBasicImage = new ImageIcon(Main.class.getResource("../images/quitBtnBasic.png"));
	private ImageIcon quitBtnEnterImage = new ImageIcon(Main.class.getResource("../images/quitBtnEnter.png"));
	private ImageIcon leftBasicBtnImage = new ImageIcon(Main.class.getResource("../images/leftBasicBtn.png"));
	private ImageIcon leftEnterBtnImage = new ImageIcon(Main.class.getResource("../images/leftEnterBtn.png"));
	private ImageIcon rightBasicBtnImage = new ImageIcon(Main.class.getResource("../images/rightBasicBtn.png"));
	private ImageIcon rightEnterBtnImage = new ImageIcon(Main.class.getResource("../images/rightEnterBtn.png"));
	private ImageIcon easyBasicBtnImage = new ImageIcon(Main.class.getResource("../images/easyBasicBtn.png"));
	private ImageIcon easyEnterBtnImage = new ImageIcon(Main.class.getResource("../images/easyEnterBtn.png"));
	private ImageIcon hardBasicBtnImage = new ImageIcon(Main.class.getResource("../images/hardBasicBtn.png"));
	private ImageIcon hardEnterBtnImage = new ImageIcon(Main.class.getResource("../images/hardEnterBtn.png"));
	private ImageIcon backBasicBtnImage = new ImageIcon(Main.class.getResource("../images/backBtnBasic.png"));
	private ImageIcon backEnterBtnImage = new ImageIcon(Main.class.getResource("../images/backBtnEnter.png"));
	
	private Image backGround = new ImageIcon(Main.class.getResource("../images/introBackGround.jpg")).getImage();

	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	
	private JButton closeBtn = new JButton(closeBtnBasicImage);
	private JButton startBtn = new JButton(startBtnBasicImage);
	private JButton quitBtn = new JButton(quitBtnBasicImage);
	private JButton leftBtn = new JButton(leftBasicBtnImage);
	private JButton rightBtn = new JButton(rightBasicBtnImage);
	private JButton easyBtn = new JButton(easyBasicBtnImage);
	private JButton hardBtn = new JButton(hardBasicBtnImage);
	
	private JButton backBtn = new JButton(backBasicBtnImage);
	
	private int mouseX, mouseY;		// 화면창 내에서 마우스의 x,y좌표
	
	private boolean isMainScreen = false;		// 메인화면인지 확인하는 변수
	private boolean isGameScreen = false;		// 현재 게임화면으로 넘어왔는지 확인하는 변수

	private ArrayList<Track> trackList = new ArrayList<Track>();		// 각 음악을 담을 수 있는 리스트
	
	private Image titleImage;
	private Image selectedImage;
	private Music selectedMusic;
	private Music introMusic = new Music("introMusic.mp3", true);		// 시작화면에서 음악이 무한반복(쓰레드)
	private int nowSeleted = 0;
	
	public static Game game;		// 프로젝트 전체에서 이용되는 변수
	
	public DynamicBeat() {
		setUndecorated(true);													// 실행했을때 기본 메뉴바가 안보임
		setTitle("Dynamic Beat"); 												// 게임창 타이틀
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); 	// 게임창 사이즈
		setResizable(false);														// 게임창 크기 조절불가
		setLocationRelativeTo(null); 											// 실행했을때 게임창이 정중앙에 위치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			// 게임창 종료시 전체가 종료됨
		setVisible(true); 															// 게임창이 정상적으로 출력
		setBackground(new Color(0,0,0,0));									// paintComponents(); 했을때 배경이 white
		setLayout(null);															// 버튼, JLabel을 그 위치에 위치하도록
		
		addKeyListener(new KeyListener());
		
		introMusic.start();
		
		// 트랙리스트에 각각 트랙객체 추가
		trackList.add(new Track("MightyLoveTitle.png", "MightyLove_startImage.png", "MightyLove_gameImage.png", "Mighty Love_selected.mp3", "Joakim Karud - Mighty Love.mp3", "Joakim Karud - Mighty Love"));		//index 0
		trackList.add(new Track("energyTitle.png", "energy_startImage.png", "energy_gameImage.png", "Energy_selected.mp3", "Bensound - Energy.mp3", "Bensound - Energy"));												//index 1
		trackList.add(new Track("wildFolwerTitle.png", "wildFlower_startImage.png", "wildFlower_gameImage.png", "Wild Flower_selected.mp3", "Joakim Karud - Wild Flower.mp3", "Joakim Karud - Wild Flower"));			//index 2
		
		// 메뉴창에 종료버튼 시작
		closeBtn.setBounds(1245, 0, 30 ,30);
		closeBtn.setBorderPainted(false);
		closeBtn.setContentAreaFilled(false);
		closeBtn.setFocusPainted(false);
		closeBtn.addMouseListener(new MouseAdapter() {		// 마우스 오버시 이미지 아이콘 변경
			@Override
			public void mouseEntered(MouseEvent e) {
				closeBtn.setIcon(closeBtnEnterImage);
				closeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));	// 커서 위치시 손가락모양으로 바뀜
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				closeBtn.setIcon(closeBtnBasicImage);
				closeBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	// 원래 커서로 바뀜
			}
			
			@Override													// 버튼 누를때
			public void mousePressed(MouseEvent e) {
				Music btnPressMusic = new Music("btnPressMusic.mp3", false);	// 버큰누룰때 효과음 삽입
				btnPressMusic.start();
				try {
					Thread.sleep(500);					// 1초뒤에 프로그램이 종료
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		
		add(closeBtn);
		// 메뉴창에 종료버튼 끝
		
		
		// 시작버튼 시작
		startBtn.setBounds(950, 350, 300 ,80);
		startBtn.setBorderPainted(false);
		startBtn.setContentAreaFilled(false);
		startBtn.setFocusPainted(false);
		startBtn.addMouseListener(new MouseAdapter() {		// 마우스 오버시 이미지 아이콘 변경
			@Override
			public void mouseEntered(MouseEvent e) {
				startBtn.setIcon(startBtnEnterImage);
				startBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));	// 커서 위치시 손가락모양으로 바뀜
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				startBtn.setIcon(startBtnBasicImage);
				startBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	// 원래 커서로 바뀜
			}
			
			@Override													// 버튼 누를때
			public void mousePressed(MouseEvent e) {
				Music btnPressMusic = new Music("btnPressMusic.mp3", false);	// 버튼누룰때 효과음 삽입
				btnPressMusic.start();
				enterMain();
			}
		});
		
		add(startBtn);
		// 시작버튼 끝
		
		// 종료버튼 시작
		quitBtn.setBounds(950, 450, 300 ,80);
		quitBtn.setBorderPainted(false);
		quitBtn.setContentAreaFilled(false);
		quitBtn.setFocusPainted(false);
		quitBtn.addMouseListener(new MouseAdapter() {		// 마우스 오버시 이미지 아이콘 변경
			@Override
			public void mouseEntered(MouseEvent e) {
				quitBtn.setIcon(quitBtnEnterImage);
				quitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));	// 커서 위치시 손가락모양으로 바뀜
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				quitBtn.setIcon(quitBtnBasicImage);
				quitBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	// 원래 커서로 바뀜
			}
			
			@Override													// 버튼 누를때
			public void mousePressed(MouseEvent e) {
				Music btnPressMusic = new Music("btnPressMusic.mp3", false);	// 버큰누룰때 효과음 삽입
				btnPressMusic.start();
				try {
					Thread.sleep(500);					// 1초뒤에 프로그램이 종료
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		
		add(quitBtn);
		// 종료버튼 끝
		
		
		// 곡선택 왼쪽버튼 시작
		leftBtn.setVisible(false);
		leftBtn.setBounds(140, 310, 60 ,60);
		leftBtn.setBorderPainted(false);
		leftBtn.setContentAreaFilled(false);
		leftBtn.setFocusPainted(false);
		leftBtn.addMouseListener(new MouseAdapter() {		// 마우스 오버시 이미지 아이콘 변경
			@Override
			public void mouseEntered(MouseEvent e) {
				leftBtn.setIcon(leftEnterBtnImage);
				leftBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));	// 커서 위치시 손가락모양으로 바뀜
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				leftBtn.setIcon(leftBasicBtnImage);
				leftBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	// 원래 커서로 바뀜
			}
			
			@Override													// 버튼 누를때
			public void mousePressed(MouseEvent e) {
				Music btnPressMusic = new Music("btnPressMusic.mp3", false);	// 버튼누룰때 효과음 삽입
				btnPressMusic.start();
				selectLeft();
			}
		});
		
		add(leftBtn);
		// 곡선택 왼쪽버튼 끝

		
		// 곡선택 오른쪽버튼 시작
		rightBtn.setVisible(false);
		rightBtn.setBounds(1080, 310, 60 ,60);
		rightBtn.setBorderPainted(false);
		rightBtn.setContentAreaFilled(false);
		rightBtn.setFocusPainted(false);
		rightBtn.addMouseListener(new MouseAdapter() {		// 마우스 오버시 이미지 아이콘 변경
			@Override
			public void mouseEntered(MouseEvent e) {
				rightBtn.setIcon(rightEnterBtnImage);
				rightBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));	// 커서 위치시 손가락모양으로 바뀜
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				rightBtn.setIcon(rightBasicBtnImage);
				rightBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	// 원래 커서로 바뀜
			}
			
			@Override													// 버튼 누를때
			public void mousePressed(MouseEvent e) {
				Music btnPressMusic = new Music("btnPressMusic.mp3", false);	// 버큰누룰때 효과음 삽입
				btnPressMusic.start();
				selectRight();
			}
		});
		
		add(rightBtn);
		// 곡선택 오른쪽버튼 끝

		
		// easy버튼 시작
		easyBtn.setVisible(false);
		easyBtn.setBounds(375, 580, 250, 67);
		easyBtn.setBorderPainted(false);
		easyBtn.setContentAreaFilled(false);
		easyBtn.setFocusPainted(false);
		easyBtn.addMouseListener(new MouseAdapter() {		// 마우스 오버시 이미지 아이콘 변경
			@Override
			public void mouseEntered(MouseEvent e) {
				easyBtn.setIcon(easyEnterBtnImage);
				easyBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));	// 커서 위치시 손가락모양으로 바뀜
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				easyBtn.setIcon(easyBasicBtnImage);
				easyBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	// 원래 커서로 바뀜
			}
			
			@Override													// 버튼 누를때
			public void mousePressed(MouseEvent e) {
				Music btnPressMusic = new Music("btnPressMusic.mp3", false);	// 버큰누룰때 효과음 삽입
				btnPressMusic.start();
				gameStart(nowSeleted, "Easy");
			}
		});
		
		add(easyBtn);
		// easy버튼 끝
		
		
		// hard버튼 시작
		hardBtn.setVisible(false);
		hardBtn.setBounds(655, 580, 250, 67);
		hardBtn.setBorderPainted(false);
		hardBtn.setContentAreaFilled(false);
		hardBtn.setFocusPainted(false);
		hardBtn.addMouseListener(new MouseAdapter() {		// 마우스 오버시 이미지 아이콘 변경
			@Override
			public void mouseEntered(MouseEvent e) {
				hardBtn.setIcon(hardEnterBtnImage);
				hardBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));	// 커서 위치시 손가락모양으로 바뀜
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				hardBtn.setIcon(hardBasicBtnImage);
				hardBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	// 원래 커서로 바뀜
			}
			
			@Override													// 버튼 누를때
			public void mousePressed(MouseEvent e) {
				Music btnPressMusic = new Music("btnPressMusic.mp3", false);	// 버큰누룰때 효과음 삽입
				btnPressMusic.start();
				gameStart(nowSeleted, "Hard");
			}
		});
		
		add(hardBtn);
		// hardBtn버튼 끝

		
		// back 버튼 시작
		backBtn.setVisible(false);
		backBtn.setBounds(20, 50, 60, 60);
		backBtn.setBorderPainted(false);
		backBtn.setContentAreaFilled(false);
		backBtn.setFocusPainted(false);
		backBtn.addMouseListener(new MouseAdapter() {		// 마우스 오버시 이미지 아이콘 변경
			@Override
			public void mouseEntered(MouseEvent e) {
				backBtn.setIcon(backEnterBtnImage);
				backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));	// 커서 위치시 손가락모양으로 바뀜
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				backBtn.setIcon(backBasicBtnImage);
				backBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	// 원래 커서로 바뀜
			}
			
			@Override													// 버튼 누를때
			public void mousePressed(MouseEvent e) {
				Music btnPressMusic = new Music("btnPressMusic.mp3", false);	// 버튼누룰때 효과음 삽입
				btnPressMusic.start();
				goMain();
			}
		});
		
		add(backBtn);
		// backBtn버튼 끝
		
		
		// 메뉴바 시작
		menuBar.setBounds(0,0,1280,30);								// 메뉴바의 위치, 크기 지정
		
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		
		// 메뉴바를 잡고 화면창을 이동할 수 있게 함
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		
		add(menuBar);														// JFrame에 메뉴바를 추가
		// 메뉴바 끝
	}

	// 게임창에 이미지를 그려주는 메서드 : 프로그램 화면 크기만 이미지를 생성해서 그려줌(전체화면 이미지를 매순간마다 반복하면서 그려줌)
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D)screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(backGround, 0, 0, null);	//	단순하게 이미지를 그려줄때 사용
	
		if(isMainScreen == true) {
			g.drawImage(selectedImage, 340, 100,  null);
			g.drawImage(titleImage, 340, 90,  null);
		}
		
		if(isGameScreen == true) {
			game.screenDraw(g);
		}

		paintComponents(g);								// 이미지 그리는 방법 - 항상존재, 고정된 이미지를 그릴때 사용
		this.repaint();
	}
	
	public void selectTrack(int nowSeleted) {
		if(selectedMusic != null) {
			selectedMusic.close();
		}
		
		titleImage = new ImageIcon(Main.class.getResource("../images/" +trackList.get(nowSeleted).getTitleImage())).getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" +trackList.get(nowSeleted).getStartImage())).getImage();
	
		selectedMusic = new Music(trackList.get(nowSeleted).getStartMusic(), true);
		selectedMusic.start();
	}
	
	public void selectLeft() {
		if(nowSeleted == 0) {
			nowSeleted = trackList.size() -1;		//가장 마지막 곡
		} else {
			nowSeleted--;
		}
		
		selectTrack(nowSeleted);
	}
	
	public void selectRight() {
		if(nowSeleted ==trackList.size() -1) {
			nowSeleted = 0;
		} else {
			nowSeleted++;
		}
		
		selectTrack(nowSeleted);
	}	
	
	public void gameStart(int nowSelected, String difficulty) {
		if(selectedMusic != null) {
			selectedMusic.close();
		}
		
		isMainScreen = false;
		leftBtn.setVisible(false);
		rightBtn.setVisible(false);
		easyBtn.setVisible(false);
		hardBtn.setVisible(false);
		
		backGround = new ImageIcon(Main.class.getResource("../images/" +trackList.get(nowSelected).getGameImage())).getImage();
		backBtn.setVisible(true);
		
		isGameScreen = true;
		
		setFocusable(true);	// 화면이 바뀜에 따라 포커스를 맞추어야함
		game = new Game(trackList.get(nowSelected).getTitleName(), difficulty, trackList.get(nowSelected).getGameMusic());
	}
	
	public void goMain() {
		isMainScreen = true;
		leftBtn.setVisible(true);
		rightBtn.setVisible(true);
		easyBtn.setVisible(true);
		hardBtn.setVisible(true);
		backGround = new ImageIcon(Main.class.getResource("../images/mainBackGround.jpg")).getImage();
		backBtn.setVisible(false);
		selectTrack(nowSeleted);
		isGameScreen = false;
		game.close();
	}
	
	public void enterMain() {
		startBtn.setVisible(false);
		quitBtn.setVisible(false);
		backGround =  new ImageIcon(Main.class.getResource("../images/mainBackGround.jpg")).getImage();
		isMainScreen = true;
		
		leftBtn.setVisible(true);
		rightBtn.setVisible(true);
		easyBtn.setVisible(true);
		hardBtn.setVisible(true);
		
		introMusic.close();
		selectTrack(0);
	}
}
