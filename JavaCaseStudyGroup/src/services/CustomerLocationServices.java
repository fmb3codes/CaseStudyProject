package services;

import classes.Customer;
import classes.CustomerLocations;
import classes.DatabaseConnection;
import interfaces.DatabaseServices;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerLocationServices implements DatabaseServices
{
	private static DatabaseConnection db_connection;
	private CustomerLocations customerLocation;
	private Customer customer;
	
	//Constructor
	public CustomerLocationServices()
	{
		
	}
	
	public CustomerLocationServices(DatabaseConnection db)
	{
		this.db_connection = db;
	}
	
	public CustomerLocationServices(DatabaseConnection db, CustomerLocations customerLocation)
	{
		this.setDatabaseConnection(db);
		this.setCustomerLocation(customerLocation);
	}
	
	public CustomerLocationServices(DatabaseConnection db, Customer customer)
	{
		this.setDatabaseConnection(db);
		this.customer = customer;
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
		
		
		
	}

	@Override
	public void Find() throws SQLException, Exception
	{	
		
		
	}
	
	public void displayForID(String custID) {
		DatabaseConnection site = new DatabaseConnection();
		Connection con = site.getConnection();
		
					
		try {
			PreparedStatement oracleStmt = con.prepareStatement("Select LOCATION_NAME as \"Location Type\", STREET_ADDRESS as \"Address\", CITY as \"City\", STATE as \"State\", ZIP_CODE as \"Zip Code\" from CUSTOMERS, CUSTOMERS_LOCATIONS, LOCATIONS_TYPES  where CUSTOMERS.C_ID=CUSTOMERS_LOCATIONS.C_ID AND CUSTOMERS_LOCATIONS.LT_ID=LOCATIONS_TYPES.LT_ID AND CUSTOMERS.C_ID=?");
			oracleStmt.setString(1, custID);
			
			
			// NEED to parse order_on_hold to properly display status
			
			ResultSet oracleRs = oracleStmt.executeQuery();
			
			int num_fields = 0;
			ResultSetMetaData meta_data;
			
			if (oracleRs != null) {
				meta_data = oracleRs.getMetaData();
				num_fields = meta_data.getColumnCount();
				
				String[] col_names = new String[num_fields];

				
				for (int i = 1; i <= num_fields; ++i) { // make iterator condition dynamic
				    col_names[i - 1] = meta_data.getColumnName(i);
				}	
				
				// add if statement to only print if a record was found
				for (String j:col_names){
					System.out.print("| " + j + " ");
				}
				
			}
			
			
			while (oracleRs.next()) {
				//System.out.println(oracleRs.getString("first_name") + " " + oracleRs.getString(2) + " " + oracleRs.getInt("salary"));
				//System.out.println(oracleRs.getString("*"));
				
				//System.out.println("Meta data is: ");
				//System.out.println(oracleRs.getMetaData().getColumnCount());
				
				//ResultSetMetaData meta_data = oracleRs.getMetaData();
				//int num_fields = meta_data.getColumnCount();
				String[] col_fields = new String[num_fields];
				//String[] col_names = new String[num_fields];
				
				for (int i = 1; i <= num_fields; ++i) { // make iterator condition dynamic
				    col_fields[i - 1] = oracleRs.getString(i); // Or even rs.getObject()
				    //col_names[i - 1] = meta_data.getColumnName(i);
				}		
				
				//System.out.println(meal_fields);
				System.out.println();
				
				for (String j:col_fields){
					System.out.print("| " + j + " ");
				}
				
			
			}
			
		}
		catch (Exception ex){
			ex.printStackTrace();
		}

		
		System.out.println("\nQuery Successful");
		
		try {
			site.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<String> getAddresses() throws SQLException, Exception
	{
		ArrayList<String> ids = new ArrayList<String>();
		int k = 0;
		String format = "|%1$-3s|%2$-50s|%3$-20s|%4$-7s|\n";
		PreparedStatement oraPrepStmt = db_connection
				.getConnection()
				.prepareStatement("SELECT CL_ID, STREET_ADDRESS, CITY, ZIP_CODE FROM CUSTOMERS_LOCATIONS"
						+ " WHERE C_ID = ?");
		oraPrepStmt.setString(1, customer.getID());
		ResultSet oraResult = oraPrepStmt.executeQuery();
		
		while(oraResult.next())
		{
			if(k == 0)
			{
				System.out.println("Where would you like the order to be delivered?");
				System.out.format(format,"#","Street","City","Zip Code");
			}
				
			k++;
			System.out.format(format,
					k,
					oraResult.getString(2),
					oraResult.getString(3),
					oraResult.getString(4)
			);
			ids.add(oraResult.getString(1));
		}
		
		return ids;
	}
	
}
