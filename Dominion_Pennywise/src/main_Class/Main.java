package main_Class;

import Splash.S_View;
import javafx.application.Application;
import javafx.stage.Stage;
import server_Models.Configuration;
import server_Models.Translator;

/**
 * Main Class of the IT Project Dominion
 * 
 * Starts the project
 * 
 * @version final version
 * @endDate 22.12.2017
 * @projectteam Pennywise
 * 
 * @author Michael Tu
 * @author Patrick Ziörjen
 * @author Sojo Nagaroor
 * @author Yujia Shi
 *
 */
public class Main extends Application {

	private ServiceLocator serviceLocator;
	private S_View splashScreen;
	private String language;

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * The method which starts the game
	 * 
	 * @author Patrick Ziörjen
	 * @param primaryStage
	 *            - Primary Stage from Splash
	 * @return -
	 */
	public void start(Stage primaryStage) {
		serviceLocator = ServiceLocator.getServiceLocator();
		serviceLocator.setConfiguration(new Configuration());
		language = serviceLocator.getConfiguration().getOption("Language");
		serviceLocator.setTranslator(new Translator(language));

		splashScreen = new S_View();
		splashScreen.run(primaryStage);
	}
}