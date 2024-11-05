import java.awt.image.BufferedImage;

public class Token {
	private String suite;
	private BufferedImage image;
	
	public Token(String s, BufferedImage b) {
		suite = s;
		image = b;
	}
	public String getSuite() {
		return suite;
	}

}
