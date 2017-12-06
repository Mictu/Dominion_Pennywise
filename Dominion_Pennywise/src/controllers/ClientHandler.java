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
		//Get message to set the player hand view
		if(message.length() > 4 && message.substring(0, 4).equals("hand")){
			message = message.substring(4);
			boardview.setHand(message);
		}
		
		
	}
	
	public void setCardDisable(String message){
		
	if(message.substring(0, 10).equals("cardoption")){
		String text = message.substring(10); 
		// get ActionPhase
		
		switch(text){
		case "buy":
			if(handCardList != null){
				for(Button b : handCardList){
					if(!b.getId().equals("copper") ||!b.getId().equals("silver")|| !b.getId().equals("gold")){
						b.setDisable(true);
					}
				}
			}
			break; 
		case "action": 
			if(handCardList != null){
				for(Button b : handCardList){
					if(!b.getId().equals("smith") || !b.getId().equals("market") || !b.getId().equals("laboratory") || 
					!b.getId().equals("funfair") || !b.getId().equals("woodcutter") || !b.getId().equals("village") ){
						b.setDisable(true);
					}
				}
			}
			break; 
		}
	}
		
		
		
		
	}
	
	public void getHand(ArrayList <Button> liste) {
		handCardList = liste; 
			
	}
	
	
	



}
