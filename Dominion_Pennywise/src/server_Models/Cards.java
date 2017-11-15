package server_Models;

public abstract class Cards {
	
	
	public enum CardType {
		Action,
		Treasure,
		Kingdom;
	}
	
	private final CardType cardType;
	private int cost;
	
	public Cards(CardType cardType, int cost) {
		this.cardType = cardType;
		this.cost = cost;
	}
	
	public CardType getType() {
		return cardType;
	}

	public int getCost() {
		return cost;
	}
	
}
