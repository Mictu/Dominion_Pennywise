package view;

import java.util.Locale;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
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
	
	VBox topvbox = new VBox(170);
	VBox vbox = new VBox(40); // for the center
	HBox hbox1 = new HBox(); // for name and textfield
	VBox vbox2 = new VBox(10); // for language and dropdown

	public Login_View(Stage stage) {
		ServiceLocator sl = ServiceLocator.getServiceLocator();
		Translator t = sl.getTranslator();

		this.stage = stage;
		// stage.setResizable(false);
		stage.setTitle("Dominion Login");
		stage.setFullScreen(true);
		stage.setFullScreenExitHint("");
		
		
		//Hauptpane as Grundger�st  
		mainPane = new BorderPane();
		mainPane.setId("mainPane");
		BorderPane.setAlignment(topvbox, Pos.CENTER);
		
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
		welcomeLbl.setAlignment(Pos.CENTER);
		
		topvbox.getChildren().add(welcomeLbl);
		topvbox.setAlignment(Pos.CENTER);
		mainPane.setTop(topvbox);

		// BORDERPANE CENTER

		//
		nameLbl = new Label(t.getString("dominion.login.name") + ": ");
		nameLbl.setId("lblText");
		languageLbl = new Label(t.getString("dominion.login.language") + ": ");
		languageLbl.setId("lblText");
		languageLbl.setAlignment(Pos.CENTER);
		nameTxtfield = new TextField();
		nameTxtfield.setId("textField");

		Region reg = new Region();
		Region reg2 = new Region();

		hbox1.getChildren().addAll(nameLbl, nameTxtfield);
		vbox2.getChildren().addAll(reg2, languageLbl, boxLanguage, reg);

		lobbyBtn = new Button(t.getString("dominion.login.button.lobby"));
		exitBtn = new Button(t.getString("dominion.login.button.quit"));

		warning = new Label("");
		warning.setId("warning");
		warning.setAlignment(Pos.CENTER);
		
		
		vbox.getChildren().addAll(warning ,hbox1, vbox2, lobbyBtn, exitBtn);
		Scene scene = new Scene(mainPane);
		scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
		stage.setScene(scene);
		stage.setFullScreen(true);
		
		// Bindings for resizing the content on the window
		// mainPane -> topvbox 	-> welcomeLbl
		//			-> vbox		-> warning
		//			-> vbox		-> hbox1	-> nameLbl
		//			-> vbox		-> hbox1	-> nameTxtfield
		//			-> vbox		-> vbox2	-> languageLbl
		//			-> vbox		-> vbox2	-> boxLanguage
		//			-> vbox		-> lobbyBtn
		//			-> vbox		-> exitBtn
		
		setStageBindings(mainPane, stage, 1, 1);
		
		setBindings(vbox, mainPane, 0.82, 0.18);
		setBindings(topvbox, mainPane, 0.15, 0.18);
		
		setBindings(hbox1, vbox, 0.05, 0.5);
		setBindings(vbox2, vbox, 0.09, 1);
		setBindings(warning, vbox, 0.1, 3);
		setBindings(lobbyBtn, vbox, 0.06, 0.6);
		setBindings(exitBtn, vbox, 0.06, 0.8);

		setBindings(nameLbl, hbox1, 1, 0.8);
		setBindings(nameTxtfield, hbox1, 1, 1.1);
		
		setBindings(reg2, vbox2, 0.03, 1);
		setBindings(languageLbl, vbox2, 0.6, 1);
		setBindings(boxLanguage, vbox2, 0.6, 0.5);
		setBindings(reg, vbox2, 0.25, 1);
		
		setBindings(welcomeLbl, topvbox, 1, 3);
		
		// Bindings for language - setting?
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
	
	
	protected void setBindings(Region child, Region parent, double heightMultiply, double widthMultiply){
		child.maxHeightProperty().bind(parent.heightProperty().multiply(heightMultiply));
		child.maxWidthProperty().bind(parent.widthProperty().multiply(widthMultiply));
		child.minHeightProperty().bind(parent.heightProperty().multiply(heightMultiply));
		child.minWidthProperty().bind(parent.widthProperty().multiply(widthMultiply));
	}
	protected void setStageBindings(Region child, Stage stage2, double heightMultiply, double widthMultiply){
		child.maxHeightProperty().bind(stage2.heightProperty().multiply(heightMultiply));
		child.maxWidthProperty().bind(stage2.widthProperty().multiply(widthMultiply));
		child.minHeightProperty().bind(stage2.heightProperty().multiply(heightMultiply));
		child.minWidthProperty().bind(stage2.widthProperty().multiply(widthMultiply));
	}

}
