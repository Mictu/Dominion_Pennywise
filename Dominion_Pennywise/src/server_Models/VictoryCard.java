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

	public VictoryCard() {
//		super();
//		this.victoryCardType = victoryCardType;
//		this.valuePoints = valuePoints;
	}
	
	public String setUpGame(){
		return "1 Estate";
	}
	
	public String getCard(String vCardType) {
		
		if(vCardType.equals(VictoryCardType.Estate)) {
			return "add the right card";
		}else if(vCardType.equals(VictoryCardType.Duchy)) {
			return "add the right card";
		}else if(vCardType.equals(VictoryCardType.Province)) {
			return "add the right card"; 
		}
		
		return "";
	}
	
	public int getValuePoints() {
		return valuePoints;
	}
		
}
