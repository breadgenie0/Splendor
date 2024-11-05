import java.util.Map;
import java.util.HashMap;


public class Card {
private int points;
private Map<Token, Integer> price;
private String suite;
private BufferedImage cardFront;
private BufferedImage cardBack;
private int level;

public Card(String s, int l, Map<Token, Integer> p, BufferedImage cf, BufferedImaged cb) {

}

public 
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