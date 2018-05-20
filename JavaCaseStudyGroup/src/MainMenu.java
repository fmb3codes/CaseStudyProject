import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Set;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import classes.DatabaseConnection;
import classes.DeliveryLocations;

import classes.Locations;
import classes.OrderMeal;
import services.MealServices;
import classes.CreditCardDetails;
import classes.Customer;
import classes.CustomerLocations;
import classes.CustomersCreditCards;
import services.CustomerServices;
import classes.Orders;
import classes.PaymentTypes;
import classes.Payments;
import services.CreditCardDetailServices;
import services.CustomerLocationServices;
import services.CustomersCreditCardServices;
import services.DeliveryServices;

import services.LocationTypeServices;

import services.MealServices;
import services.MealTypeServices;
//import services.OrderMealsServices;
import services.OrderMealsServices;
import services.OrderServices;
import services.PaymentServices;
import services.PaymentTypeServices;
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
		
		// double check; login stopped working
		
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

	    	
	    	// need to handle input mismatch exception is user enters string/etc.

	   
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
				System.out.print(fields.get(counter));
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
			
			//System.out.println("email is: " + email + " and password is: " + password);
			
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
	    	System.out.println("* 2. Meal                   *");
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
					addNewOrder();
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
	
	private static void addNewOrder() 
	{
		ArrayList<String> ids = new ArrayList<String>();
		ArrayList<Integer> selection = new ArrayList<Integer>();
		int selected;
		int k = 0;
		
		do
		{
			try 
			{
				Orders order = new Orders();
				OrderMeal om = new OrderMeal();
				CustomerLocations cl = new CustomerLocations();
				OrderServices os = new OrderServices(getConnected(), order);
				
				MealServices ms = new MealServices(getConnected());
				CustomerLocationServices cls = new CustomerLocationServices(getConnected());
				OrderMealsServices oms = new OrderMealsServices(getConnected());
				ids = ms.getMeals();
				for(int i = 0; i < ids.size(); i++)
					selection.add(i);
		
				do
				{
					System.out.print("What meal # would you like to add: ");
					selected = input.nextInt();
					
				}while(!selection.contains((selected)));
				
				om.setMealID(ids.get((selected - 1)));
				ids.clear();
				selection.clear();
				
				input.nextLine();
				System.out.println("Please enter quantity");
				om.setQty(input.nextInt());
				
				input.nextLine();
				System.out.println("What day would you like the order to be delivered?");
				order.setDelivery_date(input.nextLine());
				
				input.nextLine();
				System.out.println("Where would you like the order to be delivered?");
				ids = cls.getAddresses();
				for(int i = 0; i < ids.size(); i++)
					selection.add(i);
				do
				{
					System.out.print("choose address: ");
					selected = input.nextInt();
					
				}while(!selection.contains((selected)));
				
				order.setCustomer_location(ids.get((selected - 1)));
				order.setCustomer_id(currentCustomer.getID());
				order.setOrder_status("1000001");  //to be as default
				order.setOrder_on_hold(false); //to be as default
				order.setTimes_changes(0); //to be as default
				order.setOrder_date(today); //to be as default
				
				os.Create();
				om.setOrderID(os.getOrderID(currentCustomer.getID()));
				oms.create(om);
				
				
				input.nextLine();
				System.out.println("How would you like to pay this order");
				ids.clear();
				selection.clear();
				PaymentTypes pt = new PaymentTypes();
				PaymentTypeServices pts = new PaymentTypeServices(getConnected());
				ids = pts.getPaymentType();
				for(String i : ids)
				{
					k++;
					selection.add(k);
				}
					
				do
				{
					System.out.print("choose type: ");
					selected = input.nextInt();
					
				}while(!selection.contains((selected)));
				
				pt.setPT_ID(ids.get((selected - 1)));
				ids.clear();
				selection.clear();
				
				switch((selected-1))
				{
					//cash
					case 0:
						System.out.println("In case 1");
						
						//insert into payments
						Payments p = new Payments();
						p.setOrderID(om.getOrderID());
						p.setPaymentType(pt.getPT_ID());
						PaymentServices ps = new PaymentServices(getConnected(),p);
						ps.Create();
						break;
						
					//credit card
					case 1:
						System.out.println("In case 2");
						
						//insert into payments
						Payments p2 = new Payments();
						p2.setOrderID(om.getOrderID());
						p2.setPaymentType(pt.getPT_ID());
						PaymentServices ps2 = new PaymentServices(getConnected(),p2);
						
						ps2.Create();
						
						k = 0;
						
						CustomersCreditCards ccc = new CustomersCreditCards();
						CustomersCreditCardServices cccs = new CustomersCreditCardServices(getConnected());
						ids = cccs.getAllCreditCards(currentCustomer.getID());
						
						for(String i : ids)
						{
							k++;
							selection.add(k);
						}
						
						do
						{
							System.out.print("choose credit card: ");
							selected = input.nextInt();
							
						}while(!selection.contains((selected)));
						
						ccc.setCC_ID((ids.get((selected-1))));
						ccc.setC_ID(currentCustomer.getID());
						
						ids.clear();
						selection.clear();
						
						//insert into credit card details
						CreditCardDetails ccd = new CreditCardDetails();
						
						ccd.setP_ID(ps2.getPaymentID(os.getOrderID(currentCustomer.getID())));
						ccd.setO_ID(os.getOrderID(currentCustomer.getID()));
						ccd.setCC_ID(ccc.getCC_ID());
						
						CreditCardDetailServices ccds = new CreditCardDetailServices(
								getConnected(), ccd);
						
						ccds.Create();
				
						break;
				}
						
				
			} catch (SQLException e) 
			{
				e.printStackTrace();
			} catch (Exception e) 
			{
				e.printStackTrace();
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
					pressToContinue();
					break;
				case 2:
					viewOrders(); // this will include option to view order details, so might need separate function to just display orders
					break;
				case 3:
					viewLocations();
					break;
				case 4:
					viewCreditCards();
					pressToContinue();
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

		ArrayList<String> ids = new ArrayList<String>();
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<Integer> selection = new ArrayList<Integer>();
		int locOp = 0;

		int selected;
		input.nextLine(); //flushes any leftover characters such as carriage return
		
		do
		{
			try 
			{
				CustomerLocations cl = new CustomerLocations();
				LocationTypeServices ls = new LocationTypeServices(getConnected());
				
				System.out.print("Please enter your street address:");
				cl.setStreetAdress(input.nextLine());
				System.out.print("Please enter your city:");
				cl.setCity(input.nextLine());
				System.out.print("Please enter your state:");
				cl.setState(input.nextLine());
				System.out.print("Please enter your zip code:");
				cl.setZipCode(input.nextLine());
		
				ids = ls.getAllLocationTypeID();
				names = ls.getAllLocationTypeName();
				
				System.out.println("What type of location is it:");
				for(String name : names)
				{
					locOp++;
					System.out.println(locOp + ". " + name);
					selection.add(locOp);
				}
				
				do
				{
					System.out.print("Please select type: ");
					selected = input.nextInt();
					
				}while(!selection.contains((selected)));
			
				cl.setLocationTypeID(ids.get((selected - 1)));
				cl.setCustomerID(currentCustomer.getID());
				
				CustomerLocationServices cls = new CustomerLocationServices
				(getConnected(), cl);
				
				cls.Create();
				
			}catch (LocationException | SQLException e) 
			{
				System.out.println(e.getMessage());
			}catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
			input.nextLine(); //flushes any leftover characters such as carriage return
			System.out.println("Would you like to add another address");
			input.nextLine();
			
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
					pressToContinue();
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
		
		//OrderMealsServices orderMealsService = new OrderMealsServices();
		//orderMealsService.displayForID(currentCustomer.getID());
		
		
		// do cls here or outside?
		return 1;
		
	}
	
	public static int viewCreditCards() 
	{
		// potentially add a check to see if there are no meals, in which case a message is displayed accordingly
		// not doing this in displayRecords since admins may call same function and the message might be different
		CustomersCreditCardServices creditCardService = new CustomersCreditCardServices();
		//creditCardService.displayForID(currentCustomer.getID());
		
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
	
	public static void pressToContinue() {
		while(true)
		{
			try
	        {
				System.out.println("Press the Enter key to continue");
	            System.in.read();
	        }  
	        catch(Exception e)
	        {
	        	
	        	System.out.println(e.getMessage());
	        }  
			break;
		}
		
		return;
	}
	
	public static DatabaseConnection getConnected()
	{
		DatabaseConnection db = new DatabaseConnection();
		return db;
	}
	
}
