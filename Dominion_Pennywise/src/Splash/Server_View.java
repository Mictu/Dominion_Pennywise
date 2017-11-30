package Splash;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Server_View {

	protected boolean startServer;
	protected Stage stage;
	
	protected S_View splashScreen;
	
	public Server_View(Stage primaryStage) {
		
		// Server dringend schliessen wenn man einem anderen Spiel beitritt
		stage = new Stage();
		startServer = false;
		
		StackPane root = new StackPane();
		root.setId("root2");
		root.setPadding(new Insets(40, 40, 40, 40));
		HBox hB = new HBox(40);
		hB.setAlignment(Pos.CENTER);
		
		Button withServer = new Button("Server");
		Button withoutServer = new Button("Client");
		
		hB.getChildren().addAll(withServer, withoutServer);
		root.getChildren().add(hB);
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("SplashScreen.css").toExternalForm());
		
		stage.setTitle("Dominion");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
		
		withServer.setOnAction((event) -> { 
			startServer = true;
			this.stage.hide();
			startSplash(primaryStage);
		});
		
		withoutServer.setOnAction((event) -> {
			startServer = false;
			this.stage.hide();
			startSplash(primaryStage);
		});
	
	}
	
	public Boolean getBoolean() {
		return this.startServer;
	}
	
	public void startSplash(Stage primaryStage) {
		try {
			splashScreen = new S_View();
			splashScreen.run(primaryStage, startServer);
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
}
