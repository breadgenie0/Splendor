import java.awt.image.BufferedImage;

public class Token {
	private String suite;
	private BufferedImage image;
	
	//suites = WI, BL, GR, RE, BK in that order
	public Token(String s) {
		suite = s;
	}
	public String getSuite() {
		return suite;
	}

}
