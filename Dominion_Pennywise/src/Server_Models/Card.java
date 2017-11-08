package Server_Models;


public abstract class Card {
	protected String value;
	protected String name;
	protected int cost;


	public void card(String value, String name, int cost) {
		this.value = value;
		this.name = name;
		this.cost = cost;
	}
	
<<<<<<< HEAD
=======
	public abstract void eatThat();
	
>>>>>>> branch 'master' of https://github.com/Mictu/Dominion_Pennywise.git
	
}
