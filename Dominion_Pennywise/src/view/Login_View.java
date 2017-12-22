package view;

import java.util.Locale;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main_Class.ServiceLocator;
import server_Models.Translator;

/**
 * Opens the Login window to choose language and name.
 * 
 * @author Sojo Nagaroor
 * @author Michael Tu
 * @author Richard Bradley
 * @author Patrick Ziörjen
 */
public class Login_View {

	private BorderPane mainPane;
	private Stage stage;
	private Scene scene;

	private VBox topvbox = new VBox(170);
	private VBox vbox = new VBox(40); // for the center
	private HBox hbox1 = new HBox(); // for name and textfield
	private VBox vbox2 = new VBox(10); // for language and dropdown

	public TextField nameTxtfield;
	public Button lobbyBtn, exitBtn;
	public Label warning;
	private Label welcomeLbl, nameLbl, languageLbl;
	private Region reg;
	private Region reg2;

	private ComboBox<String> boxLanguage = new ComboBox<String>();

	private ServiceLocator sl = ServiceLocator.getServiceLocator();
	private Translator t = sl.getTranslator();

	/**
	 * Design of the login view
	 * 
	 * @param stage
	 *            primaryStage from splash view (S_View)
	 * @author Sojo Nagaroor
	 */
	public Login_View(Stage stage) {
		this.stage = stage;
		stage.setTitle("Dominion Login");
		stage.setFullScreen(true);
		stage.setFullScreenExitHint("");

		// BorderPane as Grundgerüst
		mainPane = new BorderPane();
		mainPane.setId("mainPane");
		BorderPane.setAlignment(topvbox, Pos.CENTER);
		mainPane.setCenter(vbox);

		vbox.setAlignment(Pos.CENTER);
		hbox1.setAlignment(Pos.CENTER);
		vbox2.setAlignment(Pos.CENTER);

		// Set the combo box text to choose a language and save the chosen
		// language for further use
		// copied from Richards Brad and modified by Michael Tu
		for (Locale locale : sl.getLocales()) {
			boxLanguage.getItems().add(locale.getLanguage());
			boxLanguage.setOnAction(event -> {
				sl.getConfiguration().setLocalOption("Language", boxLanguage.getSelectionModel().getSelectedItem());
				sl.setTranslator(new Translator(boxLanguage.getSelectionModel().getSelectedItem()));
				updateTexts();
			});
		}

		// BORDER PANE TOP
		// create welcome label and add to the topbox (VBox in the center)
		welcomeLbl = new Label(t.getString("dominion.login.welcome"));
		welcomeLbl.setId("welcomeLbl");
		welcomeLbl.setAlignment(Pos.CENTER);

		topvbox.getChildren().add(welcomeLbl);
		topvbox.setAlignment(Pos.CENTER);
		mainPane.setTop(topvbox);

		// BORDERPANE CENTER
		nameLbl = new Label(t.getString("dominion.login.name") + ": ");
		nameLbl.setId("lblText");
		languageLbl = new Label(t.getString("dominion.login.language") + ": ");
		languageLbl.setId("lblText");
		languageLbl.setAlignment(Pos.CENTER);
		nameTxtfield = new TextField();
		nameTxtfield.setId("textField");

		reg = new Region();
		reg2 = new Region();

		hbox1.getChildren().addAll(nameLbl, nameTxtfield);
		vbox2.getChildren().addAll(reg2, languageLbl, boxLanguage, reg);

		lobbyBtn = new Button(t.getString("dominion.login.button.lobby"));
		exitBtn = new Button(t.getString("dominion.login.button.quit"));

		warning = new Label("");
		warning.setId("warning");
		warning.setAlignment(Pos.CENTER);

		vbox.getChildren().addAll(warning, hbox1, vbox2, lobbyBtn, exitBtn);
		scene = new Scene(mainPane);
		scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
		stage.setScene(scene);
		stage.setFullScreen(true);

		// Bindings for resizing the content on the window (written by patrick)
		// mainPane -> topvbox -> welcomeLbl
		// -> vbox -> warning
		// -> vbox -> hbox1 -> nameLbl
		// -> vbox -> hbox1 -> nameTxtfield
		// -> vbox -> vbox2 -> languageLbl
		// -> vbox -> vbox2 -> boxLanguage
		// -> vbox -> lobbyBtn
		// -> vbox -> exitBtn
		setStageBindings(mainPane, stage, 1, 1);

		setBindings(vbox, mainPane, 0.82, 0.18);
		setBindings(topvbox, mainPane, 0.15, 0.18);

		setBindings(hbox1, vbox, 0.05, 0.5);
		setBindings(vbox2, vbox, 0.09, 1);
		setBindings(warning, vbox, 0.1, 3);
		setBindings(lobbyBtn, vbox, 0.06, 0.6);
		setBindings(exitBtn, vbox, 0.06, 0.8);

		setBindings(nameLbl, hbox1, 1, 0.8);
		setBindings(nameTxtfield, hbox1, 1, 1.1);

		setBindings(reg2, vbox2, 0.03, 1);
		setBindings(languageLbl, vbox2, 0.6, 1);
		setBindings(boxLanguage, vbox2, 0.6, 0.5);
		setBindings(reg, vbox2, 0.25, 1);

		setBindings(welcomeLbl, topvbox, 1, 3);
	}

