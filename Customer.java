package coffeeshop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Customer extends Person implements Observer {
	
	private int money;
	private List<Items> orderList = new ArrayList<>();

	public Customer(String name, int age, int money) {
		super(name, age);
		validateMoney(money);
		this.money = money;
		// TODO Auto-generated constructor stub
	}
	
	
	
	public List<Items> getOrderList() {
		return orderList;
	}
	
	public void setOrderList(List<Items> orderList) {
		this.orderList = orderList;
	}
	
	public int getMoney() {
		return money;
	}
	
	public void setMoney(int money) {
		validateMoney(money);
		this.money = money;
	}
	
	public void validateMoney(int money) {
		if(money < 0) {
			throw new IllegalArgumentException("Cannot have negative amounts");
		}
	}
	
	public void addItemToOrder(Items item, Shop shop) {
		
		if(shop.getInventory().get(item) > 0 && shop.getInventory().containsKey(item)) {
			orderList.add(item);
		}else {
			throw new IllegalArgumentException("We are out of that item");
		}
	}
	
	public void removeItemFromOrder(Items item) {
		if(orderList.size() > 0 && orderList.contains(item)) {
			List<Items> filteredList = orderList
					.stream()
					.filter(p -> p != item)
					.collect(Collectors.toList());
			setOrderList(filteredList);
		}else {
			throw new IllegalArgumentException("There exists no such item");
		}
	}

	
	
	public String toString() {
		String message = "";
		Collections.sort(orderList);
		Iterator iterator = orderList.iterator();
		while(iterator.hasNext()) {
			message += "[" + iterator.next() + "]";
			if(iterator.hasNext()) {
				message += ", \n";
			}
		}
		return message;
	}
	
	public void saveOrder(Customer customer) {
		String filePath = "C:\\Users\\jorge\\tdt4100-v2020-master\\git\\tdt4100-v2020-students\\minegenkode\\src\\coffeeshop\\" + customer.getName() + ".txt";
		System.out.println("Customer: " + customer.getName() + "'s order is beeing stored to file " + filePath);
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
			String content = "";
			for(Items item : orderList) {
				content += item.toString() + ", \n";
			}
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("An I/O error occurred");
		}
	}
	
	//Denne kjører etter readline
	public Items convertToCollection(String order){
		System.out.println(order);
		//Splitting the string in a list of strings
		String[] list = order.split(",");

		//Getting the name by splitting on : and taking the second element
		String[] namelist = list[0].split(":");
		String name = namelist[1].strip();
		
		//Getting the price by splitting on : and taking the second element
		String[] pricelist = list[1].split(":");
		int price = Integer.parseInt(pricelist[1].strip());
		//System.out.println(pricelist[1]);
		
		//Getting the time by splitting on : and taking the second element
		String[] timelist = list[2].split(":");
		int time = Integer.parseInt(timelist[1].replace(".", "").strip());
		//System.out.println(time);
		
		return new Items(name,price,time);
	}
	
	public Collection<Items> loadOrder(Customer customer){
		String filePath = "C:\\Users\\jorge\\tdt4100-v2020-master\\git\\tdt4100-v2020-students\\minegenkode\\src\\coffeeshop\\" + customer.getName() + ".txt";
		System.out.println("Customer: " + customer.getName() + "'s order is beeing read from file " + filePath);
		Collection<Items> loadedOrderList = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			
			List<String> allLines = Files.readAllLines(Paths.get(filePath));
			for(String line : allLines) {
				//System.out.println(line);
				loadedOrderList.add(convertToCollection(line));
			}
			
			reader.close();
			
		} catch (IOException e) {
			System.out.println("An I/O error occurred");
		}
		return loadedOrderList;
		
		
	}



	@Override
	public void update(Customer customer) {
		// TODO Auto-generated method stub
		System.out.println(customer.getName() + ", you order is done!");
		
	}



	@Override
	public void speak() {
		System.out.println("Hi! My name is "+ getName() + ", and I want: ");
		System.out.println(toString());
		
	}
	

}
