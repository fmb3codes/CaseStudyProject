package services;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import classes.DatabaseConnection;
import interfaces.DatabaseServices;

public class LocationTypeServices implements DatabaseServices
{
	private DatabaseConnection db_connection;
	private String name;
	
	//Constructor
	
	public LocationTypeServices(DatabaseConnection db)
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
	
	public ArrayList<String> getAllLocationTypeID() throws SQLException, Exception 
	{
		ArrayList<String> ids = new ArrayList<String>();

		ResultSet oraResult = db_connection
				.getStatement()
				.executeQuery("SELECT LT_ID FROM LOCATIONS_TYPES");
		
		while(oraResult.next())
			ids.add(oraResult.getString("LT_ID"));
		
		return ids;
	}
	
	public ArrayList<String> getAllLocationTypeName() throws SQLException, Exception 
	{
		ArrayList<String> names = new ArrayList<String>();
	
		ResultSet oraResult = db_connection
				.getStatement()
				.executeQuery("SELECT LOCATION_NAME FROM LOCATIONS_TYPES");
		
		while(oraResult.next())
			names.add(oraResult.getString("LOCATION_NAME"));
			
		return names;
	}
	
	

}
