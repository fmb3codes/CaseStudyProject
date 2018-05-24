package services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.CreditCard;
import classes.CustomersCreditCards;
import classes.DatabaseConnection;
import interfaces.DatabaseServices;

public class CustomersCreditCardServices implements DatabaseServices 
{
	
	private DatabaseConnection db_connection;
	private String name;
	private CustomersCreditCards customerCard;
	
	private void setDatabaseConnection(DatabaseConnection db) {this.db_connection = db;}
	private void setName(String name) 	  				  {this.name = name;}
	
	public CustomersCreditCardServices()
	{
		
	}
	
	public CustomersCreditCardServices(DatabaseConnection db) 
	{
		this.setDatabaseConnection(db);
	}

	public CustomersCreditCardServices(DatabaseConnection db, CustomersCreditCards c) 
	{
		this.setDatabaseConnection(db);
		this.customerCard = c;
	}

	@Override
	public void Create() throws SQLException, Exception
	{
		CallableStatement oraCallStmt = db_connection
				.getConnection()
				.prepareCall("{call SP_INSERT_CC(?,?)}");
		oraCallStmt.setString(1, customerCard.getCC_ID() );
		oraCallStmt.setString(2, customerCard.getC_ID());
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
	
	public void displayForID(String custID) {
		DatabaseConnection site = new DatabaseConnection();
		Connection con = site.getConnection();
		
					
		try {
			PreparedStatement oracleStmt = con.prepareStatement("Select CARD_TYPES.NAME as \"Card Type\", CREDIT_CARDS.NAME_ON_CARD as \"Name on card\", REPLACE(CREDIT_CARDS.CARD_NUMBER, SUBSTR(CREDIT_CARDS.CARD_NUMBER, 1, 12), '**** **** **** ') as \"Card number\" from CUSTOMERS, CARD_TYPES, CREDIT_CARDS, CUSTOMERS_CREDIT_CARDS  where CUSTOMERS.C_ID=CUSTOMERS_CREDIT_CARDS.C_ID AND CREDIT_CARDS.CT_ID=CARD_TYPES.CT_ID AND CUSTOMERS.C_ID=?");
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
				String[] col_fields = new String[num_fields];
				
				for (int i = 1; i <= num_fields; ++i) { // make iterator condition dynamic
				    col_fields[i - 1] = oracleRs.getString(i); // Or even rs.getObject()
				}		
				
				System.out.println();
				
				for (String j:col_fields){
					System.out.print("| " + j + " ");
				}
				
			
			}
			
		}
		catch (Exception ex){
			ex.printStackTrace();
		}

		
		//System.out.println("\nQuery Successful");
		
		try {
			site.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public ArrayList<String> getAllCreditCards(String id) throws SQLException, Exception
	{
		ArrayList<String> ids = new ArrayList<String>();
		int k = 0;
		String format = "|%1$-3s|%2$-20s|%3$-20s|%4$-15s|\n";
		PreparedStatement oraPrepStmt = db_connection
				.getConnection()
				.prepareStatement("SELECT CC.CC_ID, CC.NAME_ON_CARD, CC.CARD_NUMBER, CT.NAME FROM "
						+ "CUSTOMERS_CREDIT_CARDS CCC "
						+ "JOIN CREDIT_CARDS CC ON CCC.CC_ID = CC.CC_ID "
						+ "JOIN CARD_TYPES CT ON CC.CT_ID = CT.CT_ID "
						+ "WHERE CCC.C_ID = ?");
		oraPrepStmt.setString(1, id);
		ResultSet oraResult = oraPrepStmt.executeQuery();
		
		System.out.format(format,"#","Name","Credit Card","Type");
		while(oraResult.next())
		{
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
