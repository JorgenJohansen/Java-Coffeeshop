package coffeeshop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Worker extends Person implements Employee, Subject {
	
	private String status;
	private Collection<Customer> waitingList = new ArrayList<>();
	
	public Worker(String name, int age, String status) {
		super(name, age);
		// TODO Auto-generated constructor stub
		this.setStatus(status);
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
		notifyCustomers(customer);
		
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
	
	public void receit(Customer customer) {
		System.out.println("You have payed for: \n");
		System.out.println(toString(customer));
		
	}
	
	public String toString(Customer customer) {
		String message = "";
		Iterator<Items> iterator = customer.getOrderList().iterator();
		while(iterator.hasNext()) {
			message += "Item name: " + iterator.next().getName() + "price: " + iterator.next().getPrice() + "\n";
		}
		return message;
	}



	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}



	@Override
	public void addCustomer(Customer customer) {
		if(!waitingList.contains(customer)) {
			waitingList.add(customer);
		}
		
	}



	@Override
	public void removeCustomer(Customer customer) {
		if(waitingList.contains(customer)) {
			waitingList.remove(customer);
		}
		
	}



	@Override
	public void notifyCustomers(Customer customer) {
		for(Customer customer2 : waitingList) {
			customer2.update(customer);
		}
		removeCustomer(customer);
		
	}



	@Override
	public void speak() {
		System.out.println("Hi! My name is " + getName() + ", how may I take your order?");
		
	}


}
