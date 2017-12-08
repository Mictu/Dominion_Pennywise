package server_Models;

import java.net.Socket;
import java.util.ArrayList;

import server.client.Server;

public class Player {

	// initialize sector here
	public int winPoint, money, amountOfTurns;
	public int actionPoint, buyPoint, bonusBuyPoint;
	public boolean turn = false;

	
	public String name;
	Server server;
	Socket socket;
	
	public ArrayList<String> hand = new ArrayList<String>();
	protected ArrayList<String> deck = new ArrayList<String>();
	protected ArrayList<String> discard = new ArrayList<String>();
	public static ArrayList<Player> player = new ArrayList<Player>();
	

	// Constructor
	public Player(String name) {
		this.name = name;
		winPoint = 0;
		amountOfTurns = 0;

		money = 0;
		actionPoint = 0;
		buyPoint = 0;

	} // Close Constructor

	// set up for a new round
	protected void startRound() {
		amountOfTurns++;
		actionPoint = 1;
		buyPoint = 1;
		bonusBuyPoint = 0;														// button (increase over kingdom-card)
		money = getCashHand(); // Get money you have in your hands
	}

	// Get how many money you have in your hands to buy something
	protected int getCashHand() {
		int cash = 0;
		for (String x : this.hand) {
			if (x.equalsIgnoreCase("gold")) {
				money += 3;
			}
			if (x.equalsIgnoreCase("silver")) {
				money += 2;
			}
			if (x.equalsIgnoreCase("copper")) {
				money += 1;
			}
		}
		return cash;
	}

	// Increase the stats of the player in the following lines

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
		this.bonusBuyPoint += money;
	}
	
	// decrease money from hands (Buy-phase)
	protected void decreaseMoney(int money) {
		this.money -= money;
	}

	// Set player's name
	public void setName(String name) {
		this.name = name;
	}

	// Get stats of the Player in the following lines

	// get the name (easter eggs, player with same name)
	public String getName() {
		return this.name;
	}

	// Get the money which the player has in his hands
	protected int getMoney() {
		return this.money;
	}

	// return action-points
	protected int getActionPoints() {
		return this.actionPoint;
	}
	
	// get money which u store on the bonus button
	protected int getBonusBuyMoney() {
		return this.bonusBuyPoint;
	}

	// return win - points
	protected int getWinPoints() {
		return this.winPoint;
	}

	// return buy - points
	protected int getBuyPoints() {
		return this.buyPoint;
	}
	
	public ArrayList<String> getHand() {
		return this.hand;
	}

}

// Patrick
