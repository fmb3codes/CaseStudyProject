package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

public class Customer {
	Connection con;
	String ID; // review?
	String fName;
	String lName;
	String email; // should email have its own class?
	String password; // should password have its own class?
	Timestamp lastLogin;
	int mobileNumber;
	int homeNumber;
	//PhoneNumber mobile
	//PhoneNumber landline?
	//Login lastLogin
	//Location Address
	public static void main(String[] args) {
		
		Customer testCustomer = new Customer();
		
		//testCustomer.getInfo();
	}
	
	
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Customer(String iD, String fName, String lName, String email, String password, Timestamp lastLogin, int mobileNumber, int homeNumber) {
		super();
		this.ID = iD;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.password = password;
		this.lastLogin = lastLogin;
		this.mobileNumber = mobileNumber;
		this.homeNumber = homeNumber;
	}
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	// OrderService
	
	// create a class to fetch Connection objects
	// getconnection, getstatement, getresultset
	// add an order (addOrder(Order order) { insert into class? values (order.order_id)) , update an order, delete an order, fetch specific order, and fetch all orders
	
	/*public void getInfo() {
		try {
			// Step 1: Register the Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Step 2: Get the connection 
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@172.17.33.25:1521:XE", "db_u7", "pass");
			System.out.println("Connection Successful");
			
			// Step 3: Start the communication by creating a statement
			Statement oracleStmt = con.createStatement();
			
			// Step 4: Execute a Query and Get Results
			ResultSet oracleRs = oracleStmt.executeQuery("Select first_name from Customers");
			
			System.out.println(oracleRs);
			//System.out.println(oracleRs.getFetchSize());
			
			while (oracleRs.next()) {
				this.setfName(oracleRs.getString("first_name"));
				System.out.println(oracleRs.getString("first_name"));
			}
			
			//System.out.println()
			
			// Step 5: Close the connection
			con.close();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}*/
	
	public Connection getCon() {
		return con;
	}



	public void setCon(Connection con) {
		this.con = con;
	}



	public Timestamp getLastLogin() {
		return lastLogin;
	}



	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}



	public int getMobileNumber() {
		return mobileNumber;
	}



	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}



	public int getHomeNumber() {
		return homeNumber;
	}



	public void setHomeNumber(int homeNumber) {
		this.homeNumber = homeNumber;
	}



	@Override
	public String toString() {
		return "Customer [ID=" + ID + ", fName=" + fName + ", lName=" + lName + ", email=" + email
				+ ", password=" + password + ", lastLogin=" + lastLogin + ", mobileNumber=" + mobileNumber
				+ ", homeNumber=" + homeNumber + "]";
	}
	
	
	
    public static java.sql.Timestamp getCurrentTimeStamp() {

	java.util.Date today = new java.util.Date();
	return new java.sql.Timestamp(today.getTime());

}
	
}
