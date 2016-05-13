/**
 * 
 */
package view;

import java.util.List;

import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import model.Point;

/**
 * @author Cezara C.
 * 
 * @version 1.0, Apr 2016
 *
 */
public class Graph extends Path {
	
	public Graph(List<Point> plot, Axes axes) {

        this.getElements().add(
        		new MoveTo(mapX(plot.get(0).getX(), axes), mapY(plot.get(0).getY(), axes)));
        
        for (Point point : plot) {
        	
        	this.getElements().add(
        		new LineTo(mapX(point.getX(), axes), mapY(point.getY(), axes)));
		}
    }

    private double mapX(double x, Axes axes) {
        double tx = axes.getPrefWidth() / 2;
        double sx = axes.getPrefWidth() / 
           (axes.getXAxis().getUpperBound() - 
            axes.getXAxis().getLowerBound());

        return x * sx+ tx;
    }

    private double mapY(double y, Axes axes) {
        double ty = axes.getPrefHeight() / 2;
        double sy = axes.getPrefHeight() / 
            (axes.getYAxis().getUpperBound() - 
             axes.getYAxis().getLowerBound());

        return -y * sy + ty;
    }
  
    public String convertToSVG() {
    	
    	StringBuilder sb = new StringBuilder();
    	
    	for (PathElement element : this.getElements()) {
			
    		if(element instanceof MoveTo) {
    			
    			sb.append("M ")
    			  .append(((MoveTo) element).getX()).append(" ")
    			  .append(((MoveTo) element).getY()).append(" ");
    		}
    		else if(element instanceof LineTo) {
    			sb.append("L ")
    			  .append(((LineTo) element).getX()).append(" ")
    			  .append(((LineTo) element).getY()).append(" ");
    		}
		}
    	
    	/*close the path*/
    	sb.append("Z");
    	
    	return sb.toString();
    }
}
