package coffeeshop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class Manager extends Person implements Employee, Subject {
	
	private String status;
	private int workerIndex = 0;
	
	ArrayList<Worker> workers = new ArrayList<>();
	Collection<Customer> waitingList = new ArrayList<>();

	public Manager(String name, int age, String status, ArrayList<Worker> workers) {
		//Super uses the superclass constructor
		super(name, age);
		this.status = status;
		this.workers = workers;
		
	}
	
	public Collection<Customer> getWaitingList(){
		return waitingList;
	}
	
	
	
	@Override
	public void prepareOrder(Customer customer, Shop shop) {
		if(receivePayment(customer, shop)) {
			int prepareTime = 0;
			String message = "";
			Collection<Items> orderList = customer.getOrderList();
			Iterator<Items> iterator = orderList.iterator();
			while(iterator.hasNext()) {
				prepareTime += iterator.next().getPrepareTime();
			}
			System.out.println("The order takes: " + prepareTime + " minutes to be done.");
			addCustomer(customer);
		}else {
			throw new IllegalArgumentException("Insuffient funds!");
		}
		
	}

	@Override
	public void serveOrder(Customer customer) {
		if(workerIndex == workers.size()) {
			workerIndex = 0;
		}
		workers.get(workerIndex).notifyCustomers(customer);
		workerIndex++;
	}



	@Override
	public boolean receivePayment(Customer customer, Shop shop) {
		int sum = 0;
		int customerMoney = customer.getMoney();
		int shopMoney = shop.getCashier();
		Iterator<Items> iterator = customer.getOrderList().iterator();
		while(iterator.hasNext()) {
			sum += iterator.next().getPrice();
		}
		if(sum <= customerMoney) {
			customerMoney -= sum;
			//receit(customer);
			//customer.getOrderList().clear();
			shopMoney += shop.getCashier() + customerMoney;
			shop.setCashier(shopMoney);
			customer.setMoney(customerMoney);
			return true;
		}else {
			return false;
		}
		
	}
	
	
	//Work in progress
	public void decrementInventory(Customer customer, Shop shop) {
		if(receivePayment(customer, shop)) {
			Iterator iterator = customer.getOrderList().iterator();
			HashMap<Items, Integer> inventory = shop.getInventory();
			while(iterator.hasNext()) {
				if(iterator.next() == inventory.get(iterator)) {
					inventory.get(iterator).intValue();
				}
			}
		}
	}
	
	//TODO: Se om du får denne til å implementere comparable for å sortere 
	//Kanskje det kan gjøres i Shop isteden?
	public String toString(Customer customer) {
		String message = "";
		Iterator<Items> iterator = customer.getOrderList().iterator();
		while(iterator.hasNext()) {
			message += "Item name: " + iterator.next().getName() + "price: " + iterator.next().getPrice() + "\n";
		}
		return message;
	}
	
	
	public void receit(Customer customer) {
		System.out.println("You have payed for: \n");
		System.out.println(toString(customer));
		
	}
	
	
	public void addWorker(Worker worker) {
		workers.add(worker);
	}
	
	public void removeWorker(Worker worker) {
		workers.remove(worker);
	}
	
	
	
	public void saveOrder(Customer customer) {
		
	}
	
	public void loadOrder(Customer customer) {
		
	}



	@Override
	public void addCustomer(Customer customer) {
		
		waitingList.add(customer);
		
		
	}



	@Override
	public void removeCustomer(Customer customer) {
		if(waitingList.contains(customer)) {
			waitingList.remove(customer);
		}
		
	}



	@Override
	public void notifyCustomers(Customer customer) {
		if(workerIndex == workers.size()) {
			workerIndex = 0;
		}
		workers.get(workerIndex).notifyCustomers(customer);
		workerIndex++;
		
	}



	@Override
	public void speak() {
		System.out.println("Hi! My name is " + getName() + ", how may I take your order?");
	}

}
