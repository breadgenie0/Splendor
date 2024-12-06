import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
public class WinnerScreen extends JPanel {
	
	private BufferedImage bg, restart;
	private GameState gamestate;
	
	public WinnerScreen(GameState g) {
		try {
			bg = ImageIO.read(new File("winnerscreen.png"));
			gamestate = g;
		}catch(Exception e) {
			System.out.println("Error winner screen");
		}
	}
	
	public void paint(Graphics g) {
		g.drawImage(bg, 0,0,getWidth(),getHeight(), null);
		Font font = new Font("Book Antiqua", Font.BOLD, getWidth() * 5 / 384);
		Font bigFont = font.deriveFont(new Float(getWidth() * 3 / 128));
		Font biggerFont = font.deriveFont(new Float(getWidth() * 5 / 128));
		g.setColor(Color.white);
		int w = gamestate.winner();
		int count = 1;
		//draws the scores of each player
		for(int i = 0; i < gamestate.players.length; i++) {
			if(i == w) {
				g.setFont(biggerFont);
				g.drawString("Player " + (i+1) + ": " + gamestate.players[i].getScore() + " points", getWidth()*23/64, getHeight()*43/108);
			}else {
				g.setFont(bigFont);
				g.drawString("Player " + (i+1) + ": " + gamestate.players[i].getScore() + " points", getWidth()*27/64, getHeight()*4/9 + getHeight()/18*count);
				count++;
				
			}
		}
		//g.drawImage(restart, 565, getHeight()*7/8, getWidth()*79/192, getHeight()*19/108, null);
	}

}
