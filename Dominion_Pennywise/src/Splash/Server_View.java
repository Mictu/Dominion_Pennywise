package Splash;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Shows the ServerView in which the intern IP is shown. This IP can be used on
 * the client - side for a connection to the server in a local network.
 * 
 * @author Patrick Ziörjen
 */
public class Server_View {

	private Scene scene;
	private StackPane root;
	private Stage stage;

	private VBox vB;
	public TextArea txtClientArea = new TextArea();
	public Button withServer = new Button("Start Server");

	protected S_View splashScreen;

	/**
	 * Design of the server-view
	 * 
	 * @param stage
	 *            gets the primaryStage from the server class
	 */
	public Server_View(Stage stage) {
		this.stage = stage;
		stage.setTitle("Dominion");
		stage.setResizable(false);

		root = new StackPane();
		root.setId("root2");
		root.setPadding(new Insets(40, 40, 40, 40));
		vB = new VBox(40);
		vB.setAlignment(Pos.CENTER);

		vB.getChildren().addAll(withServer, txtClientArea);
		root.getChildren().add(vB);

		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("SplashScreen.css").toExternalForm());

		stage.setScene(scene);
	}

	/**
	 * Opens the stage of the server-view and shows the window
	 */
	public void start() {
		stage.show();
	}

	/**
	 * Updates the text-area on the server-view. Shows when a client connects to
	 * the server and the intern IP-adress.
	 * 
	 * @param sString
	 *            newest message from server
	 * @param string
	 *            the current information which is shown on the text-area
	 * @author Michael Tu
	 */
	public void updateServerView(SimpleStringProperty sString, String string) {
		Platform.runLater(() -> {
			sString.set(string);
		});
	}
}
