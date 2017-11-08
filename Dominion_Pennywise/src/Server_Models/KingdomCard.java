package Server_Models;

public class KingdomCard {
	
	int action, countMoney, countAction, countBuy, countCard; 
	
	public KingdomCard(){
		this.action = 0; 
		this.countAction = 0; 
		this.countMoney = 0; 
		this.countBuy = 0; 
		this.countCard = 0; 
	}
	
	protected void actionVillage(){
		countCard++; 
		countAction += 2; 
	}
	
	
	
	
	protected int getActionPoint(){
		return countAction; 
	}
	
	
	protected int getBuyPoint(){
		return countBuy; 
	}
	
	protected int getMoney(){
		return countMoney; 
	}
	
}
