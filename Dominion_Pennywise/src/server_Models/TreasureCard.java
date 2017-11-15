package server_Models;

public class TreasureCard extends Cards{
	
	public enum TreasureCardType {
		Copper,
		Silver,
		Gold;
	}
	private TreasureCardType treasureCardType;
	private int valueMoney; //copper 1,silver 2,gold 3 
	// costs: copper 0, silver 2, gold 6
	
	public TreasureCard(CardType cardType,int cost,TreasureCardType treasureCardType, int valueMoney) {
		super(cardType,cost);
		this.treasureCardType = treasureCardType;
		this.valueMoney = valueMoney;
	}
	
	public TreasureCardType getTType() {
		return treasureCardType;
	}
	
	public int getValuePoints() {
		return valueMoney;
	}
	
	
	
}
