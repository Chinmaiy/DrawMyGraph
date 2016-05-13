/**
 * 
 */
package controller;

import java.util.Optional;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import view.Axes;
import view.alerts.ResetAlert;
import view.alerts.SaveAlert;

/**
 * @author Cezara C.
 * 
 * @version 1.0, Apr 2016
 *
 */
public class ResetButtonController implements EventHandler<MouseEvent> {
	
	private StackPane graphArea;
	private Axes axes;
	private Pane pointsPane;
	
	private Button save;
	
	public ResetButtonController(StackPane graphArea, Axes axes, Pane pointsPane, Button save) {
		
		this.graphArea = graphArea;
		this.axes = axes;
		this.pointsPane = pointsPane;
		
		this.save = save;
	}

	@Override
	public void handle(MouseEvent event) {
		
		if(axes.isUnsaved()) {
			
			ResetAlert unsavedGraphsAlert = ResetAlert.getInstance();
			
			Optional<ButtonType> result = unsavedGraphsAlert.showAndWait();
			
			if(result.get() == unsavedGraphsAlert.getSave()) {
				
				if(pointsPane.getChildren().size() > 0) {
					SaveAlert cannotSaveAlert = SaveAlert.getInstance();
					
					cannotSaveAlert.showAndWait();
					return;
				}
				else 
					save.fire();
			}
			
			else if(result.get() == unsavedGraphsAlert.getCancel()) {
				
				return;
			}
		}
			
		
		int size = axes.getChildren().size();
		
		/*delete everything except the axes and,labels and the pane for drawing points*/
		while(size > 5) {
			axes.getChildren().remove(5);
			size = axes.getChildren().size();
		}
		
		pointsPane.getChildren().clear();
		
		graphArea.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		
		axes.setUnsaved(false);
	}

}
