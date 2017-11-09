package Views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class CardDesign_View {

	// private Button cardBtn;
	protected Button copperBtn, duchyBtn, estateBtn, funfairBtn, goldBtn, laboratoryBtn;
	protected Button marketBtn, provinceBtn, silverBtn, smithBtn, villageBtn, woodcutterBtn;

	public void cardDesign_View(Button btn) {
		
		copperBtn = new Button();
		copperBtn.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		copperBtn.setId("copper");

		duchyBtn = new Button();
		duchyBtn.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		duchyBtn.setId("duchy");

		estateBtn = new Button();
		estateBtn.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		estateBtn.setId("estate");
		
		funfairBtn = new Button();
		funfairBtn.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		funfairBtn.setId("funfair");
		
		goldBtn = new Button();
		goldBtn.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		goldBtn.setId("gold");
		
		laboratoryBtn = new Button();
		laboratoryBtn.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		laboratoryBtn.setId("laboratory");
		
		marketBtn = new Button();
		marketBtn.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		marketBtn.setId("market");
		
		provinceBtn = new Button();
		provinceBtn.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		provinceBtn.setId("province");
		
		silverBtn = new Button();
		silverBtn.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		silverBtn.setId("silver");
		
		smithBtn = new Button();
		smithBtn.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		smithBtn.setId("smith");
		
		villageBtn = new Button();
		villageBtn.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		villageBtn.setId("village");
		
		woodcutterBtn = new Button();
		woodcutterBtn.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		woodcutterBtn.setId("woodcutter");
		
		
//		Image image = new Image(getClass().getResource("/Card_Images/Dorf.jpg").toExternalForm(),300,300,true,true);
//		ImageView view = new ImageView(image);
//
//		cardBtn.setGraphic(view);
		
		

		villageBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("card clicked");
			}
		});

		
	}

}
