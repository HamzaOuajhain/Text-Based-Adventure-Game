/** 
/* - The Monster / Player class will extend from this class 
 * - both monsters and the player have health/name and both can take damage .. the main difference is that the player can regene health
 */

package Characters;

public abstract class Character {

	private Integer health = 100;

	/**
	 * set player health to 100 : start of the game
	 */
	public Character() {
		final int playerHealth = 100;
		health = playerHealth;
	}

	/**
	 * set character health constructor with one argument, used with the class
	 * Monster.
	 * 
	 * @param characterHealth
	 */
	public Character(int characterHealth) {
		health = characterHealth;
	}

	public void receiveDamage(Integer damage) {
		setHealth(getHealth() - damage);
	}

	public Integer getHealth() {
		return health;
	}

	public void setHealth(Integer health) {
		this.health = health;
	}

	public String toString() {
		return "Health : " + getHealth();
	}

}
