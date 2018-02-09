package com.drools.drools_test_app;

public class Offer {
	private Customer customer;
	private MobilePackage pkg;
	
	public Offer() {
		
	}
	
	public Offer(Customer customer,MobilePackage pkg) {
		this.setCustomer(customer);
		this.setPkg(pkg);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public MobilePackage getPkg() {
		return pkg;
	}

	public void setPkg(MobilePackage pkg) {
		this.pkg = pkg;
	}
	
	@Override
	public String toString() {
		
		return this.pkg + " Offer sent to " + this.customer;
	}
}
