package controllers;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Get sounds for the game.
 * 3 sounds exist for different actions.
 * @author Patrick
 */
public class Button_Sounds {
	
	static String musicFileCard = "clicksound.mp3";
	static Media soundCard = new Media(new File(musicFileCard).toURI().toString());

	static String musicFileSend = "pull-out.mp3";
	static Media soundSend = new Media(new File(musicFileSend).toURI().toString());
	
	static String musicFileLogger = "loggersound.mp3";
	static Media soundLogger = new Media(new File(musicFileLogger).toURI().toString());

	/**
	 * play a sound if a card is clicked in the Board_View
	 */
	public static void playCardSound() {
		new MediaPlayer(soundCard).play();
	}
	
	/**
	 * play a sound if a chat - message has been send
	 */
	public static void playSendSound() {
		new MediaPlayer(soundSend).play();
	}
	
	/**
	 * play a sound if a notification is shown in the info - box of the Board_View
	 */
	public static void playLoggerSound() {
		new MediaPlayer(soundLogger).play();
	}
	
	
}
