package org.deck;

public enum Suit {
	HEART(100), SPADE(200), DIAMOND(300), CLUB(400);
	
	private final int weight;
	
	private Suit(int value) {
		this.weight = value;
	}
	
	public int weight() { return weight; }
}
