package controllers;

import view.Board_View;

public class ClientHandler {
	
	Board_View boardview;
	
	public void getMessageFromClient(String msg){
		String message = msg; 
		
		
		if(message.length() > 4 && message.substring(0, 4).equals("hand")){
			message = message.substring(4);
			boardview.setHand(message);
		}
		
		
	}



}
