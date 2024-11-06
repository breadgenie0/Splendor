import java.util.Map;
import java.util.HashMap;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;


public class Card {
private int points;
private HashMap<Token, Integer> price;
private String suite;
private BufferedImage cardFront;
private BufferedImage cardBack;
private int level;

public Card(int points, String s, int l, HashMap<Token, Integer> p, BufferedImage cf,) {
this.points = points;
suite = s;
level = l;
price = p;
cardFront = cf;
cardBack = cb;
}

public HashMap getPrice(){
    return price;
}
public int getPoints() {
return points;
}

public String getSuite() {
    return suite;
}

public int getLevel(){
    return level;
}
public BufferedImage getFront(){
    return cardFront;
}

public BufferedImage getBack() {
    return cardBack;
}
}
