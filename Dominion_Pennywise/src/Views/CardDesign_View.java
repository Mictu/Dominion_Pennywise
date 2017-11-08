package Views;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CardDesign_View extends Application {

	// private Button cardBtn;
	private Stage stage;
	private Button cardBtn;

	public void cardDesign_View(Button btn) {

	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(new Group());
		stage.setTitle("Button Sample");
		cardBtn = new Button();
		

		BorderPane bpane = new BorderPane();

		Image image = new Image(getClass().getResource("/Card_Images/Dorf.jpg").toExternalForm(),300,300,true,true);
		ImageView view = new ImageView(image);

		cardBtn.setGraphic(view);

		cardBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("card clicked");
			}
		});

		bpane.setCenter(cardBtn);
		((Group) scene.getRoot()).getChildren().add(bpane);
		scene.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
		
	}

}
