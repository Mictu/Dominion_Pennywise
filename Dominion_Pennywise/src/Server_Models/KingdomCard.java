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
	
	
	//KingdomCard f�r Dorf-Karte
	protected void actionVillage(){
		countCard++; 
		countAction += 2; 
	}
	
	//KingdomCard f�r Market - Karth 
	protected void actionMaket(){
		countCard++; 
		countAction++; 
		countBuy++; 
		countMoney++; 
	}
	
	//Aktion f�r Schmiedkarte
	protected void actionSmith(){
		countCard += 3; 
	}
	
	//Aktion f�r Holzf�ller
	protected void  actionLumberjack(){
		countBuy++; 
		countMoney += 2; 
	}
	
	//Aktion f�r Jahrmarkt 
	protected void actionFunfair(){
		countAction += 2; 
		countBuy++;
		countMoney += 2; 
	}
	
	//Aktion f�r Laboratorium
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
