import java.util.*;

public class GameState {
    private int playerTurn;
    private HashMap<Token, Integer> totalTokens;
    private Player[] players;
    private TreeMap<Integer, Card[]> cards;
    private ArrayList<Patron> patrons;
    private Map<Integer, ArrayList<Card>> deck;
    private Card CurrentCard;
    private int tokenCounter;

}