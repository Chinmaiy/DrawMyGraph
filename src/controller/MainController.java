/**
 * 
 */
package controller;

import javafx.stage.Stage;
import view.MainWindow;

/**
 * @author Cezara C.
 * 
 * @version 1.0, Apr 2016
 *
 */
public class MainController {
	
	private MainWindow mainWindow;
	
	public MainController() {}

	public void initMainWindow(Stage stage) {
		mainWindow = new MainWindow(stage);
		mainWindow.setMainController(this);
	}
}
