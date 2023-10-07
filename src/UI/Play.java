/**
 * Play class is instanced in the Game class, in this class I initiate the methods that will be used to interact with the player
 */
package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import CoreClasses.Inventory;
import Template.ButtonTemplate;
import Template.FrameTemplate;
import Template.TitleTemplate;

public class Play {

	private FrameTemplate gameWindow = new FrameTemplate("Treasure Hunt Game");
	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	private JTextField textField;
	private JLabel labelMapIndicator, labelPlayerHealth, labelPlayerHealth1, labelRightHandWeapon,
			labelRightHandWeapon1, labelInventory, labelInventory1;
	private JTextArea textStory = new JTextArea();

	public Play() {

		textStory();
		/*
		 * Indicator in the bottom left of the frame indicating witch map are we
		 * currently in
		 */
		setMapIndicators();

		final int posX = 600, posY = 500, posYHelp = 400;

		ButtonTemplate quitButton = new ButtonTemplate(posX, posY, "Quit");
		ButtonTemplate helpButton = new ButtonTemplate(posX, posYHelp, "Help");

		gameWindow.add(helpButton);
		gameWindow.add(quitButton);
		/* adding user input field and adding it to the frame */
		final int posXText = 100, posYText = 400, textWidth = 500, textHeight = 80;
		setTextField(TextField_Template(panel1, getTextField(), posXText, posYText, textWidth, textHeight, "",
				"  --   Player Input   --   "));
		panel1.add(getTextField());
		gameWindow.add(panel1);

		TitleTemplate title_GameWindow = new TitleTemplate("Treasure Hunt");
		gameWindow.add(title_GameWindow);

		if (Main.State == Main.STATE.PLAY)
			gameWindow.setVisible(true);
		else {
			gameWindow.setVisible(false);
		}

		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.State = Main.STATE.HELP;
				Help helpFram = new Help("The Help Menu");
			}
		});

		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Quit");
				System.exit(0);
			}
		});

	}

	public JTextField TextField_Template(JPanel panel, JTextField text, int posX, int posY, int width, int height,
			String contained_string, String outer_String) {
		final int boundPosX = 50, boundposY = 100, boundwidthxAxis = 100, boundheightyAxis = 30;

		panel.setBounds(posX, posY, width, height);
		panel.setBackground(Color.black);
		final int size = 40;
		text = new JTextField(contained_string, size);
		text.setForeground(Color.GREEN);
		text.setBackground(Color.BLACK);
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setBounds(boundPosX, boundposY, boundwidthxAxis, boundheightyAxis);

		JLabel l1 = new JLabel(outer_String);
		l1.setForeground(Color.GREEN);

		panel.add(text);
		panel.add(l1);
		return text;
	}

	/* This will set the text responsible at displaying the story */
	public void textStory() {
		final int posX = 0, posY = 150, widthxAxis = 800, heightyAxis = 200, policeSize = 15;

		/* JPanel for the game story.. */
		this.panel2.setBounds(posX, posY, widthxAxis, heightyAxis);
		this.panel2.setBackground(Color.black);
		this.textStory.setText("start");
		this.textStory.setBackground(Color.black);
		this.textStory.setForeground(Color.white);
		this.textStory.setFont(new Font("Serif", Font.BOLD, policeSize));
		this.textStory.setEditable(false);
		panel2.add(textStory);
		gameWindow.add(panel2);

	}

	/**
	 * Set text with only one argument the text to input default font size is 20
	 * 
	 * @param text : text to display
	 */
	public void setText(String text) {
		final int policeSize = 20;
		setText(text, policeSize);
	}

	/**
	 * Set text with two arguments text to input + the size of the Font
	 * 
	 * @param text : text to display
	 * @param size : size of the font
	 */
	public void setText(String text, int size) {
		textStory.setText(text);
		textStory.setFont(new Font("Serif", Font.BOLD, size));
	}

	public void setMapIndicators() {

		final int posX = 0, posY = 520, widthxAxis = 80, heightyAxis = 30;
		/**
		 * setting up Room Indicator
		 */
		labelMapIndicator = new JLabel("Start");
		labelMapIndicator.setBounds(posX, posY, widthxAxis, heightyAxis);
		setLabel(labelMapIndicator, posX, posY, widthxAxis, heightyAxis);

		/**
		 * Setting up Player Health
		 */
		final int posXPh = 120, posYPh = 520, widthxAxisPh = 80, heightyAxisPh = 30;
		final int posXPh1 = 200, posXRHW = 300, widthxAxisRHW = 280, posXRHW1 = 500, posXI = 0, posYI = 120,
				widthI = 100, widthI1 = 700;
		labelPlayerHealth = new JLabel("Health : ");
		setLabel(labelPlayerHealth, posXPh, posYPh, widthxAxisPh, heightyAxisPh);

		labelPlayerHealth1 = new JLabel("100");
		setLabel(labelPlayerHealth1, posXPh1, posYPh, widthxAxisPh, heightyAxisPh);

		labelRightHandWeapon = new JLabel("Right Hand Weapon : ");
		setLabel(labelRightHandWeapon, posXRHW, posYPh, widthxAxisRHW, heightyAxisPh);

		labelRightHandWeapon1 = new JLabel("none");
		setLabel(labelRightHandWeapon1, posXRHW1, posYPh, widthxAxisRHW, heightyAxisPh);

		labelInventory = new JLabel("Inventory : ");
		setLabel(labelInventory, posXI, posYI, widthI, heightyAxisPh);
		labelInventory1 = new JLabel("empty");
		setLabel(labelInventory1, widthI, posYI, widthI1, heightyAxisPh);

		/* adding components to the frame */
		gameWindow.add(labelInventory1);
		gameWindow.add(labelInventory);
		gameWindow.add(labelRightHandWeapon);
		gameWindow.add(labelRightHandWeapon1);
		gameWindow.add(labelMapIndicator);
		gameWindow.add(labelPlayerHealth1);
		gameWindow.add(labelPlayerHealth);
	}

	public void setLabel(JLabel jLabel, int posX, int posY, int width, int heigth) {
		final int policeSize = 20;
		jLabel.setBounds(posX, posY, width, heigth);
		jLabel.setForeground(Color.GREEN);
		jLabel.setFont(new Font("Serif", Font.BOLD, policeSize));
	}

	public void updateIndicators(String room, Integer playerHealth, String weaponName, Inventory inventory) {
		String ph = playerHealth.toString();
		labelPlayerHealth1.setText(ph);
		labelMapIndicator.setText(room);
		labelRightHandWeapon1.setText(weaponName);
		labelInventory1.setText(inventory.toString());

	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

}
