package view;

import javafx.scene.control.Button;
import server.client.Client;

public class CardDesign_View {

	Client client;
	// private Button cardBtn;
	protected Button copperBtn, duchyBtn, estateBtn, funfairBtn, goldBtn, laboratoryBtn;
	protected Button marketBtn, provinceBtn, silverBtn, smithBtn, villageBtn, woodcutterBtn;

	public CardDesign_View(Client client) {
		this.client = client;
		client.run();

	} // close constructor
	
	// Getters for every Button (Card)
	public Button getCopperBtn() {
		copperBtn = new Button();
		copperBtn.setId("copper");
		setForAction(copperBtn);
		return copperBtn;
	}
	public Button getDuchyBtn() {
		duchyBtn = new Button();
		duchyBtn.setId("duchy");
		setForAction(duchyBtn);
		return duchyBtn;
	}
	public Button getEstateBtn() {
		estateBtn = new Button();
		estateBtn.setId("estate");
		setForAction(estateBtn);
		return estateBtn;
	}
	public Button getFunfairBtn() {
		funfairBtn = new Button();
		funfairBtn.setId("funfair");
		setForAction(funfairBtn);
		return funfairBtn;
	}
	public Button getGoldBtn() {
		goldBtn = new Button();
		goldBtn.setId("gold");
		setForAction(goldBtn);
		return goldBtn;
	}
	public Button getLaboratoryBtn() {
		laboratoryBtn = new Button();
		laboratoryBtn.setId("laboratory");
		setForAction(laboratoryBtn);
		return laboratoryBtn;
	}
	public Button getMarketBtn() {
		marketBtn = new Button();
		marketBtn.setId("market");
		setForAction(marketBtn);
		return marketBtn;
	}
	public Button getProvinceBtn() {
		provinceBtn = new Button();
		provinceBtn.setId("province");
		setForAction(provinceBtn);
		return provinceBtn;
	}
	public Button getSilverBtn() {
		silverBtn = new Button();
		silverBtn.setId("silver");
		setForAction(silverBtn);
		return silverBtn;
	}
	public Button getSmithBtn() {
		smithBtn = new Button();
		smithBtn.setId("smith");
		setForAction(smithBtn);
		return smithBtn;
	}
	public Button getVillageBtn() {
		villageBtn = new Button();
		villageBtn.setId("village");
		setForAction(villageBtn);
		return villageBtn;
	}
	public Button getWoodcutterBtn() {
		woodcutterBtn = new Button();
		woodcutterBtn.setId("woodcutter");
		setForAction(woodcutterBtn);
		return woodcutterBtn;
	}
	
	// Activate every created Button (Card) and send string to server handler
	protected void setForAction (Button x) {
		x.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		x.setOnAction((event) -> {
			try {
//				client.run();
				client.sendToServer(x.getId());
			} catch (Exception e) {
				System.out.println(e);
			}
		});
	}
	
	
	
	
} // close class



//Written by Michael and Patrick
