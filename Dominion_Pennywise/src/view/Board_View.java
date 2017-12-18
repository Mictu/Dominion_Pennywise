package view;

import java.io.File;
import java.util.ArrayList;
import controllers.Board_Controller;
import controllers.ClientHandler;
import controllers.Login_Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import main_Class.ServiceLocator;
import server_Models.Translator;

public class Board_View {

	public Stage stage;
	public BorderPane root;
	/*
	 * WRITE DYNAMIC HAND CARDS ARE COVERED IF HOVERED SET CHAT GOLDEN BORDER IF U
	 * CAN PLAY CARDS DECK AND DISCARD DECK SHOULD BE SEEN FROM BEHIND
	 */

	// Initialize the GUI Content here
	public Button endPhase, bonusMoney, pay, send;
	public HBox hBoxHand;
	public TextField chatText;
	public TextArea chat;
	Board_Controller bcontroller;
	protected HBox hCenter1, hCenter2, hCenter3;
	HBox hBottom;
	String cardID;

	protected int soundCounter = 0;
	protected DropShadow shadow = new DropShadow();

	protected ArrayList<Button> handCards = new ArrayList<Button>();
	protected ArrayList<String> handFromServer = new ArrayList<String>();

	protected ArrayList<Button> treasure = new ArrayList<Button>();
	protected ArrayList<Button> kingdom = new ArrayList<Button>();
	protected ArrayList<Button> victory = new ArrayList<Button>();

