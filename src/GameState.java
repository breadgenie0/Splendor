import java.util.*;

public class GameState {
    private int playerTurn;
    private HashMap<String, Integer> totalTokens;
    private Player[] players;
    private TreeMap<Integer, Card[]> cards;
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
        initializeTokens();
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

    }

    public void shuffle(){

    }
    public void drawFour(){
        for(int i = 0; i < deck.size(); i++){
            ArrayList<Card> tempCardList = deck.get(i+1);
            for(int j = 0; j < 4; j++){
                Card c = tempCardList.get(tempCardList.size() - j - 1);
                cards.put(i+1, c);
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

}