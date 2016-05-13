/**
 * 
 */
package controller;


import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import view.ClickedPoint;

/**
 * @author Cezara C.
 * 
 * @version 1.0, Apr 2016
 *
 */
public class GraphAreaClickedController implements EventHandler<MouseEvent> {
	
	private Pane pointsPane;
	
	public GraphAreaClickedController(Pane pointsPane) {
		
		this.pointsPane = pointsPane;
	}

		
	@Override
	public void handle(MouseEvent event) {
		
		double x = event.getX();
		double y = event.getY();

		ClickedPoint clickedPoint = new ClickedPoint(x, y, 2, pointsPane);
		
		clickedPoint.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				pointsPane.getChildren().remove(clickedPoint);
				event.consume();
			}
		});
	}
}
