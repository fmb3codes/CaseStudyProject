package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.DatabaseConnection;
import interfaces.DatabaseServices;

public class CardTypeServices implements DatabaseServices
{
	
	private DatabaseConnection db_connection;
	private String name;
	
	//Constructor
	
	public CardTypeServices(DatabaseConnection db)
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Find() throws SQLException, Exception 
	{
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<String> getAllCardTypeID() throws SQLException, Exception 
	{
		ArrayList<String> ids = new ArrayList<String>();
		int k = 0;
		String format = "|%1$-3s|%2$-20s|\n";
		ResultSet oraResult = db_connection
				.getStatement()
				.executeQuery("SELECT CT_ID, NAME PRICE FROM CARD_TYPES");
		
		System.out.format(format,"#","Name");
		while(oraResult.next())
		{
			k++;
			System.out.format(format,
					k,
					oraResult.getString(2)
			);
			ids.add(oraResult.getString(1));
		}
		
		return ids;
	}
	
	

}
