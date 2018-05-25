import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import classes.CardTypes;
import classes.CreditCard;
import classes.CreditCardDetails;
import classes.Customer;
import classes.CustomerLocations;
import classes.CustomersCreditCards;
import services.CustomerServices;
import classes.Orders;
import classes.PaymentTypes;
import classes.Payments;
import services.CardTypeServices;
import services.CreditCardDetailServices;
import services.CreditCardServices;
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
	static DatabaseConnection getConnected;
	
	public static void main(String[] args) 
	{
		int menuOption;

		getConnected();
		
	    do
	    {
	    	System.out.println("*****************************");
	    	System.out.println("Welcome to Mummy's Restaurant");
	    	System.out.println("*****************************");
	    	System.out.println("*                           *");
	    	System.out.println("* 1. Login                  *");
	    	System.out.println("* 2. Register               *");
	    	System.out.println("* 3. Guest                  *");
	    	System.out.println("*                           *");
	    	System.out.println("*****************************");
	    	
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
	    		case 3:
	    			guestMenu();
	    			break;
	    		default:
	    			System.out.println("Please enter 1, 2, or 3");
	    			break;
	    	}
	    	
	    }while(true);
	}
	
	private static int guestMenu() 
	{
		int menuOption;
		
	    do
	    {
	    	System.out.println("*****************************");
	    	System.out.println("*         Guest Menu        *");
	    	System.out.println("*****************************");
	    	System.out.println("*                           *");
	    	System.out.println("* 1. View Meals             *");
	    	System.out.println("* 2. Main Menu              *");
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
	    			return 1;
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
				System.out.println("Invalid combination, please re-enter your information");
	    }while(true);
		
	}

	
	public static int customerLoginMenu()
	{
		int menuOption;

		do
		{
			
			System.out.println("********************************");
	    	System.out.println("Welcome " + currentCustomer.getfName());
	    	System.out.println("********************************");
	    	System.out.println("*                              *");
	    	System.out.println("* What would you like to do?   *");
	    	System.out.println("*                              *");
	    	System.out.println("* 1. Add                       *");
	    	System.out.println("* 2. View                      *");
	    	System.out.println("* 3. Update                    *");
	    	System.out.println("* 4. Delete                    *");
	    	System.out.println("* 5. Invoice                   *");
	    	System.out.println("* 6. Main Menu                 *");
	    	System.out.println("*                              *");
	    	System.out.println("********************************");
	    	
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
					updateMenu();
					break;
				case 4:
					deleteMenu();
					break;
				case 5:
					orderInvoice();
					break;
				case 6:
					return 1;
				default:
					System.out.println("Please enter 1, 2, 3, 4, 5, or 6");
					break;
			}
			
		}while(true);
	}
	
	private static int orderInvoice() 
	{
		
		ArrayList<String> ids = new ArrayList<String>();
		ArrayList<Integer> selection = new ArrayList<Integer>();
		int selected;

		do
		{
			try
			{
				Orders order = new Orders();
				OrderServices orderService  = new OrderServices(getConnected);
				ids = orderService.getAllOrderIDs(currentCustomer.getID());
				if(ids.isEmpty())
				{
					System.out.println("No orders placed to produce an invoice");
					return 1;
				}
				else
				{
					System.out.print("Please selected the order you wish to create an invoice: ");
					for(int i = 1; i <= ids.size(); i++)
						selection.add(i);
					
					do
					{
						System.out.print("Produce invoice for #: ");
						selected = input.nextInt();
						
					}while(!selection.contains((selected)));
					
					order.setOrder_id(ids.get((selected-1)));
					orderService = new OrderServices(getConnected, order, currentCustomer);
					orderService.produceInvoice();
					
					
					System.out.println("Invoice succesfully created");
					System.out.println("Press enter to go back");
					input.nextLine();
					input.nextLine();
					
					return 1;
				}
				
			}catch(Exception e)
			{
				System.out.println(e.getMessage());
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
	    	System.out.println("* 3. Credit Card            *");
	    	System.out.println("* 4. Selection Menu         *");
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
					addCreditCard();
					break;
				case 4:
					return 1;
				default:
					System.out.println("Please enter 1, 2, 3, or 4");
					break;
			}
			
		}while(true);
		
	}
	
	private static int addCreditCard() 
	{
		ArrayList<String> ids = new ArrayList<String>();
		ArrayList<Integer> selection = new ArrayList<Integer>();
		int selected;
		int k = 0;
		
		do
		{
			try 
			{
				input.nextLine();
				CreditCard cc = new CreditCard();
				CardTypeServices cts = new CardTypeServices(getConnected);
				CustomersCreditCards customerCards = new CustomersCreditCards();
				
				System.out.println("Please enter name of credit card: ");
				cc.setNameOnCard(input.nextLine());
				System.out.println("Please enter card number");
				cc.setCardNumber(input.nextLine());
				System.out.println("What type of card is it: ");
				ids = cts.getAllCardTypeID();
				for(String i: ids)
				{
					k++;
					selection.add(k);
				}
				
				do
				{
					System.out.print("Select a card type: ");
					selected = input.nextInt();
					
				}while(!selection.contains((selected)));
				
				cc.setCardType((ids.get((selected - 1))));
				ids.clear();
				selection.clear();
				
				
				//created a new credit card
				CreditCardServices ccs = new CreditCardServices(getConnected, cc);
				ccs.Create();
				
				customerCards.setC_ID(currentCustomer.getID());
				customerCards.setCC_ID(ccs.getCreditCard());
				
				//creates a new customer credit card
				CustomersCreditCardServices cccs = new CustomersCreditCardServices(getConnected, customerCards);
				cccs.Create();
				
				
			} catch (SQLException e) 
			{
				e.printStackTrace();
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			
			System.out.println("You have succesfully added a new credit card");
			input.nextLine();
			input.nextLine();
			return 1;

		}while(true);
		
	}

	private static int addNewOrder() 
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
				OrderServices os = new OrderServices(getConnected, order);
				
				MealServices ms = new MealServices(getConnected);
				CustomerLocationServices cls = new CustomerLocationServices(getConnected, currentCustomer);
				OrderMealsServices oms = new OrderMealsServices(getConnected);
				ids = ms.getMeals();
				for(int i = 1; i <= ids.size(); i++)
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
				System.out.println("What day would you like the order to be delivered? (dd-mm-yyyy)");
				order.setDelivery_date(input.nextLine());
				
				ids = cls.getAddresses();
				if(ids.isEmpty())
				{
					addNewAddress();
					ids = cls.getAddresses();
				}
					
				for(int i = 1; i <= ids.size(); i++)
					selection.add(i);
				
				do
				{
					System.out.print("choose address: ");
					selected = input.nextInt();
					
				}while(!selection.contains((selected)));
				
				order.setCustomer_location(ids.get((selected - 1)));
				order.setCustomer_id(currentCustomer.getID());
								
				//creates the order in orders table
				os.Create();
				
				//gets the current order id of the current customer
				//and sets it in order_meals table
				om.setOrderID(os.getOrderID(currentCustomer.getID()));
				oms.create(om);
				
				input.nextLine();
				System.out.println("How would you like to pay this order");
				
				ids.clear();
				selection.clear();
				
				PaymentTypes pt = new PaymentTypes();
				PaymentTypeServices pts = new PaymentTypeServices(getConnected);
				ids = pts.getPaymentType();
				for(int i = 1; i <= ids.size(); i++)
					selection.add(i);
					
				do
				{
					System.out.print("choose type: ");
					selected = input.nextInt();
					
				}while(!selection.contains((selected)));
				
				pt.setPT_ID(ids.get((selected - 1)));
				ids.clear();
				selection.clear();
				
				switch((selected))
				{
					//cash
					case 1:
						//insert into payments
						Payments p = new Payments();
						p.setOrderID(om.getOrderID());
						p.setPaymentType(pt.getPT_ID());
						PaymentServices ps = new PaymentServices(getConnected,p);
						ps.Create();
						break;
						
					//credit card
					case 2: 
						//insert into payments
						Payments p2 = new Payments();
						p2.setOrderID(om.getOrderID());
						p2.setPaymentType(pt.getPT_ID());
						PaymentServices ps2 = new PaymentServices(getConnected,p2);
						ps2.Create();
						
						CustomersCreditCards ccc = new CustomersCreditCards();
						CustomersCreditCardServices cccs = new CustomersCreditCardServices(getConnected);
						
						
						ids = cccs.getAllCreditCards(currentCustomer.getID());
						
						if(ids.isEmpty())
						{
							addCreditCard();
							ids = cccs.getAllCreditCards(currentCustomer.getID());
						}
							
						for(int i = 1; i <= ids.size(); i++)
							selection.add(i);
						
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
						
						CreditCardDetailServices ccds = new CreditCardDetailServices(getConnected, ccd);
						ccds.Create();
				
					break;
				}
				
				System.out.println("A new meal has been successfully placed");
				System.out.println("Press enter to go back");
				input.nextLine();
				
				return 1;
						
				
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
					// Orders has sub menu so no press to continue here
					break;
				case 3:
					viewLocations();
					pressToContinue();
					break;
				case 4:
					viewCreditCards();
					pressToContinue();
					break;
				case 5:
					return 1;
				default:
					System.out.println("Please enter 1, 2, 3, 4, or 5");
					break;
			}
			
		}while(true);
		
	}
	
	public static int deleteMenu()
	{
		int menuOption;

		do
		{
			System.out.println("*****************************");
	    	System.out.println("What would you like to delete?");
	    	System.out.println("*****************************");
	    	System.out.println("*                           *");
	    	System.out.println("* 1. Order                  *");
	    	System.out.println("* 2. Address                *");
	    	System.out.println("* 3. Credit Card            *");
	    	System.out.println("* 4. Selection Menu         *");
	    	System.out.println("*                           *");
	    	System.out.println("*****************************");
	    	
			System.out.print("Please select an option # ");
			menuOption = input.nextInt();
			
			switch(menuOption)
			{
				case 1:
					viewOrdersToUpdate();
					pressToContinue();
					deleteOrder();
					break;
				case 2:
					viewLocations();
					pressToContinue();
					//deleteAddress();
					// Orders has sub menu so no press to continue here
					break;
				case 3:
					viewCreditCards();
					pressToContinue();
					//deleteCreditCard();
					break;
				case 4:
					return 1;
				default:
					System.out.println("Please enter 1, 2, 3, or 4");
					break;
			}
			
		}while(true);
		
	}
	/*
	private static void deleteAddress() {
		ArrayList<String> ids = new ArrayList<String>();
		ArrayList<Integer> selection = new ArrayList<Integer>();
		int locOp = 0;
		
		int selectOption;
		input.nextLine();
		do
		{

			try {
				LocationTypeServices LocServices = new LocationTypeServices(getConnected);
				Locations Loc = new Locations();
			//order.displayForID(currentCustomer.getID());
			ids = LocationTypeServices.getAllLocationTypeID(currentCustomer.getID());
			for(String i : ids)
			{
				locOp++;
				selection.add(locOp);
			}
			
			
	    	do
			{
				System.out.print("Enter your order number ");
				selectOption = input.nextInt();
				
			}while(!selection.contains((selectOption)));
	    	
	    	Loc.setCustomerID(ids.get((selectOption - 1)));
	    	Loc.setLocationTypeID(currentCustomer.getID());
	    	LocServices =  new LocationTypeServices(getConnected, Loc );
			
				
	    	LocServices.Delete();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/*{
				CallableStatement oraCallStmt = db_connection.getConnection()
				.prepareCall("{call SP_DELETE_ORDER(?)}");
				DatabaseConnection db = new DatabaseConnection();

				oraCallStmt.setString(1, order.getOrder_id);
				oraCallStmt.execute();
				System.out.println("Order " + order.getOrder_id() + " has been succesfully deleted");
			} 

			
		}while(true);
	}	
	*/

	public static int addNewAddress() 
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
				LocationTypeServices ls = new LocationTypeServices(getConnected);
				
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
				(getConnected, cl);
				
				cls.Create();
				
			}catch (LocationException | SQLException e) 
			{
				System.out.println(e.getMessage());
			}catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
			System.out.println("you have success added an address");
			input.nextLine();
			return 1;
			
