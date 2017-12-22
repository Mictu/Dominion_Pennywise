package server_Models;

import java.util.Collections;

import server.client.Server;
import server.client.ServerHandler;

/**
 * This is a Controller Class. Contains all of the game logic of the game
 * Dominion. GameLogic.java manages all the commands from Client and Server and
 * process the game.
 * 
 * @author Michael Tu
 * @author Patrick Ziörjen
 * @author Sojo Nagaroor
 * @author Yujia Shi
 */
public class GameLogic {

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

	/**
	 * Constructor for GameLogic.java initialize everything and set points for
	 * player from the begin of the game.
	 * 
	 * @param server
	 *            - server instance from Server.java
	 */
	public GameLogic(Server server) {
		this.server = server;
		starter = 0;
		index = 0;
		firstRound = true;
		actualPhase = "action";
		for (Player player : Player.player) {
			gameStart(player);
		}
	}

	/**
	 * Generate the start capital of every player and send the generated cards to
	 * discard from every player
	 * 
	 * @author Patrick Ziörjen
	 * 
	 * @param player
	 *            - actual player
	 */
	protected void gameStart(Player player) {
		for (int i = 0; i < START_MONEY; i++) {
			player.discard.add("copper");
		}
		for (int i = 0; i < START_ESTATE; i++) {
			player.discard.add("estate");
		}

		cleanPhase = new CleanUpPhase(player);

	}

	/**
	 * Controlls the whole game process Check if the conditions are true to start a
	 * new game. Command all classes on the server side
	 * 
	 * @author Michael Tu
	 * @author Patrick Ziörjen
	 * @author Sojo Nagaroor
	 * @author Yujia Shi
	 */
	public void theGame() {
		if (turn >= Player.player.size() * 30 || finishedCardStack >= 3 || buyPhase.pCounter == -1) {
			sendWinPoints();
			Collections.sort(Player.player);
			server.sendToClient("gameover");
			getSomeTime();
			sendPointsForResult();
		} else {
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
				server.sendToClient("rounds" + rounds);
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

	/**
	 * controlls the whole logic of playing a card in this game. Send logger
	 * messages if something specific happens
	 * 
	 * @author Michael Tu
	 * @author Sojo Nagaroor
	 * @author Patrick Ziörjen
	 * @author Yujia Shi
	 * 
	 * @param message
	 */
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

	public String getPhase() {
		return this.actualPhase;
	}

	public Player getPlayer() {
		return this.player;
	}

	/**
	 * handles the endphase butten. if the button is clicked, this method should
	 * stop the actual phase and start a new phase. at the same time, it refresh the
	 * cards on the hand
	 * 
	 * @author Yujia Shi
	 */
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
			if (turn % Player.player.size() == 0) {
				rounds++;
			}
			server.sendToClient("rounds" + rounds);
			getSomeTime();
			buyPhase.resetVariablesForBuyPhase(this.player);
			getIndex();
			theGame();
			break; // block this monitor
		}
	}

	/**
	 * This method actualize the hand and send it to the client
	 * 
	 * @author Patrick Ziörjen
	 */
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

	/**
	 * thread for a short break in the programm to load ressources
	 * 
	 * @author Patrick Ziörjen
	 */
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

	/**
	 * send the logger message to client
	 * 
	 * @author Patrick Ziörjen
	 * @param loggerMsg
	 *            - logger message for a specific action from phases
	 */
	public void sendLoggerMessage(String loggerMsg) {
		server.sendToClient("logger" + loggerMsg);
		getSomeTime();
	}

	/**
	 * send the info message to client
	 * 
	 * @author Patrick Ziörjen
	 * @param infoMsg
	 *            - info message for a specific action from phases
	 */
	public void sendInfoMessage(String infoMsg) {
		server.sendStringToClient("info" + infoMsg, index);
	}

	/**
	 * send the ABM Points to the client
	 * 
	 * @author Michael Tu
	 */
	public void sendABMPoints() {
		server.sendStringToClient(
				"abmpoints." + player.getActionPoints() + "." + player.getBuyPoints() + "." + player.getMoney(), index);
	}

	/**
	 * send the winpoints to client
	 * 
	 * @author Michael Tu
	 */
	public void sendPointsForResult() {
		String result = "result.";
		for (Player p : Player.player) {
			result = result.concat(p.getName() + "-" + p.getWinPoints() + ".");
		}
		server.sendToClient(result);
	}

	/**
	 * check decks and add winpoints to the correct player
	 * 
	 * @author Michael Tu
	 * @return playersWinPoints - return the acutal winpoints of a player
	 */
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

	/**
	 * count the points for every card that are important for winpoints
	 * 
	 * @author Michael Tu
	 * @param count
	 *            - counter for card
	 * @param card
	 *            - cardname
	 */
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
