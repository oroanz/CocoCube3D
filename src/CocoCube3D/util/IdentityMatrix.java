package CocoCube3D.util;

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


	public double get (int i, int j) { return matrix[i][j]; }

	public void set (int i, int j, double value) { matrix[i][j] = value; }


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
