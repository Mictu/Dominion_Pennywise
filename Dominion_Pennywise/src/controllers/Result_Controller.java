package controllers;

import server_Models.Player;
import view.Result_View;

public class Result_Controller {
	
//	Player winner;
	Result_View resultView; 
	
	public Result_Controller (Result_View resultView){
		this.resultView = resultView; 
		
		
	}

	private Result_View resultView() {
		// TODO Auto-generated method stub
		return null;
	}
	
//	public Player getWinner(){	
//		for(Player p : Player.player){
//			int minPoint = Integer.MIN_VALUE; 
//			if(p.winPoint > minPoint){
//				minPoint = p.winPoint;
//				winner = p; 
//			}
//		}
//		return winner;	
//	}
//	

}
