

import java.util.Map;
import java.util.HashMap;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;

public class Card{
	private int points;
	private HashMap<String, Integer> price;
	private String suite;
	private BufferedImage cardFront;
	private int level;

	public Card(String points, String s, String l, String[] p, BufferedImage cf) throws IOException{
		this.points = Integer.parseInt(points);
		//suites = WI, RE, GR, CL , BK
		suite = s;
		level = Integer.parseInt(l);
		cardFront = cf;
		price = new HashMap<>();
		putPrice(p);
	}
	
	/*
	 * public Card(int level) throws IOException { points = 0; suite = null;
	 * this.level = level; if(level == 1) { cardFront = ImageIO.read(new
	 * File("level1cardback.jpg")); } if(level == 1) { cardFront = ImageIO.read(new
	 * File("level1cardback.jpg")); } if(level == 1) { cardFront = ImageIO.read(new
	 * File("level1cardback.jpg")); } }
	 */
	
	public void putPrice(String[] p) {
		if(Integer.parseInt(p[0]) != 0)
			price.put("WI", Integer.parseInt(p[0]));
		if(Integer.parseInt(p[1]) != 0)
			price.put("BL", Integer.parseInt(p[1]));
		if(Integer.parseInt(p[2]) != 0)
			price.put("GR", Integer.parseInt(p[2]));
		if(Integer.parseInt(p[3]) != 0)
			price.put("RE", Integer.parseInt(p[3]));
		if(Integer.parseInt(p[4]) != 0)
			price.put("BK", Integer.parseInt(p[4]));
	}



	public HashMap<String, Integer> getPrice() {
		return price;
	}

	public int getPrice(String color) {
		return price.get(color);
	}

	public int points() {
		return points;
	}

	public String suite() {
		return suite;
	}

	public int level() {
		return level;
	}

	public BufferedImage getFront() {
		return cardFront;
	}

	
}