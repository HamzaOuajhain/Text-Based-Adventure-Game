/**
 * 	I want this class to be able to :
 * 			- Able to split the string of the user input for example (go south) -> stored in an array as |GO|SOUTH| 
 * 			- instead of hard coding every recognized keyword use HashMap (key,value) in order to make the same
 * 					Recognized keyword as a value and the key will afterward be checked.
 * 			- I make this HashMap in order to make it easy for me to recognize if the user input "String" is a valid input or no 
 *										
 *			- example instead of testing on | go north | proceed north | move north, I make one key and multiple values 
 *								- key in this case is Action -> the values are go/proceed/move/advance 
 *
 *			- a better explanation would be if we restrict the user to input only 2 words 
 *						-if we had 5 different words for the first word ("GO", "PROCEED", "MOVE", "ADVANCE", "TRAVEL")
 *						- 3 different combination in the 2 word ("North","N","UP")
 *						- we would need 3*5 = 15 test to avoid this I use HashMap
 * 
 */

package CoreClasses;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import Characters.Player;
import Objects.Item;

public class UserInputManager {
	// the key will contain the generic command, the ArrayList will have all the
	// ways you can implement that specific command

	private Map<String, ArrayList<String>> parser = new HashMap<>();

	public UserInputManager() {
		ArrayList<String> Action = new ArrayList<>(
				Arrays.asList("GO", "PROCEED", "MOVE", "ADVANCE", "TRAVEL", "MARCH"));
		ArrayList<String> Command = new ArrayList<>(Arrays.asList("PICK", "USE", "DROP", "TAKE", "EXAMINE", "LEAVE",
				"EXIT", "SEARCH", "FIGHT", "IGNORE", "KEEP", "GIVE"));

		ArrayList<String> Movement = new ArrayList<>(Arrays.asList("NORTH", "SOUTH", "EAST", "WEST", "UP", "DOWN",
				"LEFT", "RIGHT", "N", "S", "E", "W", "OUT"));
		ArrayList<String> Object = new ArrayList<>(
				Arrays.asList("POISON", "ITEM", "KEY", "KEYS", "ROOM", "DOOR", "POCKETS", "LETTER", "MONSTER", "MAP",
						"HEALINGPOTION", "POTION", "DEADLYPOISON", "DAGGER", "SWORD", "WEAPON", "FIGHTING", "DIAMOND"));

		ArrayList<String> specialCommand = new ArrayList<>(Arrays.asList("QUIT", "Q", "SAVE", "HELP"));

		parser = Map.ofEntries(new AbstractMap.SimpleEntry<String, ArrayList<String>>("Action", Action),
				new AbstractMap.SimpleEntry<String, ArrayList<String>>("Movement", Movement),
				new AbstractMap.SimpleEntry<String, ArrayList<String>>("Command", Command),
				new AbstractMap.SimpleEntry<String, ArrayList<String>>("Object", Object),
				new AbstractMap.SimpleEntry<String, ArrayList<String>>("specialCommand", specialCommand));

	}

	/**
	 * Search if it is part of the allowed commands or not.
	 * 
	 * @param userInput : String to be inputed and tested
	 * @return : return the key if we find the user input command or an empty string
	 *         otherwise
	 */
	public String Search(String userInput) {
		userInput = userInput.toUpperCase();
		for (Map.Entry elem : parser.entrySet()) {
			String commandkey = (String) elem.getKey();
			ArrayList<String> commandname = (ArrayList<String>) elem.getValue();
			if (commandname.contains(userInput)) {
				return commandkey;
			}

		}
		return ""; // return empty String if nothing is found

	}

	/**
	 * This method simply split and get rid of the white spaces. if the user enter
	 * go north we will have as a return GO|NORTH
	 * 
	 * @param stringtoparse : example go north
	 * @return : return an ArrayList look like this |go|north
	 */
	public ArrayList<String> Separate(String stringtoparse) {
		String[] parsedInput = stringtoparse.trim().split("\\s");
		ArrayList<String> parserout = new ArrayList<>();
		for (String a : parsedInput) {
			if (!a.isEmpty())
				parserout.add(a);
		}
		return parserout;

	}

	public String toString() {
		return parser.toString();
	}

