package CocoCube3D.util;


/**
 * This class represents a point in 2D space.
 * 
 * @author https://github.com/adrian7980
 * @version 1.0
 */
public class Point2D
{
	protected double x, y;


	public Point2D (double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public Point2D () { this(0.0, 0.0); }


	public double getX () { return x; }
	public double getY () { return y; }

	public void setX (double x) { this.x = x; }
	public void setY (double y) { this.y = y; }
}
