package main_Class;

import javafx.application.Application;
import javafx.stage.Stage;
import view.Login_View;

public class Main extends Application {

	Login_View loginView;

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
	}

}