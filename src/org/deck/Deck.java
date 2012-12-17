package org.deck;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/***
 * 
 * @author Raul Lopez Peredo
 * @version Dec. 16, 2012.
 * 
 */
public class Deck implements Comparable<Deck>, Iterable<Card> {

	private static final int DEFAULT_DECK_SIZE = 52;

	private Card[] cards;

	private List<SequenceOfCardsInADeck> sequences;

	/***
	 * Initializes cards deck, where cards are sorted in ascending mode and
	 * grouped by suit. 1, 2, 3, ...., 13 // Heart, 1, 2, 3, ...., 13 // Spade,
	 * 1, 2, 3, ...., 13 // Diamond, and 1, 2, 3, ...., 13 // Club
	 */
	public Deck() {
		cards = new Card[DEFAULT_DECK_SIZE];

		int index = 0;

		for (Suit suit : Suit.values()) {
			for (int i = 1; i <= 13; i++)
				cards[index++] = new Card(i, suit);
		}
	}

	private Deck(Card cards[]) {
		this.cards = cards;
	}

	/***
	 * 
	 * @param cards
	 * @throws InvalidParameterException
	 *             when a null cards parameter is provided or cards is an empty array.
	 * @return Returns an instance of Deck containing given cards as its deck.
	 */
	public static Deck makeDeck(Card cards[]) {
		if ((cards == null) || (cards.length == 0))
			throw new InvalidParameterException();

		return new Deck(cards);
	}

	/***
	 * Shuffles deck cards using the Fisher-Yates algorithm, For more
	 * information about Fisher-Yates algorithm visit
	 * http://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
	 */
	public void shuffle() {
		Random shuffler = new Random();
		int randomIndex;
		int index = cards.length - 1;

		while (index > 0) {
			randomIndex = shuffler.nextInt(index);
			swapCard(index, randomIndex);
			index -= 1;
		}

		this.sequences = null;
	}

	private void swapCard(int a, int b) {
		Card temp = cards[a];
		cards[a] = cards[b];
		cards[b] = temp;
	}

	@Override
	public int compareTo(Deck other) {

		// optimization when compareTo is called against itself
		if (this == other)
			return 0;

		int index = 0;
		int cardComparisson = 0;
		Card card = null;
		Card otherCard = null;

		while ((index < cards.length) && (cardComparisson == 0)) {
			card = cards[index];
			otherCard = other.cards[index];

			cardComparisson = card.compareTo(otherCard);

			index++;
		}

		return cardComparisson;
	}
	
	protected List<SequenceOfCardsInADeck> findCardSequences() {
		if (sequences == null) {

			sequences = new ArrayList<SequenceOfCardsInADeck>();
			
			Card previousCard = cards[0];
			CardSequence previousRelationship = CardSequence.NONE;
			CardSequence cardsRelationship = CardSequence.NONE;
			SequenceOfCardsInADeck sequence = null;
			Card card = null;
			
			for(int i = 1; i < cards.length; i++) {
				card = cards[i];
				cardsRelationship = previousCard.findRelationship(card);
				
				if(previousRelationship != cardsRelationship)
				{	
					switch(cardsRelationship){
					case NONE:
						sequence = null;
						break;
					case ASC:						
					case DESC:
						sequence = new SequenceOfCardsInADeck(i-1);
						sequences.add(sequence);
						break;
					}
					previousRelationship = cardsRelationship;
				} else if(sequence != null)
				{
					sequence.increaseSize();
				}
				
				previousCard = card;
			}
			
		}

		return sequences;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Deck))
			return false;

		Deck otherDeck = (Deck) o;

		return Arrays.equals(this.cards, otherDeck.cards);
	}

	@Override
	public Iterator<Card> iterator() {
		return new DeckIterator();
	}

	class DeckIterator implements Iterator<Card> {

		private int index = 0;

		@Override
		public boolean hasNext() {
			return index < cards.length;
		}

		@Override
		public Card next() {
			return cards[index++];
		}

		@Override
		public void remove() {
			// No need to implement this method.
		}
	}
}
