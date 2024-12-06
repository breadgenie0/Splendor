import java.util.*;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
public class GameState {
	public HashMap<String, Integer> totalTokens, requiredTokens;
	public Player[] players;
	public Map<Integer, Card[]> cards;
	public ArrayList<Patron> patrons, aquiredpatrons;
	public ArrayList<Card> deck1, deck2, deck3;
	public ArrayList<String> takenTokens;
	public Card cardtobuy;
	public int tokenCounter, gameover, playerTurn, extraTokens;
	public boolean availableTokenColors, gameoverboolean, paid, multiplepatrons;
	public GameState() throws IOException {
		players = new Player[4];
		for (int i = 0; i < 4; i++) {
			players[i] = new Player();
		}
		//players[2].addPoints(14);
		
		playerTurn = 0;
		totalTokens = new HashMap<>();
		requiredTokens = new HashMap<>();
		tokenCounter = 0;
		takenTokens = new ArrayList<String>();
		gameover = -1;
		extraTokens = 0;
		availableTokenColors = false;
		gameoverboolean = false;
		paid = false;
		multiplepatrons = false;
		aquiredpatrons = new ArrayList<>();
		initizalizeTokens();
		createDeck();
		drawFour();
		cardtobuy = null;
		patrons = new ArrayList<Patron>();
		selectPatrons();
		/*
		 * for(Player p : players) { for(int i = 0; i < 5; i++) { Card c = drawCard(1);
		 * p.addCard(c); p.addPoints(c.points());}; }
		 */
		//for(Player p : players) {
		/*
		 * for(int i = 0; i < 20; i++) { Card c = drawCard(1); players[1].addCard(c);}
		 * players[1].addPoints(10); //}
		 */
	}
	public void initizalizeTokens() {
		totalTokens.put("WI", 7);
		totalTokens.put("BL", 7);
		totalTokens.put("GR", 7);
		totalTokens.put("RE", 7);
		totalTokens.put("BK", 7);
		totalTokens.put("YE", 5);
	}
	public void createDeck() throws IOException {
		// initializes each deck
		deck1 = new ArrayList<>();
		deck2 = new ArrayList<>();
		deck3 = new ArrayList<>();
		BufferedReader sc = new BufferedReader(new FileReader("cards.txt"));
		String k;
		while ((k = sc.readLine()) != null) {
			String nextLine = k;
			System.out.println(nextLine);
			String filename = "/cardImages/" + nextLine + ".png";
			System.out.println(filename);
			String[] cardInfo = nextLine.split("_");
			String[] cardPrice = cardInfo[3].split(",");
			try {
				Card c = new Card(cardInfo[0], cardInfo[1], cardInfo[2], cardPrice,
						ImageIO.read(GameState.class.getResource(filename)));
				if (c.level() == 1) {
					deck1.add(c);
				}
				if (c.level() == 2) {
					deck2.add(c);
				}
				if (c.level() == 3) {
					deck3.add(c);
				}
			} catch (Exception e) {
				System.out.println("making card error: " + filename);
			}
		}
		Collections.shuffle(deck1);
		Collections.shuffle(deck2);
		Collections.shuffle(deck3);
		// initializes the purchasable cards map
		cards = new TreeMap<Integer, Card[]>();
		for (int i = 1; i <= 3; i++) {
			cards.put(i, new Card[4]);
		}
	}
	// draws 4 cards at the beginning of the game
	public void drawFour() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				Card[] tempCardArray = cards.get(i + 1);
				if (i == 0) {
					tempCardArray[j] = deck1.remove(0);
				}
				if (i == 1) {
					tempCardArray[j] = deck2.remove(0);
				}
				if (i == 2) {
					tempCardArray[j] = deck3.remove(0);
				}
			}
		}
	}
	// randomly select 5 patrons at the beginning of the game
	public void selectPatrons() throws IOException {
		ArrayList<Patron> allPatrons = new ArrayList<>();
		Scanner sc = new Scanner(new File("patrons.txt"));
		while (sc.hasNextLine()) {
			String x = sc.nextLine();
			String filename = "/patronImages/" + x + ".png";
			try {
				allPatrons.add(new Patron(x, ImageIO.read(GameState.class.getResource(filename))));
			} catch (IOException e) {
				System.out.println("Patron error " + x);
			}
		}
		Collections.shuffle(allPatrons);
		for (int i = 0; i < 5; i++) {
			patrons.add(allPatrons.get(i));
		}
		for (Patron p : patrons) {
			System.out.println(p);
		}
	}
	// ends the round
	public void endTurn() {
		if (gameover == playerTurn) {
			endGame();
		} else {
			gameOver();
			playerTurn++;
			playerTurn %= 4;
			tokenCounter = 0;
			takenTokens = new ArrayList<>();
			cardtobuy = null;
			availableTokenColors = false;
			requiredTokens = new HashMap<>();
			paid = false;
			multiplepatrons = false;
			aquiredpatrons = new ArrayList<>();

		}
	}
	// returns how many tokens of that color are available
	public int getTokens(String color) {
		return totalTokens.get(color);
	}
	// method to take tokens
	public boolean takeToken(String color) {
		System.out.println(color);
		System.out.println("player " + (playerTurn + 1));
		if (tokenCounter > 3) {
			return false;
			// player tries to choose 2 of the same color
		}
		if (totalTokens.get(color) <= 0)
			return false;
		if (tokenCounter == 0) {
			countAvailableTokenColors();
		}
		if (takenTokens.contains(color)) {
			// if there are less than 4 tokens in that stack or the player has took more
			// than 1 token already, return false
			if (totalTokens.get(color) < 3) {
				return false;
			} else if (tokenCounter >= 2) {
				return false;
			} else {
				totalTokens.put(color, totalTokens.get(color) - 1);
				takenTokens.add(color);
				players[playerTurn].addToken(color);
				tokenCounter++;
				System.out.println(tokenCounter);
				tokenLimit();
				if (extraTokens != 0) {
					return false;
				}
				endTurn();
				return true;
			}
		}
		takenTokens.add(color);
		totalTokens.put(color, totalTokens.get(color) - 1);
		players[playerTurn].addToken(color);
		tokenCounter++;
		if (tokenCounter == 3 || (tokenCounter == 2 && availableTokenColors)) {
			tokenLimit();
			if (extraTokens != 0) {
				return false;
			}
			endTurn();
			return false;
		}
		System.out.println(tokenCounter);
		return true;
	}
	public void countAvailableTokenColors() {
		int count = 0;
		for (String s : totalTokens.keySet()) {
			if (totalTokens.get(s) != 0 && !s.equals("YE"))
				count++;
		}
		if (count == 2) {
			availableTokenColors = true;
		}
	}
	// player tries to hold a card
	public boolean holdCard(Card c) {
		if (players[playerTurn].heldCards().size() >= 3) {
			return false;
		}
		if (tokenCounter != 0) {
			return false;
		}
		if (totalTokens.get("YE") > 0) {
			totalTokens.put("YE", totalTokens.get("YE") - 1);
			players[playerTurn].addToken("YE");
		}
		replaceCard(c);
		players[playerTurn].holdCard(c);
		endTurn();
		return true;
	}
	// replaces a card that is bought or held
	public void replaceCard(Card c) {
		int level = c.level();
		Card[] cardList = cards.get(level);
		for (int i = 0; i < cardList.length; i++) {
			if (cardList[i].equals(c)) {
				Card newcard = drawCard(level);
				if(newcard != null) {
				cardList[i] = newcard;
				}else {
					cardList[i] = null;
				}
				
			}
		}
	}
	// returns extra tokens
	public boolean returnExtra(String s) {
		if (players[playerTurn].getTokens(s) <= 0) {
			return false;
		}
		players[playerTurn].removeToken(s);
		extraTokens--;
		returnToken(s);
		if (extraTokens == 0) {
			endTurn();
			return true;
		}
		return false;
	}
	// draws a card from the deck
	public Card drawCard(int l) {
		if (l == 1) {
			if(deck1.size()>0)
			return deck1.remove(0);
		}
		if (l == 2) {
			if(deck2.size()>0)
			return deck2.remove(0);
		}
		if (l == 3) {
			if(deck3.size()>0)
			return deck3.remove(0);
		}
		return null;
	}
	public boolean buyCard(Card c) {
		cardtobuy = c;
		requiredTokens = (HashMap) c.getPrice().clone();
		System.out.println("required tokens");
		requiredTokens.forEach((key, value) -> System.out.println(key + " " + value));
		HashMap<String, ArrayList<Card>> discount = players[playerTurn].getCards();
		for (String suite : discount.keySet()) {
			ArrayList<Card> cardlist = discount.get(suite);
			if (cardlist.size() != 0) {
				if (requiredTokens.containsKey(suite)) {
					System.out.println("put");
					requiredTokens.put(suite, requiredTokens.get(suite) - cardlist.size());
					if(requiredTokens.get(suite) <0)
						requiredTokens.put(suite, 0);
				}
			}
		}
		for(String suite : requiredTokens.keySet()) {
			if(requiredTokens.get(suite) > 0) {
				return false;
			}
		}
		players[playerTurn].addCard(cardtobuy);
		replaceCard(cardtobuy);
		if (players[playerTurn].getHeldCards().contains(cardtobuy)) {
			players[playerTurn].removeHeldCard(cardtobuy);
		}
		players[playerTurn].addPoints(cardtobuy.points());
		patroncheck();
		if(!aquiredpatrons.isEmpty()) {
			multiplepatrons = true;
			return false;
		}
		return true;
	}
	// method player uses to pay
	public boolean pay(String s) {
		// need something to check if the token is wild token and what to do
		if (requiredTokens.containsKey(s) && requiredTokens.get(s) > 0) {
			requiredTokens.put(s, requiredTokens.get(s) - 1);
			players[playerTurn].removeToken(s);
			returnToken(s);
			paid = true;
		}
		for (String suite : requiredTokens.keySet()) {
			if (requiredTokens.get(suite) != 0)
				return false;
		}
		players[playerTurn].addCard(cardtobuy);
		replaceCard(cardtobuy);
		if (players[playerTurn].getHeldCards().contains(cardtobuy)) {
			players[playerTurn].removeHeldCard(cardtobuy);
		}
		players[playerTurn].addPoints(cardtobuy.points());
		patroncheck();
		if(!aquiredpatrons.isEmpty()) {
			multiplepatrons = true;
			return false;
		}
		return true;
	}
	public boolean payWild(String s) {
		// need something to check if the token is wild token and what to do
		if (requiredTokens.containsKey(s) && requiredTokens.get(s) > 0) {
			requiredTokens.put(s, requiredTokens.get(s) - 1);
			returnToken("YE");
		}
		for (String suite : requiredTokens.keySet()) {
			if (requiredTokens.get(suite) != 0)
				return false;
		}
		players[playerTurn].addCard(cardtobuy);
		replaceCard(cardtobuy);
		if (players[playerTurn].getHeldCards().contains(cardtobuy)) {
			players[playerTurn].removeHeldCard(cardtobuy);
		}
		players[playerTurn].addPoints(cardtobuy.points());
		patroncheck();
		if(!aquiredpatrons.isEmpty()) {
			multiplepatrons = true;
			return false;
		}
		return true;
	}
	// puts tokens back to the center
	public void returnToken(String s) {
		totalTokens.put(s, totalTokens.get(s) + 1);
	}
	// checks if the game is over
	public void gameOver() {
		if (gameover == -1) {
			if (players[playerTurn].getScore() >= 15) {
				gameover = playerTurn - 1;
				if (gameover == -1)
					gameover = 3;
			}
		}
	}
	// ends the game
	public void endGame() {
		gameoverboolean = true;
		System.out.println("end");
	}
	public void tokenLimit() {
		extraTokens = players[playerTurn].numTokens() - 10;
		if (extraTokens <= 0) {
			extraTokens = 0;
		}
	}
	public static void main(String[] args) throws IOException {
		GameState game = new GameState();
		game.takeToken("RE");
		System.out.println("total tokens: ");
		game.totalTokens.forEach((key, value) -> System.out.println(key + " " + value));
		for (Player p : game.players) {
			p.playerInfo();
		}

	}
	
	public int winner() {
		int biggest = 0;
		int j = -1;
		for(int i = 0; i < players.length; i++) {
			if(players[i].getScore() > biggest) {
				biggest = players[i].getScore();
				j = i;
			}
		}
		return j;
	}
	
	public boolean checkPatrons(Patron p) {
			HashMap<String, Integer> requiredCards  = (HashMap) p.getPrice().clone();
			HashMap<String, ArrayList<Card>> playercards = players[playerTurn].getCards();
			for(String suite : requiredCards.keySet()) {
				if(requiredCards.get(suite) > playercards.get(suite).size()) {
					return false;
				}
			}
			return true;
	}
	
	public ArrayList<Patron> availablePatrons() {
		ArrayList<Patron> aquiredpatrons = new ArrayList<>();
		for(Patron p : patrons) {
			if(checkPatrons(p)) {
				aquiredpatrons.add(p);
			}
		}
		return aquiredpatrons;
	}
	public void patroncheck() {
		aquiredpatrons = availablePatrons();
		if(aquiredpatrons.size() > 0) {
			if(aquiredpatrons.size() > 1) {
				return;
			}
			players[playerTurn].addPatron(aquiredpatrons.get(0));
			patrons.remove(aquiredpatrons.get(0));
			aquiredpatrons.remove(0);
		}
		return;
	}
	
	public void choosepatron(Patron p) {
		players[playerTurn].addPatron(p);
		patrons.remove(p);
		multiplepatrons = false;
	
		
		
	}
}


