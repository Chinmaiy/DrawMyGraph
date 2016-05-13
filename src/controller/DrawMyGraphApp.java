/**
 * 
 */
package controller;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Cezara C.
 * 
 * @version 1.0, Apr 2016
 *
 */
public class DrawMyGraphApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		MainController mainController = new MainController();
		
		mainController.initMainWindow(primaryStage);
		
		primaryStage.show();
	}

	public static void main(String[] args) {
		
		launch(args);
	}

}
