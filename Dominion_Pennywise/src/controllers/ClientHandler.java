package controllers;

import java.util.ArrayList;

public class ClientHandler {
	
	ArrayList<String> deck = new ArrayList<String>();

	public void getDeckFromServer(ArrayList<String> deck) {
		this.deck = deck;
	}

}
