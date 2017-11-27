package Splash;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class S_View {
    ProgressBar progress;
    private Label lblStatus;

    public S_View(Stage stage) {
//        stage.initStyle(StageStyle.TRANSPARENT);
//
//        BorderPane root = new BorderPane();
//
//        lblStatus = new Label("Woof");
//        root.setCenter(lblStatus);
//        
//        progress = new ProgressBar();
//        HBox bottomBox = new HBox();
////        bottomBox.setId("progressbox");
//        bottomBox.getChildren().add(progress);
//        root.setBottom(bottomBox);
//
//        Scene scene = new Scene(root);
//        scene.getStylesheets().addAll(this.getClass().getResource("SplashScreen.css").toExternalForm());
        
        
        stage.initStyle(StageStyle.TRANSPARENT);
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 450);
        scene.getStylesheets().addAll(
                this.getClass().getResource("SplashScreen.css").toExternalForm());
        stage.setScene(scene);

        root.setCenter(lblStatus);
        root.setBottom(progress);
        stage.show();

    }
}
