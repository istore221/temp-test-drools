package com.drools.drools_test_app;

public class PackageFactory {
	
	public static MobilePackage getPackage(String packagename) {
			
		if(packagename == "SilverPackage") {
			return new SilverPackage();
		}
		else if(packagename == "GoldPackage") {
			return new GoldPackage();
		}
		else if(packagename == "PlatinumPackage") {
			return new PlatinumPackage();
		}
	
		return null;
		
	}
}
