package com.drools.drools_test_app;

public class Rule {
	
	private String packageName;
	private String invokeExpr;
	
	public Rule() {
		
	}
	
	public Rule(String packageName,String invokeExpr) {
		
		this.packageName = packageName;
		this.setInvokeExpr(invokeExpr);
	}	
	

	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getInvokeExpr() {
		return invokeExpr;
	}

	public void setInvokeExpr(String invokeExpr) {
		this.invokeExpr = invokeExpr;
	}

	
	
}
