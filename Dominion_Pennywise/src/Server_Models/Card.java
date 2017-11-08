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
	
	public abstract void eatThat();
	
	
}
