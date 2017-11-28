package Splash;

import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class S_View {
	Runnable initializer = null;
	
	protected ProgressBar progress;
    protected Label lblText;
    
    protected Stage stage;
    
    protected double percent;

    public S_View() {
    	percent = 0.00;
    	
    	stage = new Stage();
    	stage.setTitle("Dominion");
    	stage.setResizable(false);
    	
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
    
	public void run() {
		stage.show();

		// Timer timer = new Timer();
		// TimerTask task = new TimerTask() {
		// public void run() {
		// percent += 0.01;
		// progress.setProgress(percent);
		//
		// if (percent == 1) {
		// timer.cancel();
		// timer.purge();
		// stage.hide();
		//
		// }
		// }
		// };
		// timer.schedule(task, 0, 50);
		// }
		
		initialize();

//		showIt();
		
	}
	
	protected Task<Void> setInitializer() {
		Task<Void> initializer = new Task<Void>() {
			protected Void call() throws Exception {

				int i = 0;
				for (; i < 100000000; i++) {
					if ((i % 100000) == 0)
						this.updateProgress(i, 100000000);
				}

				return null;
			}
		};
		return initializer;
	}

	
	public void initialize() {
		new Thread(setInitializer()).start();
		
		try {
		this.progress.progressProperty().bind(((ProgressIndicator) this.initializer).progressProperty());
		} catch (Exception e) {
			System.out.println(e);
		}
		
		setInitializer().stateProperty().addListener(
				(observable, oldValue, newValue) -> {
                if (newValue == Worker.State.SUCCEEDED)
                    System.out.println("finish");
				});
	}
	
	
    // change label text every 1-3 seconds? "gemischelt, geladen, rasen wird gemäht"
    
}
