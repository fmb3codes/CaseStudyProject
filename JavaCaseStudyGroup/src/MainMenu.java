import java.sql.SQLException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

import classes.DatabaseConnection;
import classes.CustomerLocations;
import classes.Orders;

import services.CustomerLocationServices;
import services.OrderServices;

import exceptions.LocationException;
import exceptions.OrderException;

public class MainMenu 
{
	final static SimpleDateFormat fDate = new SimpleDateFormat("dd-MM-yyyy");
	final static Date now = new Date();
	final static String today = fDate.format(now);
	static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) 
	{
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy");
	    Date now = new Date();
	    String today = sdfDate.format(now);
	    
		try
		{
			DatabaseConnection db = new DatabaseConnection();
			Orders order = new Orders(
					"1000002",
					today,
					"1000001",
					"1000001",
					"15-03-2018",
					true,
					3
			);
			
			order.setOrder_id("1000012");
			
			CustomerLocations customerL = new CustomerLocations(
					"My new Street", 
					"My new city",
					"My new State",
					"12345",
					"1000001",
					"1000001"
			);
			
			CustomerLocationServices cls = new CustomerLocationServices(db, customerL);
			OrderServices os = new OrderServices(db, order);
			
			//cls.Create();
			//os.Create();
			//os.Delete();
			//os.GetAll();
			os.Find();
			
			db.getConnection().close();
			
		}catch(SQLException | OrderException | LocationException e)
		{
			System.out.println(e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

	}
	
	//Order Options
	
	public void insertNewOrder()
	{
		
	}
	
	public void deleteOrder()
	{
		
	}
	
	//CustomerLocation Options
	
	public void insertNewCustomerLocation()
	{
		String customerID;
		String orderStatusID;
		String customerLocationID;
		String deliveryDate;
		boolean onHold;
		int timesChandes;
		
		//prompt the user to enter the following values
		do
		{
			
		}while()
		
	}
	
	public void deleteCustomerLocation()
	{
		
	}
	
}
