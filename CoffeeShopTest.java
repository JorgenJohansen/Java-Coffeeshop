package coffeeshop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class CoffeeShopTest {
	
	private Customer customer1;
	private Customer customer2;
	private Manager manager;
	private Worker worker1;
	private Shop shop;
	private HashMap<Items, Integer> inventory;
	private Items item1;
	private Items item2;
	private Items item3;
	
	@Before
	public void setUp() {
		customer1 = new Customer("Lars",27,1000);
		customer2 = new Customer("Turid",30,10);
		manager = new Manager("Jørgen",25,"Boss",new ArrayList<>());
		worker1 = new Worker("Maria",20,"Co-worker");
		item1 = new Items("Coffee",20,5);
		item2 = new Items("Caffe latte",30,5);
		item3 = new Items("Biscuit",20,0);
		inventory = new HashMap<Items,Integer>();
		inventory.put(item1, 20);
		inventory.put(item2, 20);
		inventory.put(item3, 20);
		shop = new Shop(inventory,1000);
	}
	
	@Test
	public void testCustomer() {
		customer1.addItemToOrder(item1, shop);
		customer1.addItemToOrder(item2, shop);
		customer1.addItemToOrder(item3, shop);
		
		customer2.addItemToOrder(item1, shop);
		
		assertEquals(customer1.getOrderList().size(), 3);
		
		assertTrue(manager.receivePayment(customer1, shop));
		assertFalse(manager.receivePayment(customer2, shop));
		
		customer1.removeItemFromOrder(item3);
		assertEquals(customer1.getOrderList().size(), 2);
		
	}
	
	
	
	

}
