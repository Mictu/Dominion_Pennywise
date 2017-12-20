package controllers;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Button_Sounds {
	static String musicFileCard = "clicksound.mp3"; // For example
	static Media soundCard = new Media(new File(musicFileCard).toURI().toString());

	static String musicFileSend = "pull-out.mp3"; // For example
	static Media soundSend = new Media(new File(musicFileSend).toURI().toString());
	
	static String musicFileLogger = "loggersound.mp3"; // For example
	static Media soundLogger = new Media(new File(musicFileLogger).toURI().toString());

	public static void playCardSound() {
		new MediaPlayer(soundCard).play();
	}
	
	public static void playSendSound() {
		new MediaPlayer(soundSend).play();
	}
	
	public static void playLoggerSound() {
		new MediaPlayer(soundLogger).play();
	}
	
	
}
