package classes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import exceptions.LocationException;

public class CustomerLocations extends Locations
{
	private String customerID;
	private String locationTypeID;
	private final int MINIMUM_ID_LENGTH = 7;
	
	//Constructors
	
	public CustomerLocations() 
	{
		super("", "", "", "");
	}
	
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

	public void setCustomerID(String customerID) throws LocationException
	{
		if(customerID.isEmpty())
			throw new LocationException("Customer ID cannot be empty");
		else if (customerID.length() < MINIMUM_ID_LENGTH)
			throw new LocationException("Customer ID cannot be less than " + MINIMUM_ID_LENGTH);
		else
			this.customerID = customerID;
	}
	
	public void setLocationTypeID(String locationTypeID) throws LocationException
	{
		if(locationTypeID.isEmpty())
			throw new LocationException("Location ID cannot be empty");
		else if(locationTypeID.length() < MINIMUM_ID_LENGTH)
			throw new LocationException("Location type cannot be less than " + MINIMUM_ID_LENGTH);
		this.locationTypeID = locationTypeID;
	}
	

	
	}


