package CocoCube3D.util;


/**
 * This class generates a matrix that can be used to apply a transformation to a point.
 * All the methods in this class return the same instance of the class, so that the
 * transformation can be chained.
 * 
 * @author https://github.com/adrian7980
 * @version 1.0
 */
public class IdentityMatrixEffects
{
	private IdentityMatrix matrix;


	public IdentityMatrixEffects ()
	{
		matrix = new IdentityMatrix();
	}


	public IdentityMatrix getMatrix () { return matrix; }


	/**
	 * This method applies a translation in the X axis to the matrix.
	 * 
	 * @param x The amount of translation in the X axis.
	 * @return The same instance of the class.
	 */
	public IdentityMatrixEffects translateX (double x)
	{
		IdentityMatrix result = new IdentityMatrix();

		result.set(0, 3, x);

		matrix = matrix.times(result);

		return this;
	}

	/**
	 * This method applies a translation in the Y axis to the matrix.
	 * 
	 * @param y The amount of translation in the Y axis.
	 * @return The same instance of the class.
	 */
	public IdentityMatrixEffects translateY (double y)
	{
		IdentityMatrix result = new IdentityMatrix();

		result.set(1, 3, y);

		matrix = matrix.times(result);

		return this;
	}

	/**
	 * This method applies a translation in the Z axis to the matrix.
	 * 
	 * @param z The amount of translation in the Z axis.
	 * @return The same instance of the class.
	 */
	public IdentityMatrixEffects translateZ (double z)
	{
		IdentityMatrix result = new IdentityMatrix();

		result.set(2, 3, z);

		matrix = matrix.times(result);

		return this;
	}

	/**
	 * This method applies a rotation in the X axis to the matrix.
	 * 
	 * @param angle The angle of rotation in the X axis, in radians.
	 * @return The same instance of the class.
	 */
	public IdentityMatrixEffects rotateX (double angle)
	{
		IdentityMatrix result = new IdentityMatrix();

		result.set(0, 0, 1.0);
		result.set(1, 1, Math.cos(angle));
		result.set(1, 2, Math.sin(angle));
		result.set(2, 1, -Math.sin(angle));
		result.set(2, 2, Math.cos(angle));
		result.set(3, 3, 1.0);

		matrix = matrix.times(result);

		return this;
	}

	/**
	 * This method applies a rotation in the Y axis to the matrix.
	 * 
	 * @param angle The angle of rotation in the Y axis, in radians.
	 * @return The same instance of the class.
	 */
	public IdentityMatrixEffects rotateY (double angle)
	{
		IdentityMatrix result = new IdentityMatrix();

		result.set(0, 0, Math.cos(angle));
		result.set(0, 2, -Math.sin(angle));
		result.set(1, 1, 1.0);
		result.set(2, 0, Math.sin(angle));
		result.set(2, 2, Math.cos(angle));
		result.set(3, 3, 1.0);

		matrix = matrix.times(result);

		return this;
	}

	/**
	 * This method applies a rotation in the Z axis to the matrix.
	 * 
	 * @param angle The angle of rotation in the Z axis, in radians.
	 * @return The same instance of the class.
	 */
	public IdentityMatrixEffects rotateZ (double angle)
	{
		IdentityMatrix result = new IdentityMatrix();

		result.set(0, 0, Math.cos(angle));
		result.set(0, 1, Math.sin(angle));
		result.set(1, 0, -Math.sin(angle));
		result.set(1, 1, Math.cos(angle));
		result.set(2, 2, 1.0);
		result.set(3, 3, 1.0);

		return this;
	}

	/**
	 * This method applies a scaling in all the axes to the matrix.
	 * Scale factor should be greater than 0.
	 * 
	 * @param zoom The amount of scaling in all the axes.
	 * @return The same instance of the class.
	 */
	public IdentityMatrixEffects zoom (double zoom)
	{
		if (zoom < 0.0)
			zoom = 0.0;

		IdentityMatrix result = new IdentityMatrix();

		result.set(0, 0, zoom);
		result.set(1, 1, zoom);
		result.set(2, 2, zoom);
		result.set(3, 3, 1.0);

		matrix = matrix.times(result);

		return this;
	}
}
