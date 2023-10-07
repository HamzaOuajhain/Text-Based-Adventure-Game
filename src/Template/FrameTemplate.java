/**
 * This class will contain a template of how my customized Frame will look like 
 */
package Template;

import java.awt.Color;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class FrameTemplate extends JFrame {
	private final int WIDTH = 800;
	private final int HEIGHT = 600;

	/**
	 * Constructor with only the title as an argument
	 * 
	 * @param titleFrame
	 */
	public FrameTemplate(String titleFrame) {
		// set the title
		this.setTitle(titleFrame);
		// I set the background black using the library awl.color
		this.getContentPane().setBackground(Color.BLACK);
		// width and height
		this.setSize(WIDTH, HEIGHT);
		// After Pressing the X Button to exit we terminate the program
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// using no layout managers
		this.setLayout(null);
		// to center the frame
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}
	public FrameTemplate(String titleFrame,boolean helpPopup) {
		// set the title
		this.setTitle(titleFrame);
		// I set the background black using the library awl.color
		this.getContentPane().setBackground(Color.BLACK);
		// width and height
		this.setSize(WIDTH, HEIGHT);
		// After Pressing the X Button to exit we terminate the program
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// using no layout managers
		this.setLayout(null);
		// to center the frame
		//this.setLocationRelativeTo(null);
		this.setResizable(false);
	}

}
