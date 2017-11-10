package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class CardDesign_View {

	// private Button cardBtn;
	protected Button copperBtn, duchyBtn, estateBtn, funfairBtn, goldBtn, laboratoryBtn;
	protected Button marketBtn, provinceBtn, silverBtn, smithBtn, villageBtn, woodcutterBtn;

	public CardDesign_View() {
		
//		Image image = new Image(getClass().getResource("/Card_Images/Dorf.jpg").toExternalForm(),300,300,true,true);
//		ImageView view = new ImageView(image);
//
//		cardBtn.setGraphic(view);
		
		
		
//		villageBtn.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent e) {
//				System.out.println("card clicked");
//			}
//		});
		
		

	} // close constructor

	
	// Getters for every Button (Card)
	protected Button getCopperBtn() {
		copperBtn = new Button();
		copperBtn.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		copperBtn.setId("copper");
		setForAction(copperBtn);
		return copperBtn;
	}
	protected Button getDuchyBtn() {
		duchyBtn = new Button();
		duchyBtn.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		duchyBtn.setId("duchy");
		setForAction(duchyBtn);
		return duchyBtn;
	}
	protected Button getEstateBtn() {
		estateBtn = new Button();
		estateBtn.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		estateBtn.setId("estate");
		setForAction(estateBtn);
		return estateBtn;
	}
	protected Button getFunfairBtn() {
		funfairBtn = new Button();
		funfairBtn.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		funfairBtn.setId("funfair");
		setForAction(funfairBtn);
		return funfairBtn;
	}
	protected Button getGoldBtn() {
		goldBtn = new Button();
		goldBtn.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		goldBtn.setId("gold");
		setForAction(goldBtn);
		return goldBtn;
	}
	protected Button getLaboratoryBtn() {
		laboratoryBtn = new Button();
		laboratoryBtn.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		laboratoryBtn.setId("laboratory");
		setForAction(laboratoryBtn);
		return laboratoryBtn;
	}
	protected Button getMarketBtn() {
		marketBtn = new Button();
		marketBtn.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		marketBtn.setId("market");
		setForAction(marketBtn);
		return marketBtn;
	}
	protected Button getProvinceBtn() {
		provinceBtn = new Button();
		provinceBtn.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		provinceBtn.setId("province");
		setForAction(provinceBtn);
		return provinceBtn;
	}
	protected Button getSilverBtn() {
		silverBtn = new Button();
		silverBtn.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		silverBtn.setId("silver");
		setForAction(silverBtn);
		return silverBtn;
	}
	protected Button getSmithBtn() {
		smithBtn = new Button();
		smithBtn.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		smithBtn.setId("smith");
		setForAction(smithBtn);
		return smithBtn;
	}
	protected Button getVillageBtn() {
		villageBtn = new Button();
		villageBtn.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		villageBtn.setId("village");
		setForAction(villageBtn);
		return villageBtn;
	}
	protected Button getWoodcutterBtn() {
		woodcutterBtn = new Button();
		woodcutterBtn.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		woodcutterBtn.setId("woodcutter");
		setForAction(woodcutterBtn);
		return woodcutterBtn;
	}
	
	// Activate every created Button (Card)
	protected void setForAction (Button x) {
		x.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println(x.getId());
			}
		});
	}
	
	
	
	
} // close class



//Written by Michael and Patrick
