package CocoCube3D;

import java.io.File;
import java.io.FileReader;
import java.util.Iterator;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import CocoCube3D.util.IdentityMatrix;
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
	private IdentityMatrix effects;
	private Point3D[] coordinates;


	@SuppressWarnings("unchecked")
	public Cube (int length, IdentityMatrix effects)
	{
		this.coordinates = new Point3D[16];
		this.displacements = new IdentityMatrix[16];
		this.effects = effects;

		setLayout(null);

		// The following process is used to parse the JSON file.
		// Said JSON file contains the displacements requiered to generate the cube.
		try
		{
			File displacementsFile = new File("src/displacements.json");
			FileReader fr = new FileReader(displacementsFile);
			JSONArray displacementsArray = (JSONArray) new JSONParser().parse(fr);
			Iterator<JSONArray> displacementsIterator = displacementsArray.iterator();

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

	public Cube (int length) { this(length, new IdentityMatrix()); }


	@Override
	protected void paintComponent (Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;

		g2d.setStroke(new BasicStroke(2));

		IdentityMatrix prev = effects;

		for (int i = 0; i < displacements.length; i++)
		{
			IdentityMatrix next = prev.times(displacements[i]);

			coordinates[i] = new Point3D(next.get(0, 3), next.get(1, 3), next.get(2, 3));

			prev = next;
		}

		Point2D prevPoint = coordinates[0].toPoint2D(Math.toRadians(30));

		for (int i = 1; i < coordinates.length; i++)
		{
			Point2D nextPoint = coordinates[i].toPoint2D(Math.toRadians(30));

			g2d.drawLine((int) prevPoint.getX(), (int) prevPoint.getY(), (int) nextPoint.getX(), (int) nextPoint.getY());

			prevPoint = nextPoint;
		}
	}
}
