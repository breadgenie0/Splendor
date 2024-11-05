public Player
{
    private String playerName;
    private HashMap<Token, Integer> tokens;
    private ArrayList<Card> cards;
    private ArrayList<Card> heldCards;
    private ArrayList<Patron> patrons;
    private int points;

    public Player(String name)
    {
        playerName = name;
        tokens = new HashMap<Token, Integer>();
        cards = new ArrayList<Card>();
        patrons = new ArrayList<Patron>();
        points = 0;
    }

    public ArrayList<Card> getCards()
    {
        return cards;
    }

    public ArrayList<Patron> getPatrons()
    {
        return patrons;
    }

    public void addToken(Token T)
    {
        tokens.put(T, tokens.get(T)+1);
    }

    public void addCard(Card c)
    {
        cards.add(c);
    }
    
    public void holdCard(Card c)
    {
        heldCards.add(c);
    }

    public void addPatron(Patron p)
    {
        patrons.add(p);
    }

    public int getScore()
    {
        return points;
    }
}