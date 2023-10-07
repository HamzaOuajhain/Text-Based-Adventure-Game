/**
 *  - the player start in the room called start and has to move 
 *     and find the diamond in order to give it to a guard in order to set him free
 *     the diamond will be in a treasure or maybe dropped after fighting a Monster 
 *     every time you change room you have a chance 
 *     to get a treasure or to fight a monster. You have a 30% chance to get a diamond 
 *     fighting a monster and 10% chance finding the Diamond in the Treasure, you can 
 *     find weapons in the treasure use take dagger or take sword for example to equip it
 *     you can drop items, use potions careful sometimes you pick up poison instead of potions 
 *     
 *     
 * Main method, where I use enum called State in order to switch between the Frames 
 * @author Hamza OUAJHAIN
 * 
 * credits :- the implementation of the graph is inspired by the data structure course 
 * 			- the use of random Integer is inspired by sources online
 * 			- the use of Thresh.sleep also is inspired by sources at stackoverflow
 */
package UI;

public class Main {
	/**
	 * I have tree states in my program Menu : Showing different states of the game.
	 * PLAY : State called when we start the game. Help : Showing different commands
	 * that the user is allowed to type.
	 * 
	 */
	public enum STATE {
		MENU, PLAY, HELP
	};

	/**
	 * the only static variable in the project needed in order to view in witch
	 * JFrame am currently at
	 */
	public static STATE State = STATE.MENU;

	/**
	 * Main method I don't have to use a loop since my program is event driven
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Menu startGame = new Menu();
	}
}