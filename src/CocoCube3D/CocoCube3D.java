package CocoCube3D;

import java.awt.Container;
import java.awt.Color;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * This is the main class of the CocoCube3D application.
 * It defines the main method and creates the main window.
 * 
 * @author https://github.com/adrian7980
 * @version 1.0
 */
public class CocoCube3D extends JFrame
{
	private final Container contentPane = getContentPane();
	private final Cube theCube;
	private final JTextField
		fieldX = new JTextField("0"),
		fieldY = new JTextField("0"),
		fieldZ = new JTextField("0"),
		fieldRX = new JTextField("0"),
		fieldRY = new JTextField("0"),
		fieldRZ = new JTextField("0"),
		fieldScale = new JTextField("1");


	public CocoCube3D ()
	{
		super("CocoCube3D");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setResizable(false);
		setLocationRelativeTo(null); // Spawns the window at the center of the screen.
		setVisible(true);

		contentPane.setLayout(null);
		contentPane.setBackground(Color.DARK_GRAY);

		theCube = new Cube(100, 45);
		theCube.setTranslation(640, 360, 0);
		theCube.setRotation(180, 0, 0);
		
		setup();
	}


	/**
	 * Sets up the main window.
	 */
	private void setup ()
	{
		JPanel transformationsPanel = new JPanel();

		transformationsPanel.setLayout(new BoxLayout(transformationsPanel, BoxLayout.X_AXIS));
		transformationsPanel.setSize(1280, 25);
		transformationsPanel.setBackground(Color.LIGHT_GRAY);

		JLabel translateXLabel = new JLabel("X: ");
		JLabel translateYLabel = new JLabel("Y: ");
		JLabel translateZLabel = new JLabel("Z: ");
		JLabel rotateXLabel = new JLabel("X°: ");
		JLabel rotateYLabel = new JLabel("Y°: ");
		JLabel rotateZLabel = new JLabel("Z°: ");
		JLabel scaleLabel = new JLabel("Scale: ");

		JButton applyButton = new JButton("Apply");
		applyButton.addActionListener((e) -> applyChanges());

		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener((e) -> resetChanges());

		transformationsPanel.add(Box.createHorizontalGlue());
		transformationsPanel.add(Box.createHorizontalStrut(10));
		transformationsPanel.add(translateXLabel);
		transformationsPanel.add(fieldX);
		transformationsPanel.add(Box.createHorizontalStrut(20));
		transformationsPanel.add(translateYLabel);
		transformationsPanel.add(fieldY);
		transformationsPanel.add(Box.createHorizontalStrut(20));
		transformationsPanel.add(translateZLabel);
		transformationsPanel.add(fieldZ);
		transformationsPanel.add(Box.createHorizontalStrut(20));
		transformationsPanel.add(rotateXLabel);
		transformationsPanel.add(fieldRX);
		transformationsPanel.add(Box.createHorizontalStrut(20));
		transformationsPanel.add(rotateYLabel);
		transformationsPanel.add(fieldRY);
		transformationsPanel.add(Box.createHorizontalStrut(20));
		transformationsPanel.add(rotateZLabel);
		transformationsPanel.add(fieldRZ);
		transformationsPanel.add(Box.createHorizontalStrut(20));
		transformationsPanel.add(scaleLabel);
		transformationsPanel.add(fieldScale);
		transformationsPanel.add(Box.createHorizontalStrut(20));
		transformationsPanel.add(applyButton);
		transformationsPanel.add(resetButton);
		transformationsPanel.add(Box.createHorizontalStrut(10));
		transformationsPanel.add(Box.createHorizontalGlue());

		contentPane.add(transformationsPanel);
		contentPane.add(theCube);
		contentPane.revalidate();
		contentPane.repaint();
	}

	/**
	 * Applies any changes set on the text fields.
	 * If any of the text fields is empty, it will not apply any changes.
	 */
	private void applyChanges ()
	{
		try
		{
			theCube.setTranslation(
				Double.parseDouble(fieldX.getText()),
				Double.parseDouble(fieldY.getText()),
				Double.parseDouble(fieldZ.getText())
			);
		}
		catch (NumberFormatException e)
		{
			theCube.setTranslation(0, 0, 0);
		}

		try
		{
			theCube.setRotation(
				Double.parseDouble(fieldRX.getText()),
				Double.parseDouble(fieldRY.getText()),
				Double.parseDouble(fieldRZ.getText())
			);
		}
		catch (NumberFormatException e)
		{
			theCube.setRotation(0, 0, 0);
		}

		try
		{
			theCube.setScale(
				Double.parseDouble(fieldScale.getText())
			);
		}
		catch (NumberFormatException e)
		{
			theCube.setScale(1);
		}

		contentPane.revalidate();
		contentPane.repaint();
	}

	/**
	 * Resets all the text fields to their default values.
	 */
	private void resetChanges ()
	{
		theCube.resetTransformations();
		theCube.setTranslation(640, 360, 0);
		theCube.setRotation(180, 0, 0);

		fieldX.setText("0");
		fieldY.setText("0");
		fieldZ.setText("0");
		fieldRX.setText("0");
		fieldRY.setText("0");
		fieldRZ.setText("0");
		fieldScale.setText("1");

		contentPane.revalidate();
		contentPane.repaint();
	}


	public static void main (String[] args)
	{
		new CocoCube3D();
	}
}
