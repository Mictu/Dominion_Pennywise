package server_Models;

public class KingdomCard extends Cards {

	public enum KingdomCardType {
		Village,
		Woodcutter,
		Festival,
		Laboratory,
		Market,
		Smith;
	}
	private KingdomCardType kingdomCardType;
	private String description;
	

	// Konstruktor Kingdom Card
	public KingdomCard(CardType cardType, int cost, KingdomCardType kingdomCardType, String description) {
		super(cardType, cost);
		this.kingdomCardType = kingdomCardType;
		this.description = description;
	}
	
	public KingdomCardType getKType() {
		return kingdomCardType;
	}
	
	public String getDescription() {
		return description;
	}

}
