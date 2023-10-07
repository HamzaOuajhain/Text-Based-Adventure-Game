/**
 *  This class will contain a template of how my customized button will look like 
 */
package Template;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ButtonTemplate extends JButton {

	public ButtonTemplate(int posX, int posY, String name) {
		final int sizexAxis = 150, sizeyAxis = 50;
		this.setBounds(posX, posY, sizexAxis, sizeyAxis); // x axis, y axis, width, height
		this.setText(name);// Setting the text in the Button
		this.setBackground(Color.BLACK);// Setting the background color of the button
		this.setForeground(Color.green);
		this.setFont(new Font("Articulat", Font.BOLD, 16));// Setting the font of the button
	}

}
