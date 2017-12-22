package view;

import controllers.Board_Controller;
import controllers.Button_Sounds;
import controllers.Login_Controller;
import javafx.scene.control.Button;
import main_Class.ServiceLocator;
import server_Models.Translator;

/**
 * Class to create the cards for the game (Except for the handcards, because
 * they are more dynamic). Also call a setOnAction so the cards can send their
 * ID to the server and a sound will be played. Additionally if the player
 * hovers over a card, it will show the skills and cost of it in the boardView.
 * 
 * @author Patrick Ziörjen
 */
public class CardDesign_View {

	private Button copperBtn, duchyBtn, estateBtn, funfairBtn, goldBtn, laboratoryBtn;
	private Button marketBtn, provinceBtn, silverBtn, smithBtn, villageBtn, woodcutterBtn;
	private ServiceLocator sl = ServiceLocator.getServiceLocator();
	private Translator t = sl.getTranslator();

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

	/**
	 * SetOnAction / MouseEntered / MouseExited for every created button
	 * 
	 * @param x
	 *            current button which was created
	 */
	protected void setForAction(Button x) {
		x.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		x.setOnAction((event) -> {
			Button_Sounds.playCardSound();
			String cardID = x.getId();
			Login_Controller.client.sendToServer(cardID);
		});
		x.setOnMouseEntered((Event) -> {
			Board_Controller.showCard(getCardInfo(x.getId()));
		});
		x.setOnMouseExited((Event) -> {
			Board_Controller.deleteCardInfo();
		});
	}

	/**
	 * Get the text for every card if it gets hovered (all languages). The text
	 * shows the card costs and 'skills'.
	 * 
	 * @param card
	 *            get the text of the clicked card (ID).
	 * @return String with the card-Info.
	 */
	public String getCardInfo(String card) {
		String info = t.getString("dominion.board.cardInfo." + card);
		return info;
	}

}