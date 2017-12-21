package server_Models;

import java.util.Collections;

import server.client.Server;

/**
 * Play an action-phase and reset the used content for the next player. This
 * class only starts while actual player is in action-phase.
 * 
 * @author Patrick
 */

public class ActionPhase {

	// initialize sector
	private boolean madeAnAction = false;
	private boolean sendInfo = false;
	private String playedCard, info;
	public Server server;

	/**
	 * Handle the card which a client has chosen to play. Compare the card
	 * (string) with all action-cards and if it contains the same string, start
	 * the method for this action-card. Also set two booleans, so the gamelogic
	 * knows when to send a string to all clients to update the infobox on the
	 * boardview and also when an action - card was player (to send players hand
	 * again)
	 * 
	 * @param message
	 *            - get the name of the played card from client-side
	 * @param player
	 *            - get the player who is having his move
	 */
	public void chosenCard(String message, Player player) {
		sendInfo = false;
		madeAnAction = false;
		String cardName = message.substring(4);
		this.playedCard = cardName;
		if (player.getActionPoints() > 0) {
			switch (cardName) {
			case "village":
				actionVillage(player);
				reloadHand(cardName, player);
				break;
			case "woodcutter":
				actionWoodcutter(player);
				reloadHand(cardName, player);
				break;
			case "funfair":
				actionFunfair(player);
				reloadHand(cardName, player);
				break;
			case "laboratory":
				actionLaboratory(player);
				reloadHand(cardName, player);
				break;
			case "market":
				actionMarket(player);
				reloadHand(cardName, player);
				break;
			case "smith":
				actionSmith(player);
				reloadHand(cardName, player);
				break;
			}
		} else {
			sendInfo = true;
		}
	}

	/**
	 * Return a boolean to find out if an action was successfully made
	 * 
	 * @return boolean if action was executed
	 */
	public boolean getActionMadeBoolean() {
		return madeAnAction;
	}

	/**
	 * Call the actions for a smith action-card
	 * 
	 * @param player
	 *            - get actual player who has his turn
	 */
	public void actionSmith(Player player) {
		increaseCard(3, player);
	}

	/**
	 * Call the actions for a market action-card
	 * 
	 * @param player
	 *            - get actual player who has his turn
	 */
	public void actionMarket(Player player) {
		increaseCard(1, player);
		player.increaseActionPoints(1);
		player.increaseBuyPoints(1);
		player.increaseMoney(1);
	}

	/**
	 * Call the actions for a laboratory action-card
	 * 
	 * @param player
	 *            - get actual player who has his turn
	 */
	public void actionLaboratory(Player player) {
		increaseCard(2, player);
		player.increaseActionPoints(1);
	}

	/**
	 * Call the actions for a funfair action-card
	 * 
	 * @param player
	 *            - get actual player who has his turn
	 */
	public void actionFunfair(Player player) {
		player.increaseActionPoints(2);
		player.increaseBuyPoints(1);
		player.increaseMoney(2);
	}

	/**
	 * Call the actions for a woodcutter action-card
	 * 
	 * @param player
	 *            - get actual player who has his turn
	 */
	public void actionWoodcutter(Player player) {
		player.increaseBuyPoints(1);
		player.increaseMoney(2);
	}

	/**
	 * Call the actions for a village action-card
	 * 
	 * @param player
	 *            - get actual player who has his turn
	 */
	public void actionVillage(Player player) {
		player.increaseActionPoints(2);
		increaseCard(1, player);
	}

	/**
	 * Method gets called for every action card which increases the cards amount
	 * for the actual players hand.
	 * 
	 * Get cards from actual players deck to fill in players hand. Control if
	 * the deck has enough cards to do this action and if not - shuffle the
	 * discard deck of the player and swap it into the player deck to get the
	 * remaining cards for the player hand.
	 * 
	 * @param amount
	 *            - amount of cards which should be added to players hand
	 * @param player
	 *            - actual player
	 */
	public void increaseCard(int amount, Player player) {
		int have = (player.deck.size() + player.discard.size());

		if (amount > have) {
			amount = have;
		}

		for (int i = 1; i <= amount; i++) {
			if (player.deck.size() == 0) {
				for (int j = 1; j <= player.discard.size(); j = 1) {
					player.deck.add(player.discard.get(0));
					player.discard.remove(0);
				}
				Collections.shuffle(player.deck);
			}
			player.hand.add(player.deck.get(0));
			player.deck.remove(0);
			// shuffles Cards if deck is empty
		}
	}

	public String getPlayedCard() {
		return this.playedCard;
	}

	public boolean getInfoMessage() {
		info = "not enough action - points";
		return this.sendInfo;
	}

	public String getInfoString() {
		return this.info;
	}

	/**
	 * Set the player hand to the current situation. Remove the played card from
	 * hand and add it to the discard deck. Set a boolean to true to let the
	 * gamelogic know if players hand should be send again. Also decrease the
	 * current players action points.
	 * 
	 * @param card
	 *            - played action card from current player
	 * @param player
	 *            - current player
	 */
	public void reloadHand(String card, Player player) {
		player.discard.add(player.hand.get(player.hand.lastIndexOf(card)));
		player.hand.remove(player.hand.lastIndexOf(card));
		madeAnAction = true;
		player.decreaseActionPoints();
	}
}
