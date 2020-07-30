package coffeeshop;

import java.util.HashMap;
import java.util.Map;

public class Shop {
	
	private HashMap<Items,Integer> inventory = new HashMap<Items, Integer>();
	private int cashier;
	
	
	public Shop(HashMap<Items, Integer> inventory, int cashier) {
		validateCashier(cashier);
		this.setInventory(inventory);
		this.setCashier(cashier);
	}


	public HashMap<Items, Integer> getInventory() {
		return inventory;
	}


	public void setInventory(HashMap<Items, Integer> inventory) {
		this.inventory = inventory;
	}


	public int getCashier() {
		return cashier;
	}


	public void setCashier(int cashier) {
		validateCashier(cashier);
		this.cashier = cashier;
	}
	
	public void validateCashier(int amount) {
		if(amount < 0) {
			throw new IllegalArgumentException("Amount can't be negative");
		}
	}
	
	public String toString() {
		String message = "";
		for(Map.Entry<Items, Integer> item : inventory.entrySet()) {
			message += "Item: " + item.getKey().getName() +  " has " + item.getValue() + " units left.\n";
		}
		
		return message;
	}
	
	//Implementer disse hvis du har tid til det
	public void saveInventory(HashMap<Items, Integer> inventory) {
		
	}
	
	public HashMap<Items, Integer> loadInventory() {
		return null;
	}
	
	
	
	

}
