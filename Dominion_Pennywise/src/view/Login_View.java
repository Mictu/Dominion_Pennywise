package view;

import java.util.Locale;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main_Class.ServiceLocator;
import server_Models.Translator;

public class Login_View {

	BorderPane mainPane;
	Stage stage;

	public Button lobbyBtn;
	public Button exitBtn;
	Label welcomeLbl;////
	public Label nameLbl;
	Label languageLbl;
	public Label warning;

	public TextField nameTxtfield;
	ComboBox<String> boxLanguage = new ComboBox<String>();
	
	VBox topvbox = new VBox(180);
	VBox vbox = new VBox(30); // for the center
	HBox hbox1 = new HBox(); // for name and textfield
	VBox vbox2 = new VBox(5); // for laguage and dropdown

	public Login_View(Stage stage) {
		ServiceLocator sl = ServiceLocator.getServiceLocator();
		Translator t = sl.getTranslator();

		this.stage = stage;
		// stage.setResizable(false);
		stage.setTitle("Dominion Login");
		stage.setFullScreen(true);
	
		//Hauptpane as Grundger�st  
		mainPane = new BorderPane();
		mainPane.setId("mainPane");
		
		mainPane.setCenter(vbox);
		vbox.setAlignment(Pos.CENTER);
		hbox1.setAlignment(Pos.CENTER);
		vbox2.setAlignment(Pos.CENTER);

//		boxLanguage.getSelectionModel().select(t.getString("dominion.login.choseLanguage"));

		for (Locale locale : sl.getLocales()) {
			boxLanguage.getItems().add(locale.getLanguage());
			boxLanguage.setOnAction(event -> {
				sl.getConfiguration().setLocalOption("Language", boxLanguage.getSelectionModel().getSelectedItem());
				sl.setTranslator(new Translator(boxLanguage.getSelectionModel().getSelectedItem()));
				updateTexts();

			});
		}

		// BORDER PANE TOP
		// Wilkommens Label erstellen und Top
		welcomeLbl = new Label(t.getString("dominion.login.welcome"));
		welcomeLbl.setId("welcomeLbl");
		
		topvbox.getChildren().add(welcomeLbl);
		topvbox.setAlignment(Pos.CENTER);
		mainPane.setTop(topvbox);

		// BORDERPANE CENTER

		//
		nameLbl = new Label(t.getString("dominion.login.name") + ": ");
		nameLbl.setId("lblText");
		languageLbl = new Label(t.getString("dominion.login.language") + ": ");
		languageLbl.setId("lblText");
		nameTxtfield = new TextField();
		nameTxtfield.setId("textField");

		hbox1.getChildren().addAll(nameLbl, nameTxtfield);
		vbox2.getChildren().addAll(languageLbl, boxLanguage);

		lobbyBtn = new Button(t.getString("dominion.login.button.lobby"));
		exitBtn = new Button(t.getString("dominion.login.button.quit"));

		warning = new Label("");
		warning.setId("warning");

		vbox.getChildren().addAll(warning ,hbox1, vbox2, lobbyBtn, exitBtn);
		Scene scene = new Scene(mainPane);
		scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
		stage.setScene(scene);
		stage.setFullScreen(true);

	}

	protected void updateTexts() {
		Translator t = ServiceLocator.getServiceLocator().getTranslator();
		welcomeLbl.setText(t.getString("dominion.login.welcome"));
		languageLbl.setText(t.getString("dominion.login.language") + ": ");
		lobbyBtn.setText(t.getString("dominion.login.button.lobby"));
		exitBtn.setText(t.getString("dominion.login.button.quit"));
		warning.setText(t.getString("dominion.login.warning"));
//		boxLanguage.getSelectionModel().select(t.getString("dominion.login.choseLanguage"));
	}

	public void start() {
		this.stage.show();
	}

	public void stop() {
		this.stage.hide();
	}

	public Stage getStage() {
		return this.stage;
	}

}
