package services;

import classes.CustomerLocations;
import classes.DatabaseConnection;
import interfaces.DatabaseServices;
import java.sql.CallableStatement;
import java.sql.SQLException;

public class CustomerLocationServices implements DatabaseServices
{
	private DatabaseConnection db_connection;
	private CustomerLocations customerLocation;
	
	//Constructor
	
	public CustomerLocationServices(DatabaseConnection db, CustomerLocations customerLocation)
	{
		this.setDatabaseConnection(db);
		this.setCustomerLocation(customerLocation);
	}
	
	//Setters
	
	private void setDatabaseConnection(DatabaseConnection db) {this.db_connection = db;}
	private void setCustomerLocation(CustomerLocations cl) 	  {this.customerLocation = cl;}
	
	
	//DatabaseService Interface Methods

	@Override
	public void Create() throws SQLException, Exception
	{
		CallableStatement oraCallStmt = this.db_connection
				.getConnection()
				.prepareCall("{call SP_INSERT_NEW_CL(?,?,?,?,?,?)}");
		oraCallStmt.setString(1, this.customerLocation.getLocationType());
		oraCallStmt.setString(2, this.customerLocation.getStreetAddress());
		oraCallStmt.setString(3, this.customerLocation.getCity());
		oraCallStmt.setString(4, this.customerLocation.getState());
		oraCallStmt.setString(5, this.customerLocation.getZipCode());
		oraCallStmt.setString(6, this.customerLocation.getCustomerID());
		oraCallStmt.execute();
		System.out.println("Procedure Executed");
		System.out.println("A new customer location has been succesfully added");
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Find() throws SQLException, Exception
	{
		// TODO Auto-generated method stub
		
	}
	
}
