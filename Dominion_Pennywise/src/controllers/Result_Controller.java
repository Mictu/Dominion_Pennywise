package controllers;

import javafx.application.Platform;
import view.Result_View;

public class Result_Controller {
	
//	Player winner;
	Result_View resultView;
	
	
	public Result_Controller (Result_View resultView){
		this.resultView = resultView;
		resultView.stage.setOnCloseRequest(event -> Login_Controller.client.disconnectClient());
	}


	public void showResultInView(String[] resultsPlayerAndPoints) {
		Platform.runLater(() -> {
			resultView.setRangList(resultsPlayerAndPoints);
		});
	}


}
