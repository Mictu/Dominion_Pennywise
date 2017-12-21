package server_Models;

import java.util.Collections;
import server.client.Server;
import server.client.ServerHandler;

public class GameLogic {

	// Initialize sector here
	protected CleanUpPhase cleanUpPhase;
	ActionPhase actionPhase = new ActionPhase();
	BuyPhase buyPhase = new BuyPhase();

	protected int countRounds = 0;
	protected final int START_MONEY = 7;
	protected final int START_ESTATE = 3;
	int starter;
	protected String actualPhase;

	ServerHandler serverHandler;
	Server server;
	CleanUpPhase cleanPhase;
	int index;
	boolean firstRound;

	Player player;

	// Constructor
	public GameLogic(Server server) {
		this.server = server;
		starter = 0;
		index = 0;
		firstRound = true;
		actualPhase = "action";
		for (Player player : Player.player) {
			gameStart(player);
		}
	} // Close Constructor

	protected void gameStart(Player player) {

		for (int i = 0; i < START_MONEY; i++) {
			player.discard.add("copper"); // don't forget to counter++
		}
		for (int i = 0; i < START_ESTATE; i++) {
			player.discard.add("estate");
		}
		cleanPhase = new CleanUpPhase(player);
	}

	public void theGame() {
		if(countRounds == 20){
			Collections.sort(Player.player);
			getSomeTime();
			server.sendToClient("gameover");
			getSomeTime();
			sendPointsForResult();
		}else{
		actualPhase = "action";
//		server.sendToClient(actualPhase);
		this.player = Player.player.get(index);
		sendPlayersHand();
		getSomeTime();
		sendLoggerMessage("name" + this.player.getName());
		this.player.startRound();
		sendABMPoints();
		int ind = 0;
		for (Player p : Player.player) {
			if (p.equals(this.player)) {
				server.sendStringToClient("action", ind);
				sendLoggerMessage("playaction");
			} else {
				server.sendStringToClient("cleanup", ind);
			}
			ind++;
		}
		if (starter == 0) {
			sendWinPoints();
			starter = 1;
		}
		countRounds++;
		}
		if (firstRound) {
			server.sendToClient(buyPhase.sendRestCards());
			getSomeTime();
			firstRound = false;
		}
	}

	public void playCard(String message) {
		switch (actualPhase) {
		case "action":
			actionPhase.chosenCard(message, this.player);
			if (actionPhase.getActionMadeBoolean()) {
				sendPlayersHand();
				getSomeTime();
				sendLoggerMessage("ac" + actionPhase.getPlayedCard());
			} else if (actionPhase.getInfoMessage()) {
				sendInfoMessage(actionPhase.getInfoString());
			}
			sendABMPoints();
			break;
		case "buy":
			buyPhase.buyCard(message, this.player);
			if (buyPhase.sendHandAgain()) {
				sendPlayersHand();
			}
			if (buyPhase.buySuccessfull()) {
				if (buyPhase.isFinished().contains("cardempty")) {
					server.sendToClient(buyPhase.isFinished());
					getSomeTime();
				}
				server.sendToClient(buyPhase.sendRestCards());
				getSomeTime();
				sendLoggerMessage("bc" + buyPhase.getBoughtCard());
				sendWinPoints();
			}
			if (buyPhase.getInfoMsg()) {
				sendInfoMessage(buyPhase.getInfoString());
			}
			sendABMPoints();
			break;
		}
	}

	public int getIndex() {
		index++;
		if (index == Player.player.size()) {
			index = 0;
		}
		return this.index;
	}

	// get the actual phase to let the client know what cards can be pressed
	public String getPhase() {
		return this.actualPhase;
	}

	// get the player which is playing in this moment
	public Player getPlayer() {
		return this.player;
	}

	// start following phase if button on board view is clicked
	public void endPhase() {
		switch (actualPhase) {
		case "action":
			actualPhase = "buy";
			sendLoggerMessage("playbuy");
			server.sendStringToClient(actualPhase, index);
			sendPlayersHand();
			// sendABMPoints();
			break;
		case "buy":
			actualPhase = "cleanup";
			cleanUpPhase = new CleanUpPhase(this.player);
			server.sendStringToClient(actualPhase, index);
			sendPlayersHand();
			buyPhase.resetVariablesForBuyPhase(this.player);
//			sendABMPoints();
			getIndex();
			theGame();
			break; // block this monitor
		}
	}

	// This method should actualize the hand on the view (e.g. call it in buy
	// phase
	// when u gave away a copper card)
	public void sendPlayersHand() {
		getSomeTime();
		if (player.hand.isEmpty()) {
			server.sendStringToClient("empty", index);
		} else {
			String theHand = "hand.";
			for (String card : player.hand) {
				theHand = theHand.concat(card + ".");
			}
			theHand = theHand.substring(0, theHand.length() - 1);
			server.sendStringToClient(theHand, index);
		}
		getSomeTime();
	}

	public void getSomeTime() {
		try {
			Thread.sleep(250);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getActualPhase() {
		return this.actualPhase;
	}

	public Player getActualPlayer() {
		return this.player;
	}

	public void sendLoggerMessage(String loggerMsg) {
		server.sendToClient("logger" + loggerMsg);
		getSomeTime();
	}

	public void sendInfoMessage(String infoMsg) {
		server.sendStringToClient("info" + infoMsg, index);
	}

	public void sendABMPoints() { // action, buy, money
		server.sendStringToClient(
				"abmpoints." + player.getActionPoints() + "." + player.getBuyPoints() + "." + player.getMoney(), index);
	}
	
	public void sendPointsForResult() {
		String result = "result.";
		result = result.concat(sendWinPoints());
		server.sendToClient(result);
	}

	public String sendWinPoints() {
		int countWinP = 0;
		String playersWinPoints = "winpoints.";
		for (Player p : Player.player) {
			for (String card : p.deck) {
				countWinP = addWinPoints(countWinP, card);
			}
			for (String card : p.discard) {
				countWinP = addWinPoints(countWinP, card);
			}
			for (String card : p.hand) {
				countWinP = addWinPoints(countWinP, card);
			}
			player.winPoint = countWinP;

		
			playersWinPoints = playersWinPoints.concat(p.getName() + "-" + player.winPoint + ".");
			countWinP = 0;
		}
		server.sendToClient(playersWinPoints);
		return playersWinPoints;
	}

	public int addWinPoints(int count, String card) {
		switch (card) {
		case "estate":
			count += 1;
			break;
		case "duchy":
			count += 3;
			break;
		case "province":
			count += 6;
			break;
		}
		return count;
	}

}

// Patrick