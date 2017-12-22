package main_Class;

import Splash.S_View;
import Splash.Server_View;
import controllers.Login_Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import server.client.Server;
import server_Models.Configuration;
import server_Models.Translator;
import view.Board_View;
import view.Lobby_View;
import view.Login_View;
import view.Result_View;

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

	Login_View loginView;
	Lobby_View lobbyView;
	Board_View boardView;
	Result_View resultView;
	Login_Controller loginController;
	private ServiceLocator serviceLocator;

	S_View splashScreen;
	Server_View serverView;
	Server server;

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
		String language = serviceLocator.getConfiguration().getOption("Language");
		serviceLocator.setTranslator(new Translator(language));

		splashScreen = new S_View();
		splashScreen.run(primaryStage);

	}
}