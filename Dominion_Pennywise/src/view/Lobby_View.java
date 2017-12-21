package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main_Class.ServiceLocator;
import server_Models.Translator;

public class Lobby_View {

	ServiceLocator sl = ServiceLocator.getServiceLocator();
	Translator t = sl.getTranslator();
	
	public static Stage stage;
	
	BorderPane mainPane;

	// connected Players and online players area
	public TextArea txtConnectedPlayers = new TextArea();

	// Chat area and its controlls
	public TextArea txtChatArea = new TextArea();
	public TextField txtChatMessage = new TextField();
	public Button btnSend = new Button(t.getString("dominion.lobby.btn.send"));

	// controls button
	public Button btnLeaveGame;
	public Button btnStartGame;
	
	public Label lblLobby, lblChat, lblOnline;

	public Lobby_View(Stage stage) {
		ServiceLocator sl = ServiceLocator.getServiceLocator();
		Translator t = sl.getTranslator();
		Lobby_View.stage = stage;
		// stage.setResizable(false);
		stage.setTitle("Dominion Lobby");
		stage.setFullScreenExitHint("");

		btnLeaveGame = new Button(t.getString("dominion.lobby.btn.quit"));
		btnStartGame = new Button(t.getString("dominion.lobby.btn.start"));
		
		lblLobby = new Label("Lobby");
		lblLobby.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		lblLobby.setId("lblLobby");
		
		lblChat = new Label(t.getString("dominion.lobby.lbl.chat"));
		lblChat.setId("lbl");
		lblOnline = new Label(t.getString("dominion.lobby.lbl.online"));
		lblOnline.setId("lbl");
		
		txtConnectedPlayers.setEditable(false);
		txtChatArea.setEditable(false);

		HBox bottomChatBox = new HBox(txtChatMessage, btnSend);
		bottomChatBox.getStyleClass().add("hboxBottom");
		HBox.setHgrow(txtChatMessage, Priority.ALWAYS);
		
		HBox topBox = new HBox(btnStartGame, btnLeaveGame);
		topBox.getStyleClass().add("topBox");

		btnLeaveGame.getStyleClass().add("btn");
		btnStartGame.getStyleClass().add("btn");

		VBox rightBox = new VBox(lblLobby, topBox, lblOnline, txtConnectedPlayers, lblChat, txtChatArea, bottomChatBox);
		rightBox.setId("rightBox");
		VBox.setVgrow(txtChatArea, Priority.ALWAYS);


		mainPane = new BorderPane();
		mainPane.setCenter(rightBox);
		mainPane.getStyleClass().add("mainPane");

		Scene scene = new Scene(mainPane);
		scene.getStylesheets().add(getClass().getResource("Lobby.css").toExternalForm());
		stage.setScene(scene);
		stage.setFullScreen(true);
	}

	public void start() {
		stage.setFullScreen(true);
		stage.show();
	}

	public static void stop() {
		stage.hide();
	}

	public Stage getStage() {
		return Lobby_View.stage;
	}

}
