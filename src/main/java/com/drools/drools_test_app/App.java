package com.drools.drools_test_app;

import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;



import org.drools.template.ObjectDataCompiler;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;


public class App 
{
    public static void main( String[] args )
    {
    	
    		/*
    		 * 
    		 * table : offer_triggers  -> mapped to Rule Modal
    		 * 
    		 * Client can decide which package should be triggered based on his custom expression
    		 * given from directly from client application (web ui).
    		 * trigger expression can be complicated or simple based on the his requrement he
    		 * can decide
    		 * 
    		 *
    		 *
    		 * 	offer_package		triggerExpression 
    		 * 
    		 * 	SilverPackage		100 <= voiceRevenue  &&  voiceRevenue < 500
    		 * 	GoldPackage			500 <= voiceRevenue  &&  voiceRevenue < 1000
    		 * 	PlatinumPackage		1000 <= voiceRevenue  &&  voiceRevenue < 1500
    		 */
    	
    	
    		Offer offer = new Offer(); // will be evaluated by rule engine
        
    	
    	
    	   // Customer applicable for SilverPackage Offer
       Customer c1 = new Customer(100,"John","Carter",Age.YOUNG,120);
       
       // Customer applicable for GoldPackage Offer
       Customer c2 = new Customer(200,"Sean","Kingston",Age.MATURE,580);
       
       // Customer applicable for PlatinumPackage Offer
       Customer c3 = new Customer(300,"Kevin","Apple",Age.OLD,1300);
       
       // Customer is not applicable for  any offer
       Customer c4 = new Customer(400,"Baby","Driver",Age.YOUNG,50);
       
       
   	
       
       // check if the current customer applicable for any package offer 
       try {
    	   	
    	   
    	   	KieSession kSession = getStateFullKiSession(applyRuleTemplate());
    	   	
    	   	Customer targetCustomer = c1; // change target customer here ( can be realtime)
    	   
    	    kSession.setGlobal("offer", offer);
    	    kSession.insert(targetCustomer);
    	    kSession.fireAllRules(); 
    	    
    	    if(offer.getPkg() == null) {
    	    		System.out.println(targetCustomer + " Not Applicable for any Offer");
    	    }else {
    	    	 System.out.println(offer); 
    	    }
    	    
    	    kSession.dispose(); // release the session 
    	    
    	  
    	    
	} catch (Exception e) {
		e.printStackTrace();
	}
    }
     
       
    
    
    static private KieSession getStateFullKiSession(String drl) throws Exception {
    	
    		
    	 	KieServices ks = KieServices.Factory.get();
 		KieFileSystem kfs = ks.newKieFileSystem();
 		kfs.write("src/main/resources/stateFulSessionRule.drl", ks.getResources().newReaderResource(new StringReader(drl))
					.setResourceType(ResourceType.DRL));
 		KieBuilder kieBuilder = ks.newKieBuilder(kfs).buildAll();
     
 		if (kieBuilder.getResults().hasMessages(Message.Level.ERROR)) {
				System.out.println(kieBuilder.getResults().toString());
		 }
 		KieContainer kContainer = ks.newKieContainer(kieBuilder.getKieModule().getReleaseId());
		KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
		KieBase kbase = kContainer.newKieBase(kbconf);
		KieSession kieSession = kbase.newKieSession();
  
		return kieSession;
    }
  
    
    static private String applyRuleTemplate() throws Exception {
    	
    		/*
    		 * for the example  rules are loaded from hard coded arraylist 
    		 * we can map this Rule object to any data source
    		 * eg: database  xml json .....
    		 */
    		Collection<Rule> rules = new ArrayList<Rule>();
    		
    		// package name | trigger expression
    		rules.add(new Rule("SilverPackage","100 <= voiceRevenue  &&  voiceRevenue < 500"));
    		rules.add(new Rule("GoldPackage","500 <= voiceRevenue  &&  voiceRevenue < 1000"));
    		rules.add(new Rule("PlatinumPackage","1000 <= voiceRevenue  &&  voiceRevenue < 1500"));
    		
        ObjectDataCompiler converter = new ObjectDataCompiler();
        
        InputStream inputStream = App.class.getResourceAsStream("/rules/Rules.drl");
        String drl = converter.compile(rules, inputStream);
        return drl;
       
      

    }
}
