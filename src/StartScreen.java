

import java.awt.*;
import java.awt.Graphics;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
public class StartScreen extends JPanel  {
private BufferedImage bg;
private BufferedImage startButton;
private BufferedImage rulesButton;
    public StartScreen() {
          try {
              
  				bg = ImageIO.read(new File("StartScreen.png"));
  				startButton = ImageIO.read(new File("startbtn.png"));
  				rulesButton = ImageIO.read(new File("rulesbtn.png"));
          }
          catch(Exception E){
                System.out.println("Exception error");
                return;
          }
         
       
          repaint();
    }
   
    public void paint(Graphics g) {
   	    super.paint(g);
   	    g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
   	    g.drawImage(startButton, getWidth()*47/120, getHeight()/2, getWidth()*13/60, getHeight()*107/540, null);
   	    g.drawImage(rulesButton, getWidth()*55/128, getHeight()*3/4, getWidth()*9/64, getHeight()*7/54, null);
   	}
  
  
  
}
