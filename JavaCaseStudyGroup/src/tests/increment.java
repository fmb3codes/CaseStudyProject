package tests;

public class increment {
	
	
	
	public static void main(String[] args){
		String number = "0000000";
		
		for (int i = 0; i < 40; ++i){
			System.out.println(number);
			//number = String.valueOf((Integer.parseInt(number))+1);
			
			number = String.format("%07d", Integer.parseInt(number)+1);
		}
		
		
		
	}
}
