package services;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import classes.CreditCardDetails;
import classes.CustomersCreditCards;
import classes.DatabaseConnection;
import interfaces.DatabaseServices;

public class CreditCardDetailServices implements DatabaseServices 
{
	private DatabaseConnection db_connection;
	private String payment;
	private CreditCardDetails c;
	
	private void setDatabaseConnection(DatabaseConnection db) {this.db_connection = db;}
	
	public CreditCardDetailServices()
	{
		
	}

	public CreditCardDetailServices(DatabaseConnection db, CreditCardDetails c) 
	{
		this.setDatabaseConnection(db);
		this.c = c;
	}


	@Override
	public void Create() throws SQLException, Exception 
	{
		CallableStatement oraCallStmt = db_connection
				.getConnection()
				.prepareCall("{call SP_INSERT_CCD(?,?,?)}");
		oraCallStmt.setString(1, c.getP_ID());
		oraCallStmt.setString(2, c.getCC_ID());
		oraCallStmt.setString(3, c.getO_ID());
		oraCallStmt.executeQuery();
		System.out.println("Procedure Executed");
		System.out.println("A new ccd has been succesfully added");
		
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
