/**
 * 
 */
package model;

/**
 * A <code>Point</code> object represents a point in space.
 * A point has a x-coordinate (horizontal positioning)
 * and a y-coordinate (vertical positioning), relative to the
 * (0,0) location situated in the top-left part of the space.
 * @author Cezara C.
 * 
 * @version 1.0, Apr 2016
 */

public class Point {
	
	private double x;
	private double y;
	
	/**
	 * Default constructor.
	 * Creates a point at (0,0).
	 */
	public Point() {
		x = 0;
		y = 0;
	}
	
	/**
	 * Explicit constructor.
	 * Creates a point at given coordinates.
	 * @param x the horizontal coordinate
	 * @param y the vertical coordinate
	 */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns the horizontal positioning of the point.
	 * @return the x
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Sets the horizontal positioning of the point.
	 * @param x the horizontal location of the point
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * Returns the vertical positioning of the point.
	 * @return the y
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Sets the vertical positioning of the point.
	 * @param y the vertical location of the point
	 */
	public void setY(double y) {
		this.y = y;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
	
	
}
