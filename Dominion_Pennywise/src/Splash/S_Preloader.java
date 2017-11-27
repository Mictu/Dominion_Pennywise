package Splash;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class S_Preloader extends Preloader {
    private Stage stage;
    private ProgressBar progress = new ProgressBar();
    private Label lblStatus = new Label();

    public void start(Stage splashStage) throws Exception {
        this.stage = splashStage;
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

    public void handleProgressNotification(ProgressNotification pn) {
        progress.setProgress(pn.getProgress());
    }

    public void handleApplicationNotification(PreloaderNotification info) {
        if (info instanceof InfoToSplashScreen) {
            InfoToSplashScreen myInfo = (InfoToSplashScreen) info;
            lblStatus.setText(myInfo.getDetails());
        }
    }

    public void handleStateChangeNotification(StateChangeNotification evt) {
        if (evt.getType() == StateChangeNotification.Type.BEFORE_START) {
            stage.hide();
        }
    }

    public boolean handleErrorNotification(Preloader.ErrorNotification info) {
        lblStatus.setText(info.getDetails());
        return true;
    }

    public static class InfoToSplashScreen implements PreloaderNotification {
        private String details;

        public InfoToSplashScreen(String details) {
            this.details = details;
        }

        public String getDetails() {
            return details;
        }
    }
}
