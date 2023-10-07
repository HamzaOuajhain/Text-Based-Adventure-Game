/**
 * This class is where I test on the user input and change the displays accordingly, This is where I call all my classes and use them.
 */
package CoreClasses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import Characters.Monster;
import Characters.Player;
import Objects.Item;
import Objects.Weapon;
import UI.Play;

public class Game {
	private Play uiPlay;
	private Position map;

	private Player thePlayer;
	private Monster theMonster;

	private Item item;
	private Weapon rightHandWeapon;
	private Inventory inventory;

	private boolean trapFound = false;
	private boolean treasureFound = false;
	private boolean fightStarted = false;
	private boolean gameEnded = false;

	/**
	 * the constructor initiate the Player with his username, create the Frame Play,
	 * initial the position of the player
	 */
	public Game() {
		thePlayer = new Player();
		uiPlay = new Play();
		map = new Position();
		inventory = new Inventory();
		Start();
		waitInput();
	}

	/* the start of the story */
	public void Start() {
		uiPlay.setText(
				"You found yourself in a dark room.\nYou don't feel you left arm... maybe it's broken or paralised who knows...\n"
						+ "No one seems to be arround, but there is an open door in front of you."
						+ "\nWhat will you do ?" + "\n\n>Examine Room" + "\n>Go out");

	}

