/**
 * Class representing Monster character
 */
package Characters;

import java.util.Random;

public class Monster extends Character {
	/**
	 * constructor with one argument calling the super class in this case Character
	 * 
	 * @param monsterHealth
	 */
	public Monster(int monsterHealth) {
		super(monsterHealth);
	}

	/**
	 * this function is used when the monster attack a player.
	 * 
	 * @return an Integer ranging from 12 - 16
	 */
	public int monsterAttack() {
		final int minMonsterDamage = 12, maxMonsterDamage = 17;
		return random(minMonsterDamage, maxMonsterDamage);
	}

	/* return a random Integer between min - max (max excluded) */
	public Integer random(int min, int max) {
		Random randomInt = new Random();
		return randomInt.nextInt((max - min) + 1) + min;
	}
}
