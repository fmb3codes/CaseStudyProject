package services;


import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import classes.DatabaseConnection;
import interfaces.DatabaseServices;

public class LocationTypeServices implements DatabaseServices
{
	private static String getLT_ID;
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
	public void Delete() throws SQLException, Exception 
	{
		CallableStatement oraCallStmt = db_connection
				.getConnection()
				.prepareCall("{call SP_DEL_NEW_Order(?)}");

				oraCallStmt.setString(1, LocationTypeServices.getLT_ID());
				oraCallStmt.execute();
				System.out.println("Order " + LocationTypeServices.getLT_ID() + " has been succesfully deleted");
		
	}

	private static String getLT_ID() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<String> getAllOrderIDs(String id) throws SQLException, Exception
	{
		ArrayList<String> ids = new ArrayList<String>();
		int k = 0;

		PreparedStatement oraResult = db_connection
				.getConnection()
				.prepareStatement("SELECT O_ID, DELIVERY_DATE, ORDER_ON_HOLD FROM ORDERS"
						+ " WHERE C_ID = ? ");
		oraResult.setString(1, id);
		ResultSet result = oraResult.executeQuery();
		

		while(result.next())
		{
			k++;
			System.out.println(k + " " + result.getString(2) + result.getString(3));
			ids.add(result.getString(1));
		}
			
		
		return ids;
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

	@Override
	public void Update() throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}
	
	

}
