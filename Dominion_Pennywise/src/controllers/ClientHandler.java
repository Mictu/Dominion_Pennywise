package controllers;

import java.util.ArrayList;

import javafx.scene.control.Button;
import view.Board_View;

public class ClientHandler {
	
	Board_View boardview;
	
	String phase; 
	ArrayList<Button> handCardList = new ArrayList<Button>();
	
	
	public void getMessageFromClient(String msg){
		String message = msg;
		
		System.out.println("clientHandler1:" + message);
		//Get message to set the player hand view
		if(message.length() > 4 && message.substring(0, 4).equals("hand")){
			message = message.substring(4);
			boardview.setHand(message);
		}
		System.out.println("clientHandler2:" + message);

		if (message.equals("buy")) {
			phase = "buy";
		}
		
		System.out.println("clientHandler3:" + message);
		
		if(message.equals("action")) {
			phase = "action";
		}
		System.out.println("clientHandler4:" + message);
	}
	
	public String getPhase() { // get the actual phases
		return this.phase;
	}
}
