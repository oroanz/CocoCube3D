package CocoCube3D;

import java.awt.Container;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


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
	private double scale = 1.0;


	public CocoCube3D ()
	{
		super("CocoCube3D");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setResizable(false);
		setLocationRelativeTo(null); // Spawns the window at the center of the screen.
		setVisible(true);

		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
		
		setup();
	}


	/**
	 * Sets up the main window.
	 */
	private void setup ()
	{
		JPanel buttonRow = new JPanel();

		buttonRow.setLayout(new BoxLayout(buttonRow, BoxLayout.X_AXIS));

		buttonRow.add(Box.createHorizontalGlue());
		buttonRow.add(new JButton("Translate"));
		buttonRow.add(new JButton("Rotate"));
		buttonRow.add(new JButton("Scale"));
		buttonRow.add(new JButton("Reset"));
		buttonRow.add(Box.createHorizontalGlue());

		contentPane.add(buttonRow);
		contentPane.add(new Cube(100));
		contentPane.revalidate();
		contentPane.repaint();
	}


	public static void main (String[] args)
	{
		new CocoCube3D();
	}
}
