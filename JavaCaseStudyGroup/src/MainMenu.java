import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import classes.DatabaseConnection;
import classes.DeliveryLocations;
<<<<<<< HEAD
=======
import services.MealServices;
>>>>>>> 67e931d1ca6bcb17754454ad0cbf0d21535487a0
import classes.Customer;
import classes.CustomerLocations;
import services.CustomerServices;
import classes.Orders;

import services.CustomerLocationServices;
import services.CustomersCreditCardServices;
import services.DeliveryServices;
import services.MealServices;
import services.MealTypeServices;
import services.OrderMealsServices;
import services.OrderServices;

import exceptions.LocationException;
import exceptions.OrderException;
import java.io.Console;

public class MainMenu 
{
	
	final static SimpleDateFormat fDate = new SimpleDateFormat("dd-MM-yyyy");
	final static Date now = new Date();
	final static String today = fDate.format(now);
	static Scanner input = new Scanner(System.in);
	static Customer currentCustomer;
	
	
	
	public static void main(String[] args) 
	{
		int menuOption;
		Console cnsl = null;
		
		cnsl = System.console();
	    do
	    {
	    	System.out.println("*****************************");
	    	System.out.println("Welcome to Mummy's Restaurant");
	    	System.out.println("*****************************");
	    	System.out.println("*                           *");
	    	System.out.println("* 1. Login                  *");
	    	System.out.println("* 2. Register               *");
	    	System.out.println("*                           *");
	    	System.out.println("*****************************");
	    	
	    	try 
	    	{
	    		cnsl.flush();
	    	}catch(Exception e)
	    	{
	    		System.out.println(e.getMessage());
	    	}

	   
	    	System.out.print("Please select an option # ");
	    	menuOption = input.nextInt();
	    	
	    	switch(menuOption)
	    	{
	    		case 1:
	    			customerLoginValidation();
	    			customerLoginMenu();
	    			break;
	    		case 2:
	    			customerRegistration();
	    			break;
	    		default:
	    			System.out.println("Please enter 1 or 2");
	    			break;
	    	}
	    	
	    }while(true);
	}
	
	public static int customerLoginValidation()
	{
		CustomerServices custService = new CustomerServices();
		
		//System.out.println(fields);
		do
	    {
			List fields = new ArrayList(); 
			fields.add("Please enter your email: ");
			fields.add("Please enter your password: ");

			
			int counter = 0;
			

			//Scanner scnr = new Scanner(System.in);
			
			while (counter < fields.size())
			{
				System.out.print(fields.get(counter) + ": ");
				String user_input = input.next(); // is .next() good enough?
				
				// validation on proper password/email go below (this is before actually checking if the customer exists)
				if(user_input.length() > 0){ // could do boolean validation check here
					// need to make confirmation check for password; need to confirm and also check if it's 9 characters or less
					
					// validate 
					fields.set(counter, user_input);
					counter++;
				}
				
			}
			
			// input valid at this point so now check if the customer exists
			
			String email = (String) fields.get(0);
			String password = (String) fields.get(1);
			
			Customer customerCheck = custService.customerExists(email, password); // could also just directly assign to currentCustomer
			
			if (customerCheck != null)
			{
				currentCustomer = customerCheck;
				System.out.println("Login successful!");
				return 1; // could be break and then return
			}
			
			else
			{
				System.out.println("Invalid combination, please re-enter your information");
			}
	    	

	    	
	    }while(true);
		
	}

	
	public static int customerLoginMenu()
	{
		int menuOption;

		do
		{
			System.out.println("*****************************");
	    	System.out.println("Welcome " + currentCustomer.getfName() + "");
	    	System.out.println("*****************************");
	    	System.out.println("*                           *");
	    	System.out.println("* What would you like to do?*");
	    	System.out.println("*                           *");
	    	System.out.println("* 1. Add                    *");
	    	System.out.println("* 2. View                   *");
	    	System.out.println("* 3. Update                 *");
	    	System.out.println("* 4. Delete                 *");
	    	System.out.println("* 5. Main Menu              *");
	    	System.out.println("*                           *");
	    	System.out.println("*****************************");
	    	
			System.out.print("Please select an option # ");
			menuOption = input.nextInt();
			
			switch(menuOption)
			{
				case 1:
					addMenu();
					break;
				case 2:
					viewMenu();
					break;
				case 3:
					//updateMenu();
					break;
				case 4:
	
					break;
					
				case 5:
					return 1;
				default:
					System.out.println("Please enter 1 - 5");
					break;
			}
			
		}while(true);
	}
	
