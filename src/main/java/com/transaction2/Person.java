package com.transaction2;

// pojo classes plain old java object

public class Person {

	private Integer ID;
	private String name;
	private String lastName;
	private int age;
	private String address;
	private String email;

	public Person(Integer iD, String name, String lastName, int age, String address, String email) {
		ID = iD;
		this.name = name;
		this.lastName = lastName;
		this.age = age;
		this.address = address;
		this.email = email;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Person [ID: " + ID + ", name: " + name + ", lastName: " + lastName + ", age: " + age + ", address: "
				+ address + ", email: " + email + "]";
	}

	
}
