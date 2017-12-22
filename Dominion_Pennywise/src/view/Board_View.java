package view;

import java.util.ArrayList;

import controllers.Button_Sounds;
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
import javafx.stage.Stage;
import main_Class.ServiceLocator;
import server_Models.Translator;

/**
 * View for the game field. shows all elements of the game field
 * 
 * @author Patrick Ziörjen
 * @author Sojo Nagaroor
 * @author Yujia Shi
 *
 */
public class Board_View {

	public Stage stage;
	public BorderPane root;

	// Initialize the GUI Content here
	public Button endPhase, send;
	public HBox hBoxHand;
	public TextField chatText;
	public TextArea chat;
	protected HBox hCenter1, hCenter2, hCenter3;
	HBox hBottom;
	String cardID;
	public Label aBMpoints;
	public TextArea logger;
	public Label info;
	public static Label cardInfo;
	public Label playerStats, roundCounter;

	protected int soundCounter = 0;
	protected DropShadow shadow = new DropShadow();

	protected ArrayList<Button> handCards = new ArrayList<Button>();
	protected ArrayList<String> handFromServer = new ArrayList<String>();
	public ArrayList<Label> labelCountStack = new ArrayList<Label>();
	ArrayList<StackPane> cardCountStack = new ArrayList<StackPane>();

	public ArrayList<Button> treasure = new ArrayList<Button>();
	public ArrayList<Button> kingdom = new ArrayList<Button>();
	public ArrayList<Button> victory = new ArrayList<Button>();

	ServiceLocator sl = ServiceLocator.getServiceLocator();
	Translator t = sl.getTranslator();

	CardDesign_View cdV;

	Button village = new Button();
	Button market = new Button();
	Button funfair = new Button();
	Button woodcutter = new Button();
	Button smith = new Button();
	Button laboratory = new Button();
	Button copper = new Button();
	Button silver = new Button();
	Button gold = new Button();
	Button estate = new Button();
	Button duchy = new Button();
	Button province = new Button();

	HBox labels;
	Label firstPhase, secondPhase, thirdPhase;

