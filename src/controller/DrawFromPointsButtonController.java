/**
 * 
 */
package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import model.FunctionPlotModel;
import model.Point;
import view.Axes;
import view.ClickedPoint;
import view.Graph;

/**
 * @author Cezara C.
 * 
 * @version 1.0, Apr 2016
 *
 */
public class DrawFromPointsButtonController implements EventHandler<MouseEvent> {
	
	private Pane pointsPane;
	private Slider strokeWidthSlider;
	private ColorPicker colorPicker ;
	
	private Axes axes;

	private List<Point> points;
	
	public DrawFromPointsButtonController(Pane pointsPane, Slider strokeWidthSlider,
										  ColorPicker colorPicker, Axes axes) {
		
		this.pointsPane = pointsPane;
		this.strokeWidthSlider = strokeWidthSlider;
		this.colorPicker = colorPicker;
		this.axes = axes;
		
		points = new ArrayList<Point>();
	}
	
	
	@Override
	public void handle(MouseEvent event) {
	
		this.setPoints();

		FunctionPlotModel plot = new FunctionPlotModel(points);
		
		Graph graph = new Graph(plot.getPlot(), axes);
		Tooltip t = new Tooltip(plot.getExpression());
		Tooltip.install(graph, t);
		
		graph.setStrokeWidth(strokeWidthSlider.getValue());
		
		graph.setStroke(colorPicker.getValue());
		
		Rectangle rect = new Rectangle(axes.getWidth(), axes.getHeight());
		graph.setClip(rect);
		
		axes.getChildren().add(graph);
		axes.setUnsaved(true);
		
		/*clening up for future graphs*/
		points.clear();
		pointsPane.getChildren().clear();
		
	}
	
	private void setPoints() {
	
		for (Node child : pointsPane.getChildren()) {
		
			double x = mapX(((ClickedPoint)child).getCenterX());
			double y = mapY(((ClickedPoint)child).getCenterY());
			
			points.add(new Point(x, y));
		}
	}

	private double mapX(double x) {
		double tx = axes.getPrefWidth() / 2;
		double sx = axes.getPrefWidth() / 
				(axes.getXAxis().getUpperBound() - 
						axes.getXAxis().getLowerBound());

		return (x - tx) / sx;
	}

	private double mapY(double y) {
		double ty = axes.getPrefHeight() / 2;
		double sy = axes.getPrefHeight() / 
				(axes.getYAxis().getUpperBound() - 
						axes.getYAxis().getLowerBound());

		return (y - ty) / sy * (-1);
	}
}
