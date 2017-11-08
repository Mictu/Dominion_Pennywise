package Server_Models;

public class Player {
	private int point;
	private int totalTurn = 0;
	private int money;
	private int actionPoint;
	private boolean turn;
	private int buyPoints;
	private CardPile deck;
	
	protected void player() {
		
	}
	
	protected void incrementTurn() {
		totalTurn++;
	}
	
}
