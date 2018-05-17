package classes;
import exceptions.LocationException;

public class Locations 
{
	protected String streetAddress;
	protected String city;
	protected String state;
	protected String zipCode;
	
	//Constructor
	
	public Locations(String streetAddress, String city, String state, String zipCode)
	{
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}
	
	//Getters
	
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
