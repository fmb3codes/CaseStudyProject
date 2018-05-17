package services;

import java.sql.SQLException;

import classes.DatabaseConnection;
import classes.LocationTypes;
import classes.Orders;
import interfaces.DatabaseServices;

public class LocationTypeServices implements DatabaseServices
{
	private DatabaseConnection db_connection;
	private String name;
	
	//Constructor
	
	public LocationTypeServices(DatabaseConnection db, String name)
	{
		this.setDatabaseConnection(db);
		this.setName(name);
	}
	
	//Setters
	
	private void setDatabaseConnection(DatabaseConnection db) {this.db_connection = db;}
	private void setName(String name) 	  				  {this.name = name;}
	

	@Override
	public void Create() throws SQLException, Exception 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Update() throws SQLException, Exception 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Delete() throws SQLException, Exception 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void GetAll() throws SQLException, Exception 
	{
		
		
	}

	@Override
	public void Find() throws SQLException, Exception
	{
		
		
	}
	
	

}
