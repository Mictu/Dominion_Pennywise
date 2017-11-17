package server_Models;

import java.util.ArrayList;

import javafx.scene.control.Button;

public class Player {
	private int point, money, actionPoint, buyPoints;
	private int totalTurn = 0;
	private boolean turn;
	private String name; 
	
	ActionPhase actionPhase;

	protected ArrayList<Button> hand = new ArrayList<Button>();
	protected ArrayList<Button> deck = new ArrayList<Button>();
	protected ArrayList<Button> discard = new ArrayList<Button>();

	protected Deck newDeck;
	
	public Player() {
		deck = newDeck.startDeck();
	}

	protected void handleActionPoints() {
		String testString = "Action.Dorf.PlusZweiKarte";

		String[] extractCardType = testString.split("\\.");
		String cardName = extractCardType[1].toString();
		String cardDescription = extractCardType[2].toString();
		
		actionPhase = new ActionPhase(cardName, cardDescription);
		
		if (extractCardType[0].equals("Action")) {
			actionPhase.startPhase(cardName, cardDescription);
		}

	}

	protected void increaseActionPoints(int actionPoint) {
		System.out.println(actionPoint + "Aktionspunkte");
	}
	
	protected void increaseCard(int card) {
		System.out.println(card + "Karte");
	}

	protected void incrementTurn() {
		totalTurn++;
	}

	public static void main(String args[]) {
		Player player = new Player();
		player.handleActionPoints();
	}
	
	public String getName(){
		return this.name; // gibt name zurück nur zum testen
	}
	public void setName(String name){
		this.name = name; // gibt den name ein 
	}

}