	// constructor
	public Board_View(Stage s) {
		playSound();
		CardDesign_View cdV = new CardDesign_View();
		ServiceLocator sl = ServiceLocator.getServiceLocator();
		Translator t = sl.getTranslator();

		this.stage = s;

		// Set up the GUI in here
		stage.setTitle("Dominion");
		// stage.setResizable(false);
		root = new BorderPane();
		root.setId("boardRoot");
		root.setPadding(new Insets(10, 10, 10, 10));
		root.autosize();
		root.layoutBoundsProperty();
		stage.setFullScreenExitHint("");

		// SET TOP
		// SET CENTER
		// use V- and H- Boxes to add the Cards (Buttons)
		VBox vCenter = new VBox(8);
		vCenter.setAlignment(Pos.CENTER);
		hCenter1 = new HBox(20); // add Victory cards
		hCenter2 = new HBox(20); // add Kingdom-/ Action card
		hCenter3 = new HBox(20); // add Treasure cards

		vCenter.setPadding(new Insets(0, 10, 0, 0));
		hCenter1.setId("boxes");
		hCenter2.setId("boxes");
		hCenter3.setId("boxes");

		try {

			treasure.add(cdV.getCopperBtn());
			treasure.add(cdV.getSilverBtn());
			treasure.add(cdV.getGoldBtn());
			kingdom.add(cdV.getFunfairBtn());
			kingdom.add(cdV.getLaboratoryBtn());
			kingdom.add(cdV.getMarketBtn());
			kingdom.add(cdV.getSmithBtn());
			kingdom.add(cdV.getVillageBtn());
			kingdom.add(cdV.getWoodcutterBtn());
			victory.add(cdV.getEstateBtn());
			victory.add(cdV.getDuchyBtn());
			victory.add(cdV.getProvinceBtn());
			
			for (Button l : treasure) {
				hCenter3.getChildren().add(l);
				bindingsForContent(l, hCenter3, 1, 0.12);
			}
			for (Button l : kingdom) {
				hCenter2.getChildren().add(l);
				bindingsForContent(l, hCenter2, 1, 0.12);
			}
			for (Button l : victory) {
				hCenter1.getChildren().add(l);
				bindingsForContent(l, hCenter1, 1, 0.12);
			}
			

//			setCardsOnViewEnable();

		} catch (Exception e) {
			e.printStackTrace();
		}

		endPhase = new Button("Phase beenden");
		endPhase.setId("endBtn");
		bonusMoney = new Button("Bonusgeld: 0");
		bonusMoney.setId("btn");
		pay = new Button("Bezahlen bestätigen");
		pay.setId("btn");

		HBox labels = new HBox(20);
		Label firstPhase = new Label(t.getString("dominion.board.lbl.actionPhase"));
		firstPhase.setId("phaseLabels");
		Label secondPhase = new Label(t.getString("dominion.board.lbl.buyPhase"));
		secondPhase.setId("phaseLabels");
		Label thirdPhase = new Label(t.getString("dominion.board.lbl.cleanupPhase"));
		thirdPhase.setId("phaseLabels");

		Region reg = new Region();
		Region reg2 = new Region();
		reg.setPrefWidth(140);
		reg2.setPrefWidth(140);

		labels.setAlignment(Pos.CENTER);

		labels.getChildren().addAll(firstPhase, secondPhase, thirdPhase, reg, bonusMoney, pay, reg2, endPhase);

		// bindings for the part in between of players hand and cards to buy
		bindingsForContent(firstPhase, labels, 0.98, 0.16);
		bindingsForContent(secondPhase, labels, 0.98, 0.16);
		bindingsForContent(thirdPhase, labels, 0.98, 0.16);
		bindingsForContent(reg, labels, 0.98, 0.02);
		bindingsForContent(bonusMoney, labels, 0.98, 0.16);
		bindingsForContent(pay, labels, 0.98, 0.16);
		bindingsForContent(reg2, labels, 0.98, 0.02);
		bindingsForContent(endPhase, labels, 0.98, 0.16);

		// CENTER BOTTOM

		StackPane stackPane = new StackPane();
		stackPane.setPadding(new Insets(0, 10, 0, 0));

		Label hand = new Label(t.getString("dominion.board.lbl.hand"));
		hand.setId("handLabel");

		hBoxHand = new HBox();
		hBoxHand.setAlignment(Pos.CENTER);

		// testing the players hand

		// hBoxHand.getChildren().addAll(cdV.getWoodcutterBtn(), cdV.getFunfairBtn(),
		// cdV.getFunfairBtn(),
		// cdV.getFunfairBtn(), cdV.getFunfairBtn());

		Button deck = new Button();
		deck.setId("back");
		Button discard = new Button();
		discard.setId("back");

		stackPane.getChildren().addAll(hand, hBoxHand);

		hBottom = new HBox(40); // Spacing between deck and discard
		hBottom.getChildren().addAll(deck, stackPane, discard);
		hBottom.setAlignment(Pos.CENTER);

		bindingsForContent(deck, hBottom, 0.7, 0.1);
		bindingsForContent(discard, hBottom, 0.7, 0.1);
		bindingsForContent(stackPane, hBottom, 1, 0.75);

		bindingsForContent(hand, stackPane, 1, 1);
		bindingsForContent(hBoxHand, stackPane, 0.7, 0.98);

		reg = new Region();
		reg.setPrefHeight(15);
		reg2 = new Region();
		reg2.setPrefHeight(15);

		vCenter.getChildren().addAll(hCenter1, hCenter2, hCenter3, reg2, labels, reg, hBottom);

		// bindings for Center content
		bindingsForContent(hCenter1, vCenter, 0.2, 0.8);
		bindingsForContent(hCenter2, vCenter, 0.2, 0.8);
		bindingsForContent(hCenter3, vCenter, 0.2, 0.8);
		bindingsForContent(reg, vCenter, 0.005, 0.65);
		bindingsForContent(labels, vCenter, 0.05, 0.8);
		bindingsForContent(reg2, vCenter, 0.005, 0.65);
		bindingsForContent(hBottom, vCenter, 0.25, 0.8);

		TextArea playerStats = new TextArea();
		playerStats.setEditable(false);
		playerStats.setId("playerStats");

		StackPane centerSP = new StackPane();
		centerSP.getChildren().addAll(playerStats, vCenter);
		StackPane.setAlignment(playerStats, Pos.TOP_RIGHT);

		root.setCenter(centerSP);

		bindingsForContent(playerStats, centerSP, 0.15, 0.15);
		bindingsForContent(vCenter, centerSP, 1, 1);

		// SET RIGHT
		VBox vRight = new VBox(10);

		HBox chatInput = new HBox(5);
		chatText= new TextField();
		send = new Button();
		send.setId("sendButton");
		send.setText(t.getString("dominion.lobby.btn.send"));

		chatInput.getChildren().addAll(chatText, send);
		chatInput.setAlignment(Pos.CENTER);
		HBox.setHgrow(chatText, Priority.ALWAYS);

		TextArea logger = new TextArea();
		logger.setEditable(false);
		chat = new TextArea();
		chat.setEditable(false);

		reg = new Region();
		reg.setPrefHeight(20);

		vRight.getChildren().addAll(logger, reg, chat, chatInput);

		root.setRight(vRight);

		// SET BOTTOM

		// SET SCENE
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		stage.setScene(scene);

		bindingsForContent(centerSP, root, 0.98, 0.79);
		bindingsForContent(vRight, root, 0.98, 0.19);

		setStageBindings(root, stage, 0.98, 0.98);
	} // Close the Constructor

	public void start() {
		stage.setFullScreen(true);
		stage.show();
	}

	public Stage getStage() {
		return this.stage;
	}

	public void stop() {
		this.stage.hide();
	}

