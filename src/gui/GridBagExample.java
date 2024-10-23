package gui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.*;
import javax.swing.*;
public class GridBagExample {
  public static void main(String[] args) {
     JPanel mainPanel = new JPanel(new GridBagLayout());
     GridBagConstraints gbc = new GridBagConstraints(0, 0, 5, 5, 10.0, 1.0,
           GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(
                 0, 0, 0, 0), 0, 0);
     PlayerPanel NE = new PlayerPanel();
     mainPanel.add(NE.getPanel());
     gbc.gridx = 1;
     gbc.gridy = 0;
     gbc.anchor = GridBagConstraints.NORTHEAST;
     PlayerPanel NW = new PlayerPanel();
     mainPanel.add(NW, gbc);
     gbc.gridx = 0;
     gbc.gridy = 1;
     gbc.anchor = GridBagConstraints.SOUTHWEST;
     PlayerPanel SE = new PlayerPanel();
     mainPanel.add(SE, gbc);
     gbc.gridx = 1;
     gbc.gridy = 1;
     gbc.anchor = GridBagConstraints.SOUTHEAST;
     PlayerPanel SW = new PlayerPanel();
     mainPanel.add(SW, gbc);     
     JFrame frame = new JFrame("Test");
     frame.setSize(800, 600);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.getContentPane().add(mainPanel);
     frame.setLocationRelativeTo(null);
     //frame.pack();
     frame.setVisible(true);
  }
}
