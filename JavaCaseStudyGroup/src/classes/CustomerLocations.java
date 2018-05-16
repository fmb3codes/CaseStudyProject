package classes;
import exceptions.LocationException;

public class CustomerLocations extends Locations
{
	private String customerID;
	private String locationTypeID;
	private final int MINIMUM_ID_LENGTH = 7;
	
	//Constructor
	
	/**
	 * 
	 * @param streetAddress
	 * @param city
	 * @param state
	 * @param zipCode
	 * @param customerID
	 * @param locationTypeID
	 * @throws LocationException
	 */
	public CustomerLocations(String streetAddress, String city, String state, String zipCode, String customerID, String locationTypeID) throws LocationException  
	{
		super(streetAddress, city, state, zipCode);
		this.setCustomerID(customerID);
		this.setLocationTypeID(locationTypeID);
	}
	
	//Getters
	
	public String getCustomerID()   {return customerID; }
	public String getLocationType() {return locationTypeID;}
	
	//Setters

	private void setCustomerID(String customerID) throws LocationException
	{
		if(customerID.isEmpty())
			throw new LocationException("Customer ID cannot be empty");
		else if (customerID.length() < MINIMUM_ID_LENGTH)
			throw new LocationException("Customer ID cannot be less than " + MINIMUM_ID_LENGTH);
		else
			this.customerID = customerID;
	}
	
	private void setLocationTypeID(String locationTypeID) throws LocationException
	{
		if(locationTypeID.isEmpty())
			throw new LocationException("Location ID cannot be empty");
		else if(locationTypeID.length() < MINIMUM_ID_LENGTH)
			throw new LocationException("Location type cannot be less than " + MINIMUM_ID_LENGTH);
		this.locationTypeID = locationTypeID;
	}

}
