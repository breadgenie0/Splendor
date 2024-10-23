package gui;

import java.awt.*;
import javax.swing.*;
public class PlayerPanel extends JPanel {
	public JPanel playerpanel;
	public JLabel label;
	
	public PlayerPanel() {
		playerpanel = new JPanel();
		playerpanel.setBackground(Color.pink);
		playerpanel.setLayout(new FlowLayout());
		playerpanel.setPreferredSize(new Dimension(250,250));
		label = new JLabel();
		playerpanel.add(label);
	}
	
	public JPanel getPanel() {
		return playerpanel;
	}
	
	public void label(String s) {
		label.setText(s);
	}
	
	public void setBounds(int x, int y, int w, int h) {
		playerpanel.setBounds(x,y,w,h);
	}
	
	

}
