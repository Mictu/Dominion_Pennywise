package Splash;

import javafx.concurrent.Worker;
import main_Class.Main;

//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
//import javafx.concurrent.Worker;


public class S_Controller {
	
	Main main;
	
    public S_Controller(S_Model model, S_View view) {
        
        view.progress.progressProperty().bind(model.initializer.progressProperty());

        model.initializer.stateProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue == Worker.State.SUCCEEDED)
                        main.run();
                });
    }
}
