package server_Models;

import server_Models.VictoryCard.VictoryCardType;

public class TreasureCard extends Cards{
	
	public enum TreasureCardType {
		Copper,
		Silver,
		Gold;
	}
	private TreasureCardType treasureCardType;
	private int valueMoney; //copper 1,silver 2,gold 3 
	// costs: copper 0, silver 2, gold 6
	
	public TreasureCard() {
//		super();
//		this.treasureCardType = treasureCardType;
//		this.valueMoney = valueMoney;
	}
	
	public String getCard(String tCardType) {
		if(tCardType.equals(TreasureCardType.Copper)) {
			return "add the right card";
		}else if(tCardType.equals(TreasureCardType.Silver)) {
			return "add the right card";
		}else if(tCardType.equals(TreasureCardType.Gold)) {
			return "add the right card"; 
		}
		return "";
	}
	
	public int getValuePoints() {
		return valueMoney;
	}
	
	public String setUpGame() {
		return "1 Copper";
	}
	
	
	
}
