package Server_Models;

public class KingdomCard {
	
	int cost, action, countMoney, countAction, countBuy, countCard; 
	
	
	//Konstruktor vom Kingdom Card
	public KingdomCard(){
		this.action = 0; 
		this.countAction = 0; 
		this.countMoney = 0; 
		this.countBuy = 0; 
		this.countCard = 0; 
		
	}
	
	
	//KingdomCard für Dorf-Karte
	protected void actionVillage(){
		countCard++; 
		countAction += 2; 
	}
	
	//KingdomCard für Market - Karth 
	protected void actionMaket(){
		countCard++; 
		countAction++; 
		countBuy++; 
		countMoney++; 
	}
	
	//Aktion für Schmiedkarte
	protected void actionSmith(){
		countCard += 3; 
	}
	
	//Aktion für Holzfäller
	protected void  actionLumberjack(){
		countBuy++; 
		countMoney += 2; 
	}
	
	//Aktion für Jahrmarkt 
	protected void actionFunfair(){
		countAction += 2; 
		countBuy++;
		countMoney += 2; 
	}
	
	//Aktion für Laboratorium
	protected void actionLaboratory(){
		countCard += 2; 
		countAction++; 
	}
	
	
	protected void cardPlayed(){
		this.action = 0; 
		this.countAction = 0; 
		this.countMoney = 0; 
		this.countBuy = 0; 
		this.countCard = 0; 
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
