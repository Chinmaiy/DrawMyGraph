/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.List;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 * A <code>FunctionPlotModel</code> object stores a list of points
 * that connected describe a function's plot.
 * @author Cezara C. 
 * 
 * @version 1.0, Apr 2016
 *
 */
public class FunctionPlotModel {
	
	private final String VARIABLE = "x";
	private final double X_INC = 0.01;

	private double xLow = -10;
	private double xHi = 10;
	
	//private double yLow = -10;
	//private double yHi = 10;
	
	private Expression expression;
	private String pointExpression;
	
	List<Point> plot;
	
	public FunctionPlotModel(String expression) {
		this.expression = new ExpressionBuilder(expression).variable(VARIABLE).build();
		
		plot = new ArrayList<Point>();
		
		generatePlot();
	}
	
	public FunctionPlotModel(List<Point> points) {
		
		pointExpression = getExpression(points);
		
		this.expression = new ExpressionBuilder(pointExpression).variable(VARIABLE).build();
		
		plot = new ArrayList<Point>();
		
		generatePlot();
	}
	
	private void generatePlot() {
		
		double x = xLow;
		
		expression.setVariable(VARIABLE, x);
		double y = expression.evaluate();
		
		/*while(!Double.isFinite(y) || y > yHi || y < yLow) {
			x += X_INC;
			
			expression.setVariable(VARIABLE, x);
			
			y = expression.evaluate();
		}*/
		
		plot.add(new Point(x, y));
		
		x += X_INC;
		while(x <= xHi) {
			expression.setVariable(VARIABLE, x);
			
			y = expression.evaluate();
			
			//if(Double.isFinite(y) && y <= yHi && y >= yLow)
            	plot.add(new Point(x, y));
            
            x += X_INC;
		}
	}

	private String getExpression(List<Point> points) {
		
		StringBuilder sb = new StringBuilder();
		
		for (Point point : points) {
			
			sb.append(point.getY());
			
			for (Point p : points) {
				
				if(!point.equals(p)) {
					sb.append("*(").append(VARIABLE).append("-").append(p.getX()).append(")/")
													.append(point.getX()-p.getX());
				}
			}
			
			sb.append("+");
		}
		
		/*delete last + */
		sb.deleteCharAt(sb.length()-1);
		
		return sb.toString();
	}
	
	public List<Point> getPlot() {
		return plot;
	}
	
	public String getExpression() {
		return pointExpression;
	}
}
