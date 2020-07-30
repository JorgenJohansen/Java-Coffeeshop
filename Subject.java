package coffeeshop;

public interface Subject {
	
	public void addCustomer(Customer customer);
	public void removeCustomer(Customer customer);
	public void notifyCustomers(Customer customer);

}
