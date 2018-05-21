package services;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import classes.DatabaseConnection;
import classes.Payments;
import interfaces.DatabaseServices;

public class PaymentServices implements DatabaseServices
{
	
	private DatabaseConnection db_connection;
	private String name;
	private Payments payment;
	
	private void setDatabaseConnection(DatabaseConnection db) {this.db_connection = db;}
	private void setName(String name) 	  				  {this.name = name;}
	
	
	public PaymentServices()
	{
		
	}

	public PaymentServices(DatabaseConnection db) 
	{
		this.setDatabaseConnection(db);
		this.setName(name);
	}
	
	public PaymentServices(DatabaseConnection db, Payments p) 
	{
		this.setDatabaseConnection(db);
		this.payment = p;
	}

	@Override
	public void Create() throws SQLException, Exception 
	{
		CallableStatement oraCallStmt = db_connection
				.getConnection()
				.prepareCall("{call SP_INSERT_PAYMENT(?,?)}");
		oraCallStmt.setString(1, payment.getPaymentType());
		oraCallStmt.setString(2, payment.getOrderID());
		oraCallStmt.executeQuery();
		System.out.println("Procedure Executed");
		System.out.println("A new payment has been succesfully added");
		
		
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
	
	public String getPaymentID(String orderID) throws SQLException, Exception
	{
		PreparedStatement oraPrepStmt = db_connection
				.getConnection()
				.prepareStatement("SELECT TEMP.P_ID FROM (SELECT P.*, ROW_NUMBER() "
						+ "OVER (ORDER BY P.P_ID DESC) R FROM PAYMENTS P) TEMP "
						+ "WHERE R=1 AND O_ID=?");
		oraPrepStmt.setString(1, orderID);
		ResultSet result = oraPrepStmt.executeQuery();
		result.next();
		return result.getString(1);
	}

}
