package server_Models;

import java.util.Collections;

/**
 * Play the clean up phase and reset the used content for the next player. This
 * class only starts while actual player is in cleanup phase. Contains
 * everything it needed for a proper cleanup Phase in game
 * 
 * @author Patrick Ziörjen
 */
public class CleanUpPhase {

	private Player player;

	/**
	 * Starts the methods in the right order to clean up after a player ends his
	 * buy-phase (clear hand - get cards for the hand).
	 * 
	 * @param player
	 *            current player which has his turn
	 */
	public CleanUpPhase(Player player) {
		this.player = player;
		throwCard();
		drawCard();
	}

	/**
	 * Clears the hand of the player (push cards on to discard deck)
	 */
	public void throwCard() {
		for (int i = 1; i <= player.hand.size(); i = 1) {
			player.discard.add(player.hand.get(0));
			player.hand.remove(0);
		}
	}

	/**
	 * Get cards from players deck into the hand (5 in the end). If the deck is
	 * empty, the shuffle-method will be called to shuffle the discards and add
	 * the cards to the deck.
	 */
	public void drawCard() {
		for (int i = 1; i <= 5; i++) {
			if (player.deck.isEmpty()) {
				shuffle();
			}
			player.hand.add(player.deck.get((player.deck.size() - 1)));
			player.deck.remove((player.deck.size() - 1));
		}
	}

	/**
	 * Shuffles the discard-deck and adds the cards to the deck.
	 */
	public void shuffle() {
		Collections.shuffle(player.discard);
		for (int i = 1; i <= player.discard.size(); i = 1) {
			player.deck.add(player.discard.get(0));
			player.discard.remove(0);
		}
	}
}
