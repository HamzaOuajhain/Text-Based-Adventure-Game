/**
 *  This class will display the Menu Frame
 */
package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import CoreClasses.Game;
import Template.ButtonTemplate;
import Template.FrameTemplate;
import Template.TitleTemplate;

public class Menu {
	/**
	 * Variable declaration
	 */
	private FrameTemplate menu = new FrameTemplate("Main Menu");

	private TitleTemplate title_Menu;

	private ButtonTemplate playButton;
	private ButtonTemplate helpButton;
	private ButtonTemplate quitButton;

	/**
	 * 
	 * Creating the Frame(Page) of the main menu where I use the
	 * templates(Button-Frame-Titles)
	 *
	 */
	public Menu() {
		this("Treasure Hunt");
	}

	public Menu(String title) {
		final int posX = 320, posY1 = 200, posY2 = 300, posY3 = 400;

		title_Menu = new TitleTemplate(title);
		/**
		 * buttons creation
		 */
		playButton = new ButtonTemplate(posX, posY1, "Play");
		helpButton = new ButtonTemplate(posX, posY2, "Help");
		quitButton = new ButtonTemplate(posX, posY3, "Quit");

		/**
		 * Adding button to my frame
		 */
		menu.add(playButton);
		menu.add(helpButton);
		menu.add(quitButton);

		buttonListeners();

		menu.add(title_Menu);

		/**
		 * Show the Frame only when the ENUM = MENU
		 * 
		 */
		if (Main.State == Main.STATE.MENU)
			menu.setVisible(true);
		else
			menu.setVisible(false);
	}

	/**
	 * buttonListeners : detect when the button is pressed we change states, create
	 * another instance of Game in the case of playButton we create a Help instance
	 * in the case of helpButton and we terminate the main thread if the quitButton
	 * is triggered
	 */
	public void buttonListeners() {
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.State = Main.STATE.PLAY;
				menu.dispose();
				Game startGame = new Game();
			}
		});
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.State = Main.STATE.HELP;
				menu.dispose();
				Help helpFrame = new Help();
			}
		});

		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(Main.State);
				System.exit(0);
			}
		});
	}
}
