package server_Models;

import java.util.ArrayList;

import javafx.scene.control.Button;

public class Player {
	private int point, money ,gandalf;
	private int totalTurn = 0;
	private int actionPoint;
	private boolean turn;
	private int buyPoints;
	
	private int gaggi;
	
	protected Button[] hand = new Button[5];
	protected ArrayList<Button> deck = new ArrayList<Button>();
	protected ArrayList<Button> discard = new ArrayList<Button>();
	
	
	protected void player() {
		
	}
	
	protected void incrementTurn() {
		totalTurn++;
	}
	
}
