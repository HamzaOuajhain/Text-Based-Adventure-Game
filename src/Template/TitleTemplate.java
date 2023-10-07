/**
 *  This class will contain a template of how my customized Title will look like 
 */
package Template;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TitleTemplate extends JPanel {

	public TitleTemplate(String Title) {
		final int posX = 100, posY = 0, widthxAxis = 600, heightyAxis = 120;
		this.setBounds(posX, posY, widthxAxis, heightyAxis); // set the bound of the container
		this.setBackground(Color.BLACK);// Setting the background color of the title

		JLabel labell = new JLabel(Title);
		final int size = 90;
		labell.setFont(new Font("arial", Font.PLAIN, size));
		labell.setForeground(Color.WHITE);
		labell.setHorizontalAlignment(JLabel.CENTER);
		labell.setVerticalAlignment(JLabel.CENTER);
		this.add(labell);
	}

}
