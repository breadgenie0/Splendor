package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame {
	 public static void main(String[] args) {
	     JPanel mainPanel = new JPanel(new GridBagLayout());
	     PlayerPanel NE = new PlayerPanel();
	     mainPanel.add(NE.getPanel());
	     JFrame frame = new JFrame("Test");
	     frame.setSize(1200, 800);
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     frame.getContentPane().add(mainPanel);
	     frame.setLocationRelativeTo(null);
	     //frame.pack();
	     frame.setVisible(true);
	  }
	 
	 
	 
	 public void corner(int x, JPanel p) {
			if(x == 1) {
				p.setVerticalAlignment(p.TOP_ALIGNMENT);
			}else if(x ==2) {
				
			}else if(x == 3) {
				
			}else if(x == 4) {
				 
			}
		}
}
