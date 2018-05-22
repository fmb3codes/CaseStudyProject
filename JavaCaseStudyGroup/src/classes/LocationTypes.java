package classes;

import exceptions.LocationTypeException;
import exceptions.OrderException;


public class LocationTypes 
{
	private String locationName;
	private String LT_ID;
	private final int MINIMUM_ID_LENGTH = 7;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LocationTypes [locationName=" + locationName + ", LT_ID=" + LT_ID + "]";
	}


	//Constructor
	/**
	 * @param locationName
	 */
	
	public LocationTypes(String locationName) {
		super();
		this.locationName = locationName;
	}
	
	
	//Getters
	
	public String getLocationName() {return this.locationName;}
	
	//Setters
	
	public void setLocationName(String ln) throws LocationTypeException
	{
		this.locationName = ln;
	}


	/**
	 * @return the lT_ID
	 * @throws LocationTypeException 
	 * @throws OrderException 
	 */
	public String getLT_ID(String LT_ID) throws LocationTypeException, OrderException {
		if(LT_ID.length() < MINIMUM_ID_LENGTH)
			throw new OrderException("Customer ID must be at least " + MINIMUM_ID_LENGTH + " characters long");
		else
			this.LT_ID = LT_ID;
		return LT_ID;
	}


	/**
	 * @param lT_ID the lT_ID to set
	 */
	public void setLT_ID(String lT_ID) {
		LT_ID = lT_ID;
	}
	
}
