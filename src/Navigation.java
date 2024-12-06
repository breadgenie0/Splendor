import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
public class Navigation extends JPanel implements MouseListener {
	
	private BufferedImage pg1, pg2, pg3, nextbtn, startbtn;
	private int page;
	
	public Navigation() {
		try{
			pg1 = ImageIO.read(new File("navigation1.png"));
			pg2 = ImageIO.read(new File("navigation2.png"));
			pg3 = ImageIO.read(new File("navigation3.png"));
			nextbtn = ImageIO.read(new File("nextbtn.png"));
			startbtn = ImageIO.read(new File("startgamebtn.png"));
			page = 1;
		}catch(Exception E) {
			System.out.println("Navigation error");
			
		}
		addMouseListener(this);
	}
	
	
	public void paint(Graphics g) {
		super.paint(g);
		if(page == 1) {
			g.drawImage(pg1, 0,0,getWidth(),getHeight(),null);
			g.drawImage(nextbtn, getWidth()*100/128, getHeight()/36, getWidth()*35/192,getHeight()/15, null );
		}if(page == 2) {
			g.drawImage(pg2, 0,0,getWidth(),getHeight(),null);
			g.drawImage(nextbtn, getWidth()*100/128, getHeight()/36, getWidth()*35/192,getHeight()/15, null);
		}if(page == 3) {
			g.drawImage(pg3, 0,0,getWidth(),getHeight(),null);
			g.drawImage(startbtn, getWidth()*3/4, getHeight()*5/6, getWidth()*35/192,getHeight()/15, null );
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
		if(page < 3 && x > getWidth()*100/128 && x < getWidth()*100/128 + getWidth()*35/192 && y >  getHeight()/36 && y <  getHeight()/36 + getHeight()/15) {
			page++;
		}
		repaint();
		if(page == 3 && x > getWidth()*3/4 && x < getWidth()*3/4 + getWidth()*35/192 && y > getHeight()*5/6 && y < getHeight()*5/6 + getHeight()/15) {
			JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			if (parentFrame instanceof Frame) {
				((Frame) parentFrame).showMainPanel();
			}
		}			

	
		
	
	}
	
}

