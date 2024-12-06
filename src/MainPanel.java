import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.util.*;
import java.awt.event.MouseListener;
import java.awt.font.TextLayout;
import java.awt.event.MouseEvent;

public class MainPanel extends JPanel implements MouseListener {
	private BufferedImage vpbg, exampleCard, background, buycardbtn, tokenlimit, holdcardbtn;
	private BufferedImage[] cardbacks;
	private ArrayList<Token> tokens;
	public GameState gamestate;
	private String holdcard;

	public MainPanel() {

	}

	public MainPanel(GameState g) {
		try {
			gamestate = g;
			// gamestate.players[gamestate.playerTurn].addPoints(14);
			background = ImageIO.read(new File("background.png"));
			buycardbtn = ImageIO.read(new File("buycardbtn.png"));
			holdcard = "";
			holdcardbtn = ImageIO.read(new File("holdcardbtn.png"));
			String x = "0_BK_1_0,0,1,3,1.png";
			exampleCard = ImageIO.read(new File(x));
			vpbg = ImageIO.read(new File("victory point background.png"));
			// uploading cardback images
			cardbacks = new BufferedImage[3];
			tokenlimit = ImageIO.read(new File("tokenlimit.png"));
			for (int i = 1; i <= 3; i++) {
				String name = "level" + i + "cardback.jpg";
				cardbacks[i - 1] = ImageIO.read(new File(name));
			}
			// uploading token images
			tokens = new ArrayList<>();
			tokens.add(new Token("RE", ImageIO.read(MainPanel.class.getResource("/tokenImages/redToken.png"))));
			tokens.add(new Token("BL", ImageIO.read(MainPanel.class.getResource("/tokenImages/blueToken.png"))));
			tokens.add(new Token("WI", ImageIO.read(MainPanel.class.getResource("/tokenImages/whiteToken.png"))));
			tokens.add(new Token("GR", ImageIO.read(MainPanel.class.getResource("/tokenImages/greenToken.png"))));
			tokens.add(new Token("BK", ImageIO.read(MainPanel.class.getResource("/tokenImages/blackToken.png"))));
			tokens.add(new Token("YE", ImageIO.read(MainPanel.class.getResource("/tokenImages/yellowToken.png"))));
		} catch (Exception E) {
			System.out.println("Exception error");
			return;
		}
		repaint();
		addMouseListener(this);
	}

