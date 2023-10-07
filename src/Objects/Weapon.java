/**
 * Weapon is an item used as a rightHand arm in the Game class in order to allow the player to deal higher damage
 * weapon can be a Dagger or a Sword - a Sword deal more damage.
 */
package Objects;

import java.util.Random;

public class Weapon extends Item {

	public Weapon(String itemName) {
		super(itemName);
	}

	public Integer useWeapon() {
		Integer answer = 0;
		if (getItemName().toUpperCase().equals("SWORD")) {
			final int min = 15;
			final int max = 20;
			answer = random(min, max);
		} else if (getItemName().toUpperCase().equals("DAGGER")) {
			final int min = 9;
			final int max = 14;
			answer = random(min, max);
		}
		return answer;
	}

	/**
	 * return a random Integer between min - max (max excluded)
	 * 
	 * @param min
	 * @param max
	 * @return : random Integer
	 */
	public Integer random(int min, int max) {
		Random randomInt = new Random();
		return randomInt.nextInt((max - min) + 1) + min;
	}

}
