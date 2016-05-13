/**
 * 
 */
package view.alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.StageStyle;
import javafx.scene.control.ButtonType;

/**
 * @author Cezara C.
 * 
 * @version 1.0, Apr 2016
 *
 */
public class ResetAlert extends Alert {
	
	private static ResetAlert instance;
	
	private ButtonType save;
	private ButtonType reset;
	private ButtonType cancel;

	private ResetAlert(AlertType alertType) {
		super(alertType);
		
		this.setTitle("Confirm Reset");
		this.setHeaderText("You have unsaved graphs.");
		this.setContentText("What would you like to do?");
		
		this.initStyle(StageStyle.UTILITY);
		
		save = new ButtonType("Save first");
		reset = new ButtonType("Reset anyway");
		cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		
		this.getButtonTypes().setAll(save, reset, cancel);
	}
	
	public static ResetAlert getInstance() {
		
		if(instance == null)
			instance = new ResetAlert(AlertType.CONFIRMATION);
		
		return instance;
	}

	public ButtonType getSave() {
		
		return save;
	}
	
	public ButtonType getReset() {
		
		return reset;
	}
	
	public ButtonType getCancel() {
		
		return cancel;
	}
}