	/**
	 * initialize all elements on boardview and display them.
	 * 
	 * @author Patrick Ziörjen
	 * @author Sobo Nagaroor
	 * @author Yujia Shi
	 * @param s
	 *            - stage from boardController
	 */
	public Board_View(Stage s) {
		cdV = new CardDesign_View();
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
		stage.setFullScreen(true);

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
			this.copper = cdV.getCopperBtn();
			this.silver = cdV.getSilverBtn();
			this.gold = cdV.getGoldBtn();
			this.funfair = cdV.getFunfairBtn();
			this.laboratory = cdV.getLaboratoryBtn();
			this.market = cdV.getMarketBtn();
			this.smith = cdV.getSmithBtn();
			this.village = cdV.getVillageBtn();
			this.woodcutter = cdV.getWoodcutterBtn();
			this.estate = cdV.getEstateBtn();
			this.duchy = cdV.getDuchyBtn();
			this.province = cdV.getProvinceBtn();

			treasure.add(copper);
			treasure.add(silver);
			treasure.add(gold);
			kingdom.add(funfair);
			kingdom.add(laboratory);
			kingdom.add(market);
			kingdom.add(smith);
			kingdom.add(village);
			kingdom.add(woodcutter);
			victory.add(estate);
			victory.add(duchy);
			victory.add(province);

			// Add some CardCounters via StackPanes
			for (int i = 0; i < 12; i++) {
				StackPane cardStack = new StackPane();
				Label labelStack = new Label();
				labelStack.setId("cardcounter");
				labelStack.setText("0");
				labelCountStack.add(labelStack);
				cardStack.setAlignment(Pos.TOP_LEFT);
				cardCountStack.add(cardStack);
			}
			for (int i = 0; i < 3; i++) { // victory
				cardCountStack.get(i).getChildren().addAll(victory.get(i), labelCountStack.get(i));
				bindingsForContent(cardCountStack.get(i), hCenter1, 1, 0.12);
				bindingsForContent(labelCountStack.get(i), cardCountStack.get(i), 0.15, 0.25);
				bindingsForContent(victory.get(i), cardCountStack.get(i), 1, 1);
				hCenter1.getChildren().add(cardCountStack.get(i));
			}
			for (int i = 0; i < 6; i++) { // kingdom
				cardCountStack.get(i + 3).getChildren().addAll(kingdom.get(i), labelCountStack.get(i + 3));
				bindingsForContent(cardCountStack.get(i + 3), hCenter2, 1, 0.12);
				bindingsForContent(labelCountStack.get(i + 3), cardCountStack.get(i + 3), 0.15, 0.25);
				bindingsForContent(kingdom.get(i), cardCountStack.get(i + 3), 1, 1);
				hCenter2.getChildren().add(cardCountStack.get(i + 3));
			}
			for (int i = 0; i < 3; i++) { // treasure
				cardCountStack.get(i + 9).getChildren().addAll(treasure.get(i), labelCountStack.get(i + 9));
				bindingsForContent(cardCountStack.get(i + 9), hCenter3, 1, 0.12);
				bindingsForContent(labelCountStack.get(i + 9), cardCountStack.get(i + 9), 0.15, 0.25);
				bindingsForContent(treasure.get(i), cardCountStack.get(i + 9), 1, 1);
				hCenter3.getChildren().add(cardCountStack.get(i + 9));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		endPhase = new Button(t.getString("dominion.board.btn.endphase"));
		endPhase.setId("endBtn");

		HBox labels = new HBox(20);
		labels = new HBox(20);
		firstPhase = new Label(t.getString("dominion.board.lbl.actionPhase"));
		firstPhase.setId("phaseLabels");
		secondPhase = new Label(t.getString("dominion.board.lbl.buyPhase"));
		secondPhase.setId("phaseLabels");
		thirdPhase = new Label(t.getString("dominion.board.lbl.cleanupPhase"));
		thirdPhase.setId("phaseLabels");

		aBMpoints = new Label();
		aBMpoints.setId("playerPoints");

		Region reg = new Region();
		Region reg2 = new Region();
		reg.setPrefWidth(140);
		reg2.setPrefWidth(140);

		labels.setAlignment(Pos.CENTER);

		labels.getChildren().addAll(aBMpoints, reg, firstPhase, secondPhase, thirdPhase, reg2, endPhase);

		// bindings for the part in between of players hand and cards to buy
		bindingsForContent(firstPhase, labels, 0.98, 0.18);
		bindingsForContent(secondPhase, labels, 0.98, 0.18);
		bindingsForContent(thirdPhase, labels, 0.98, 0.18);
		bindingsForContent(reg, labels, 0.98, 0.08);
		bindingsForContent(reg2, labels, 0.98, 0.08);
		bindingsForContent(endPhase, labels, 0.98, 0.16);
		bindingsForContent(aBMpoints, labels, 2.5, 0.18);

		// CENTER BOTTOM
		StackPane stackPane = new StackPane();
		stackPane.setPadding(new Insets(0, 10, 0, 0));

		Label hand = new Label(t.getString("dominion.board.lbl.hand"));
		hand.setId("handLabel");

		hBoxHand = new HBox();
		hBoxHand.setAlignment(Pos.CENTER);

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

		playerStats = new Label();
		playerStats.setId("playerStats");

		info = new Label("");
		info.setWrapText(true);
		info.setId("infoLabel");

		Region regio = new Region();
		VBox infoBox = new VBox();
		infoBox.getChildren().addAll(info, regio);

		cardInfo = new Label("");
		cardInfo.setWrapText(true);
		cardInfo.setId("cardInfo");

		Region regio2 = new Region();
		VBox cardBox = new VBox();
		cardBox.getChildren().addAll(cardInfo, regio2);

		roundCounter = new Label();
		roundCounter.setId("roundLabel");

		StackPane centerSP = new StackPane();
		centerSP.getChildren().addAll(playerStats, roundCounter, infoBox, cardBox, vCenter);
		StackPane.setAlignment(playerStats, Pos.TOP_RIGHT);
		StackPane.setAlignment(infoBox, Pos.BOTTOM_LEFT);
		StackPane.setAlignment(cardBox, Pos.BOTTOM_RIGHT);
		StackPane.setAlignment(roundCounter, Pos.TOP_LEFT);

		root.setCenter(centerSP);

		bindingsForContent(playerStats, centerSP, 0.15, 0.2);
		bindingsForContent(infoBox, centerSP, 0.5, 0.3);
		bindingsForContent(cardBox, centerSP, 0.55, 0.2);
		bindingsForContent(roundCounter, centerSP, 0.1, 0.4);
		bindingsForContent(vCenter, centerSP, 1, 1);

		bindingsForContent(info, infoBox, 0.2, 1);
		bindingsForContent(regio, infoBox, 0.8, 1);
		bindingsForContent(cardInfo, cardBox, 0.32, 1);
		bindingsForContent(regio2, cardBox, 0.68, 1);

		// SET RIGHT
		VBox vRight = new VBox();

		HBox chatInput = new HBox(5);
		chatText = new TextField();
		send = new Button();
		send.setId("sendButton");
		send.setText(t.getString("dominion.lobby.btn.send"));

		chatInput.getChildren().addAll(chatText, send);
		chatInput.setAlignment(Pos.CENTER);
		HBox.setHgrow(chatText, Priority.ALWAYS);

		logger = new TextArea();
		logger.setEditable(false);
		logger.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
		logger.setText("");
		chat = new TextArea();
		chat.setEditable(false);
		chat.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");

		Label chatLbl = new Label("Chat:");
		Label loggerLbl = new Label("Info:");
		chatLbl.setId("vRightLabel");
		loggerLbl.setId("vRightLabel");

		vRight.getChildren().addAll(loggerLbl, logger, chatLbl, chat, chatInput);

		root.setRight(vRight);

		// SET BOTTOM

		// SET SCENE
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		stage.setScene(scene);

		bindingsForContent(centerSP, root, 0.98, 0.79);
		bindingsForContent(vRight, root, 0.98, 0.19);

		bindingsForContent(loggerLbl, vRight, 0.05, 1);
		bindingsForContent(logger, vRight, 0.4, 1);
		bindingsForContent(chatLbl, vRight, 0.05, 1);
		bindingsForContent(chat, vRight, 0.4, 1);
		bindingsForContent(chatInput, vRight, 0.1, 1);

		setStageBindings(root, stage, 0.98, 0.98);
	}

	public void start() {
		stage.show();
	}

	public Stage getStage() {
		return this.stage;
	}

	public void stop() {
		this.stage.hide();
	}

	/**
	 * change labels with the actual phase that comes from the server. enable the
	 * phase label if its the actual phase
	 * 
	 * @author Yujia Shi
	 */
	public void changePhaseLabel() {
		if (ClientHandler.phase.equals("action")) {
			firstPhase.setId("phaseLabelsEnable");
			secondPhase.setId("phaseLabels");
			thirdPhase.setId("phaseLabels");
		} else if (ClientHandler.phase.equals("buy")) {
			secondPhase.setId("phaseLabelsEnable");
			firstPhase.setId("phaseLabels");
			thirdPhase.setId("phaseLabels");
		} else {
			secondPhase.setId("phaseLabels");
			firstPhase.setId("phaseLabels");
			thirdPhase.setId("phaseLabelsEnable");
		}
	}

	/**
	 * get Hand from server and display the cards in a button. create new buttons
	 * for hand.
	 * 
	 * @author Sojo Nagaroor
	 */
	public void setHand() {
		handFromServer = ClientHandler.tempHandCard;

		if (handFromServer.size() > 5)
			hBoxHand.setSpacing(20 - handFromServer.size() * 4.5);
		else
			hBoxHand.setSpacing(20);

		this.hBoxHand.getChildren().clear();
		while (handCards.size() < handFromServer.size()) {
			Button b = new Button();
			b.setOnAction((event) -> {
				try {
					Button_Sounds.playCardSound();
					cardID = "hand" + b.getId();
					Login_Controller.client.sendToServer(cardID);
				} catch (Exception e) {
					System.out.println("Button der hand haben noch keine ID erhalten");
				}

			});
			b.setOnMouseEntered((Event) -> {
				cardInfo.setText(t.getString("dominion.board.cardInfo." + b.getId()));
			});
			b.setOnMouseExited((Event) -> {
				cardInfo.setText("");
			});
			bindingsForContent(b, hBoxHand, 1, 0.16);
			handCards.add(b);
		}
		for (int i = 0; i < handFromServer.size(); i++) {
			handCards.get(i).setId(handFromServer.get(i));
		}
		changePhaseLabel();
		setCards();
		for (int i = 0; i < handFromServer.size(); i++) {
			this.hBoxHand.getChildren().add(handCards.get(i));
		}
		handFromServer.clear();
		ClientHandler.tempHandCard.clear();
	}

	/**
	 * disable and enable cards that can be used in a specific phase
	 * 
	 * @author Sojo Nagaroor
	 * @author Patrick Ziörjen
	 */
	public void setCards() {
		if (ClientHandler.phase.equals("buy")) {
			setCardsOnViewEnable();
			for (Button b : handCards) {
				if (b.getId().equals("copper") || b.getId().equals("silver") || b.getId().equals("gold")) {
					b.setDisable(false);
				} else {
					b.setDisable(true);

				}

			}
		}
		if (ClientHandler.phase.equals("action")) {
			setCardsOnViewDisable();
			for (Button b : handCards) {

				if (b.getId().equals("smith") || b.getId().equals("market") || b.getId().equals("laboratory")
						|| b.getId().equals("funfair") || b.getId().equals("woodcutter")
						|| b.getId().equals("village")) {
					b.setDisable(false);
				} else {
					b.setDisable(true);
				}

			}
		}

	}

	/**
	 * enables buttons while player is able to chose which card he wants to buy
	 * 
	 * @author Patrick Ziörjen
	 */
	public void setCardsOnViewEnable() {
		for (StackPane sP : cardCountStack) {
			sP.setDisable(false);
		}
	}

	/**
	 * disables buttons while player is in action phase
	 * 
	 * @author Patrick Ziörjen
	 */
	public void setCardsOnViewDisable() {
		for (StackPane sP : cardCountStack) {
			sP.setDisable(true);
		}
	}

	public void setEmptyHand() {
		hBoxHand.getChildren().clear();
	}

	/**
	 * Bind the child to parent so the child resizes analog to the parent node
	 * 
	 * @author Patrick Ziörjen
	 * 
	 * @param child
	 *            get the child node which should be binded
	 * @param stage2
	 *            get parent node to bind child to it
	 * @param heightMultiply
	 *            get factor to multiply the height of the child-node with the
	 *            parents height
	 * @param widthMultiply
	 *            get factor to multiply the width of the child-node with the
	 *            parents width
	 */
	protected void bindingsForContent(Region child, Region parent, double heightMultiply, double widthMultiply) {
		child.maxHeightProperty().bind(parent.heightProperty().multiply(heightMultiply));
		child.maxWidthProperty().bind(parent.widthProperty().multiply(widthMultiply));
		child.minHeightProperty().bind(parent.heightProperty().multiply(heightMultiply));
		child.minWidthProperty().bind(parent.widthProperty().multiply(widthMultiply));
	}

	/**
	 * Bind the child to parent so the content inside the window resizes analog to
	 * the window size
	 * 
	 * @author Patrick Ziörjen
	 * @param child
	 *            get the child node which should be binded
	 * @param stage2
	 *            get parent node to bind child to it
	 * @param heightMultiply
	 *            get factor to multiply the height of the child-node with the
	 *            parents height
	 * @param widthMultiply
	 *            get factor to multiply the width of the child-node with the
	 *            parents width
	 */
	protected void setStageBindings(Region child, Stage stage2, double heightMultiply, double widthMultiply) {
		child.maxHeightProperty().bind(stage2.heightProperty().multiply(heightMultiply));
		child.maxWidthProperty().bind(stage2.widthProperty().multiply(widthMultiply));
		child.minHeightProperty().bind(stage2.heightProperty().multiply(heightMultiply));
		child.minWidthProperty().bind(stage2.widthProperty().multiply(widthMultiply));
	}

	/**
	 * block the players window if it is not his turn
	 * 
	 * @author Sojo Nagaroor
	 */
	public void blockWindow() {
		hCenter1.setDisable(true);
		hCenter2.setDisable(true);
		hCenter3.setDisable(true);
		hBottom.setDisable(true);
		endPhase.setDisable(true);
		aBMpoints.setDisable(true);
		playerStats.setDisable(true);
	}

	/**
	 * enable the window for player if it is his turn
	 * 
	 * @author Sojo Nagaroor
	 */
	public void enableWindow() {
		hCenter1.setDisable(false);
		hCenter2.setDisable(false);
		hCenter3.setDisable(false);
		hBottom.setDisable(false);
		endPhase.setDisable(false);
		aBMpoints.setDisable(false);
		playerStats.setDisable(false);
	}

	/**
	 * check which cardpile is empty. if a cardpile is empty turn the card back -->
	 * backside
	 * 
	 * @author Sojo Nagaroor
	 * @author Yujia Shi
	 * @param message
	 *            - cardName
	 */
	public void turnCardBack(String message) {
		String cardName = message;
		switch (cardName) {
		case "village":
			this.village.setId("back");
			this.village.setDisable(true);
			break;
		case "woodcutter":
			this.woodcutter.setId("back");
			this.woodcutter.setDisable(true);
			break;
		case "funfair":
			this.funfair.setId("back");
			this.funfair.setDisable(true);
			break;
		case "laboratory":
			this.laboratory.setId("back");
			this.laboratory.setDisable(true);
			break;
		case "market":
			this.market.setId("back");
			this.market.setDisable(true);
			break;
		case "smith":
			this.smith.setId("back");
			this.smith.setDisable(true);
			break;
		case "copper":
			this.copper.setId("back");
			this.copper.setDisable(true);
			break;
		case "silver":
			this.silver.setId("back");
			this.silver.setDisable(true);
			break;
		case "gold":
			this.gold.setId("back");
			this.gold.setDisable(true);
			break;
		case "estate":
			this.estate.setId("back");
			this.estate.setDisable(true);
			break;
		case "duchy":
			this.duchy.setId("back");
			this.duchy.setDisable(true);
			break;
		case "province":
			this.province.setId("back");
			this.province.setDisable(true);
			break;
		}
	}
}
