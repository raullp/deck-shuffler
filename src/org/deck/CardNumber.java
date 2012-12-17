package org.deck;

/***
 * 
 * @author Raul Lopez
 * 
 *         CardNumber enum will help us to avoid effort validating if a card is
 *         between 1 and 13,
 * 
 */
public enum CardNumber {
	AS(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(
			9), TEN(10), JACK(11), QUEEN(12), KING(13);

	private final int numericValue;

	private CardNumber(int numericValue) {
		this.numericValue = numericValue;
	}

	public int numericValue() {
		return this.numericValue;
	}
}
