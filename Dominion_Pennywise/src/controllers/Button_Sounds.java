package controllers;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Add effect sounds to the game 3 different sounds exist for different actions.
 * 
 * @author Patrick Ziörjen
 */
public class Button_Sounds {

	static String musicFileCard = "clicksound.mp3";
	static Media soundCard = new Media(new File(musicFileCard).toURI().toString());

	static String musicFileSend = "pull-out.mp3";
	static Media soundSend = new Media(new File(musicFileSend).toURI().toString());

	static String musicFileLogger = "loggersound.mp3";
	static Media soundLogger = new Media(new File(musicFileLogger).toURI().toString());

	/**
	 * Play a specific sound if a card is clicked in the Board_View
	 * 
	 * @author Patrick Ziörjen
	 * @param -
	 * @return -
	 */
	public static void playCardSound() {
		new MediaPlayer(soundCard).play();
	}

	/**
	 * Play a specific sound if the send button is clicked in the Board_View
	 * 
	 * @author Patrick Ziörjen
	 * @param -
	 * @return -
	 */
	public static void playSendSound() {
		new MediaPlayer(soundSend).play();
	}

	/**
	 * Play a specific sound if logger updates himself in the Board_View
	 * 
	 * @author Patrick Ziörjen
	 * @param -
	 * @return -
	 */
	public static void playLoggerSound() {
		new MediaPlayer(soundLogger).play();
	}

}