	public void setHand() {
		handFromServer = ClientHandler.tempHandCard;
		
		if (handFromServer.size() > 5)
			hBoxHand.setSpacing(20 - handFromServer.size() * 4);
		else 
			hBoxHand.setSpacing(20);
		
		this.hBoxHand.getChildren().clear();
		while (handCards.size() < handFromServer.size()) {
			Button b = new Button();
			b.setOnAction((event) -> {
				try {
					playSound();
					cardID = "hand" + b.getId();
					//b.setDisable(true); // die Angeklickte karte sollte disabled oder in Discard geschickt werden
					Login_Controller.client.sendToServer(cardID);
				} catch (Exception e) {
					System.out.println("Button der hand haben noch keine ID erhalten");
				}
			});
			bindingsForContent(b, hBoxHand, 1, 0.16);
			handCards.add(b);
		}
		for (int i = 0; i < handFromServer.size(); i++) {
			handCards.get(i).setId(handFromServer.get(i));
		}
//		setCards();
		for (int i = 0; i < handFromServer.size(); i++) {
			this.hBoxHand.getChildren().add(handCards.get(i));
		}
		handFromServer.clear();
		ClientHandler.tempHandCard.clear();
	}

//	public void setCards() {
//
//		if (ClientHandler.phase.equals("buy")) {
//			setCardsOnViewEnable();
//			for (Button b : handCards) {
//
//				if (b.getId().equals("copper") || b.getId().equals("silver") || b.getId().equals("gold")) {
//					b.setDisable(false);
//				} else {
//					b.setDisable(true);
//				
//				}
//	
//			} 
//		}
//		if (ClientHandler.phase.equals("action")) {
////			setCardsOnViewDisable();
//			for (Button b : handCards) {
//				
//				if (b.getId().equals("smith") || b.getId().equals("market") || b.getId().equals("laboratory")
//						|| b.getId().equals("funfair") || b.getId().equals("woodcutter")
//						|| b.getId().equals("village")) {
//					b.setDisable(false);
//				} else {
//					b.setDisable(true);
//				}
//
//			}
//		}
//		
//	}

	// enables buttons while player is able to chose which card he wants to buy
//	public void setCardsOnViewEnable() {
//		for (Button l : victory) {
//			l.setEffect(null);
//			l.setDisable(false);
//		}
//		for (Button m : kingdom) {
//			m.setEffect(null);
//			m.setDisable(false);
//		}
//		for (Button n : treasure) {
//			n.setEffect(null);
//			n.setDisable(false);
//		}
//	}
//
//	// disables buttons while player is in action phase
//	public void setCardsOnViewDisable() {
//		for (Button l : victory) {
//			l.setEffect(shadow);
//			l.setDisable(true);
//		}
//		for (Button m : kingdom) {
//			m.setEffect(shadow);
//			m.setDisable(true);
//		}
//		for (Button n : treasure) {
//			n.setEffect(shadow);
//			n.setDisable(true);
//		}
//	}
	
	public void setEmptyHand() {
		hBoxHand.getChildren().clear();
	}

	// play a sound if card is pressed
	public void playSound() {
		String musicFile = "clicksound.mp3"; // For example
		Media sound = new Media(new File(musicFile).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		if (soundCounter == 1)
			mediaPlayer.play();
		soundCounter = 1;
	}

	protected void bindingsForContent(Region child, Region parent, double heightMultiply, double widthMultiply) {
		child.maxHeightProperty().bind(parent.heightProperty().multiply(heightMultiply));
		child.maxWidthProperty().bind(parent.widthProperty().multiply(widthMultiply));
		child.minHeightProperty().bind(parent.heightProperty().multiply(heightMultiply));
		child.minWidthProperty().bind(parent.widthProperty().multiply(widthMultiply));
	}

	protected void setStageBindings(Region child, Stage stage2, double heightMultiply, double widthMultiply) {
		child.maxHeightProperty().bind(stage2.heightProperty().multiply(heightMultiply));
		child.maxWidthProperty().bind(stage2.widthProperty().multiply(widthMultiply));
		child.minHeightProperty().bind(stage2.heightProperty().multiply(heightMultiply));
		child.minWidthProperty().bind(stage2.widthProperty().multiply(widthMultiply));
	}

//	public void blockWindow() {
//		hCenter1.setDisable(true);
//		hCenter2.setDisable(true);
//		hCenter3.setDisable(true);
//		hBottom.setDisable(true);
//		endPhase.setDisable(true);
//		pay.setDisable(true);
//		bonusMoney.setDisable(true);
//		
//	}
//	public void enableWindow() {
//		hCenter1.setDisable(false);
//		hCenter2.setDisable(false);
//		hCenter3.setDisable(false);
//		hBottom.setDisable(false);
//		endPhase.setDisable(false);
//		pay.setDisable(false);
//		bonusMoney.setDisable(false);
//		
//	}

}// close class

// Written by Patrick