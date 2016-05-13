/**
 * 
 */
package view.alerts;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 * @author Cezara C.
 * 
 * @version 1.0, Apr 2016
 *
 */
public class SaveAlert extends Alert {
	
	private static SaveAlert instance;

	/**
	 * @param alertType
	 */
	private SaveAlert(AlertType alertType) {
		super(alertType);
		
		this.setTitle("Save Error");
		this.setHeaderText("There are unconnected points!");
		this.setContentText("You can not save while there are unconnected points.");
		
		this.initStyle(StageStyle.UTILITY);
	}
	
	public static SaveAlert getInstance() {
		
		if(instance == null)
			instance = new SaveAlert(AlertType.ERROR);
		
		return instance;
	}

}
