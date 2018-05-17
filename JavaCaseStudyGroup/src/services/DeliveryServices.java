package services;

import java.sql.CallableStatement;
import java.sql.SQLException;

import classes.DatabaseConnection;
import classes.DeliveryLocations;
import interfaces.DatabaseServices;

public class DeliveryServices implements DatabaseServices
{
	
	private DatabaseConnection db_connection;
	private DeliveryLocations delivery;
	
	//Constructor
	
	public DeliveryServices(DatabaseConnection db, DeliveryLocations dl)
	{
		this.setDatabaseConnection(db);
		this.setDeliveryLocation(dl);
	}
	
	private void setDatabaseConnection(DatabaseConnection db) {this.db_connection = db;}
	private void setDeliveryLocation(DeliveryLocations dl) 	  {this.delivery = dl;}

	@Override
	public void Create() throws SQLException, Exception 
	{
		CallableStatement oraCallStmt = db_connection
				.getConnection()
				.prepareCall("{call SP_INSERT_NEW_DP(?,?,?,?,?)}");
		oraCallStmt.setString(1,delivery.getStreetAddress());
		oraCallStmt.setString(2,delivery.getCity());
		oraCallStmt.setString(3,delivery.getState());
		oraCallStmt.setString(4,delivery.getZipCode());
		oraCallStmt.setInt(5,delivery.getPhone());
		oraCallStmt.execute();
		System.out.println("Procedure Executed");
		System.out.println("A new delivering location has been succesfully added");
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

}
