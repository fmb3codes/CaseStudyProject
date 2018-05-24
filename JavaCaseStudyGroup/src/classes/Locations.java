package classes;
import exceptions.LocationException;

public class Locations 
{
	protected String streetAddress;
	protected String city;
	protected String state;
	protected String zipCode;
	protected String CustomerID;
	protected String LocationTypeID;
	/**
	 * 
	 */
	public Locations() {
		super();
	}

	/**
	 * @param streetAddress
	 * @param city
	 * @param state
	 * @param zipCode
	 * @param customerID
	 * @param locationTypeID
	 */
	public Locations(String streetAddress, String city, String state, String zipCode, String customerID,
			String locationTypeID) {
		super();
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		CustomerID = customerID;
		LocationTypeID = locationTypeID;
	}

	//Constructor
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Locations [streetAddress=" + streetAddress + ", city=" + city + ", state=" + state + ", zipCode="
				+ zipCode + ", CustomerID=" + CustomerID + ", LocationTypeID=" + LocationTypeID + "]";
	}

	/**
	 * @return the customerID
	 */
	public String getCustomerID() {
		return CustomerID;
	}

	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}

	/**
	 * @return the locationTypeID
	 */
	public String getLocationTypeID() {
		return LocationTypeID;
	}

	/**
	 * @param locationTypeID the locationTypeID to set
	 */
	public void setLocationTypeID(String locationTypeID) {
		LocationTypeID = locationTypeID;
	}

	/**
	 * @param streetAddress the streetAddress to set
	 */
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public Locations(String streetAddress, String city, String state, String zipCode)
	{
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}
	
	//Getters
	
	/**
	 * 
	 */

	public String getStreetAddress() {return streetAddress;}
	public String getCity() {return city;}
	public String getState() {return state;}
	public String getZipCode() {return zipCode;}
	
	//Setters
	
	/**
	 * This method will set a street address
	 * @param streetAdress String parameter
	 */
	public void setStreetAdress(String streetAddress) throws LocationException
	{
		if(streetAddress.isEmpty())
			throw new LocationException("Street Address cannot be empty");
		else
			this.streetAddress = streetAddress;
	}
	
	public void setCity(String city) throws LocationException
	{
		if(city.isEmpty())
			throw new LocationException("City cannot be empty");
		else
			this.city = city;
	}
	
	public void setState(String state) throws LocationException
	{
		if(state.isEmpty())
			throw new LocationException("State cannot be empty");
		else
			this.state = state;
	}
	
	public void setZipCode(String zipCode) throws LocationException
	{
		if(zipCode.isEmpty())
			throw new LocationException("Zip code cannot be empty");
		else
			this.zipCode = zipCode;
	}
	
}
