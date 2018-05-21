package services;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import classes.CreditCard;
import classes.DatabaseConnection;
import interfaces.DatabaseServices;

public class CreditCardServices implements DatabaseServices
{

	private DatabaseConnection db_connection;
	private String name;
	private CreditCard cc;
	
	private void setDatabaseConnection(DatabaseConnection db) {this.db_connection = db;}
	private void setName(String name) 	  				  {this.name = name;}
	
	
	public CreditCardServices()
	{
		
	}

	public CreditCardServices(DatabaseConnection db, CreditCard c) 
	{
		this.setDatabaseConnection(db);
		this.cc = c;
	}
	
	
	@Override
	public void Create() throws SQLException, Exception
	{
		CallableStatement oraCallStmt = db_connection
				.getConnection()
				.prepareCall("{call SP_INSERT_NEW_CARD(?,?,?)}");
		oraCallStmt.setString(1, cc.getNameOnCard());
		oraCallStmt.setString(2, cc.getCardNumber());
		oraCallStmt.setString(3, cc.getCardType());
		oraCallStmt.executeQuery();
		System.out.println("A new credit card has been succesfully added");
		
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
	public String getCreditCard() throws SQLException, Exception
	{
		ResultSet oraResult = db_connection
				.getStatement()
				.executeQuery("SELECT TEMP.CC_ID FROM (SELECT C.*, ROW_NUMBER() "
						+ "OVER (ORDER BY C.CC_ID DESC) R FROM CREDIT_CARDS C) TEMP "
						+ "WHERE R=1");
		oraResult.next();
		return oraResult.getString(1);
	}

}
