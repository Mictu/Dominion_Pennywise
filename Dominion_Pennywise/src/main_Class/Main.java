package main_Class;

import Splash.S_View;
import controllers.Login_Controller;
import javafx.application.Application;
import javafx.stage.Stage;
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

	// MVC STARTS THE PROGRAMM AND INITIALIZES THE MVC-CLASSES (MODEL, VIEW,
	// CONTROLLER)

	public static void main(String[] args) {
		launch(args);
	}

	// Maybe initialize something (e.g.DATABASE or NETWORK SERVER)
	public void init() {
	}

	public void start(Stage primaryStage) {

		// start the splash-screen
		try {
			splashScreen = new S_View();
			splashScreen.run(primaryStage);
		} catch (Exception e) {
			System.err.println(e);
		}
		
		serviceLocator = ServiceLocator.getServiceLocator();
		serviceLocator.setConfiguration(new Configuration());
		String language = serviceLocator.getConfiguration().getOption("Language");
		serviceLocator.setTranslator(new Translator(language));
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
	}

}