	public static int addMenu()
	{
		int menuOption;

		do
		{
			System.out.println("*****************************");
	    	System.out.println("What would you to add?       ");
	    	System.out.println("*****************************");
	    	System.out.println("*                           *");
	    	System.out.println("* 1. Address                *");
	    	System.out.println("* 2. ####                   *");
	    	System.out.println("* 3. ######                 *");
	    	System.out.println("* 4. #######                *");
	    	System.out.println("* 5. Selection Menu         *");
	    	System.out.println("*                           *");
	    	System.out.println("*****************************");
	    	
			System.out.print("Please select an option # ");
			menuOption = input.nextInt();
			
			switch(menuOption)
			{
				case 1:
					addNewAddress();
					break;
				case 2:
					
					break;
				case 3:
					
					break;
				case 4:
	
					break;
				case 5:
					return 1;
				default:
					System.out.println("Please enter 1 or 2");
					break;
			}
			
		}while(true);
		
	}
	
	public static int viewMenu()
	{
		int menuOption;

		do
		{
			System.out.println("*****************************");
	    	System.out.println("What would you like to view? ");
	    	System.out.println("*****************************");
	    	System.out.println("*                           *");
	    	System.out.println("* 1. Meals                  *");
	    	System.out.println("* 2. Orders                 *");
	    	System.out.println("* 3. Locations              *");
	    	System.out.println("* 4. Credit cards           *");
	    	System.out.println("* 5. Selection Menu         *");
	    	System.out.println("*                           *");
	    	System.out.println("*****************************");
	    	
			System.out.print("Please select an option # ");
			menuOption = input.nextInt();
			
			switch(menuOption)
			{
				case 1:
					viewMeals();
					break;
				case 2:
					viewOrders(); // this will include option to view order details, so might need separate function to just display orders
					break;
				case 3:
					viewLocations();
					break;
				case 4:
					viewCreditCards();
					break;
				case 5:
					return 1;
				default:
					System.out.println("Please enter 1 or 2");
					break;
			}
			
		}while(true);
		
	}
	
	public static void addNewAddress() 
	{
		String street;
		String city;
		String state;
		String zip;
		String customerID;   //will be removed
		String locationType = ""; //will be removed
		int type;
		
		
		input.nextLine(); //flushes any leftover characters such as carriage return
		do
		{
			
			System.out.print("Please enter your street address: ");
			street = input.nextLine();
			System.out.print("Please enter your city: ");
			city = input.nextLine();
			System.out.print("Please enter your state: ");
			state = input.nextLine();
			System.out.print("Please enter your zip code: ");
			zip = input.nextLine();
			System.out.print("cusID (testing purposes): ");
			customerID = input.nextLine();
			
			System.out.println("What type of location is it:");
			System.out.println("1. Billing");
			System.out.println("2. Residencial");
			
			try 
			{
				type = input.nextInt();
				switch(type)
				{
					case 1:
						locationType = "Billing";
						break;
					case 2:
						locationType = "Residencial";
						break;
				}
				CustomerLocations customerL = new CustomerLocations
				(street, city, state, zip, customerID, locationType);
				
				CustomerLocationServices cls = new CustomerLocationServices
				(getConnected(), customerL);
				cls.Create();
				
			}catch (LocationException | SQLException e) 
			{
				System.out.println(e.getMessage());
			}catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
		}while(true);	
	}
	
