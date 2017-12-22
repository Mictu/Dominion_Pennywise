package server_Models;

import java.util.Arrays;
import java.util.Collections;

import server.client.Server;
import server.client.ServerHandler;

public class GameLogic {

	// Initialize sector here
	protected CleanUpPhase cleanUpPhase;
	ActionPhase actionPhase = new ActionPhase();
	BuyPhase buyPhase = new BuyPhase();

	protected int turn = 0;
	protected final int START_MONEY = 7;
	protected final int START_ESTATE = 3;
	int starter;
	protected String actualPhase;

	ServerHandler serverHandler;
	Server server;
	CleanUpPhase cleanPhase;
	int index;
	int rounds = 1;
	boolean firstRound;
	int finishedCardStack = 0;
	
	int[] resultPoints = {};

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
		if(turn >= Player.player.size()*30 || finishedCardStack >= 3 || buyPhase.pCounter == -1){
			sendWinPoints();
			Collections.sort(Player.player);
			server.sendToClient("gameover");
			getSomeTime();
			sendPointsForResult();
//			sortPlayersResult();
		}else{
		actualPhase = "action";
		this.player = Player.player.get(index);
		sendPlayersHand();
		sendLoggerMessage("name" + this.player.getName());
		this.player.startRound();
		sendABMPoints();
		getSomeTime();
		int ind = 0;
		for (Player p : Player.player) {
			if (p.equals(this.player)) {
				server.sendStringToClient("action", ind);
				getSomeTime();
				sendLoggerMessage("playaction");
			} else {
				server.sendStringToClient("cleanup", ind);
			}
			ind++;
		}
		getSomeTime();
		if (starter == 0) {
			sendWinPoints();
			getSomeTime();
			server.sendToClient("rounds"+rounds);
			starter = 1;
		}
		turn++;
			if (firstRound) {
				server.sendToClient(buyPhase.sendRestCards());
				getSomeTime();
				firstRound = false;
			}
		}
	}
	
	public String sortPlayersResult() {
		String resultString = "result.";
		for(Player p : Player.player) {
			for(int i = 0; i < Player.player.size(); i++) {
				resultPoints[i] = p.getWinPoints();
			}
		}
		
		Arrays.sort(resultPoints);
		
		for(int i : resultPoints) {
			System.out.println(i);
		}
		
		//Revers the array
		for(int i = 0; i < resultPoints.length / 2; i++)
		{
		    int temp = resultPoints[i];
		    resultPoints[i] = resultPoints[resultPoints.length - i - 1];
		    resultPoints[resultPoints.length - i - 1] = temp;
		}
		
		for(int i : resultPoints) {
			System.out.println(i);
		}
		
		for(int i : resultPoints) {
			for(Player pl : Player.player) {
				if(i == pl.getWinPoints()) {
					resultString = resultString.concat(pl.getName() + "-" + i + ".");
				}
			}
		}
		System.out.println(resultString);
		return resultString;
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
				String isFinished = buyPhase.isFinished();
				if (isFinished.contains("cardempty")) {
					server.sendToClient(isFinished);
					finishedCardStack++;
					getSomeTime();
				}
				server.sendToClient(buyPhase.sendRestCards());
				getSomeTime();
				sendLoggerMessage("bc" + buyPhase.getBoughtCard());
				sendWinPoints();
				getSomeTime();
				sendABMPoints();
			}
			if (buyPhase.getInfoMsg()) {
				sendInfoMessage(buyPhase.getInfoString());
			}
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
			break;
		case "buy":
			actualPhase = "cleanup";
			cleanUpPhase = new CleanUpPhase(this.player);
			server.sendStringToClient(actualPhase, index);
			sendPlayersHand();
			if(turn % Player.player.size() == 0) {
				rounds++;
			}
			server.sendToClient("rounds"+rounds);
			getSomeTime();
			buyPhase.resetVariablesForBuyPhase(this.player);
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
	
//	public void sendPointsForResult() {
//		String result = "";
//		for(Player p : Player.player){
//			result = p.getName()+"-"+p.getWinPoints()+".";
//			for(Player pl : Player.player) {
//				if(p.winPoint > pl.winPoint) {
//					result = result + pl.getName()+"-"+pl.getWinPoints()+".";
//				} else {
//					result = pl.getName()+"-"+pl.getWinPoints()+"."+result;
//				}
//			}
//		}
//		System.out.println(result);
//		server.sendToClient("result.".concat(result));
//	}


	public void sendPointsForResult() {
		String result = "result.";
		for(Player p : Player.player) {
			result = result.concat(p.getName() + "-" + p.getWinPoints() + ".");
		}
		System.out.println(result);
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
			p.winPoint = countWinP;
		
			playersWinPoints = playersWinPoints.concat(p.getName() + "-" + p.winPoint + ".");
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