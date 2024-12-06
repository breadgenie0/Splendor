
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;


public class Frame extends JFrame implements MouseListener, ActionListener {
	private static final int WIDTH = 1920;
	private static final int HEIGHT = 1080;
	private JPanel container;
	private JButton exitButton;
	private CardLayout cardlayout;
	private MainPanel mainGame;
	private String currentPanel;
	private GameState gamestate;


	public Frame(String fr){
		super(fr);
		try {
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cardlayout = new CardLayout();


		container = new JPanel();
		container.setLayout(cardlayout);
		gamestate = new GameState();
		StartScreen begin = new StartScreen();
		mainGame = new MainPanel(gamestate);
		currentPanel = "start";
		SplendorRules loadRules = new SplendorRules();
		BuyCardPanel buyCardScreen = new BuyCardPanel(gamestate);
		WinnerScreen winnerscreen = new WinnerScreen(gamestate);
		Navigation guide = new Navigation();
		container.add(begin, "StartScreen");
		container.add(mainGame, "MainPanel");
		container.add(guide, "navigation");
		container.add(loadRules, "rules");
		container.add(buyCardScreen, "BuyCard");
		container.add(winnerscreen, "winnerScreen");
		getContentPane().add(container);
		setVisible(true);
		addMouseListener(this);
		setResizable(true);
		}
		catch(Exception e) {
			System.out.println("frame error");
		}


	}


	public void mousePressed(MouseEvent e) {


	}


	public void mouseReleased(MouseEvent e) {


	}


	public void mouseEntered(MouseEvent e) {


	}


	public void mouseExited(MouseEvent e) {


	}


	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (e.getButton() == e.BUTTON1) {
			System.out.println(currentPanel);
			System.out.print(x + " " + y + " ");
			if (x >= getWidth()*47/120 && x <= getWidth()*47/120 + getWidth()*13/60 && y >= getHeight()/2 && y <= getHeight()/2 + getHeight()*107/540 && currentPanel.equals("start")){
				System.out.print("heyyy");
				cardlayout.show(container, "navigation");
				currentPanel = "main";
				//mainGame.setVisible(true);
			}
			if (y >= getHeight()*3/4 && y <= getHeight()*3/4 + getHeight()*7/54 && x >= getWidth()*55/128 && x <= getWidth()*55/128 + getWidth()*9/64 && currentPanel.equals("start")) {
				cardlayout.show(container, "rules");
				exitButton = new JButton("Exit Rulebook");
				exitButton.addActionListener(this);
				// Add the button to the bottom of the panel
				JPanel buttonPanel = new JPanel(); // Panel to hold the button
				buttonPanel.add(exitButton); // Add the button to this panel
				add(buttonPanel, BorderLayout.SOUTH); // Add the button panel to the south (bottom) of the main panel
			}
			if(currentPanel.equals("main")) {
				System.out.println("in frame class");
				if(x>10) {
					System.out.println("test");
				}
				if(x >= getWidth()*29/64 && x <= getWidth()*29/64 + getWidth() * 4/32 && y >= getHeight() * 23/27 && y <= getHeight() * 23/27 + getHeight() /18) {
					System.out.println("buy card clicked");
					cardlayout.show(container, "BuyCard");
					
				}
			}


		}


	}


	public void showBuyCardPanel() {
		System.out.println("buy");
		cardlayout.show(container,  "BuyCard");
	}
	public void showMainPanel() {
		cardlayout.show(container, "MainPanel");
	}
	public void showWinnerScreen() {
		cardlayout.show(container, "winnerScreen");
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exitButton) {
			cardlayout.show(container, "StartScreen");
			exitButton.setVisible(false);
		}
	}
	public void showNavigation() {
		cardlayout.show(container, "navigation");
	}
}





