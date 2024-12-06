
import java.awt.image.BufferedImage;
import javax.swing.*;


import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.util.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.util.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class BuyCardPanel extends JPanel implements MouseListener {
	private BufferedImage background, backbtn, cardframe, cardboughtsign, useWild, choosepatron;
	private ArrayList<Token> tokens;
	private GameState gamestate;
	private boolean cardbought;
	private Player currentplayer;
	public BuyCardPanel(GameState g) {
		try {
			gamestate = g;
			currentplayer = gamestate.players[gamestate.playerTurn];
			background = ImageIO.read(new File("playerviewbg.png"));
			backbtn = ImageIO.read(new File("backbtn.png"));
			cardframe = ImageIO.read(new File("cardframe.png"));
			cardboughtsign = ImageIO.read(new File("cardbought.png"));
			System.out.println(3);
			useWild = ImageIO.read(new File("useWild.png"));
			choosepatron = ImageIO.read(new File("choosepatron.png"));		
			tokens = new ArrayList<>();
			tokens.add(new Token("RE", ImageIO.read(MainPanel.class.getResource("/tokenImages/redToken.png"))));
			tokens.add(new Token("BL", ImageIO.read(MainPanel.class.getResource("/tokenImages/blueToken.png"))));
			tokens.add(new Token("WI", ImageIO.read(MainPanel.class.getResource("/tokenImages/whiteToken.png"))));
			tokens.add(new Token("GR", ImageIO.read(MainPanel.class.getResource("/tokenImages/greenToken.png"))));
			tokens.add(new Token("BK", ImageIO.read(MainPanel.class.getResource("/tokenImages/blackToken.png"))));
			tokens.add(new Token("YE", ImageIO.read(MainPanel.class.getResource("/tokenImages/yellowToken.png"))));
		} catch (Exception e) {
			System.out.println("Exception Error: Buy card panel");
		}
		addMouseListener(this);
	}

	public void paint(Graphics g) {
		super.paint(g);
		Font font = new Font("Book Antiqua", Font.BOLD, getWidth() * 5 / 384);
		Font bigFont = font.deriveFont(new Float(getWidth() * 3 / 128));
		Font biggerFont = font.deriveFont(new Float(getWidth() * 5 / 128));
		g.setFont(biggerFont);
		g.setColor(new Color(213, 160, 69));
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
		// current player
		 currentplayer = gamestate.players[gamestate.playerTurn];
		// draw patron
		for (int i = 0; i < gamestate.patrons.size(); i++) {
			Patron p = gamestate.patrons.get(i);
			g.drawImage(p.getImage(), getWidth() * 5 / 192 + getWidth() * 5 / 64 * i, getHeight() / 40,
					getWidth() * 7 / 96, getWidth() * 7 / 96, null);
		}
		// draws purchasable cards
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				Card c = gamestate.cards.get(3 - i)[j];
				if(c != null) {
				g.drawImage(c.getFront(), getWidth() / 16 + getWidth() * 5 / 64 * j,
						getHeight() / 5 + getHeight() * 43 / 216 * i, getWidth() * 9 / 128, getHeight() * 20 / 108,
						null);
				if (c.equals(gamestate.cardtobuy)  && !cardbought) {
					g.drawImage(cardframe, getWidth() / 16 + getWidth() * 5 / 64 * j,
							getHeight() / 5 + getHeight() * 43 / 216 * i, getWidth() * 9 / 128, getHeight() * 20 / 108,
							null);
				}
				}
			}
		}
		// draws back button
		g.drawImage(backbtn, getWidth() * 10 / 64, getHeight() * 23 / 27, getWidth() * 4 / 32, getHeight() / 18, null);
		// player turn label
		g.drawString("PLAYER " + (gamestate.playerTurn + 1), getWidth() / 2, getHeight() / 10);
		// drawing inventory
		// draws the players tokens
		g.setFont(bigFont);
		g.setColor(Color.white);
		HashMap<String, ArrayList<Card>> playercards = currentplayer.getCards();
		for (int i = 0; i < 5; i++) {
			Token t = tokens.get(i);
			String suite = t.suite();
			g.drawImage(t.getImage(), getWidth() * 15 / 32, getHeight() * 5 / 54 + getHeight() * 5 / 36 * (i + 1),
					getWidth() * 11 / 192, getHeight() * 11 / 108, null);
			g.drawString("" + currentplayer.getTokens(t.suite()), getWidth() * 89 / 192,
					getHeight() * 23 / 108 + getHeight() * 5 / 36 * (i + 1));
			//draws use wild token option
			if(currentplayer.getTokens("YE") > 0 && !cardbought)
			{
				g.drawImage(useWild, getWidth()*11/192 + getWidth()*15/32, getHeight() * 5 / 54 + getHeight() * 5 / 36 * (i + 1), getWidth()*11/192, getHeight() * 11 / 108/3, null);
			}
			// draws players cards
			ArrayList<Card> cardlist = playercards.get(suite);
			if (cardlist != null) {
				for (int j = 0; j < cardlist.size(); j++) {
					g.drawImage(cardlist.get(j).getFront(), getWidth() * 38 / 64 + getWidth() * 10 / 640 * j,
							getHeight() / 12 + getHeight() * 5 / 36 * (i + 1), getWidth() * 3 / 64,
							getHeight() * 127 / 1080, null);
				}
			}
		}

		// draws hold cards
		g.drawImage(tokens.get(5).getImage(), getWidth() * 175 / 192, getHeight() * 5 / 54 + getHeight() * 5 / 36,
				getWidth() * 11 / 192, getHeight() * 11 / 108, null);
		g.drawString("" + currentplayer.getTokens("YE"), getWidth() * 174 / 192,
				getHeight() * 23 / 108 + getHeight() * 5 / 36);

		for (int i = 0; i < currentplayer.heldCards().size(); i++) {
			g.drawImage(currentplayer.heldCards().get(i).getFront(), getWidth() * 175 / 192,
					getHeight() * 12 / 54 + getHeight() * 4 / 27 * (i + 1), getWidth() * 9 / 160,
					getHeight() * 19 / 135, null);
		}
		// draws the required tokens
		g.setFont(font);
		if (gamestate.requiredTokens != null && !gamestate.requiredTokens.isEmpty()) {
			int j = 0;
			for (String suite : gamestate.requiredTokens.keySet()) {
				Token t;
				for (int i = 0; i < tokens.size(); i++) {
					if (tokens.get(i).suite().equals(suite)) {
						j++;
						t = tokens.get(i);
						g.drawImage(t.getImage(), getWidth() * 11 / 16 + getWidth() / 24 * j, getHeight() * 2 / 27,
								getWidth() / 32, getHeight() / 18, null);
						g.drawString("" + gamestate.requiredTokens.get(suite),
								getWidth() * 11 / 16 + getWidth() / 24 * j, getHeight() * 7 / 54);
					}
				}
			}
		}
		if(gamestate.multiplepatrons) {
			g.drawImage(choosepatron, getWidth() / 12, getHeight() * 25 / 108, getWidth() * 25 / 96,
					getHeight() * 143 / 540, null );
			for(int i = 0; i < gamestate.aquiredpatrons.size(); i++) {
				g.drawImage(gamestate.aquiredpatrons.get(i).getImage(), getWidth()*5/48 + getWidth()*11/192*i, getHeight()*37/108, getWidth()*10/192,getHeight()*10/108, null);
			}
		}	// draws cardbought sign if the card is bought
		else if (cardbought && !gamestate.multiplepatrons) {
			g.drawImage(cardboughtsign, getWidth() / 12, getHeight() * 25 / 108, getWidth() * 25 / 96,
					getHeight() * 143 / 540, null);
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
		System.out.println("In buy card panel " + x + "," + y);
		// cardbought clicked
		if (cardbought && x > getWidth() / 12 && x < getWidth() / 12 + getWidth() * 25 / 96
				&& y > getHeight() * 25 / 108 && y < getHeight() * 25 / 108 + getHeight() * 143 / 540) {
			gamestate.endTurn();
			cardbought = false;
			JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			if (parentFrame instanceof Frame) {
				((Frame) parentFrame).showMainPanel();
			}
		}else {
			// checking if they click a card to buy
			Card c = chooseCard(x, y);
			if (c != null && !cardbought && !gamestate.multiplepatrons) {
				cardbought = gamestate.buyCard(c);
			}	
		}
		//choose patron if multiple patrons are available
				if(gamestate.multiplepatrons) {
					Patron p = choosepatron(x, y);
					if(p != null) {
						gamestate.choosepatron(p);
						cardbought = true;
					}
				}
				
		
		//paying for token
		if (gamestate.requiredTokens != null && !gamestate.requiredTokens.isEmpty() && !cardbought) {
			String tokenclicked = clickedToken(x, y);
			if (tokenclicked != null) {
				cardbought = gamestate.pay(tokenclicked);
			}
			else
			{
				tokenclicked = clickedWild(x, y);
				if(tokenclicked != null)
				{
					cardbought = gamestate.payWild(tokenclicked);
				}
			}
			
		}
		

		// back button
		if (!cardbought && x >= getWidth() * 10 / 64 && x <= getWidth() * 10 / 64 + getWidth() * 4 / 32
				&& y >= getHeight() * 23 / 27 && y <= getHeight() * 23 / 27 + getHeight() / 18) {
			System.out.println("back");
			JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			if (parentFrame instanceof Frame) {
				((Frame) parentFrame).showMainPanel();
			}
		}
		
		
		repaint();
	}

	public Card chooseCard(int x, int y) {
		System.out.println("chooseCArd");
		Card c = null;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				if (x >= getWidth() / 16 + getWidth() * 5 / 64 * j
						&& x <= getWidth() / 16 + getWidth() * 5 / 64 * j + getWidth() * 9 / 128
						&& y >= getHeight() / 5 + getHeight() * 43 / 216 * i
						&& y <= getHeight() / 5 + getHeight() * 43 / 216 * i + getHeight() * 20 / 108) {
					System.out.println("card chose" + x + y);
					c = gamestate.cards.get(3 - i)[j];

				}
			}
		}
		for (int i = 0; i < gamestate.players[gamestate.playerTurn].heldCards().size(); i++) {
			if (x >= getWidth() * 175 / 192 && x <= getWidth() * 175 / 192 + getWidth() * 9 / 160
					&& y >= getHeight() * 12 / 54 + getHeight() * 4 / 27 * (i + 1)
					&& y <= getHeight() * 12 / 54 + getHeight() * 4 / 27 * (i + 1) + getHeight() * 19 / 135)
				c = gamestate.players[gamestate.playerTurn].heldCards().get(i);
		}

		return c;
	}
	public Patron choosepatron(int x, int y) {
		for(int i = 0; i < gamestate.aquiredpatrons.size(); i++) {
			if(x > getWidth()*5/48 + getWidth()*11/192*i && x < getWidth()*5/48 + getWidth()*11/192*i + getWidth()*10/192 && y > getHeight()*37/108 && y < getHeight()*37/108 + getHeight()*10/108) {
				return gamestate.aquiredpatrons.get(i);
			}
		}
		return null;
		
	}

	public String clickedToken(int x, int y) {
		for (int i = 0; i < 5; i++) {
			double lx = getWidth() * 15 / 32;
			double ly = getHeight() * 5 / 54 + getHeight() * 5 / 36 * (i + 1);
			double radius = getWidth() * 11 / 384;
			double rx = lx + radius;
			double ry = ly + getHeight() * 11 / 216;
			double distance = Math.sqrt(Math.pow(x - rx, 2) + Math.pow(y - ry, 2));
			if (distance <= radius && currentplayer.getTokens(tokens.get(i).suite()) > 0) {
				return tokens.get(i).suite();
			}
		}

		return null;
	}
	
	public String clickedWild(int x, int y)
	{
		int lx = getWidth()*11/192 + getWidth()*15/32;
		int len = getWidth()*11/192;
		int rx = lx + len;
		int wid = getHeight() * 11 / 108/3;
		if(x >= lx && x <= rx)
		{
			for(int i = 0; i < 5; i++)
			{	
				int lt = getHeight() * 5 / 54 + getHeight() * 5 / 36 * (i + 1);
				int lb = lt + wid;
				if(x >= lx && x <= rx && y >= lt && y <= lb && gamestate.requiredTokens.get(tokens.get(i).suite()) != null && gamestate.requiredTokens.get(tokens.get(i).suite()) >0)
				{
					currentplayer.removeToken("YE");
					return tokens.get(i).suite();
				}
			}
		}
		return null;
	}
}
