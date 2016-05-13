/**
 * 
 */
package controller;

import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import model.FunctionPlotModel;
import view.Axes;
import view.Graph;

/**
 * @author Cezara C.
 * 
 * @version 1.0, Apr 2016
 *
 */
public class DrawButtonController implements EventHandler<MouseEvent> {
	
	private TextField function;
	private Slider strokeWidthSlider;
	private ColorPicker colorPicker ;
	
	private Axes axes;
	
	public DrawButtonController(TextField function, Slider strokeWidth, ColorPicker color, Axes axes) {
		this.function = function;
		this.strokeWidthSlider = strokeWidth;
		this.colorPicker = color;
		this.axes = axes;
	}

	@Override
	public void handle(MouseEvent event) {
		
		FunctionPlotModel plot = new FunctionPlotModel(function.getText());
		
		Graph graph = new Graph(plot.getPlot(), axes);
		Tooltip t = new Tooltip(function.getText());
		Tooltip.install(graph, t);
		
		graph.setStrokeWidth(strokeWidthSlider.getValue());
		
		graph.setStroke(colorPicker.getValue());
		
		Rectangle rectangleClip = new Rectangle(axes.getWidth(), axes.getHeight());
		graph.setClip(rectangleClip);
		
		axes.getChildren().add(graph);
		axes.setUnsaved(true);
		
		System.out.println(graph.convertToSVG());
		
	}

}
