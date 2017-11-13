package main_Class;

import javafx.application.Application;
import javafx.stage.Stage;
import view.Login_View;
import view.Lobby_View;
import view.Board_View;
import view.Result_View;

public class Main extends Application {

	Login_View loginView;
	Lobby_View lobbyView;
	Board_View boardView;
	Result_View resultView;
	Stage stage;

	// MVC STARTS THE PROGRAMM AND INITIALIZES THE MVC-CLASSES (MODEL, VIEW,
	// CONTROLLER)

	public static void main(String[] args) {
		launch(args);
	}

	// Maybe initialize something (e.g.DATABASE or NETWORK SERVER)
	public void init() {
	}

	public void start(Stage primaryStage) {
			
			loginView = new Login_View(primaryStage);
			loginView.start();
			
	}

	public void stop() {
		if (loginView != null){
			loginView.stop();
		}
		if (lobbyView != null){
			lobbyView.stop();
		}
		if (boardView != null){
			boardView.stop();
		}
		if (resultView != null){
			resultView.stop();
		}
	}

}