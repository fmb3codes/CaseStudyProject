package classes;

public class Admin 
{
	private String adminID;
	private String firstName;
	private String lastName;
	private String password;
	
	
	
	@Override
	public String toString() {
		return "Admin [adminID=" + adminID + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + "]";
	}


	public Admin() 
	{
		
	}
	
	
	public Admin(String adminID, String firstName, String lastName, String password) {

		this.adminID = adminID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}


	public String getAdminID() {
		return adminID;
	}
	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
