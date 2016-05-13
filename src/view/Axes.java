/**
 * 
 */
package view;

import javafx.beans.binding.Bindings;
import javafx.geometry.Side;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * @author Cezara C.
 * 
 * @version 1.0, Apr 2016
 *
 */
public class Axes extends Pane {
	
	private double xLow = -10;
	private double xHi = 10;
	
	private double yLow = -10;
	private double yHi = 10;
	
	private final double TICK_UNIT = 1;
	
	private NumberAxis xAxis;
    private NumberAxis yAxis;
    private Label xLabel;
    private Label yLabel;
    
    private boolean unsavedGraphs = false;
    
    public Axes(int width, int height) {

    	this.setSize(width, height);
    	
    	this.setXAxis(width, height, xLow, xHi, TICK_UNIT);
    	
    	this.setYAxis(width, height, yLow, yHi, TICK_UNIT);
    	
    	this.setXLabel(width, height);
    	
    	this.setYLabel(width);
    	
        this.getChildren().setAll(xAxis, yAxis, xLabel, yLabel);
    }

    public NumberAxis getXAxis() {
        return xAxis;
    }

    public NumberAxis getYAxis() {
        return yAxis;
    }
    
    private void setSize(int width, int height) {
    	
    	setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        setPrefSize(width, height);
        setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
    }
    
    private void setXAxis(int width, int height, 
    				 double xLow, double xHi, double xTickUnit) {
    	
    	xAxis = new NumberAxis(xLow, xHi, xTickUnit);
        xAxis.setSide(Side.BOTTOM);
        xAxis.setMinorTickVisible(false);
        xAxis.setPrefWidth(width);
        xAxis.setLayoutY(height / 2);    
    }
    
    private void setYAxis(int width, int height, 
			 double yLow, double yHi, double yTickUnit) {

    	 yAxis = new NumberAxis(yLow, yHi, yTickUnit);
         yAxis.setSide(Side.LEFT);
         yAxis.setMinorTickVisible(false);
         yAxis.setPrefHeight(height);
         yAxis.layoutXProperty().bind(
         		Bindings.subtract((width / 2) + 1, yAxis.widthProperty()));
	}
    
    private void setXLabel(int width, int height) {
    	
    	xLabel = new Label("x");
    	xLabel.setLayoutY(height/2 - 5);
    	xLabel.setLayoutX(width + 10);
    }
    
    private void setYLabel(int width) {
    	
    	yLabel = new Label("y");
    	yLabel.layoutXProperty().bind(Bindings.subtract(width/2 + 20, yAxis.widthProperty()));
    	yLabel.setLayoutY(-20);
    }
    
    public double getXLow() {
    	return xLow;
    }
    
    public double getXHi() {
    	return xHi;
    }
    
    public double getYLow() {
    	return yLow;
    }
    
    public double getYHi() {
    	return yHi;
    }
    
    public void setUnsaved(boolean value) {
    	
    	unsavedGraphs = value;
    }
    
    public boolean isUnsaved() {
    	
    	return unsavedGraphs;
    }
}
