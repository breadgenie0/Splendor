package testingagain;

import java.util.*;

public class GameState {
    private int playerTurn;
    private HashMap<String, Integer> totalTokens;
    private Player[] players;
    private Map<Integer, Card[]> cards;
    private ArrayList<Patron> patrons;
    private Map<Integer, ArrayList<Card>> deck;
    private Card CurrentCard;
    private int tokenCounter;

    public GameState(){
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
    
    public void createDeck(){
    	
    	
    	cards = new TreeMap<Integer, Card[]>();
    	for(int i = 1; i <= 3; i++) {
    		cards.put(i, new Card[4]);
    	}

    }

    public void shuffle(){

    }
    public void drawFour(){
        for(int i = 0; i < deck.size(); i++){
            ArrayList<Card> tempCardList = deck.get(i+1);
            for(int j = 0; j < 4; j++){
                Card c = tempCardList.get(tempCardList.size() - j - 1);
                Card[] temp = cards.get(i+1);
                temp[j] = c;
                
            }
        }
    }
    public void selectPatrons(){
        ArrayList<Patron> allPatrons = new ArrayList<>();
        //allPatrons.add(new Patron())

    }
    public int getTokens(String color){
        return totalTokens.get(color);
    }

    public boolean buyCard(Card c){
        

    }

    public boolean takeToken(String color){
        if(tokenCounter <= 3){
            players[playerTurn].addToken(new Token(color));
        }
    }

}
