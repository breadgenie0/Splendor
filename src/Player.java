import java.util.*;
public class Player
{
    private String playerName; //not used as of now
    private HashMap<String, Integer> tokens;
    private HashMap<String, ArrayList<Card>> cards;
    private ArrayList<Card> heldCards;
    private ArrayList<Patron> patrons;
    private int points;

    public Player()
    {
        tokens = new HashMap<String, Integer>();
        tokens.put("RE", 0);
    	tokens.put("BL", 0);
    	tokens.put("WI", 0);
    	tokens.put("GR", 0);
    	tokens.put("BK", 0);
    	tokens.put("YE", 0);

        cards = new HashMap<>();
        initializeCards();
        heldCards = new ArrayList<Card>();
        patrons = new ArrayList<Patron>();
        points = 0;
    }

    public void initializeCards() {
    	cards.put("RE", new ArrayList<Card>());
    	cards.put("WI", new ArrayList<Card>());
    	cards.put("BL", new ArrayList<Card>());
    	cards.put("BK", new ArrayList<Card>());
    	cards.put("GR", new ArrayList<Card>());
    	
    }

    public HashMap<String, ArrayList<Card>> getCards()
    {
        return cards;
    }

    public ArrayList<Patron> patrons()
    {
        return patrons;
    }
    
    public ArrayList<Card> heldCards(){
    	return heldCards;
    }

    public void addToken(String s)
    {
        tokens.put(s, tokens.get(s)+1);
    }
    public boolean removeToken(String s) {
    	if(tokens.get(s) <= 0) {
    		return false;
    	}
    	tokens.put(s, tokens.get(s) - 1);
    	return true;
    }

    public void addCard(Card c)
    {
    	ArrayList<Card> cardlist = cards.get(c.suite());
    	cardlist.add(c);
        
    }
    public int numTokens() {
    	int count = 0;
    	for(Integer c : tokens.values()) {
    		count += c;
    	}
    	return count;
    }
    public void holdCard(Card c)
    {
        heldCards.add(c);
    }

    public void addPatron(Patron p)
    {
        patrons.add(p);
        points+= 3;

    }

    public int getScore()
    {
        return points;
    }
    
    public void addPoints(int p) {
    	points += p;
    }
    
    //s is the suite and it returns how many tokens you have of that suite
    public int getTokens(String s) {
    	if(tokens.get(s) == null) {
    		return -1;
    	}
    	return tokens.get(s);
    }
    
    public void playerInfo() {
    	tokens.forEach((key, value) -> System.out.println(key + " " + value));
    	System.out.println("points " + points);
    	
    }
    
    public ArrayList<Card> getHeldCards()
    {
    	return heldCards;
    }
    
    public void removeHeldCard(Card c)
    {
    	heldCards.remove(c);
    }
}