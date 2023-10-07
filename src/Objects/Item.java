/**
 * I have two types of objects consumable/weapons but since I delete the consumable from the Inventory once used
 * I don't have to add another class for it. The class Item might represent Potion/Poison/Diamond in the game .
 */
package Objects;

public class Item {
	private String itemName;

	public Item(String item) {
		itemName = item;
	}

	public String getItemName() {
		return itemName;
	}

	public String toString() {
		return itemName;
	}
}
