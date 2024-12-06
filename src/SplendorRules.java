

import java.awt.Graphics;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.text.html.ImageView;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.*;
import javax.swing.*;

public class SplendorRules extends JPanel {
	private BufferedImage bg;
	private JButton exitButton;

	public SplendorRules() {
		try {
			bg = ImageIO.read(new File("fullRules.png"));
			Image scaledImage = bg.getScaledInstance(1600, 6260, Image.SCALE_SMOOTH);
			JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
			JScrollPane scrollPane = new JScrollPane(imageLabel);
			scrollPane.setPreferredSize(new Dimension(1, 2));
			setLayout(new BorderLayout());
			add(scrollPane, BorderLayout.CENTER);
// Display the frame                                                                                                                            
			scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Or VERTICAL_SCROLLBAR_NEVER
																							// to hide
		} catch (Exception E) {
			System.out.println("Exception error");
			return;
		}

	}

	public void paint(Graphics g) {
		super.paint(g);

	}

}
                                                                                                                                          