//			input.nextLine(); //flushes any leftover characters such as carriage return
//			System.out.println("Would you like to add another address");
//			input.nextLine();
			
		}while(true);	
	}
	
	public static void customerRegistration()
	{
		List<String> fields = new ArrayList(); 
		fields.add("First name");
		fields.add("Last name");
		fields.add("Email");
		fields.add("Password");
		fields.add("Mobile number");
		fields.add("Home number");

		int counter = 0;
		
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
        newCust.setfName(fields.get(0)); // note indices not in insertion order
        newCust.setlName(fields.get(1));
        newCust.setPassword(fields.get(3));
        newCust.setLastLogin(newCust.getCurrentTimeStamp()); // should be able to set to null
        newCust.setEmail(fields.get(2));
        newCust.setHomeNumber(Integer.parseInt(fields.get(4)));
        newCust.setMobileNumber(Integer.parseInt(fields.get(5)));
        
        CustomerServices custService = new CustomerServices();
        custService.insertToDB(newCust);
		
	}
	
	public static int deleteOrder(){	
		ArrayList<String> ids = new ArrayList<String>();
		ArrayList<Integer> selection = new ArrayList<Integer>();
		int locOp = 0;
		
		int selectOption;
		input.nextLine();
		do
		{

			try {
			OrderServices orderServices = new OrderServices(getConnected);
			Orders order = new Orders();
			//order.displayForID(currentCustomer.getID());
			ids = orderServices.getAllOrderIDs(currentCustomer.getID());
			
			if(ids.isEmpty())
			{
				System.out.println("No order been placed");
				return 1;
			}
			
			System.out.println("ids" + ids.toString());
			for(String i : ids)
			{
				locOp++;
				selection.add(locOp);
			}
			
			System.out.println("selection" + selection.toString());
	    	do
			{
				System.out.print("Enter your order number ");
				selectOption = input.nextInt();
				
			}while(!selection.contains((selectOption)));
	    	
	    	order.setOrder_id(ids.get((selectOption - 1)));
	    	order.setCustomer_id(currentCustomer.getID());
			orderServices =  new OrderServices(getConnected, order);
			
	    	orderServices.Delete();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/*{
				CallableStatement oraCallStmt = db_connection.getConnection()
				.prepareCall("{call SP_DELETE_ORDER(?)}");
				DatabaseConnection db = new DatabaseConnection();

				oraCallStmt.setString(1, order.getOrder_id);
				oraCallStmt.execute();
				System.out.println("Order " + order.getOrder_id() + " has been succesfully deleted");
			} */
			
			return 1;

			
		}while(true);
	}
		
	/*	
	public void deleteCustomerLocation()
	{
		ArrayList<String> ids = new ArrayList<String>();
		ArrayList<Integer> selection = new ArrayList<Integer>();
		int locOp = 0;
		
		int selectOption;
		input.nextLine();
		do
		{

			try {
				CustomerLocationServices LocCustServices = new CustomerLocationServices(getConnected);
				CustomerLocations cusLoc = new CustomerLocations();
			//order.displayForID(currentCustomer.getID());
			ids = CustomerLocationServices.GetAll(currentCustomer.getID());
			for(String i : ids)
			{
				locOp++;
				selection.add(locOp);
			}
			
			
	    	do
			{
				System.out.print("Enter your order number ");
				selectOption = input.nextInt();
				
			}while(!selection.contains((selectOption)));
	    	
	    	cusLoc.setCustomerID(ids.get((selectOption - 1)));
	    	cusLoc.setLocationTypeID(currentCustomer.getID());
	    	LocCustServices =  new CustomerLocationServices(getConnected, cusLoc );
			
				
	    	LocCustServices.Delete();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/*{
				CallableStatement oraCallStmt = db_connection.getConnection()
				.prepareCall("{call SP_DELETE_ORDER(?)}");
				DatabaseConnection db = new DatabaseConnection();

				oraCallStmt.setString(1, order.getOrder_id);
				oraCallStmt.execute();
				System.out.println("Order " + order.getOrder_id() + " has been succesfully deleted");
			} 

			
		}while(true);
	}
		*/
	
	public static int viewMeals()     
	{    
		try
		{
			MealServices mealService = new MealServices(getConnected);    
			mealService.getMeals();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
		//input.nextLine();
		//System.out.println("\nPress enter to go back");
		//input.nextLine();
		return 1;    
	}  
	
	public static int viewOrdersToUpdate()
	{
		OrderServices orderService = new OrderServices();
		//TODO add way to parse order_on_hold field from 0/1 to readable format (on hold/active)
		orderService.displayForIDToUpdate(currentCustomer.getID());

		
		return 1;
	}
	
	public static int viewOrders() 
	{
		// potentially add a check to see if there are no meals, in which case a message is displayed accordingly
		// not doing this in displayRecords since admins may call same function and the message might be different
		OrderServices orderService  = new OrderServices(); 
		
		// TODO ONLY DISPLAY ORDER DETAILS IF THE CUSTOMER HAS ORDERS
		
		int menuOption;

		do
		{
			orderService.displayForID(currentCustomer.getID());
			System.out.println("*****************************");
	    	System.out.println("What would you like to do?   ");
	    	System.out.println("*****************************");
	    	System.out.println("*                           *");
	    	System.out.println("* 1. Order Details          *");
	    	System.out.println("* 2. View Menu              *");
	    	System.out.println("*                           *");
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
		
		OrderMealsServices orderMealsService = new OrderMealsServices();
		orderMealsService.displayForID(currentCustomer.getID());
		
		
		// do cls here or outside?
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
	    	System.out.println("* 1. Order                     *");
	    	System.out.println("* 2. Address                   *");
	    	System.out.println("* 3. Credit Card               *");
	    	System.out.println("* 4. Selection Menu            *");
	    	System.out.println("*                              *");
	    	System.out.println("********************************");
	    	
			System.out.print("Please select an option # ");
			menuOption = input.nextInt();
			
			switch(menuOption)
			{
				case 1:
					viewOrdersToUpdate(); // should only be called/orders displayed if customer has any orders
					//pressToContinue();
					updateOrders();
					break;
				case 2:
					updateAddress(); // TODO
					break;
				case 3:
					//updateCreditCards(); // TODO
					break;
				case 4:
					return 1;
				default:
					System.out.println("Please enter 1, 2, 3, or 4");
					break;
			}
			
		}while(true);
	}
	
	private static void updateOrders() {
		OrderServices orderService = new OrderServices();
		
		// should check if customer has any orders and immediately break out if so, or handle this higher up
		
		
		int menuOption;
		
		List fields = new ArrayList(); 
		fields.add("Which order would you like to update? (Please enter the ID): ");
		
		int counter = 0;
		
		//Scanner scnr = new Scanner(System.in);
		
		while (counter < fields.size())
		{
			System.out.print(fields.get(counter));
			String user_input = input.next(); // is .next() good enough?
			
			// validation on proper order number here
			if(user_input.length() > 0){ // could do boolean validation check here
				
				// validate 
				if (orderService.orderExists(user_input) == 1)
				{
					fields.set(counter, user_input);
					counter++;
				}
				else
				{
					System.out.println("Invalid order number. Please re-enter the order number to update");
					continue; // used for readability only, not necessary
				}
			}
			
		}
		
		String order_id = (String) fields.get(0);
		
		String item_to_update = "";

		do {
			System.out.println("********************************");
	    	System.out.println("Order update options            ");
			System.out.println("********************************");
	    	System.out.println("*                              *");
	    	System.out.println("* 1. Change delivery date      *");
	    	System.out.println("* 2. Pause/unpause order       *"); // check if paused/on hold, and "flip" value
	    	System.out.println("*                              *");
	    	System.out.println("********************************");
			System.out.print("Please select an option #: ");
			menuOption = input.nextInt();
			
			switch(menuOption)
			{
				case 1:
					item_to_update = "DELIVERY_DATE";
					break;
				case 2:
					item_to_update = "ORDER_ON_HOLD";
					break;
				default:
					System.out.println("Please enter 1 or 2");
					break;
			}
			
			break;
		}while(true);
		
		
		if (item_to_update.equals("ORDER_ON_HOLD")) // flip to opposite value
		{
			orderService.updateOrderHold(order_id);
			
		}
		
		else
		{
			counter = 0;
			
			fields.set(counter, "Please enter the new delivery date in the form dd-mm-yyyy: ");
			
			
			//Scanner scnr = new Scanner(System.in);
			
			while (counter < fields.size())
			{
				System.out.print(fields.get(counter));
				String user_input = input.next(); // is .next() good enough?
				
				// validation on proper delivery date here
				if(user_input.length() > 0){ // could do boolean validation check here
					
					// validate 
					fields.set(counter, user_input);
					counter++;
				}
				
			}
			
			String new_delivery_date = (String) fields.get(0); // at this point it should be validated as a proper delivery date
			
			orderService.updateDeliveryAddress(order_id, new_delivery_date); // didn't need to initialize object since method is static
		}
		
		
		System.out.println("Update successful");
		
	}

	

	public static void updateAddress()
	{
		
	}
	
	public static void pressToContinue() {
		while(true)
		{
			try
	        {
				System.out.println("\nPress the Enter key to continue");
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
	
	public static void getConnected()
	{
		DatabaseConnection db = new DatabaseConnection();
		getConnected = db;
	}
	
}
