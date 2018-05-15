package classes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import interfaces.ServiceOperations;

public class CustomerServices implements ServiceOperations{
	
	public static void main(String[] args) {
		
		CustomerServices service = new CustomerServices();
		
		Customer testCustomer1 = new Customer("1000006", "Bob", "Salads", "bobsalad@gmail.com", "pass321", getCurrentTimeStamp(), 321, 154);
		Customer testCustomer2 = new Customer("1000007", "Randy", "Manlet", "ranlet@gmail.com", "pass123", getCurrentTimeStamp(), 123, 143);
		
		//Customer testCustomer3 = new Customer(2, "Randy", "Manlet", "ranlet@gmail.com", "pass123", getCurrentTimeStamp(), 123, 143);

		// sync?
		//service.insertToDB(testCustomer1);
		//service.disp(testCustomer1);
		//service.deleteCustomer(testCustomer2);
		//service.viewCustomer(testCustomer1);
		//service.updateCustomer(new Customer(5, "Randy", "Manletter", "ranlet@gmail.com", "pass123", getCurrentTimeStamp(), 123, 150));
		//service.viewCustomer(testCustomer2);
		//service.addCustomerSP(testCustomer1);
		//service.deleteCustomerSP(testCustomer1);
		service.displayRecords();
		
		
	}
	

	public CustomerServices() {
		super();
		// TODO Auto-generated constructor stub
		
		
	}
	
