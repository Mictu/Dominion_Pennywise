package server_Models;

import java.util.ArrayList;

import javafx.scene.control.Button;

public class Player {
	
	// initialize sector here
	protected int point, money;
	protected int actionPoint, buyPoint;
	protected boolean turn;
	
	protected String name; 
	
	ActionPhase actionPhase;

	protected ArrayList<Cards> hand = new ArrayList<Cards>();
	protected ArrayList<Cards> deck = new ArrayList<Cards>();
	protected ArrayList<Cards> discard = new ArrayList<Cards>();

	protected Deck newDeck;
	
	// Constructor
	public Player() {
		turn = false;
		point = 0;
		money = 0;
		
		actionPoint = 0; // maybe 1
		buyPoint = 0; // maybe 1
		
		deck = newDeck.startDeck();
		
	} // Close Constructor

	protected void increaseActionPoints(int actionPoint) {
		System.out.println(actionPoint + "Aktionspunkte");
	}
	
	protected void increaseCard(int card) {
		System.out.println(card + "Karte");
	}

	protected void incrementTurn() {
		totalTurn++;
	}


	
	
	public String getName(){
		return this.name; // gibt name zurück nur zum testen
	}
	public void setName(String name){
		this.name = name; // gibt den name ein 
	}

}

// Patrick
