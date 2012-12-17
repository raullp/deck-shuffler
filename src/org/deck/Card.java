package org.deck;

import java.security.InvalidParameterException;

public class Card implements Comparable<Card> {

	private Suit suit;
	private CardNumber number;

	public Card(int number, Suit suit) {
		this(buildCardNumber(number), suit);
	}

	public Card(CardNumber number, Suit suit) {
		this.number = number;
		this.suit = suit;
	}

	private static CardNumber buildCardNumber(int number) {
		switch (number) {
		case 1:
			return CardNumber.AS;
		case 2:
			return CardNumber.TWO;
		case 3:
			return CardNumber.THREE;
		case 4:
			return CardNumber.FOUR;
		case 5:
			return CardNumber.FIVE;
		case 6:
			return CardNumber.SIX;
		case 7:
			return CardNumber.SEVEN;
		case 8:
			return CardNumber.EIGHT;
		case 9:
			return CardNumber.NINE;
		case 10:
			return CardNumber.TEN;
		case 11:
			return CardNumber.JACK;
		case 12:
			return CardNumber.QUEEN;
		case 13:
			return CardNumber.KING;
		}

		throw new InvalidParameterException(
				"Not a valid card number, out of range 1-13");
	}

	@Override
	public int compareTo(Card other) {
		return this.hashCode() - other.hashCode();
	}

	@Override
	public int hashCode() {
		return this.suit.weight() + this.number.numericValue();
	}

	@Override
	public String toString() {
		return Integer.toString(this.number.numericValue());
	}

	public CardSequence findRelationship(Card other) {
		CardSequence sequence = CardSequence.NONE;

		int diff = this.number.numericValue() - other.number.numericValue();

		int absDiff = Math.abs(diff);

		if (absDiff == 1) {
			sequence = (diff < 0) ? CardSequence.ASC : CardSequence.DESC;
		} else if (absDiff == 12) {
			sequence = (diff > 0) ? CardSequence.ASC : CardSequence.DESC;
		}

		return sequence;
	}

}
