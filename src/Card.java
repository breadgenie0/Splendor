import java.util.Map;
import java.util.HashMap;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;


public class Card {
private int points;
private HashMap<String, Integer> price;
private String suite;
private BufferedImage cardFront;
private BufferedImage cardBack;
private int level;

public Card(int points, String s, int l, int[] p, BufferedImage cf,) {
this.points = points;
suite = s;
level = l;
price = p;
cardFront = cf;
price = getPrice(p);

}

public HashMap<String, Integer> getPrice(int[] p){
    HashMap<String, Integer> priceMap = new HashMap<>();
    priceMap.put("WI", p[0]);
    priceMap.put("BL", p[1]);
    priceMap.put("GR", p[2]);
    priceMap.put("RE", p[3]);
    priceMap.put("BK", p[4]);
    return priceMap;
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
