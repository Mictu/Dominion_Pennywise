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
import server.client.Client_Chat;
import server.client.Server;
import view.Lobby_View;

public class Server_View {

	protected boolean startServer;
	protected Stage stage;
	Server server;
	Lobby_View lobby_view;
	public TextArea txtClientArea = new TextArea();
	public Button withServer = new Button("Start Server");

	protected S_View splashScreen;

	public Server_View(Stage stage) {
		this.stage = stage;
		// Server dringend schliessen wenn man einem anderen Spiel beitritt
		// stage = new Stage();
		startServer = false;

		StackPane root = new StackPane();
		root.setId("root2");
		root.setPadding(new Insets(40, 40, 40, 40));
		VBox vB = new VBox(40);
		vB.setAlignment(Pos.CENTER);

		// Button withoutServer = new Button("Client");

		vB.getChildren().addAll(withServer, txtClientArea);
		root.getChildren().add(vB);

		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("SplashScreen.css").toExternalForm());

		stage.setScene(scene);

		// stage.setTitle("Dominion");
		// stage.setResizable(false);
		// stage.setScene(scene);
		// stage.show();

		// withServer.setOnAction(event -> {
		// startServer = true;
		// withServer.setDisable(true);
		// server = new Server();
		// server.connect(server_View);
		// });

		// withoutServer.setOnAction((event) -> {
		// startServer = false;
		// this.stage.hide();
		// startSplash(primaryStage);
		// });

	}

	public void start() {
		stage.setTitle("Dominion");
		stage.setResizable(false);
		stage.show();
	}

	public Boolean getBoolean() {
		return this.startServer;
	}

	public void updateServerView(SimpleStringProperty sString,String string) {

		Platform.runLater(() -> {
			sString.set(string);
		});

	}
	
	public void updateClients() {
		StringBuffer sb = new StringBuffer();
		for (Client_Chat c : server.clients) {
			sb.append(c.toString());
			sb.append("\n");
		}
		Platform.runLater(() -> {
			txtClientArea.setText(sb.toString());
		});
		
	}

	// public void startSplash(Stage primaryStage) {
	// try {
	// splashScreen = new S_View();
	// splashScreen.run(primaryStage, startServer);
	// } catch (Exception e) {
	// System.err.println(e);
	// }
	// }
	//
}
