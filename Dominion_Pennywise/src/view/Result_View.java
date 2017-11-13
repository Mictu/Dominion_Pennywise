package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Result_View {

	BorderPane mainPane;
	Stage stage;

	Label congratulation;
	Label youwon;

	VBox vbox = new VBox();

	public Result_View(Stage stage) {
		this.stage = stage;
		stage.setHeight(600.0);
		stage.setWidth(400.0);
		stage.setResizable(false);
		stage.setTitle("WINNER");
		mainPane = new BorderPane();
		mainPane.setId("mainPaneResult");

		congratulation = new Label("CONGRATULATION");
		congratulation.setId("lblresult");
		youwon = new Label("You Won!!!!");
		youwon.setId("lblresult");
		vbox.getChildren().addAll(congratulation, youwon);
		vbox.setAlignment(Pos.CENTER);

		mainPane.setCenter(vbox);

		Scene scene = new Scene(mainPane);
		// scene.getStylesheets().add(getClass().getResource("Result.css").toExternalForm());
		stage.setScene(scene);

	}

	public void start() {
		stage.show();
	}
	
	public void stop () {
		stage.hide();
	}
	
}
