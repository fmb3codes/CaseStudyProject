import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import classes.DatabaseConnection;
import classes.DeliveryLocations;
import classes.CustomerLocations;
import classes.Orders;

import services.CustomerLocationServices;
import services.DeliveryServices;
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

	
	public static int customerLoginMenu()
	{
		int menuOption;

		do
		{
			System.out.println("*****************************");
	    	System.out.println("Welcome " + "placeholder name");
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
					
					break;
				case 3:
					
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
	
	public static void addNewAddress() 
	{
		String street;
		String city;
		String state;
		String zip;
		String customerID;   //will be removed
		String locationType; //will be removed
		
		
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
			System.out.print("locationType (testing purposes): ");
			locationType = input.nextLine();
		
			try 
			{
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
	
	public static DatabaseConnection getConnected()
	{
		DatabaseConnection db = new DatabaseConnection();
		return db;
	}
	
}
