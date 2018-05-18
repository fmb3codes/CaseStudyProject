package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import classes.DatabaseConnection;
import interfaces.DatabaseServices;

public class CustomersCreditCardServices implements DatabaseServices 
{

	@Override
	public void Create() throws SQLException, Exception {
		// TODO Auto-generated method stub
		
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

}
