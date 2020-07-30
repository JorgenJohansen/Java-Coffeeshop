package coffeeshop;

public interface Employee {
	
	void prepareOrder(Customer customer, Shop shop);
	void serveOrder(Customer customer);
	boolean receivePayment(Customer customer, Shop shop);

}
