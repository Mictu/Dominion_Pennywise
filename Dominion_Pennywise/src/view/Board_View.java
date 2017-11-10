package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Board_View extends Application {

//	protected Stage stage;
//	private Game model;
	
	public static void main (String[] args) {
		launch(args);
	}

	public void start(Stage stage) {
		CardDesign_View cdV = new CardDesign_View();
		
	// Initialize the GUI Content here
		
		
	// constructor
//	protected Board_View(Stage s, Game m) {
//		this.stage = s;
//		this.model = m;

		// Set up the GUI in here
		stage.setTitle("Dominion");
//		stage.setResizable(false);
		stage.setFullScreen(true);
		BorderPane root = new BorderPane();
		root.setId("boardRoot");
		root.setPadding(new Insets(10,10,10,10));
		
		
		
		//Add an Icon for all the Windows
//		try {
//		stage.getIcons().add(new Image(Board_View.class.getResourceAsStream("gold.jpg")));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
	//SET TOP
	//SET CENTER
		// use V- and H- Boxes to add the Cards (Buttons)
		VBox vCenter = new VBox(8);
		vCenter.setAlignment(Pos.CENTER);
		HBox hCenter1 = new HBox(20); //add Victory cards
		HBox hCenter2 = new HBox(20); //add Kingdom-/ Action card
		HBox hCenter3 = new HBox(20); //add Treasure cards
		
		vCenter.setPadding(new Insets(0,10,0,0));
		hCenter1.setId("boxes");
		hCenter2.setId("boxes");
		hCenter3.setId("boxes");
		

		try {
			hCenter1.getChildren().addAll(cdV.getEstateBtn(),cdV.getDuchyBtn(),cdV.getProvinceBtn());
			
			hCenter2.getChildren().addAll(cdV.getFunfairBtn(), cdV.getLaboratoryBtn(), cdV.getMarketBtn(), 
									cdV.getSmithBtn(), cdV.getVillageBtn(), cdV.getWoodcutterBtn());
		
			hCenter3.getChildren().addAll(cdV.getCopperBtn(), cdV.getSilverBtn(), cdV.getGoldBtn());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		HBox labels = new HBox(20) ;
			Label firstPhase = new Label("Phase 1");
			firstPhase.setId("phaseLabels");
			Label secondPhase = new Label("Phase 2");
			secondPhase.setId("phaseLabels");
			Label thirdPhase = new Label("Phase 3");
			thirdPhase.setId("phaseLabels");
			
		labels.getChildren().addAll(firstPhase,secondPhase,thirdPhase);
		
		
	//CENTER BOTTOM	
		
		StackPane stackPane = new StackPane();
		stackPane.setPadding(new Insets(0,10,0,0));
		
		Label hand = new Label("Spielerhand");
		hand.setId("handLabel");

		HBox hBoxHand = new HBox(20);
		hBoxHand.getChildren().addAll(cdV.getFunfairBtn(),cdV.getFunfairBtn(),cdV.getFunfairBtn(),cdV.getFunfairBtn(),cdV.getFunfairBtn());
		hBoxHand.setAlignment(Pos.CENTER);
		
		HBox hBottom = new HBox(70);
		hBottom.getChildren().addAll(cdV.getCopperBtn(),hBoxHand,cdV.getDuchyBtn());
		hBottom.setAlignment(Pos.CENTER);
		
		stackPane.getChildren().addAll(hand,hBottom);
		root.setBottom(stackPane);
		
		
		
		vCenter.getChildren().addAll(hCenter1, hCenter2, hCenter3, labels, stackPane);
		
		TextArea playerStats = new TextArea();
		playerStats.setEditable(false);
		playerStats.setMaxHeight(100);
		playerStats.setMaxWidth(200);
		playerStats.setId("playerStats");
		
		
		StackPane centerSP = new StackPane();
		centerSP.getChildren().addAll(playerStats, vCenter);
		StackPane.setAlignment(playerStats, Pos.TOP_RIGHT);
		
		root.setCenter(centerSP);
		
		
	//SET RIGHT
		VBox vRight = new VBox (10);
		
		HBox chatInput = new HBox(5);
		TextField chatText = new TextField();
		Button send = new Button();
		send.setId("sendButton");
		send.setText("Senden");
		
		chatInput.getChildren().addAll(chatText, send);
		chatInput.setAlignment(Pos.CENTER);
		HBox.setHgrow(chatText, Priority.ALWAYS);
		
		TextArea logger = new TextArea();
		logger.setEditable(false);
		TextArea chat = new TextArea();
		chat.setEditable(false);
		
		Region reg = new Region();
		reg.setPrefHeight(20);
		
		vRight.getChildren().addAll(logger, reg, chat, chatInput);
		
		root.setRight(vRight);
		
	//SET BOTTOM
		
		
		
		
		
		// SET SCENE
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		stage.setScene(scene);
		
		stage.show();

	} // Close the Constructor

	
}// close class

// Written by Patrick