	public void paint(Graphics g) {
		super.paint(g);
		checkGameOver();
		Font font = new Font("Book Antiqua", Font.BOLD, getWidth() * 5 / 384);
		Font bigFont = font.deriveFont(new Float(getWidth() * 3 / 128));
		Font biggerFont = font.deriveFont(new Float(getWidth() * 5 / 128));
		g.setFont(font);
		g.setColor(Color.WHITE);
		// draws the background
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
		// draw patron
		for (int i = 0; i < gamestate.patrons.size(); i++) {
			Patron p = gamestate.patrons.get(i);
			g.drawImage(p.getImage(), getWidth() * 78 / 192 + getWidth() * 17 / 384 * i, getHeight() * 11 / 54,
					getWidth() / 24, getWidth() / 24, null);
		}
		// draws purchasable cards
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				Card c = gamestate.cards.get(3 - i)[j];
				if(c != null) {
				g.drawImage(c.getFront(),
						getWidth() * 47 / 128 + getWidth() * 9 / 160 * (j + 1) + (getWidth() / 128 * j),
						getHeight() * 83 / 270 + getHeight() * 167 / 1080 * i, getWidth() * 9 / 160,
						getHeight() * 19 / 135, null);
				}
			}
		}
		// draws decks
		for (int i = 1; i <= 3; i++) {
			g.drawImage(cardbacks[3 - i], getWidth() * 23 / 64,
					getHeight() / 6 + getHeight() * 19 / 135 * i + (getHeight() / 72 * (i - 1)), getWidth() * 9 / 160,
					getHeight() * 19 / 135, null);
		}
		// draws take button
		/*
		 * for (int i = 0; i < 2; i++) { g.drawImage(vpbg, getWidth() * 73/192,
		 * getHeight() *2 / 9 + (i * getHeight() * 5/9), getWidth()/24, getHeight() *
		 * 2/27, null); }
		 */
		// draws player buy card button
		g.drawImage(buycardbtn, getWidth() * 25 / 64, getHeight() * 23 / 27, getWidth() * 4 / 32, getHeight() / 18,
				null);
		// draws hold card button
		g.drawImage(holdcardbtn, getWidth() * 25 / 48, getHeight() * 23 / 27, getWidth() * 4 / 32, getHeight() / 18,
				null);
		// draws the available tokens
		for (int i = 0; i < tokens.size(); i++) {
			Token t = tokens.get(i);
			g.drawImage(t.getImage(), getWidth() * 24 / 64 + (i + 1) * getWidth() * 7 / 192, getHeight() * 7 / 9,
					getWidth() / 32, getHeight() / 18, null);
			g.drawString("" + gamestate.totalTokens.get(t.suite()),
					getWidth() * 18 / 48 + (i + 1) * getWidth() * 7 / 192, getHeight() * 179 / 216);
		}

		// draws each players inventory
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				g.setFont(font);
				g.setColor(Color.white);
				Patron p = gamestate.patrons.get(0);
				Player paintingPlayer = gamestate.players[2 * i + j];
				// draws the hold cards of each player starting from top left
				for (int k = 0; k < paintingPlayer.heldCards().size(); k++) {
					g.drawImage(cardbacks[paintingPlayer.heldCards().get(k).level() - 1],
							getWidth() * 23 / 640 * k + getWidth() * 5 / 64 + getWidth() * 150 / 192 * j,
							getHeight() * 5 / 54 + getHeight() * 25 / 54 * i, getWidth() / 32, getHeight() * 19 / 270,
							null);
				}
				// draws patrons
				for(int a = 0; a < paintingPlayer.patrons().size(); a++) {
					g.drawImage(paintingPlayer.patrons().get(a).getImage(), getWidth() * 44 / 192 + getWidth() * 101 / 192 * j,
							getHeight() * 11 / 108 + getHeight() * 25 / 54 * i + getHeight() *5/54 * a, getWidth() / 24, getWidth() / 24, null);
				}
				// draws the tokens of each player + how much of each + cards of each color
				HashMap<String, ArrayList<Card>> playercards = paintingPlayer.getCards();
				for (int m = 0; m < 5; m++) {
					Token t = tokens.get(m);
					String suite = t.suite();
					g.drawImage(t.getImage(),
							getWidth() * 7 / 192 + getWidth() * 7 / 192 * m + getWidth() * 150 / 192 * j,
							getHeight() * 5 / 27 + getHeight() * 25 / 54 * i, getWidth() * 5 / 192,
							getHeight() * 5 / 108, null);
					g.drawString("" + paintingPlayer.getTokens(t.suite()),
							getWidth() * 7 / 192 + getWidth() * 7 / 192 * m + getWidth() * 150 / 192 * j,
							getHeight() * 25 / 108 + getHeight() * 25 / 54 * i);
					ArrayList<Card> cardlist = playercards.get(suite);
					if (cardlist != null) {
						for (int l = 0; l < cardlist.size(); l++) {
							g.drawImage(cardlist.get(l).getFront(),
									getWidth() * 7 / 192 + getWidth() * 7 / 192 * m + getWidth() * 150 / 192 * j,
									getHeight() * 13 / 54 + getHeight() / 54 * l + getHeight() * 25 / 54 * i,
									getWidth() * 5 / 192, getHeight() * 7 / 108, null);
						}
					}
				}
				// draws the yellow tokens of each player
				g.drawImage(tokens.get(5).getImage(), getWidth() * 7 / 192 + getWidth() * 150 / 192 * j,
						getHeight() * 11 / 108 + getHeight() * 25 / 54 * i, getWidth() / 32, getHeight() / 18, null);
				g.drawString("" + paintingPlayer.getTokens("YE"), getWidth() * 7 / 192 + getWidth() * 150 / 192 * j,
						getHeight() * 17 / 108 + getHeight() * 25 / 54 * i);
				// draws victory points + the score of each player
				g.drawImage(vpbg, getWidth()*7/24 + getWidth() * 780 / 1920 * j,
						getHeight() * 317 / 1080 + getHeight() / 3 * i, getWidth() * 29 / 640, getHeight() * 29 / 360,
						null);
				g.setFont(bigFont);
				g.drawString("" + paintingPlayer.getScore(), getWidth() * 37/120 + getWidth() * 780 / 1920 * j,
						getHeight() * 25 / 72 + getHeight() / 3 * i);
				g.setColor(new Color(213, 160, 69));
				// drawing player labels
				g.drawString("PLAYER " + (2 * i + j + 1), getWidth() * 5 / 96 + getWidth() * 25 / 32 * j,
						getHeight() * 2 / 27 + getHeight() * 25 / 54 * i);
			}
		}
		g.drawString(holdcard, getWidth() * 23 / 64, getHeight() / 10);
		g.setFont(biggerFont);
		g.drawString("Player " + (gamestate.playerTurn + 1) + "'s Turn", getWidth() * 25 / 64, getHeight() / 6);
		if (gamestate.gameover != -1) {
			g.drawString("LAST ROUND", getWidth() * 25 / 64, getHeight() * 26 / 27);
		}
		if (gamestate.extraTokens != 0) {
			drawTokenLimit(g, gamestate.extraTokens, bigFont);
		}

	}

	public void drawTokenLimit(Graphics g, int t, Font f) {
		g.setFont(f);
		g.setColor(Color.white);
		g.drawImage(tokenlimit, getWidth() * 23 / 64, getHeight() * 59 / 216, getWidth() * 5 / 16,
				getHeight() * 287 / 1080, null);
		for (int i = 0; i < 5; i++) {
			g.drawImage(tokens.get(i).getImage(), getWidth() * 41 / 96 + getWidth() * 5 / 128 * i, getHeight() * 7 / 18,
					getWidth() / 32, getHeight() / 18, null);
			g.drawString("" + gamestate.players[gamestate.playerTurn].getTokens(tokens.get(i).suite()),
					getWidth() * 41 / 96 + getWidth() * 5 / 128 * i, getHeight() * 49 / 108);
		}
		g.drawString("Return " + t + " more token(s)", getWidth() * 25 / 64, getHeight() * 112 / 216);
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
		System.out.println(x + "," + y);
		/*
		 * if (x >= 748 && x <= 1117 && y >= 332 && y <= 833) { //
		 * System.out.println("true"); }
		 */
		// checks to see if the player can hold a card
		if (holdcard.equals("SELECT A CARD TO HOLD")) {
			Card holdc = getHoldCard(x, y);
			if (holdc != null) {
				gamestate.holdCard(holdc);
				holdcard = "";
			}
		}
		String tokenString = clickedToken(x, y);
		if (tokenString != null) {
			gamestate.takeToken(tokenString);
		}
		if (gamestate.extraTokens != 0) {
			String extra = removeExtraToken(x, y);
			if (extra != null) {
				gamestate.returnExtra(extra);
			}
		}
		// hold card button
		if (x >= getWidth() * 25 / 48 && x <= getWidth() * 25 / 48 + getWidth() * 4 / 32 && y >= getHeight() * 23 / 27
				&& y <= getHeight() * 23 / 27 + getHeight() / 18) {
			if (gamestate.tokenCounter == 0 && gamestate.players[gamestate.playerTurn].getHeldCards().size() < 3)
				holdcard = "SELECT A CARD TO HOLD";

		}
		// buy card button
		if (x >= getWidth() * 25 / 64 && x <= getWidth() * 25 / 64 + getWidth() * 4 / 32 && y >= getHeight() * 23 / 27
				&& y <= getHeight() * 23 / 27 + getHeight() / 18 && !holdcard.equals("SELECT A CARD TO HOLD")
				&& gamestate.takenTokens.size() == 0) {
			System.out.println("Buy Card");
			JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			if (parentFrame instanceof Frame) {
				((Frame) parentFrame).showBuyCardPanel();
			}
		}

		repaint();
	}

	public Card getHoldCard(int x, int y) {
		Card c = null;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				if (x >= getWidth() * 47 / 128 + getWidth() * 9 / 160 * (j + 1) + (getWidth() / 128 * j)
						&& x <= getWidth() * 47 / 128 + getWidth() * 9 / 160 * (j + 1) + (getWidth() / 128 * j)
								+ getWidth() * 9 / 160
						&& y >= getHeight() * 83 / 270 + getHeight() * 167 / 1080 * i
						&& y <= getHeight() * 83 / 270 + getHeight() * 167 / 1080 * i + getHeight() * 19 / 135) {
					c = gamestate.cards.get(3 - i)[j];
				}
			}
		}
		for (int i = 1; i <= 3; i++) {
			if( x >= getWidth() * 23 / 64 && x <= getWidth() * 23 / 64 + getWidth() * 9 / 160 && y >= getHeight() / 6 + getHeight() * 19 / 135 * i + (getHeight() / 72 * (i - 1)) && y <= getHeight() / 6 + getHeight() * 19 / 135 * i + (getHeight() / 72 * (i - 1)) + getHeight() * 19 / 135) 
			if(i == 1) 
				c = gamestate.drawCard(3-i+1);
		}
		
		return c;
	}

	public String clickedToken(int x, int y) {
		for (int i = 0; i < 5; i++) {
			double lx = getWidth() * 24 / 64 + (i + 1) * getWidth() * 7 / 192;
			double ly = getHeight() * 7 / 9;
			double radius = getWidth() / 64;
			double rx = lx + radius;
			double ry = ly + getHeight() / 36;
			double distance = Math.sqrt(Math.pow(x - rx, 2) + Math.pow(y - ry, 2));
			if (distance <= radius) {
				return tokens.get(i).suite();
			}
		}
		return null;
	}

	public String removeExtraToken(int x, int y) {
		for (int i = 0; i < tokens.size(); i++) {
			double lx = getWidth() * 41 / 96 + getWidth() * 5 / 128 * i;
			double ly = getHeight() * 7 / 18;
			double radius = getWidth() / 64;
			double rx = lx + radius;
			double ry = ly + getHeight() / 36;
			double distance = Math.sqrt(Math.pow(x - rx, 2) + Math.pow(y - ry, 2));
			if (distance <= radius) {
				return tokens.get(i).suite();
			}
		}
		return null;
	}

	public void checkGameOver() {
		if (gamestate.gameoverboolean) {
			JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			if (parentFrame instanceof Frame) {
				((Frame) parentFrame).showWinnerScreen();
			}
		}

	}

}
