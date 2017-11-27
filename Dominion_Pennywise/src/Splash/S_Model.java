package Splash;

import javafx.concurrent.Task;

public class S_Model {

    public S_Model() {
    }

    final Task<Void> initializer = new Task<Void>() {
        protected Void call() throws Exception {

            // First, take some time, update progress
            Integer i = 0;
            for (; i < 100000; i++) {
                if ((i % 1000) == 0)
                    this.updateProgress(i, 100000);
            }

            return null;
        }
    };

    public void initialize() {
        new Thread(initializer).start();
    }

}
