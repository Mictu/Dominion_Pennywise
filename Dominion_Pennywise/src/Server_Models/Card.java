package Server_Models;

public abstract class Card {
	private String value;
	private String name;
	private int cost;
	

	public void card(String value, String name, int cost) {
		this.value = value;
		this.name = name;
		this.cost = cost;
		
		System.out.println("Sojo schreibt");
		
	}
}
