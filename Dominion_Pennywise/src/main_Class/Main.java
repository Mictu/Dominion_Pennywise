package main_Class;

import Splash.S_View;
import Splash.Server_View;
import controllers.Login_Controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import server.client.Server;
import server_Models.Configuration;
import server_Models.Translator;
import view.Login_View;
import view.Lobby_View;
import view.Board_View;
import view.Result_View;

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

	// MVC STARTS THE PROGRAMM AND INITIALIZES THE MVC-CLASSES (MODEL, VIEW, CONTROLLER)

	public static void main(String[] args) {
		launch(args);
	}

	// Maybe initialize something (e.g.DATABASE or NETWORK SERVER)
	public void init() {
	}

	public void start(Stage primaryStage) {

		// decide if a Server needs to get started, then start Splashscreen
//		serverView = new Server_View(primaryStage);
		
		serviceLocator = ServiceLocator.getServiceLocator();
		serviceLocator.setConfiguration(new Configuration());
		String language = serviceLocator.getConfiguration().getOption("Language");
		serviceLocator.setTranslator(new Translator(language));
		
		splashScreen = new S_View();
		splashScreen.run(primaryStage);
		
		
	}

	public void stop() {
		serviceLocator.getConfiguration().save();
		
		if (loginView != null) {
			loginView.stop();
		}
		if (lobbyView != null) {
			lobbyView.stop();
		}
		if (boardView != null) {
			boardView.stop();
		}
		if (resultView != null) {
			resultView.stop();
		}
		Platform.exit();							// Close Server if it is running
	}

}