package org.deck;

public class TestDeckUtils {
	
	static public Deck buildADeck(Suit suit, int[] cardNumbers) {
		Card[] cards = buildCards(suit, cardNumbers);
		return Deck.makeDeck(cards);
	}

	static public Card[] buildCards(Suit suit, int[] cardNumbers) {
		Card cards[] = new Card[cardNumbers.length];
		int index = 0;

		for (int cardNumber : cardNumbers) {
			Card card = new Card(cardNumber, suit);
			cards[index++] = card;
		}

		return cards;
	}

}
