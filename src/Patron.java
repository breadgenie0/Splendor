import java.util.*;
import java.awt.image.BufferedImage;

public class Patron {
    private ArrayList<Integer> cards;
    private BufferedImage image;


    public Patron (String c, BufferedImage i) {
        cards = new ArrayList<>();
        int strLength = c.length();
        for(int i = 0; i < strLength; i++) {
            cards.add(Integer.parseInt(c.substring(0,1)));
            c = c.substring(1);
        }
        image = i;
    }

    public ArrayList<> getPrice() {
        return cards;
    }
    public BufferedImage getImage() {
        return image;
    }
}