

import java.util.*;
import java.awt.image.BufferedImage;

public class Patron {
    private HashMap<String, Integer> price;
    private BufferedImage image;


    public Patron (String c, BufferedImage img) {
    	price = new HashMap<>();
    	String[] arr = c.split(",");
        putPrice(arr);
        image = img;
    }


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

    public BufferedImage getImage() {
        return image;
    }
    public HashMap<String, Integer> getPrice(){
    	return price;
    }
    public String toString() {
    	return price.toString();
    }
}