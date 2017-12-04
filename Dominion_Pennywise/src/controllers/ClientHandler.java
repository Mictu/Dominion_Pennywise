package controllers;

public class ClientHandler {
	
	
	public void getMessageFromClient(String msg){
		String message = msg; 
		
		
		if(message.length() > 4 && message.substring(0, 4).equals("hand")){
			String parttwo = message.substring(4); 
			
			switch(parttwo){
			case"copper":
				break;
			case"dutchy": 
				break; 
			case"estate":
				break; 
			case"funfair":
				break; 
			case"gold":
				break; 
			case"laboratory":
				break; 
			case"market":
				break; 
			case"province":
				break; 
			case"silver":
				break;
			case"smith": 
				break; 
			case"village":
				break; 
			case"woodcutter":
				break; 
				
			}
			
		}
	}



}
