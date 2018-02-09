package com.drools.drools_test_app;

enum Age { YOUNG, MATURE, OLD }  


public class Customer {
	private int id;
	private String firstName;
	private String lastName;
	private Age age;
	private int voiceRevenue;
	
	Customer(int id,String firstName,String lastName,Age age,int voiceRevenue){
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.voiceRevenue = voiceRevenue;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Age getAge() {
		return age;
	}
	public void setAge(Age age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return this.getFirstName()+" "+this.getLastName();
	}

	public int getVoiceRevenue() {
		return voiceRevenue;
	}

	public void setVoiceRevenue(int voiceRevenue) {
		this.voiceRevenue = voiceRevenue;
	}
}
