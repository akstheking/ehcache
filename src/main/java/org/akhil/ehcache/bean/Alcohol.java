package org.akhil.ehcache.bean;

public class Alcohol {
	
	private Double quantity;
	private int age;
	
	public Alcohol(Double quantity, int age) {
		this.quantity = quantity;
		this.age = age;
	}

	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Alcohol [quantity=" + quantity + ", age=" + age + "]";
	}
	
	

}
