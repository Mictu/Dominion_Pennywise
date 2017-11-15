package server_Models;

public class VictoryCard extends Cards{
	
	public enum VictoryCardType {
		Estate,
		Duchy,
		Province;
	}
	
	public VictoryCardType victoryCardType;
	public int valuePoints; // estate 1, duchy 3, province 6
	//costs: estate 2, duchy 5, province 8

	public VictoryCard(CardType cardType, int cost, VictoryCardType victoryCardType,int valuePoints) {
		super(cardType, cost);
		this.victoryCardType = victoryCardType;
		this.valuePoints = valuePoints;
	}
	
	public VictoryCardType getVType() {
		return victoryCardType;
	}
	
	public int getValuePoints() {
		return valuePoints;
	}
		
}
