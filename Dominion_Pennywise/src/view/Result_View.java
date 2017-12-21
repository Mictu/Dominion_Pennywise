package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Result_View {

	BorderPane mainPane;
	public Stage stage;

	Label congratulation;
	Label youwon;

	VBox vbox = new VBox();
	HBox hbox;
	Label namelbl;
	Label pointlbl;


		public Result_View(Stage stage) {
		this.stage = stage; 
		
		stage.setHeight(600.0);//
		stage.setWidth(400.0);//
		// stage.setResizable(false);
		stage.setTitle("WINNER");
		stage.setFullScreen(true);
		stage.setFullScreenExitHint("");
		mainPane = new BorderPane();
		mainPane.setId("mainPaneResult");
		

		// congratulation = new Label("CONGRATULATION");
		// congratulation.setId("lblresult");
		// youwon = new Label("You Won!!!!");
		// youwon.setId("lblresult");
		// vbox.getChildren().addAll(congratulation, youwon);
		vbox.setAlignment(Pos.CENTER);
		vbox.setId("resultvbox");

//		for (int i = 0; i < Player.player.size(); i++) {
//			hbox = new HBox();
//			hbox.setAlignment(Pos.CENTER);
//			hbox.setSpacing(50);
//
//			Label ranglbl = new Label("Platz: " + (i + 1));
//			ranglbl.setId("ranglbl" + (i + 1));
//			if (i >= 2) {
//				ranglbl.setStyle("-fx-text-fill: white;" + "-fx-font-weight: bold;" + "-fx-font-size: 20; ");
//			}
//			namelbl = new Label();
//			namelbl.setText(Player.player.get(i).getName());
//			namelbl.setId("namelbl" + (i + 1));
//			if (i >= 2) {
//				namelbl.setStyle("-fx-text-fill: white;" + "-fx-font-weight: bold;" + "-fx-font-size: 20; ");
//			}
//			pointlbl = new Label();
//			pointlbl.setId("pointlbl" + (i + 1));
//			if (i >= 2) {
//				pointlbl.setStyle("-fx-text-fill: white;" + "-fx-font-weight: bold;" + "-fx-font-size: 20; ");
//			}
//			String point = Integer.toString(Player.player.get(i).winPoint);
//			pointlbl.setText(point);
//			hbox.getChildren().addAll(ranglbl, namelbl, pointlbl);
//			vbox.getChildren().add(hbox);
//		}

		mainPane.setCenter(vbox);

		Scene scene = new Scene(mainPane);
		scene.getStylesheets().add(getClass().getResource("Result.css").toExternalForm());
		stage.setScene(scene);

		 }
		
	public void start() {
		stage.show();

	}

	public void stop() {
		stage.hide();
	}
	
	public void setRangList(String[] playerliste){
		for(int i = 0; i < playerliste.length; i++){
			hbox = new HBox();
			hbox.setAlignment(Pos.CENTER);
			hbox.setSpacing(50);

			Label ranglbl = new Label("Platz: " + (i + 1));
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
