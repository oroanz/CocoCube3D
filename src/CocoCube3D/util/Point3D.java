package CocoCube3D.util;


/**
 * This class theoretically represents a point in 3D space.
 * Since this is a 2D application, it can be used to convert
 * a point in 3D space to a point in 2D space given a
 * perspective projection.
 * 
 * @author https://github.com/adrian7980
 * @version 1.0
 */
public class Point3D extends Point2D
{
	protected double z;


	public Point3D (double x, double y, double z)
	{
		super(x, y);
		this.z = z;
	}

	public Point3D () { this(0.0, 0.0, 0.0); }


	public double getZ () { return z; }

	public void setZ (double z) { this.z = z; }


	/**
	 * Transforms this tridimensional point into a bidimensional point
	 * by adjusting the Z coordinate based on the angle of the perspective.
	 * 
	 * @param angle The angle of the perspective, in radians.
	 * @return The transformed bidimensional point.
	 */
	public Point2D toPoint2D (double angle)
	{
		double x2d = (x) - (z * Math.cos(angle));
		double y2d = (y) - (z * Math.sin(angle));

		return new Point2D(x2d, y2d);
	}


	@Override
	public String toString ()
	{
		return "(" + x + ", " + y + ", " + z + ")";
	}
}
