package coffeeshop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author jorgen
 * This class is a basis for items
 * Should rename this to items since a coffeeshop can sell pastries
 */

public class Items implements Comparable<Items>, Iterable<Items>{
	
	private String name;
	private int price;
	private int prepareTime;
	
	List<Items> list = new ArrayList<>();
	
	public void addElement(Items item) {
		list.add(item);
	}
	public void removeElement(Items item) {
		list.remove(item);
	}
	
	
	public Items(String name, int price, int prepareTime) {
		validatePrice(price);
		validateTime(prepareTime);
		this.name = name;
		this.price = price;
		this.prepareTime = prepareTime;
	}
	
	public int getPrice() {
		return price;
	}
	
	public int getPrepareTime() {
		return prepareTime;
	}
	
	public void setPrice(int price) {
		validatePrice(price);
		this.price = price;
	}
	
	public void setprepareTime(int prepareTime) {
		validateTime(prepareTime);
		this.prepareTime = prepareTime;
	}
	
	public void validatePrice(int price) {
		if(price <= 0) {
			throw new IllegalArgumentException("A price cannot be negative or less than one");
		}
	}
	
	public void validateTime(int prepareTime) {
		if(prepareTime < 0) {
			throw new IllegalArgumentException("Time cannot be negative");
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return "Item name: " + name + ", price: " + price + ", prepare time: " + prepareTime + ".";
		
	}

	@Override
	public int compareTo(Items item) {
		// TODO Auto-generated method stub
		if(this.getPrice() > item.getPrice()) {
			return 1;
		}else if(this.getPrice() == item.getPrice()) {
			return 0;
		}else {
			return -1;
		}
	}

	@Override
	public Iterator<Items> iterator() {
		return new ItemsIterator<Items>(list);
	}
	
	public class ItemsIterator<Items> implements Iterator<Items>{
		
		int pointer = 0;
		List<Items> internalList;
		
		public ItemsIterator(List<Items> internalList) {
			this.internalList = internalList;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			
			if(internalList.size() >= pointer + 1) {
				return true;
			}
			return false;
		}

		@Override
		public Items next() {
			// TODO Auto-generated method stub
			
			Items item = internalList.get(pointer);
			pointer++;
			return item;
		}
		
	}
	

}