	public static void customerRegistration()
	{
		List fields = new ArrayList(); 
		fields.add("First name");
		fields.add("Last name");
		fields.add("Email");
		fields.add("Password");
		fields.add("Mobile number");
		fields.add("Home number");

		
		int counter = 0;
		

		//Scanner scnr = new Scanner(System.in);
		
		while (counter < fields.size())
		{
			System.out.print(fields.get(counter) + ": ");
			String user_input = input.next();
			
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
	
	public void insertNewOrder()
	{
		
	}
	
	public void deleteOrder()
	{
		
	}
	
	public void deleteCustomerLocation()
	{
		
	}
	
	public static int viewMeals() 
	{
		// potentially add a check to see if there are no meals, in which case a message is displayed accordingly
		// not doing this in displayRecords since admins may call same function and the message might be different
		MealServices mealService = new MealServices();
		mealService.displayRecords();
		
		return 1;
	}
	
	public static int viewOrders() 
	{
		// potentially add a check to see if there are no meals, in which case a message is displayed accordingly
		// not doing this in displayRecords since admins may call same function and the message might be different
		OrderServices orderService  = new OrderServices(); 
		
		int menuOption;

		do
		{
			orderService.displayForID(currentCustomer.getID());
			System.out.println("*****************************");
	    	System.out.println("What would you like to do?   ");
	    	System.out.println("*****************************");
	    	System.out.println("* 1. Order Details          *");
	    	System.out.println("* 2. View Menu              *");
	    	System.out.println("*****************************");
	    	
			System.out.print("Please select an option # ");
			menuOption = input.nextInt();
			
			switch(menuOption)
			{
				case 1:
					viewOrderDetails();
					break;
				case 2:
					return 1;
				default:
					System.out.println("Please enter 1 or 2");
					break;
			}
			
		}while(true);

	}
	
	public static int viewLocations() 
	{
		// potentially add a check to see if there are no meals, in which case a message is displayed accordingly
		// not doing this in displayRecords since admins may call same function and the message might be different
		CustomerLocationServices custLocService = new CustomerLocationServices();
		custLocService.displayForID(currentCustomer.getID());
		
		return 1;
	}
	
	public static int viewOrderDetails() 
	{
		
		OrderMealsServices orderMealsService = new OrderMealsServices();
		orderMealsService.displayForID(currentCustomer.getID());
		
		return 1;
		
	}
	
	public static int viewCreditCards() 
	{
		// potentially add a check to see if there are no meals, in which case a message is displayed accordingly
		// not doing this in displayRecords since admins may call same function and the message might be different
		CustomersCreditCardServices creditCardService = new CustomersCreditCardServices();
		creditCardService.displayForID(currentCustomer.getID());
		
		return 1;
	}

	public static int updateMenu()
	{
		int menuOption;

		do
		{
			System.out.println("********************************");
	    	System.out.println("What would you like to update?  ");
	    	System.out.println("********************************");
	    	System.out.println("*                              *");
	    	System.out.println("* 1. Address                   *");
	    	System.out.println("* 2. ####                      *");
	    	System.out.println("* 3. ######                    *");
	    	System.out.println("* 4. #######                   *");
	    	System.out.println("* 5. Selection Menu            *");
	    	System.out.println("*                              *");
	    	System.out.println("*****************************");
	    	
			System.out.print("Please select an option # ");
			menuOption = input.nextInt();
			
			switch(menuOption)
			{
				case 1:
					updateAddress();
					break;
				case 2:
					
					break;
				case 3:
					
					break;
				case 4:
	
					break;
				case 5:
					return 1;
				default:
					System.out.println("Please enter 1 or 2");
					break;
			}
			
		}while(true);
	}
	
	public static void updateAddress()
	{
		
	}
	
	public static DatabaseConnection getConnected()
	{
		DatabaseConnection db = new DatabaseConnection();
		return db;
	}
	
}
