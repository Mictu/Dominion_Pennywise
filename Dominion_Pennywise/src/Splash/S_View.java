package Splash;

import controllers.Login_Controller;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import server.client.Server;
import view.Login_View;

public class S_View {
	
	protected Server server;
	protected ProgressBar progress;
    protected Label lblText;
    
    protected Stage stage;
    
    protected boolean startTheServer;
    protected double percent;

    public S_View() {
    	percent = 0.00;
    	
    	stage = new Stage();
    	stage.setTitle("Dominion");
    	stage.setResizable(false);
    	stage.initStyle(StageStyle.TRANSPARENT);
    	
    	StackPane root = new StackPane();
    	root.setPadding(new Insets(10,10,10,10));
    	
    	VBox vB = new VBox(280);
    	vB.setAlignment(Pos.CENTER);
    	
    	lblText = new Label("Karten werden gemischt");
    	lblText.setId("ownLable");
    	vB.getChildren().add(lblText);
    	
    	progress = new ProgressBar();
    	vB.getChildren().add(progress);
    			
    			
    	root.getChildren().add(vB);
    	
    	Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("SplashScreen.css").toExternalForm());
		stage.setScene(scene);
    }
    
	public void run(Stage primaryStage/*, boolean server*/) {
		stage.show();
//		this.startTheServer = server;
		
		runnable(primaryStage);
		initialize();
	}
	
	Task<Void> loadServer = new Task<Void>() {
		protected Void call() throws Exception {
			try {
				server = new Server();
				server.connect();
			} catch (Exception e) {
				System.out.println("Failed Server-Connection");
			}
			return null;
		}
	};
	
		Task<Void> initializer = new Task<Void>() {
			protected Void call() throws Exception {
				Integer i = 0;
//				for (; i < 1000000000; i++) {
//					if ((i % 20) == 0)										// Set LoadSpeed
//						this.updateProgress(i, 1000000000);
//				}
				for (; i < 100000000; i++) {
					if ((i % 20) == 0)										// Splash screen loading a lot faster in here
						this.updateProgress(i, 100000000);
				}
				return null;
			}
		};

	
	public void initialize() {
		new Thread(initializer).start();
		
		if (startTheServer) {
			new Thread(loadServer).start();
		}
	}
	
	public void runnable(Stage primaryStage) {
		try {
			this.progress.progressProperty().bind(initializer.progressProperty());
		} catch (Exception e) {
			System.out.println(e);
		}

		initializer.stateProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == Worker.State.SUCCEEDED) {
				Login_View loginView = new Login_View(primaryStage);
				Login_Controller loginController = new Login_Controller(loginView);
				loginView.start();
				this.stage.hide();
			}
		});
	}

	
    // change label text every 1-3 seconds? "gemischelt, geladen, rasen wird gemäht"
    
}
