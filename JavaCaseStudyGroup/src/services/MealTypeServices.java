package services;
import java.sql.*;
import java.util.ArrayList;

import classes.DatabaseConnection;
import classes.Meal;
import interfaces.DatabaseServices;

public class MealTypeServices implements DatabaseServices 
{
	private DatabaseConnection db_connection;
	private Meal meal;
	
	public MealTypeServices()
	{
		
	}

	public MealTypeServices(DatabaseConnection db) 
	{
		this.db_connection = db;
	}
	
	public MealTypeServices(DatabaseConnection db, Meal meal) 
	{
		this.db_connection = db;
		this.meal = meal;
	}

	@Override
	public void Create() throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Update() throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Delete() throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void GetAll() throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Find() throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<String> getMealType() throws SQLException, Exception
	{
		ArrayList<String> ids = new ArrayList<String>();
		int k = 0;
		String format = "|%1$-3s|%2$-20s|\n";
		ResultSet oraResult = db_connection
				.getStatement()
				.executeQuery("SELECT MT_ID, NAME FROM MEAL_TYPES");
		
		
		while(oraResult.next())
		{
			if(k == 0)
			{
				System.out.println("What type of meal is it: ");
				System.out.format(format,"#","Name");
			}
			
			k++;
			System.out.format(format,k,oraResult.getString(2));
			ids.add(oraResult.getString(1));
		}
		
		return ids;
		
	}

}
