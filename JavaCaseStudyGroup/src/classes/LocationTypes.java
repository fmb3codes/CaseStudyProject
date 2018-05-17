package classes;

import exceptions.LocationTypeException;

public class LocationTypes 
{
	private String locationName;
	
	//Constructor
	
	public LocationTypes(String locationName) throws LocationTypeException
	{
		this.setLocationName(locationName);
	}
	
	//Getters
	
	public String getLocationName() {return this.locationName;}
	
	//Setters
	
	public void setLocationName(String ln) throws LocationTypeException
	{
		this.locationName = ln;
	}
	
}
