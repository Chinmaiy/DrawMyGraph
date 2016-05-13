/**
 * 
 */
package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * @author Cezara C.
 * @version 1.0, Apr 2016
 *
 */
public class ColorPickerController implements ChangeListener<Color>{
	
	private Line sampleLine;
	
	public ColorPickerController(Line sampleLine) {
		
		this.sampleLine = sampleLine;
	}

	@Override
	public void changed(ObservableValue<? extends Color> observable, Color oldValue, Color newValue) {
		sampleLine.setStroke(newValue);
	}
}
