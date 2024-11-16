package frontend;
import java.util.*;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;

public class GameState {
    private int playerTurn;
    private HashMap<String, Integer> totalTokens;
    private Player[] players;
    public Map<Integer, Card[]> cards;
    public ArrayList<Patron> patrons;
    public ArrayList<Card> deck1, deck2, deck3;
    private Card CurrentCard;
    private int tokenCounter;

    public GameState() throws IOException{
        players = new Player[4];
        for(int i = 0; i < 4; i++){
            players[i] = new Player();
        }
        playerTurn = 0;
        totalTokens = new HashMap<>();
        tokenCounter = 0;
        //initializeTokens();
        createDeck();
        drawFour();
        patrons = new ArrayList<Patron>();
        selectPatrons();

    }

    public void initizalizeTokens(){
        totalTokens.put("WI", 7);
        totalTokens.put("BL", 7);
        totalTokens.put("GR", 7);
        totalTokens.put("RE", 7);
        totalTokens.put("BK", 7);
        totalTokens.put("YE", 5);
    }
    
    public void createDeck() throws IOException{
    	//initializes each deck
    	deck1 = new ArrayList<>();
    	deck2 = new ArrayList<>();
    	deck3 = new ArrayList<>();
    	Scanner sc = new Scanner(new File("cards.txt"));
    	while(sc.hasNextLine()) {
    		String nextLine = sc.nextLine();
    		System.out.println(nextLine);
    		String filename = "/cardImages/" +  nextLine + ".png";
    		System.out.println(filename);
    		String[] cardInfo = nextLine.split("_");
    		String[] cardPrice = cardInfo[3].split(",");
    		try{
    			Card c = new Card(cardInfo[0], cardInfo[1], cardInfo[2], cardPrice, ImageIO.read(GameState.class.getResource(filename)));
    			if(c.level() == 1) {
    				deck1.add(c);
    			}if(c.level() == 2) {
    				deck2.add(c);
    			}if(c.level() == 3) {
    				deck3.add(c);
    			}
    		}catch(Exception e) {
    			System.out.println("making card error: " + filename);
    		}
    	}
    	Collections.shuffle(deck1);
    	Collections.shuffle(deck2);
    	Collections.shuffle(deck3);
    	//initializes the purchasable cards map
    	cards = new TreeMap<Integer, Card[]>();
    	for(int i = 1; i <= 3; i++) {
    		cards.put(i, new Card[4]);
    	}

    }

    //draws 4 cards at the beginning of the game
    public void drawFour(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 4; j++){
            	Card[] tempCardArray = cards.get(i+1);
            	if(i == 0) {
            		tempCardArray[j] = deck1.remove(0);
            	}
            	if(i == 1) {
            		tempCardArray[j] = deck2.remove(0);

            	}
            	if(i == 2) {
            		tempCardArray[j] = deck3.remove(0);
            	}
 
            }
        }
    }
    public void selectPatrons() throws IOException{
        ArrayList<Patron> allPatrons = new ArrayList<>();
        Scanner sc = new Scanner(new File("patrons.txt"));
        while(sc.hasNextLine()) {
        	String x = sc.nextLine();
    		String filename = "/patronImages/" +  x + ".png";
    		try {
				allPatrons.add(new Patron(x, ImageIO.read(GameState.class.getResource(filename))));
			} catch (IOException e) {
				System.out.println("Patron error " + x);
			}
        }
        Collections.shuffle(allPatrons);
        for(int i = 0; i < 5; i++) {
        	patrons.add(allPatrons.get(i));
        }
        for(Patron p : patrons) {
        	System.out.println(p);
        }

    }
    public int getTokens(String color){
        return totalTokens.get(color);
    }

   // public boolean buyCard(Card c){
        

    //}

 /*   public boolean takeToken(String color){
        if(tokenCounter <= 3){
            players[playerTurn].addToken(new Token(color));
        }
    }
   */
   
   public static void main(String[] args) throws IOException {
	   GameState game = new GameState();
   }

}
