import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import classes.Admin;
import classes.Customer;
import classes.DatabaseConnection;
import classes.Meal;
import classes.MealTypes;
import services.AdminServices;
import services.MealServices;
import services.MealTypeServices;

public class AdminMenu 
{
	static Scanner input = new Scanner(System.in);
	static Admin currentAdmin;
	static DatabaseConnection getConnected;
	
	public static void main(String[] args)
	{		
		
		int menuOption;

		getConnected();
		
	    do
	    {
	    	System.out.println("***********************************");
	    	System.out.println("Mummy's Administrator view");
	    	System.out.println("**********************************");
	    	System.out.println("*                                *");
	    	System.out.println("* 1. Login                       *");
	    	System.out.println("*                                *");
	    	System.out.println("**********************************");
	    	
	    	// need to handle input mismatch exception is user enters string/etc.
	   
	    	System.out.print("Please select an option # ");
	    	menuOption = input.nextInt();
	    	
	    	switch(menuOption)
	    	{
	    		case 1:
	    			adminLogin();
	    			adminSubMenu();
	    			break;
	    	}
	    	
	    }while(true);
	}
	
	
	private static int adminLogin() 
	{
		do
		{
			try
			{
				Admin admin = new Admin();
				
				input.nextLine();
				System.out.print("Please enter your id: ");
				admin.setAdminID(input.nextLine());
				System.out.print("Please enter your password: ");
				admin.setPassword(input.nextLine());
				
				AdminServices adminServ = new AdminServices(getConnected, admin);
				Admin found = adminServ.AdminExists();
				
				if(found.getFirstName() != null)
				{
					currentAdmin = found;
					System.out.println("Login Successful!");
					return 1;
				}
				else
					System.out.println("Wrong Email/Password");
				
			}catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
		}while(true);
	}
	
	private static int adminSubMenu() 
	{
		int menuOption;
		
		do
	    {
	    	System.out.println("***********************************");
	    	System.out.println("Hello " + currentAdmin.getFirstName());
	    	System.out.println("**********************************");
	    	System.out.println("*                                *");
	    	System.out.println("* 1. Add                         *");
	    	System.out.println("* 2. View                        *");
	    	System.out.println("* 3. Update                      *");
	    	System.out.println("* 4. Delete                      *");
	    	System.out.println("* 6. Main Menu                   *");
	    	System.out.println("*                                *");
	    	System.out.println("**********************************");
	    	
	    	// need to handle input mismatch exception is user enters string/etc.
	   
	    	System.out.print("Please select an option # ");
	    	menuOption = input.nextInt();
	    	
	    	switch(menuOption)
	    	{
	    		case 1:
	    			addMenu();
	    			break;
	    		case 6:
	    			return 1;
	    			
	    	}
	    	
	    }while(true);
		
	}


	private static int addMenu() 
	{
		int menuOption;
		
		do
	    {
	    	System.out.println("**********************************");
	    	System.out.println("What would you like to add       *");
	    	System.out.println("**********************************");
	    	System.out.println("*                                *");
	    	System.out.println("* 1. Meals                       *");
	    	System.out.println("* 2. #####                       *");
	    	System.out.println("* 3. #####                       *");
	    	System.out.println("* 4. #####                       *");
	    	System.out.println("* 6. Back                        *");
	    	System.out.println("*                                *");
	    	System.out.println("**********************************");
	    	
	    	// need to handle input mismatch exception is user enters string/etc.
	   
	    	System.out.print("Please select an option # ");
	    	menuOption = input.nextInt();
	    	
	    	switch(menuOption)
	    	{
	    		case 1:
	    			addMeal();
	    			break;
	    		case 6:
	    			return 1;
	    			
	    	}
	    	
	    }while(true);
		
	}


	private static int addMeal() 
	{
		ArrayList<String> ids = new ArrayList<String>();
		ArrayList<Integer> selection = new ArrayList<Integer>();
		int selected;
	
		do
		{
			try
			{
				Meal meal = new Meal();
				MealTypeServices  mealTypeServ = new MealTypeServices(getConnected);
				MealServices mealServ = new MealServices();
				input.nextLine();
				System.out.print("Please enter name of the meal: ");
				meal.setName(input.nextLine());
				System.out.print("Please enter description: ");
				meal.setDescription(input.nextLine());
				System.out.print("please enter price: ");
				meal.setPrice(input.nextDouble());
				
				
				ids = mealTypeServ.getMealType();
				for(int i = 1; i <= ids.size(); i++)
					selection.add(i);
				
				do
				{
					System.out.print("Select type #: ");
					selected = input.nextInt();
					
				}while(!selection.contains((selected)));
				
				meal.setMT_ID(ids.get((selected-1)));
				mealServ.insertToDB(meal);
				
				input.nextLine();
				System.out.println("You succesfully added a new meal");
				System.out.println("Press enter to go back");
				input.nextLine();
				
				return 1;
				
			}catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
		}while(true);
	}


	public static void getConnected()
	{
		DatabaseConnection db = new DatabaseConnection();
		getConnected = db;
	}
}
