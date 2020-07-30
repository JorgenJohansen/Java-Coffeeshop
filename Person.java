package coffeeshop;

public abstract class Person {
	
	private String name;
	private int age;
	
	public Person(String name, int age) {
		validateAge(age);
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		validateAge(age);
		this.age = age;
	}
	
	public void validateAge(int age) {
		if(age < 1) {
			throw new IllegalArgumentException("A person must be at least 1 year old, considering that babies don't drink coffee");
		}
	}
	
	public abstract void speak();
	
	

}