	/**
	 * Set the text on the login view with the current chosen language
	 * 
	 * @author Richard Bradley
	 * @author Michael Tu
	 */
	protected void updateTexts() {
		Translator t = ServiceLocator.getServiceLocator().getTranslator();
		welcomeLbl.setText(t.getString("dominion.login.welcome"));
		languageLbl.setText(t.getString("dominion.login.language") + ": ");
		lobbyBtn.setText(t.getString("dominion.login.button.lobby"));
		exitBtn.setText(t.getString("dominion.login.button.quit"));
		warning.setText(t.getString("dominion.login.warning"));
	}

	/**
	 * Opens the login-view
	 * 
	 * @author Sojo Nagaroor
	 */
	public void start() {
		this.stage.show();
	}

	/**
	 * closes the login-view
	 * 
	 * @author Sojo Nagaroor
	 */
	public void stop() {
		this.stage.hide();
	}

	/**
	 * Returns the primaryStage
	 * 
	 * @return current stage
	 */
	public Stage getStage() {
		return this.stage;
	}

	/**
	 * Bind the child to parent so the child resizes analog to the parent node
	 * 
	 * @param child
	 *            get the child node which should be binded
	 * @param stage2
	 *            get parent node to bind child to it
	 * @param heightMultiply
	 *            get factor to multiply the height of the child-node with the
	 *            parents height
	 * @param widthMultiply
	 *            get factor to multiply the width of the child-node with the
	 *            parents width
	 * @author Patrick Ziörjen
	 */
	protected void setBindings(Region child, Region parent, double heightMultiply, double widthMultiply) {
		child.maxHeightProperty().bind(parent.heightProperty().multiply(heightMultiply));
		child.maxWidthProperty().bind(parent.widthProperty().multiply(widthMultiply));
		child.minHeightProperty().bind(parent.heightProperty().multiply(heightMultiply));
		child.minWidthProperty().bind(parent.widthProperty().multiply(widthMultiply));
	}

	/**
	 * Bind the child to parent so the content inside the window resizes analog
	 * to the window size
	 * 
	 * @param child
	 *            get the child node which should be binded
	 * @param stage2
	 *            get parent node to bind child to it
	 * @param heightMultiply
	 *            get factor to multiply the height of the child-node with the
	 *            parents height
	 * @param widthMultiply
	 *            get factor to multiply the width of the child-node with the
	 *            parents width
	 * @author Patrick Ziörjen
	 */
	protected void setStageBindings(Region child, Stage stage2, double heightMultiply, double widthMultiply) {
		child.maxHeightProperty().bind(stage2.heightProperty().multiply(heightMultiply));
		child.maxWidthProperty().bind(stage2.widthProperty().multiply(widthMultiply));
		child.minHeightProperty().bind(stage2.heightProperty().multiply(heightMultiply));
		child.minWidthProperty().bind(stage2.widthProperty().multiply(widthMultiply));
	}

}
