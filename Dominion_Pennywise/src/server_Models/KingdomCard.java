package server_Models;

import server_Models.VictoryCard.VictoryCardType;

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
	

	public KingdomCard() {
//		super();
//		this.kingdomCardType = kingdomCardType;
//		this.description = description;
	}
	
	public String getCard(String kCardType) {
		if(kCardType.equals(KingdomCardType.Village)) {
			return "add the right Card";
		}else if(kCardType.equals(KingdomCardType.Woodcutter)) {
			return "add the right Card";
		}else if(kCardType.equals(KingdomCardType.Festival)) {
			return "add the right Card";
		}else if(kCardType.equals(KingdomCardType.Laboratory)) {
			return "add the right Card";
		}else if(kCardType.equals(KingdomCardType.Market)) {
			return "add the right Card";
		}else if(kCardType.equals(KingdomCardType.Smith)) {
			return "add the right Card";
		}

		return "";
	}
	
	public String getDescription() {
		return description;
	}

}
