package classes;

public class CreditCard 
{
	private String nameOnCard;
	private String cardNumber;
	private String cardType;
	
	public CreditCard()
	{
		
	}
	public CreditCard(String nameOnCard, String cardNumber, String cardType) {
		super();
		this.nameOnCard = nameOnCard;
		this.cardNumber = cardNumber;
		this.cardType = cardType;
	}
	
	public String getNameOnCard() {
		return nameOnCard;
	}
	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

}
