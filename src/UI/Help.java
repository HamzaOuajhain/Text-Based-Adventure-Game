/**
 * Help is class defined to show the Frame Help where I show the player different commands allowed.
 */
package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import Template.ButtonTemplate;
import Template.FrameTemplate;

public class Help {
	/**
	 * creating instance of JFrame and setting the title
	 */
	private FrameTemplate gameWindow ;
	private ButtonTemplate backButton = new ButtonTemplate(620, 500, "Back");
/**
 * Constructor without argument used in the main menu
 */
	Help() {
		gameWindow =  new FrameTemplate("The Help Menu");
		final int heightyAxie1 = 100, heightyAxie2 = 200, heightyAxie3 = 250, heightyAxie4 = 300, heightyAxie5 = 450,
				heightyAxie6 = 500, heightyAxie7 = 550, heightyAxie8 = 600, heightyAxie9 = 700;
		final int fontsize = 30;
		/* Calling the text method that will set the text in our Help menu */
		gameWindow.add(text("Allowed Commands :", heightyAxie1, fontsize));
		gameWindow.add(text("In order to move : \"GO\",\"PROCEED\",\"MOVE\"" + ",\"ADVANCE\",\"TRAVEL\",\"MARCH\"",
				heightyAxie2));
		gameWindow
				.add(text("Directions : \"NORTH\",\"SOUTH\",\"EAST\",\"WEST\"" + ",\"N\", \"S\", \"E\", \"W\", \"OUT\"",
						heightyAxie3));
		gameWindow.add(text("Aditional Directions : \"UP\", \"DOWN\",\r\n" + "\"LEFT\", \"RIGHT\"", heightyAxie4));

		gameWindow.add(text("Command : \"PICK\",\"DROP\",\"TAKE\",\"EXAMINE\",\"LEAVE\", \"EXIT\"", heightyAxie5));
		gameWindow.add(text("Additional Command : \"SEARCH\", \"FIGHT\", \"IGNORE\", \"GIVE\\", heightyAxie6));

		gameWindow.add(
				text("Objects Commanded : \"POTION\",\"POISON\",\"DEADLYPOISON\",\"HEALINGPOTION\"", heightyAxie7));
		gameWindow.add(text("Additional Objects Commanded : \"DAGGER\", \"SWORD\", \"WEAPON\",\"ITEM\"", heightyAxie8));

		gameWindow.add(text("Special Command : \"QUIT\",\"Q\",\"SAVE\",\"HELP\"", heightyAxie9));

		gameWindow.add(backButton);
		gameWindow.setVisible(true);
		/**
		 * Functionality of the back button will close current Frame and open a the Menu
		 */
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.State = Main.STATE.MENU;
				gameWindow.dispose();
				Menu m = new Menu();
			}
		});
	}
	/**
	 * Constructor with one argument used as a popup inside the game "in the Class Play"
	 * @param menuTitle : title of the JFrame
	 */
	public Help(String menuTitle) {
		gameWindow =  new FrameTemplate(menuTitle,true);
		final int heightyAxie1 = 100, heightyAxie2 = 200, heightyAxie3 = 250, heightyAxie4 = 300, heightyAxie5 = 450,
				heightyAxie6 = 500, heightyAxie7 = 550, heightyAxie8 = 600, heightyAxie9 = 700;
		final int fontsize = 30;
		/* Calling the text method that will set the text in our Help menu */
		gameWindow.add(text("Allowed Commands :", heightyAxie1, fontsize));
		gameWindow.add(text("In order to move : \"GO\",\"PROCEED\",\"MOVE\"" + ",\"ADVANCE\",\"TRAVEL\",\"MARCH\"",
				heightyAxie2));
		gameWindow
				.add(text("Directions : \"NORTH\",\"SOUTH\",\"EAST\",\"WEST\"" + ",\"N\", \"S\", \"E\", \"W\", \"OUT\"",
						heightyAxie3));
		gameWindow.add(text("Aditional Directions : \"UP\", \"DOWN\",\r\n" + "\"LEFT\", \"RIGHT\"", heightyAxie4));

		gameWindow.add(text("Command : \"PICK\",\"DROP\",\"TAKE\",\"EXAMINE\",\"LEAVE\", \"EXIT\"", heightyAxie5));
		gameWindow.add(text("Additional Command : \"SEARCH\", \"FIGHT\", \"IGNORE\", \"GIVE\\", heightyAxie6));

		gameWindow.add(
				text("Objects Commanded : \"POTION\",\"POISON\",\"DEADLYPOISON\",\"HEALINGPOTION\"", heightyAxie7));
		gameWindow.add(text("Additional Objects Commanded : \"DAGGER\", \"SWORD\", \"WEAPON\",\"ITEM\"", heightyAxie8));

		gameWindow.add(text("Special Command : \"QUIT\",\"Q\",\"SAVE\",\"HELP\"", heightyAxie9));

		
		gameWindow.setVisible(true);
	}
	
	/**
	 * 
	 * @param text  : Text that I want to write in the JFrame.
	 * @param yAxie : the exact pixel position in the y axis.
	 * @return
	 */
	public JLabel text(String text, int yAxie) {
		final int fontSize = 15;
		return text(text, yAxie, fontSize);
	}

	public JLabel text(String text, int yAxie, int fontSize) {
		final int posX = 50, posY = 50, widthxAxis = 700;
		JLabel labelHelp = new JLabel(text);
		labelHelp.setForeground(Color.GREEN);
		labelHelp.setFont(new Font("Serif", Font.BOLD, fontSize));
		labelHelp.setBounds(posX, posY, widthxAxis, yAxie);
		return labelHelp;

	}
	public void setVisible(boolean setter) {
		this.setVisible(setter);
	}

}
