package server_Models;

import java.util.ArrayList;

public class Cards {

	TreasureCard tCard = new TreasureCard();
	KingdomCard kCard = new KingdomCard();
	VictoryCard vCard = new VictoryCard();
	ArrayList<String> startDeck = new ArrayList<String>();

	public Cards() {
		setUpFirstRound();
	}

	public enum CardType {
		Victory, Treasure, Kingdom;
	}

	public void setUpFirstRound() {
		for (int i = 0; i < 7; i++) {
			startDeck.add(tCard.setUpGame());
		}

		for (int i = 0; i < 3; i++) {
			startDeck.add(vCard.setUpGame());
		}

	}

	public String getCard(CardType type) {
		
		String vCardType = null;
		String tCardType = null;
		String kCardType = null;
		
		switch (type) {
		case Victory:
			vCard.getCard(vCardType);
			break;
		case Treasure:
			tCard.getCard(tCardType);
			break;
		case Kingdom:
			kCard.getCard(kCardType);
			break;
		}

		return "";
	}

}
