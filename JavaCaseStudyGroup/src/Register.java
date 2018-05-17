import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

// need to change these to correct packages
import classes.Customer;
import classes.CustomerServices;


public class Register {
	
    public static void main(String[] args) {
    	
    	Register test_reg = new Register();
    	test_reg.register();
    	
    }

	public Register() {
		// TODO Auto-generated constructor stub
	}
	
	public void register() {
	
		List fields = new ArrayList(); 
		fields.add("First name");
		fields.add("Last name");
		fields.add("Email");
		fields.add("Password");
		fields.add("Mobile number");
		fields.add("Home number");

		
		int counter = 0;
		

		Scanner scnr = new Scanner(System.in);
		
		while (counter < fields.size())
		{
			System.out.print(fields.get(counter) + ": ");
			String user_input = scnr.next();
			
			if(user_input.length() > 0){ // could do boolean validation check here
				// need to make confirmation check for password; need to confirm and also check if it's 9 characters or less
				// also email and other unique constrained fields
				
				// validate 
				fields.set(counter, user_input);
				counter++;
			}
			
		}
		
		System.out.println(fields);
		
		
		// after reading in values, need to insert into table
        Customer newCust = new Customer();
        newCust.setfName((String) fields.get(0)); // note indices not in insertion order
        newCust.setlName((String) fields.get(1));
        newCust.setPassword((String) fields.get(3));
        newCust.setLastLogin(newCust.getCurrentTimeStamp()); // should be able to set to null
        newCust.setEmail((String) fields.get(2));
        newCust.setHomeNumber(Integer.parseInt((String) fields.get(4)));
        newCust.setMobileNumber(Integer.parseInt((String) fields.get(5)));
        
        CustomerServices custService = new CustomerServices();
        custService.insertToDB(newCust);

        
		
	}

}
