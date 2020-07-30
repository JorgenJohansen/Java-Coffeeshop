package coffeeshop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public class CoffeeShopProgram {
	
	//Using predicate to filter items in the itemlist
	public static void printFilter(List<Items> itemList, Predicate<Items> condition) {
		for(Items item : itemList) {
			if(condition.test(item)) {
				System.out.println("Item: " + item.getName());
			}
		}
	}
	
	//Comparator gir oss mulighet til å sammenligne elementer uten å
	//endre kildekode eller implementere grensesnitt
	public static Comparator<Items> comparator = new Comparator<Items>() {
		public int compare(Items item1, Items item2) {
			if(item1.getPrepareTime() > item2.getPrepareTime()) {
				return 1;
			}else {
				return -1;
			}
		}
	};
	
	public static void main(String[] args) {
		
		//Testing items
		Items item = new Items("Coffee",20,5);
		Items item2 = new Items("Caffe latte",30,5);
		Items item3 = new Items("Biscuit",20,0);
		
		List<Items> itemList = new ArrayList<>();
		
		itemList.add(item);
		itemList.add(item2);
		itemList.add(item3);
		
		//System.out.println("Filtered list: ");
		//printFilter(itemList, p -> p.getPrice() < 30);
		//printFilter(itemList, p -> p.getPrice() == 30);
		
		//Collections.sort(itemList);
		Collections.sort(itemList, comparator);
		for(Items element : itemList) {
			System.out.println(element);
		}
		
		
		
		/*
		for(Items element : itemList) {
			System.out.println(element.toString());
		}*/
		
		
		//Testing shop
		HashMap<Items,Integer> inventory = new HashMap<Items, Integer>();
		inventory.put(item, 20);
		inventory.put(item2, 20);
		inventory.put(item3, 20);
		
		Shop coffeeShop = new Shop(inventory, 2000);
		//System.out.println(coffeeShop.toString());
		
		//Testing manager and worker:
		Manager manager = new Manager("Alex",35,"Manager", new ArrayList<>());
		
		Worker worker1 = new Worker("Thomas",30,"Shift leader");
		Worker worker2 = new Worker("Roger",20,"Co-worker");
		
		manager.addWorker(worker1);
		manager.addWorker(worker2);
		
		
		
		
		//Testing customer
		Customer customer1 = new Customer("John", 26, 1000);
		Customer customer2 = new Customer("Sandra", 40, 1000);
		Customer customer3 = new Customer("Roland", 34, 1000);
		
		customer1.addItemToOrder(item, coffeeShop);
		customer1.addItemToOrder(item2, coffeeShop);
		customer1.addItemToOrder(item3, coffeeShop);
		
		customer2.addItemToOrder(item2, coffeeShop);
		
		customer3.addItemToOrder(item3, coffeeShop);
		
		worker1.speak();
		
		
		customer1.speak();
		
		worker1.prepareOrder(customer1, coffeeShop);
		worker1.prepareOrder(customer2, coffeeShop);
		worker1.prepareOrder(customer3, coffeeShop);
		
		
		//System.out.println(manager.getWaitingList());
		
		worker1.serveOrder(customer1);
		
		//customer1.update(customer1);
		
		//System.out.println(manager.receivePayment(customer1, coffeeShop));
		
		/*
		System.out.println("Før sortering: ");
		for(Items custItem : customer1.getOrderList()) {
			System.out.println(custItem.toString());
		}
		
		Collections.sort(customer1.getOrderList());
		
		System.out.println("Etter sortering: ");
		for(Items custItem : customer1.getOrderList()) {
			System.out.println(custItem.toString());
		}
		*/
		
		//System.out.println(customer1.toString());
		
		//customer1.removeItemFromOrder(item2);
		
		//customer1.saveOrder(customer1);
		
		//Tester transaksjoner:
		//manager.
		
		//Tester skriving og lesing av fil
		//String order = "Item name: Coffee, price: 20, prepare time: 5., ";
		//customer1.convertToCollection(order);
		//Collection<Items> loadedOrderList = customer1.loadOrder(customer1);
		//System.out.println(loadedOrderList.equals(customer1.getOrderList()));
		//System.out.println();
		/*
		if(loadedOrderList.toString().strip().equalsIgnoreCase(customer1.getOrderList().toString().strip()) ) {
			System.out.println("Hurrah!");
			
		}else {
			System.out.println("Java mener at: " + loadedOrderList.toString().strip() 
					+ " ikke er det samme som \n" + customer1.getOrderList().toString().strip());
		}*/
		
		
		
		
	}

}