	public void addCustomerSP(Customer customerToAdd) {
		String ID = customerToAdd.getID();
		String fName = customerToAdd.getfName();
		String lName = customerToAdd.getlName();
		String email = customerToAdd.getEmail();
		String password = customerToAdd.getPassword();
	    Timestamp lastLogin = customerToAdd.getLastLogin();
	    int mobileNumber = customerToAdd.getMobileNumber();
	    int homeNumber = customerToAdd.getHomeNumber();
		
		DatabaseConnection site = new DatabaseConnection();
		Connection con = site.getConnection();
		
		CallableStatement oracleCallableStmt;
		
		try {
			oracleCallableStmt = con.prepareCall("{call SP_INS_CUST(?,?,?,?,?,?,?,?)}");
			
			oracleCallableStmt.setString(1,  ID);
			oracleCallableStmt.setString(2,  fName);
			oracleCallableStmt.setString(3,  lName);
			oracleCallableStmt.setString(4,  password);
			oracleCallableStmt.setTimestamp(5,  lastLogin);
			oracleCallableStmt.setString(6,  email);
			oracleCallableStmt.setInt(7,  homeNumber);
			oracleCallableStmt.setInt(8,  mobileNumber);
			
			oracleCallableStmt.execute();
			oracleCallableStmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Customer inserted, hopefully");
		try {
			site.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void insertToDB(Object customerToAdd) {
		String ID = ((Customer) customerToAdd).getID();
		String fName = ((Customer) customerToAdd).getfName();
		String lName = ((Customer) customerToAdd).getlName();
		String email = ((Customer) customerToAdd).getEmail();
		String password = ((Customer) customerToAdd).getPassword();
	    Timestamp lastLogin = ((Customer) customerToAdd).getLastLogin();
	    int mobileNumber = ((Customer) customerToAdd).getMobileNumber();
	    int homeNumber = ((Customer) customerToAdd).getHomeNumber();
		
	    DatabaseConnection site = new DatabaseConnection();
		Connection con = site.getConnection();
		
		PreparedStatement oraclePreparedStmt;
		try {
			oraclePreparedStmt = con.prepareStatement("insert" + " into customers values(?,?,?,?,?,?,?,?)");
			
			oraclePreparedStmt.setString(1,  ID);
			oraclePreparedStmt.setString(2,  fName);
			oraclePreparedStmt.setString(3,  lName);
			oraclePreparedStmt.setString(4,  password);
			oraclePreparedStmt.setTimestamp(5,  lastLogin);
			oraclePreparedStmt.setString(6,  email);
			oraclePreparedStmt.setInt(7,  homeNumber);
			oraclePreparedStmt.setInt(8,  mobileNumber);
			oraclePreparedStmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//PreparedStatement oraclePreparedStmt = con.prepareStatement("select " + "first_name from customers where department_id=?");

		
		// Step 4: Execute a Query and Get Results
		//ResultSet oracleRs = oracleStmt.executeQuery("Select first_name, last_name, salary from Employees");
		
		//ResultSet oracleRs = oraclePreparedStmt.getResultSet();
		
		//System.out.println(oracleRs);
		//System.out.println(oracleRs.getFetchSize());
		
		System.out.println("Customer inserted, hopefully");
		
		try {
			site.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void updateInDB(Object customerToUpdate) {
		// do null check on input
		String ID = ((Customer) customerToUpdate).getID();
		String fName = ((Customer) customerToUpdate).getfName();
		String lName = ((Customer) customerToUpdate).getlName();
		String email = ((Customer) customerToUpdate).getEmail();
		String password = ((Customer) customerToUpdate).getPassword();
	    Timestamp lastLogin = ((Customer) customerToUpdate).getLastLogin();
	    int mobileNumber = ((Customer) customerToUpdate).getMobileNumber();
	    int homeNumber = ((Customer) customerToUpdate).getHomeNumber();
		
	    DatabaseConnection site = new DatabaseConnection();
		Connection con = site.getConnection();
		
		PreparedStatement oraclePreparedStmt;
		try {
			oraclePreparedStmt = con.prepareStatement("update" + " customers set first_name=?, last_name=?, password=?, last_login=?, email=?, home_number=?, mobile_number=? WHERE C_ID=?");
			
			oraclePreparedStmt.setString(1,  fName);
			oraclePreparedStmt.setString(2,  lName);
			oraclePreparedStmt.setString(3,  password);
			oraclePreparedStmt.setTimestamp(4,  lastLogin);
			oraclePreparedStmt.setString(5,  email);
			oraclePreparedStmt.setInt(6,  homeNumber);
			oraclePreparedStmt.setInt(7,  mobileNumber);
			oraclePreparedStmt.setString(8,  ID);
			oraclePreparedStmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//PreparedStatement oraclePreparedStmt = con.prepareStatement("select " + "first_name from customers where department_id=?");

		
		// Step 4: Execute a Query and Get Results
		//ResultSet oracleRs = oracleStmt.executeQuery("Select first_name, last_name, salary from Employees");
		
		//ResultSet oracleRs = oraclePreparedStmt.getResultSet();
		
		//System.out.println(oracleRs);
		//System.out.println(oracleRs.getFetchSize());
		
		System.out.println("Customer updated, hopefully");
		
		try {
			site.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteCustomerSP(Customer customerToDelete) {
		String ID = customerToDelete.getID();

		DatabaseConnection site = new DatabaseConnection();
		Connection con = site.getConnection();
		
		CallableStatement oracleCallableStmt;
		
		try {
			oracleCallableStmt = con.prepareCall("{call SP_DEL_CUST(?)}");
			
			oracleCallableStmt.setString(1,  ID);
			
			oracleCallableStmt.executeUpdate();
			oracleCallableStmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Customer deleted, hopefully");
		try {
			site.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteFromDB(Object customerToDelete) {
		DatabaseConnection site = new DatabaseConnection();
		Connection con = site.getConnection();
		
		System.out.println("Getting here");
		
		System.out.println(((Customer) customerToDelete).getID());
		try {
			Statement oracleStmt = con.createStatement();
			
			//con.setAutoCommit(false);

			System.out.println("About to delete");
			PreparedStatement oraclePreparedStmt = con.prepareStatement("Delete from Customers where C_ID=?");
			oraclePreparedStmt.setString(1, ((Customer) customerToDelete).getID());
			
			
			oraclePreparedStmt.executeUpdate();
			//oracleStmt.executeUpdate("DELETE FROM CUSTOMERS WHERE C_ID =" + customerToDelete.getID());
			
			
			//con.commit();
			
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		
		// check size of oracleRs return and mention if empty
		
		System.out.println("Query successful");
		try {
			site.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void displayRecord(Object customerToView) {
		DatabaseConnection site = new DatabaseConnection();
		Connection con = site.getConnection();
		
		System.out.println("Getting here");
		
		try {
			Statement oracleStmt = con.createStatement();

			// change to prepared
			ResultSet oracleRs = oracleStmt.executeQuery("Select * from Customers where C_ID =" + ((Customer) customerToView).getID());
			
			System.out.println(oracleRs);
			
			while (oracleRs.next()) {
				//System.out.println(oracleRs.getString("first_name") + " " + oracleRs.getString(2) + " " + oracleRs.getInt("salary"));
				//System.out.println(oracleRs.getString("*"));
				
				String[] cust_fields = new String[8];
				for (int i = 1; i <= 8; ++i) {
				    cust_fields[i - 1] = oracleRs.getString(i); // Or even rs.getObject()
				}		
				
				/*String[] cust_fields = new String[8];
				for (int i = 1; i <= 8; ++i) {
				    cust_fields[i - 1] = oracleRs.getString(i); // Or even rs.getObject()
				}		
				
				System.out.println(cust_fields);
				for (String j:cust_fields){
					System.out.println(j);
				}*/
				
				System.out.println("-----Customer Information-----");
				for (int j = 0; j < cust_fields.length; j++)
				{
					System.out.println(cust_fields[j]);
				}
				
			
			}
			
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		
		// check size of oracleRs return and mention if empty
		
		System.out.println("Query successful");
		try {
			site.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void displayRecords() {
		
		DatabaseConnection site = new DatabaseConnection();
		Connection con = site.getConnection();
		
		
		
		try {
			Statement oracleStmt = con.createStatement();

			
			ResultSet oracleRs = oracleStmt.executeQuery("Select * from Customers");
			
			
			int num_fields = 0;
			ResultSetMetaData meta_data;
			
			if (oracleRs != null) {
				meta_data = oracleRs.getMetaData();
				num_fields = meta_data.getColumnCount();
				
				String[] customer_names = new String[num_fields];

				
				for (int i = 1; i <= num_fields; ++i) { // make iterator condition dynamic
				    customer_names[i - 1] = meta_data.getColumnName(i);
				}	
				
				for (String j:customer_names){
					System.out.print(j + " ");
				}
				
			}
			
			
			while (oracleRs.next()) {
				//System.out.println(oracleRs.getString("first_name") + " " + oracleRs.getString(2) + " " + oracleRs.getInt("salary"));
				//System.out.println(oracleRs.getString("*"));
				
				//System.out.println("Meta data is: ");
				//System.out.println(oracleRs.getMetaData().getColumnCount());
				
				//ResultSetMetaData meta_data = oracleRs.getMetaData();
				//int num_fields = meta_data.getColumnCount();
				String[] customer_fields = new String[num_fields];
				//String[] meal_names = new String[num_fields];
				
				for (int i = 1; i <= num_fields; ++i) { // make iterator condition dynamic
					customer_fields[i - 1] = oracleRs.getString(i); // Or even rs.getObject()
				    //meal_names[i - 1] = meta_data.getColumnName(i);
				}		
				
				//System.out.println(meal_fields);
				System.out.println();
				
				for (String j:customer_fields){
					System.out.print(j + " ");
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
	
    private static java.sql.Timestamp getCurrentTimeStamp() {

	java.util.Date today = new java.util.Date();
	return new java.sql.Timestamp(today.getTime());

}
	
}
