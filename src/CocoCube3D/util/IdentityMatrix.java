package CocoCube3D.util;


/**
 * This class represents an identity matrix, where {@code matrix[i][j] = 1},
 * and evey other position is {@code 0}.
 * 
 * @author https://github.com/adrian7980
 * @version 1.0
 */
public class IdentityMatrix
{
	private double[][] matrix;


	public IdentityMatrix ()
	{
		matrix = new double[4][4];

		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				if (i == j)
					matrix[i][j] = 1.0;
				else
					matrix[i][j] = 0.0;
			}
		}
	}


	/**
	 * Gets the value at {@code matrix[i][j].}
	 * 
	 * @param i The zero-based position of the row to get.
	 * @param j The zero-based position of the column to get.
	 * @return The value at the specified positions.
	 */
	public double get (int i, int j) { return matrix[i][j]; }

	/**
	 * Sets the value at {@code matrix[i][j] = value}.
	 * 
	 * @param i The zero-based position of the row to set.
	 * @param j The zero-based position of the column to set.
	 * @param value The value to set.
	 */
	public void set (int i, int j, double value) { matrix[i][j] = value; }


	/**
	 * Multiplies the identity matrix by another identity matrix and returns the result.
	 * The original matrix is not modified.
	 * 
	 * @param other The identity matrix to multiply with.
	 * @return The result of multipliying {@code this} matrix with {@code other}.
	 */
	public IdentityMatrix times (IdentityMatrix other)
	{
		IdentityMatrix result = new IdentityMatrix();

		// Multiply a matrix by a matrix.
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				double sum = 0.0;

				for (int k = 0; k < 4; k++)
					sum += matrix[i][k] * other.get(k, j);

				result.set(i, j, sum);
			}
		}

		return result;
	}
}