	/**
	 * 
	 * check if the input is valid or not what makes a valid input ? - the user
	 * input can be a One word : special command or Two words : Action or command as
	 * first word and Movement or Object as a Second word
	 * 
	 * @param userInput : input of the user
	 * @return false means Invalid Input | true means Valid Input
	 */
	public boolean checkInput(String userInput) {// In order to check the input first I need to split and store it
		ArrayList<String> userInputArray = Separate(userInput);
		if (userInputArray.isEmpty())
			System.out.println("You haven't Inputed anything");
		else { // first I need to know the length of my arrayList -> how many words are in my
				// input
				// if there is 1 word see if the word is a specialCommand, example quit or q
				// if the sentence (user input) has 2 words do the necessary tests on it
			if (userInputArray.size() == 1) {// making sure our ArrayList is of size one -> one word as input
				if (Search(userInputArray.get(0)) == "specialCommand") {
					return true;
				}
			}

			else if (userInputArray.size() == 2) { // making sure our ArrayList is of size two -> two words as input so
													// we don't get an exception
				if ((Search(userInputArray.get(0)) == "Action" && Search(userInputArray.get(1)) == "Movement")
						|| (Search(userInputArray.get(0)) == "Command" && Search(userInputArray.get(1)) == "Object")) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * This function is where the user input is evaluated and tested on. Since I
	 * want the user to be able to use potion for example everywhere in the map.
	 * 
	 * @param userInput       : input of the user
	 * @param playerPosition  : position of the player
	 * @param playerInventory : the inventory of the player
	 * @param playerItem      : Right hand Item sometimes we want to drop this item
	 *                        or move it from the inventory to the right hand slot
	 * @param player          : used to heal the player ..
	 */
	public void inputAnalyzer(String userInput, Position playerPosition, Inventory playerInventory, Item playerItem,
			Player player) {
		userInput = userInput.toUpperCase();
		ArrayList<String> userInputArray = Separate(userInput);
		if (userInputArray.isEmpty())
			System.out.println("You haven't Inputed anything");
		else { // first I need to know the length of my arrayList -> how many words are in my
				// input
				// if there is 1 word see if the word is a specialCommand, example quit or q
				// if the sentence (user input) has 2 words do the necessary tests on it

			// making sure our ArrayList is of size one -> one word as input
			if (userInputArray.size() == 1 && (Search(userInputArray.get(0)) == "specialCommand")) {

				// "QUIT","Q","SAVE","HELP","ABOUT"
				switch (userInput) {
				case "QUIT":
				case "Q":
					System.exit(0);
					break;
				}

			} else if (userInputArray.size() == 2) { // making sure our ArrayList is of size two -> two words as input
														// so we don't get an exception

				if (Search(userInputArray.get(0)) == "Command" && Search(userInputArray.get(1)) == "Object") {
					if (userInputArray.get(0).equals("DROP")) {
						switch (userInputArray.get(1)) {
						case "POTION":
						case "HEALINGPOTION":
							playerInventory.deletItemByName("HealingPotion");
							break;
						case "POISON":
						case "DEADLYPOISON":
							playerInventory.deletItemByName("DeadlyPoison");
							break;
						case "WEAPON":
							playerInventory.deletItemByName("Dagger");
							playerInventory.deletItemByName("Sword");
							break;
						case "DAGGER":
							playerInventory.deletItemByName("Dagger");
							break;
						case "SWORD":
							playerInventory.deletItemByName("Sword");
							break;
						}
					}

					if (userInputArray.get(0).equals("USE")
							&& (userInputArray.get(1).equals("POTION") || userInputArray.get(1).equals("HEALINGPOTION"))
							&& (playerInventory.search("HealingPotion") || (playerInventory.search("DeadlyPoison")))) {
						player.regenHealth();
						playerInventory.deletItemByName("HealingPotion");
					}

				}
				// first case
				if (Search(userInputArray.get(0)) == "Action" && Search(userInputArray.get(1)) == "Movement") {
					switch (userInputArray.get(1)) {
					// "NORTH","SOUTH","EAST","WEST","UP","DOWN","LEFT","RIGHT","N","S","E"
					case "NORTH":
					case "N":
					case "UP":
						playerPosition.goUp();
						break;
					case "SOUTH":
					case "S":
					case "DOWN":
						playerPosition.goDown();
						break;
					case "WEST":
					case "W":
					case "LEFT":
						playerPosition.goLeft();
						break;
					default: // EAST - E - RIGHT
						playerPosition.goRight();
					}
				}
			}
		}
	}

}
