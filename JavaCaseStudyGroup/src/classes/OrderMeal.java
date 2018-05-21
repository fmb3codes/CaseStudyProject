package classes;

public class OrderMeal 
{
	private String orderID;
	private String mealID;
	private int Qty;
	
	public int getQty() {
		return Qty;
	}

	public void setQty(int qty) {
		Qty = qty;
	}

	public OrderMeal()
	{}
	
	public OrderMeal(String orderID, String mealID, int qty)
	{
		this.orderID = orderID;
		this.mealID = mealID;
		this.Qty = qty;
	}
	
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getMealID() {
		return mealID;
	}
	public void setMealID(String mealID) {
		this.mealID = mealID;
	}
	
}
