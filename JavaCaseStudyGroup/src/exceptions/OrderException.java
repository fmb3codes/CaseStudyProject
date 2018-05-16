package exceptions;

@SuppressWarnings("serial")
public class OrderException extends Exception
{
	public OrderException(String msg)
	{
		super(msg);
	}
}
