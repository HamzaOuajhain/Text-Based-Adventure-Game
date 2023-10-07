/**
 * 	Inventory will be able to store a fixed amount of Items (9) 
 *  - able to delete-add an item from an inventory
 *  - able to search for an item in the inventory
 * 
 */
package CoreClasses;

import Objects.Item;

public class Inventory {

	private final int slots = 9;
	private Item[] inventory;

	public Inventory() {
		inventory = new Item[slots];

	}

	/**
	 * adds item to the inventory if there is a slot available
	 * 
	 * @param itemToAdd : Item added to the Inventory if there is a slot available
	 */
	public void addItem(Item itemToAdd) {
		boolean temp = true;
		System.out.println("item to be added in the Inventory : " + itemToAdd);
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i] == null && temp) {
				inventory[i] = itemToAdd;
				temp = false;
			}
		}
	}

	/**
	 * look for the item in the inventory
	 * 
	 * @param itemName : Item to look for
	 * @return true if found, false if the item is not found.
	 */
	public boolean search(String itemName) {
		for (int i = 0; i < inventory.length; i++) {
			/*
			 * to make sure we don't use .equals when temp.getItemName() will return null
			 * case : when the user use the command -> use potion and there is no potion in
			 * the inventory.
			 */
			if (inventory[i] != null) {
				Item temp = inventory[i];
				if (temp.getItemName().equals(itemName)) {
					return true;
				}
			}
		}
		System.out.println("item : " + itemName + " not found!");
		return false;

	}

	/**
	 * delete Item from the inventory : we delete the first encounter
	 * 
	 * @param ItemName : name of the item to be deleted
	 */
	public void deletItemByName(String ItemName) {
		int i = 0;
		boolean continueExecution = true;
		while (i < inventory.length && continueExecution) {
			if (inventory[i] != null) {
				Item temp = inventory[i];
				if (temp.getItemName().equals(ItemName)) {
					inventory[i] = null;
					continueExecution = false;

				}
			}
			i++;
		}
	}

	public String toString() {
		String s = "";

		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i] != null) {
				s += inventory[i].toString();
				s += " - ";
			}
		}
		return s;
	}

}
