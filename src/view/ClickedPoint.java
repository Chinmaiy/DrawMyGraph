/**
 * 
 */
package view;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

/**
 * @author Cezara C.
 * 
 * @version 1.0, Apr 2016
 *
 */
public class ClickedPoint extends Circle {
	

	public ClickedPoint(double centerX, double centerY, double radius, Pane pointsPane) {
		
		super(centerX, centerY, radius);
		
		pointsPane.getChildren().add(this);
	}
}
