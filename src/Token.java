
import java.awt.image.BufferedImage;

public class Token {
	private String suite;
	private BufferedImage image;
	
	//suites = WI, BL, GR, RE, BK in that order
	public Token(String s, BufferedImage img) {
		suite = s;
		image = img;
	}
	public String suite() {
		return suite;
	}
	public BufferedImage getImage(){
		return image;
	}
	public String toString() {
		return suite;
	}

}