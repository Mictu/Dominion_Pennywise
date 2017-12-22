package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Creates the result-view and adds the players in the right order to the view
 * 
 * @author Sojo Nagaroor
 *
 */
public class Result_View {

	private Scene scene;
	public Stage stage;
	private BorderPane mainPane;

	private VBox vbox;
	private HBox hbox;
	private Label namelbl;
	private Label ranglbl;

	/**
	 * Design of the resultView
	 * 
	 * @param stage
	 *            new stage created in the client-handler class
	 */
	public Result_View(Stage stage) {
		this.stage = stage;

		stage.setHeight(600.0);
		stage.setWidth(400.0);
		stage.setTitle("WINNER");
		stage.setFullScreen(true);
		stage.setFullScreenExitHint("");
		mainPane = new BorderPane();
		mainPane.setId("mainPaneResult");

		vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setId("resultvbox");

		mainPane.setCenter(vbox);

		scene = new Scene(mainPane);
		scene.getStylesheets().add(getClass().getResource("Result.css").toExternalForm());
		stage.setScene(scene);
	}

	/**
	 * Opens the result-view (called in client handler)
	 */
	public void start() {
		stage.show();
	}

	/**
	 * Creates for every player an HBox. The text is set to a label (name /
	 * points) and added to the HBox. Also changes the font-size for place 2 - 4
	 * so the winners text will be shown bigger.
	 * 
	 * @param playerliste
	 *            Array with the players from first to last place
	 */
	public void setRangList(String[] playerliste) {
		for (int i = 0; i < playerliste.length; i++) {
			hbox = new HBox();
			hbox.setAlignment(Pos.CENTER);
			hbox.setSpacing(50);

			ranglbl = new Label("Platz: " + (i + 1));
			ranglbl.setId("ranglbl" + (i + 1));
			if (i >= 2) {
				ranglbl.setStyle("-fx-text-fill: white;" + "-fx-font-weight: bold;" + "-fx-font-size: 20; ");
			}
			namelbl = new Label();
			namelbl.setText(playerliste[i]);
			namelbl.setId("namelbl" + (i + 1));
			if (i >= 2) {
				namelbl.setStyle("-fx-text-fill: white;" + "-fx-font-weight: bold;" + "-fx-font-size: 20; ");
			}
			hbox.getChildren().addAll(ranglbl, namelbl);
			vbox.getChildren().add(hbox);
		}
	}

}
