package server_Models;

import java.net.Socket;
import java.util.ArrayList;
import server.client.Server;

/**
 * Player class contains and saves all the data from a player handles the
 * players data
 * 
 * @author Michael Tu
 * @author Patrick Ziörjen
 * @author Sojo Nagaroor
 * @author Yujia Shi
 */
public class Player implements Comparable<Player> {

	public int winPoint, money, countMoneyForHand, amountOfTurns;
	public int actionPoint, buyPoint, bonusBuyPoint;
	public boolean turn = false;

	public String name;
	Server server;
	Socket socket;

	public ArrayList<String> hand = new ArrayList<String>();
	protected ArrayList<String> deck = new ArrayList<String>();
	protected ArrayList<String> discard = new ArrayList<String>();
	public static ArrayList<Player> player = new ArrayList<Player>();

	/**
	 * initialize all variables in Player.java
	 * 
	 * @param name
	 *            - name from Login_View that the player typed in
	 */
	public Player(String name) {
		this.name = name;
		winPoint = 0;
		amountOfTurns = 0;
		money = 0;
		actionPoint = 0;
		buyPoint = 0;
	}

	/**
	 * set the start capital
	 * 
	 * @author Patrick Ziörjen
	 */
	protected void startRound() {
		amountOfTurns++;
		actionPoint = 1;
		buyPoint = 1;
		bonusBuyPoint = 0;
	}

	/**
	 * check the cards on the hand and calculate the cash in the hand
	 * 
	 * @author Patrick Ziörjen
	 * @return countMoneyForHand - total cash
	 */
	protected int getCashHand() {
		countMoneyForHand = 0;
		for (String x : this.hand) {
			if (x.equalsIgnoreCase("gold")) {
				countMoneyForHand += 3;
			}
			if (x.equalsIgnoreCase("silver")) {
				countMoneyForHand += 2;
			}
			if (x.equalsIgnoreCase("copper")) {
				countMoneyForHand += 1;
			}
		}
		return countMoneyForHand;
	}

	// increase the amount of actions for the player
	protected void increaseActionPoints(int points) {
		this.actionPoint += points;
	}

	// decrease the amount of actions for the player
	protected void decreaseActionPoints() {
		this.actionPoint--;
	}

	// increase the buy points of the player
	protected void increaseBuyPoints(int points) {
		this.buyPoint += points;
	}

	// decrease the amount of buy options
	protected void decreaseBuyPoints() {
		this.buyPoint--;
	}

	// increase the winning points of the player
	protected void increasePoints(int points) {
		this.winPoint += points;
	}

	// increase money in the same round
	protected void increaseMoney(int money) {
		this.money += money;
		// this.bonusBuyPoint += money;
	}

	// decrease money from hands (Buy-phase)
	protected void decreaseMoney(int money) {
		this.money -= money;
	}

	// Set player's name
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	protected int getMoney() {
		return this.money;
	}

	protected int getActionPoints() {
		return this.actionPoint;
	}

	protected int getBonusBuyMoney() {
		return this.bonusBuyPoint;
	}

	protected int getWinPoints() {
		return this.winPoint;
	}

	protected int getBuyPoints() {
		return this.buyPoint;
	}

	public ArrayList<String> getHand() {
		return this.hand;
	}

	/**
	 * compareTo method for collections sort. used in GameLogic.java
	 * 
	 * @author Sojo Nagaroor
	 * @param OPlayer
	 */
	@Override
	public int compareTo(Player OPlayer) {
		return OPlayer.winPoint - this.winPoint;
	}
}
