package classes;

import exceptions.DeliveryException;

public class DeliveryLocations extends Locations
{
	private int phone;
	
	//Constructor
	public DeliveryLocations(String streetAddress, String city, String state, String zipCode, int phone) throws DeliveryException
	{
		super(streetAddress, city, state, zipCode);
		this.setPhone(phone);
	}
	
	//Getters

	public int getPhone() {return phone;}
	
	//Setters

	public void setPhone(int phone) throws DeliveryException
	{
		this.phone = phone;
	}
}
