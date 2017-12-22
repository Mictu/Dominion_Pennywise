package controllers;

import javafx.application.Platform;
import view.Result_View;

/**
 * Controller Class for Result_View. Manage the elements from Result_View. Get
 * Resources from ClientHandler and use it for Result_View to show the highscore
 * Handles the setOnActions from Result_View
 * 
 * @author Michael Tu
 * @author Sojo Nagaroor
 */
public class Result_Controller {
	Result_View resultView;

	/**
	 * Close the Result_View if the client closes the window
	 * 
	 * @param resultView
	 *            - get the correct resultView from ClientHandler to handle the view
	 * @author Michael Tu
	 * @return -
	 */
	public Result_Controller(Result_View resultView) {
		this.resultView = resultView;
		resultView.stage.setOnCloseRequest(event -> Login_Controller.client.disconnectClient());
	}

	/**
	 * Open the Result_View and show Higscore
	 * 
	 * @param resultsPlayerAndPoints
	 *            - String Array with Playername and winpoints
	 * @author Sojo Nagaroor
	 * @return -
	 */
	public void showResultInView(String[] resultsPlayerAndPoints) {
		Platform.runLater(() -> {
			resultView.setRangList(resultsPlayerAndPoints);
		});
	}

}
