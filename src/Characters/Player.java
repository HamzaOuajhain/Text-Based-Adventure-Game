package Characters;

import Objects.Weapon;

public class Player extends Character {
	/**
	 * Constructor to create a player with no argument, by default the health is set
	 * to 100.
	 */
	public Player() {
		super();
	}

	/**
	 * Regenerate all the health of the player using the potion
	 * 
	 */
	public void regenHealth() {
		setHealth(100);
	}

	public int playerAttack(Weapon weaponType) {
		final int defaultPlayerAttack = 5;

		if (weaponType == null)
			return defaultPlayerAttack;
		return weaponType.useWeapon();

	}

}
