package controllers;

import server_Models.Player;
import view.Login_View;

public class Login_Controller {
	
	Login_View loginview; 
	Player player = new Player(); 
	
	public Login_Controller(Login_View loginview  ){
		
		this.loginview = loginview; 
		// 
		
		String text = loginview.nameLbl.getText(); 
		player.setName(text);// name als text

	}
	
	
	
}