	/**
	 * 
	 * This Method is complicated to understand due to the fact that since am using
	 * Graphical interface, require to add an action listener in order to detect
	 * user input unlike scanner I can create an infinite amount of instances and
	 * therefore make the code cleaner.
	 * 
	 * 
	 *
	 */
	public void waitInput() {
		uiPlay.getTextField().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// if the player dies the game quit if the user tries to move
				if (gameEnded) {
					try {
						Thread.sleep(3000);
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}

					System.exit(0);
				}
				UserInputManager test = new UserInputManager();
				String userInput = uiPlay.getTextField().getText().toUpperCase();
				uiPlay.getTextField().setText("");// reset the input

				System.out.println("--------------------------");
				System.out.println("Your inventory is : " + inventory);

				// If the text is a valid input.
				if (test.checkInput(userInput)) {

					/*
					 * update the value of position of the player and check if the input is valid
					 * and then move in the map
					 */

					test.inputAnalyzer(userInput, map, inventory, rightHandWeapon, thePlayer);
					/**
					 * updating the Map indicator / the position of the player / and the
					 * rightHandWeapon in the GUI
					 */
					if (rightHandWeapon != null)
						uiPlay.updateIndicators(map.getActualPosition(), thePlayer.getHealth(),
								rightHandWeapon.getItemName(), inventory);
					else
						uiPlay.updateIndicators(map.getActualPosition(), thePlayer.getHealth(), "none", inventory);
					/* Updating the Inventory */

					if (userInput.equals("TAKE SWORD") && inventory.search("Sword")) {
						rightHandWeapon = new Weapon("Sword");
						inventory.deletItemByName("Sword");
					} else if (userInput.equals("TAKE DAGGER") && inventory.search("Dagger")) {
						rightHandWeapon = new Weapon("Dagger");
						inventory.deletItemByName("Dagger");
					}

					/* cases when the user find a treasure */
					if (map.getActualPosition() != "Start" && map.getActualPosition() != "Finish") {
						if (treasureFound == true && map.getActualPosition() != "Start"
								&& map.getActualPosition() != "Finish") {
							if (userInput.equals("PICK ITEM")) {
								inventory.addItem(item);
								uiPlay.setText("The item : " + item.getItemName()
										+ " was added to your inventory.\n\n> Go north \n> Go east \n> Go west \n> Go south");
							} else if (userInput.equals("IGNORE ITEM"))
								waitSentence();

						}
						/* cases when the user find a treasure */
						if (trapFound == true && map.getActualPosition() != "Start"
								&& map.getActualPosition() != "Finish") {
							if (userInput.equals("FIGHT MONSTER") || userInput.equals("KEEP FIGHTING"))
								monsterFight();

						}
					}

					if (map.getActualPosition().equals("Start")) {
						switch (userInput) {
						case "EXAMINE ROOM":
							uiPlay.setText(
									"You found a flash light, a Map and a Letter...\n\n>Examine Letter.\n\n>go out.");
							break;
						case "EXAMINE LETTER":
							uiPlay.setText(
									"There is a room in the north/east of this building, you will find a guard.\n\nIf you go to him without his precious diamond, you will most likely DIE! "
											+ "\n\nTherefore find it and as a recompense we will grant you your freedom :)\n>Examine map.\n>Go out.");
							break;
						case "EXAMINE MAP":
							uiPlay.setText(
									"\t\t\t(Finish)\n\n\t(0,2)\t(1,2)\t(2,2)\n(Start)\t(0,1)\t(1,1)\t(2,1)\n\t(0,0)\t(1,0)\t(2,0)\n>Go out");
							break;
						default:
							InvalidInput();
							break;
						}
					}
					/*
					 * If the Actual position != Previous position we do something Because sometimes
					 * if you are in the north west edge of the map and you move north we shouldn't
					 * do anything because the player didn't change rooms.
					 */
					if ((!map.getActualPosition().equals(map.getPreviousPosition()))) {
						// System.out.println("Actual Position != Previous Position");
						treasureFound = false;
						trapFound = false;
						if (map.getActualPosition() == "Finish") {
							if (inventory.search("Diamond")) {
								uiPlay.setText("You Found the Diamond the Guard will set you free now ...");
								gameEnded = true;
							} else
								uiPlay.setText("You still haven't found the diamond");
						} else {
							/*
							 * I want to have a 60 /100 chance to get a treasure - 40/100 chance to find a
							 * monster -> trap
							 */
							final int minRandomInteger = 0, maxRandomInteger = 100;
							final int chanceToGetTrap = 41;
							if (random(minRandomInteger, maxRandomInteger) < chanceToGetTrap) {
								// trap found
								Trap();
							} else {
								// treasure found
								Treasure();
							}

						}
					}
				}

				else {
					InvalidInput();
				}
				// make sure we detect if the player stayed in the same room
				map.setPreviousPosition(map.getActualPosition());

			}
		});
	}

	/**
	 * Calling this function will create an object depending on the chance of the
	 * player
	 * 
	 */
	public void Treasure() {

		final int minRandomInteger = 0, maxRandomInteger = 100;
		int randomInteger = random(minRandomInteger, maxRandomInteger);
		boolean chanceToGetDiamand = randomInteger < 10;
		boolean chanceToGetSword = randomInteger >= 10 && randomInteger < 25;
		boolean chanceToGetDagger = randomInteger >= 25 && randomInteger < 55;
		boolean chanceToGetDeadlyPoison = randomInteger >= 55 && randomInteger < 60;

		treasureFound = true;
		if (chanceToGetDiamand) {
			item = new Item("Diamond");
		}

		else if (chanceToGetSword)
			item = new Weapon("Sword");

		else if (chanceToGetDagger)
			item = new Weapon("Dagger");

		else if (chanceToGetDeadlyPoison)
			item = new Item("DeadlyPoison");

		else
			item = new Item("HealingPotion");

		System.out.println("Item created in treasure : " + item);

		uiPlay.setText("There is a Treasure In front of you !!!" + "\n\nWhat will you do ? " + "\n\n>Ignore Item."
				+ "\n\n>Pick Item.", 20);
	}

	/**
	 * Message displayed before the fight
	 */
	public void Trap() {
		trapFound = true;
		uiPlay.setText("You found a Monster In front of you !!!" + "\nWhat will you do ? " + "\n>Fight Monster."
				+ "\n>Go north\n>Go south\n>Go east\n>Go west");
	}

	/**
	 * Fight scenario between the player and the monster
	 */
	public void monsterFight() {
		
		if (!fightStarted) {
			final int minimumHp = 25, maximumHP = 40;
			final int monsterHP = random(minimumHp, maximumHP);
			theMonster = new Monster(monsterHP);
			fightStarted = true;

			thePlayer.receiveDamage(theMonster.monsterAttack());

			theMonster.receiveDamage(thePlayer.playerAttack(rightHandWeapon));
			uiPlay.setText("You attacked the Monster , he received " + thePlayer.playerAttack(rightHandWeapon)
					+ " damage" + "\nthe Monster attacked you back, you received " + theMonster.monsterAttack()
					+ " damage" + "\n>What will you do ?\n>Keep fighting\n>Go north\t>Go south\n>Go east\t>Go west");

		} else if (thePlayer.getHealth() > 0 && theMonster.getHealth() > 0 && fightStarted) {
			thePlayer.receiveDamage(theMonster.monsterAttack());
			theMonster.receiveDamage(thePlayer.playerAttack(rightHandWeapon));
			uiPlay.setText("You attacked the Monster again, he received " + thePlayer.playerAttack(rightHandWeapon)
					+ " damage" + "\nthe Monster attacked you back, you received " + theMonster.monsterAttack()
					+ " damage" + "\n>What will you do ?\n>Keep fighting\n>Go North\t>Go South\n>Go East\t>Go West");

		}
		final int characterDead = 0;
		if (thePlayer.getHealth() <= characterDead) {
			uiPlay.setText("Game Over !", 35);
			gameEnded = true;
		} else if (theMonster.getHealth() <= characterDead) {
			uiPlay.setText("The Monster Died ! \n\n>Go North\n>Go South\n>Go East\n>Go West");
			final int minimum = 0, maximum = 101, threshold = 30;
			int chanceToGetDiamond = random(minimum, maximum);
			if (chanceToGetDiamond < threshold) {
				item = new Item("Diamond");
				inventory.addItem(item);
			}
			fightStarted = false;
		}
		System.out.println("Monster hp : "+theMonster);
	}

	/**
	 * when the user finish a task we ask him to change map
	 */
	public void waitSentence() {
		uiPlay.setText("There is Nothing to do in this Map! " + "\n\n> Go north" + "\n> Go east" + "\n> Go west"
				+ "\n> Go south", 25);
	}

	public void InvalidInput() {
		uiPlay.getTextField().setText("Invalid Input.");
	}

	/* return a random Integer between min - max (max excluded) */
	public Integer random(int min, int max) {
		Random randomInt = new Random();
		return randomInt.nextInt((max - min) + 1) + min;
	}

}
