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
import main_Class.ServiceLocator;
import server_Models.Translator;
import view.Login_View;

/**
 * Create a splash - screen to show that the game is loading. The screen shows
 * up before the login-view.
 * 
 * @author Richards Brad
 * @author Patrick Ziörjen (handled code to fit in project)
 */
public class S_View {

	private Scene scene;
	private Stage stage;
	private StackPane root;

	private VBox vB;
	private ProgressBar progress;
	private Label lblText;
	private ServiceLocator sl = ServiceLocator.getServiceLocator();
	private Translator t = sl.getTranslator();

	private Login_View loginView;
	private Login_Controller loginController;

	/**
	 * Design of the splash-screen, connected to a .css-document
	 */
	public S_View() {
		stage = new Stage();
		stage.setTitle("Dominion");
		stage.setResizable(false);
		stage.initStyle(StageStyle.TRANSPARENT);

		lblText = new Label(t.getString("dominion.splash.mixing"));
		lblText.setId("ownLable");

		progress = new ProgressBar();

		vB = new VBox(280);
		vB.setAlignment(Pos.CENTER);
		vB.getChildren().add(lblText);
		vB.getChildren().add(progress);

		root = new StackPane();
		root.setPadding(new Insets(10, 10, 10, 10));
		root.getChildren().add(vB);

		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("SplashScreen.css").toExternalForm());
		stage.setScene(scene);
	}

	/**
	 * Method gets called from main-class to open the the splash-screen. It
	 * handles the primaryStage over to runnable() an starts the initializer
	 * method to update the progressBar.
	 * 
	 * @param primaryStage
	 *            - Get the primaryStage to show the splash-screen
	 */
	public void run(Stage primaryStage) {
		stage.show();
		runnable(primaryStage);
		initialize();
	}

	/**
	 * Updates the progressBar over a for - loop
	 */
	Task<Void> initializer = new Task<Void>() {
		protected Void call() throws Exception {
			Integer i = 0;
			for (; i < 100000000; i++) {
				if ((i % 20) == 0) // Set LoadSpeed
					this.updateProgress(i, 100000000);
			}
			return null;
		}
	};

	/**
	 * Starts a new Thread to run the initializer Task.
	 */
	public void initialize() {
		new Thread(initializer).start();
	}

	/**
	 * Binds the progressBar to the initializer and waits until the loop has
	 * ended (ActionListener). After the initializer task has ended, the
	 * LoginView and LoginController are getting initialized and started.
	 * 
	 * @param primaryStage
	 *            Handle the primaryStage over to the LoginView
	 */
	public void runnable(Stage primaryStage) {
		try {
			this.progress.progressProperty().bind(initializer.progressProperty());
		} catch (Exception e) {
			System.out.println(e);
		}
		initializer.stateProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == Worker.State.SUCCEEDED) {
				loginView = new Login_View(primaryStage);
				loginController = new Login_Controller(loginView);
				loginView.start();
				this.stage.hide();
			}
		});
	}

}
