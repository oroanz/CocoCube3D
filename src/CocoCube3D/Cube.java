package CocoCube3D;

import java.io.File;
import java.io.FileReader;
import java.util.Iterator;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;

import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import CocoCube3D.util.IdentityMatrix;
import CocoCube3D.util.IdentityMatrixEffects;
import CocoCube3D.util.Point2D;
import CocoCube3D.util.Point3D;


/**
 * This class is the graphic representation of a cube in the 2D view.
 * 
 * @author https://github.com/adrian7980
 * @version 1.0
 */
public class Cube extends JPanel
{
	private IdentityMatrix[] displacements;
	private IdentityMatrixEffects transformations;
	private Point3D[] coordinates;
	private double angle, scale, x, y, z, rx, ry, rz;


	/**
	 * Generates all the data needed to form the cube based on the "displacements.json"
	 * file, the length of each stroke and the angle of the Z axis perspective.
	 * 
	 * @param length The length (in pixels) of the stroke to draw the cube.
	 * @param angle The angle (in degrees) of the Z axis perspective.
	 */
	@SuppressWarnings("unchecked")
	public Cube (int length, double angle)
	{
		this.coordinates = new Point3D[16];
		this.displacements = new IdentityMatrix[16];
		this.angle = Math.toRadians(angle);

		resetTransformations();
		setSize(1280, 620);

		// The following process is used to parse the JSON file.
		// Said JSON file contains the displacements requiered to generate the cube.
		try
		{
			File displacementsFile = new File("src/displacements.json");
			FileReader fr = new FileReader(displacementsFile);
			JSONArray displacementsArray = (JSONArray) new JSONParser().parse(fr);
			Iterator<JSONArray> displacementsIterator = displacementsArray.iterator();

			// The following loop parses the JSON file and generates the cube.
			for (int i = 0; displacementsIterator.hasNext(); i++)
			{
				JSONArray displacement = displacementsIterator.next();
				Iterator<Long> displacementIterator = displacement.iterator();

				this.displacements[i] = new IdentityMatrix();

				for (int j = 0; displacementIterator.hasNext(); j++)
					this.displacements[i].set(j, 3, (double) displacementIterator.next() * length);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	/**
	 * Resets the transformations applied to the cube.
	 */
	public void resetTransformations ()
	{
		transformations = new IdentityMatrixEffects();

		x = 0.0;
		y = 0.0;
		z = 0.0;
		rx = 0.0;
		ry = 0.0;
		rz = 0.0;
		scale = 1.0;
	}

	/**
	 * Modifies the scale of the cube by the given scale factor.
	 * 
	 * @param zoom The scale factor. It may not be less than 0.
	 */
	public void setScale (double zoom)
	{
		if (zoom < 0)
			return;

		transformations.scale(zoom);
		scale = zoom;
	}

	/**
	 * Sets the new point in X, Y and Z where the cube will be drawn.
	 * 
	 * @param x The point in X, relative to this JPanel instance.
	 * @param y The point in Y, relative to this JPanel instance.
	 * @param z The point in Z, relative to this JPanel instance.
	 */
	public void setTranslation (double x, double y, double z)
	{
		transformations
			.translateX(x)
			.translateY(y)
			.translateZ(z);

		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Sets the rotation in the X, Y and Z axis, respective to the anchor point.
	 * 
	 * @param rx The angle to rotate in X.
	 * @param ry The angle to rotate in Y.
	 * @param rz The angle to rotate in Z.
	 */
	public void setRotation (double rx, double ry, double rz)
	{
		transformations
			.rotateX(Math.toRadians(rx))
			.rotateY(Math.toRadians(ry))
			.rotateZ(Math.toRadians(rz));

		this.rx = rx;
		this.ry = ry;
		this.rz = rz;
	}

	// These are your usual get methods.

	public double getAngle () { return Math.toDegrees(angle); }
	public double getScale () { return scale; }
	public double getTranslationX () { return x; }
	public double getTranslationY () { return y; }
	public double getTranslationZ () { return z; }
	public double getRotationX () { return rx; }
	public double getRotationY () { return ry; }
	public double getRotationZ () { return rz; }

	public void setAngle (double angle) { this.angle = Math.toRadians(angle); }


	@Override
	protected void paintComponent (Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		IdentityMatrix prev = transformations.getMatrix();

		g2d.setStroke(new BasicStroke(1));
		g2d.setColor(Color.WHITE);

		// The following loop establishes the coordinates of the cube.
		for (int i = 0; i < displacements.length; i++)
		{
			IdentityMatrix next = prev.times(displacements[i]);

			coordinates[i] = new Point3D(
				next.get(0, 3),
				next.get(1, 3),
				next.get(2, 3)
			);

			prev = next;
		}

		Point2D prevPoint = coordinates[0].toPoint2D(angle);

		// The following loop draws the cube.
		for (int i = 1; i < coordinates.length; i++)
		{
			// The tridimensional point is parsed to a 2D point using the angle of the Z axis.
			Point2D nextPoint = coordinates[i].toPoint2D(angle);

			g2d.drawLine(
				(int) prevPoint.getX(),
				(int) prevPoint.getY(),
				(int) nextPoint.getX(),
				(int) nextPoint.getY()
			);

			prevPoint = nextPoint;
		}
	}
}
