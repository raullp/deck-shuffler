package org.deck;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

public class TestDeck {
	private List<SequenceOfCardsInADeck> sequences;
	private List<SequenceOfCardsInADeck> expectedSequences;
	
	@Before
	public void init()
	{
		sequences = null;
		expectedSequences = new ArrayList<SequenceOfCardsInADeck>();
	}

	@Test
	public void itShouldGenerateDifferentDecksWhenShuffleMethodIsCalled() {
		int statisticalSampleSize = 50000;
		Set<Deck> shuffledDecks = new TreeSet<Deck>();

		for (int index = 0; index < statisticalSampleSize; index++) {
			Deck aDeck = new Deck();
			aDeck.shuffle();
			shuffledDecks.add(aDeck);
		}

		assertEquals(statisticalSampleSize, shuffledDecks.size());
	}

	@Test
	public void itShouldReturnEmptyCardSequenceWhenThereIsNoSequenceInADeck() {
		int cards[] = { 1, 3, 5, 8, 13, 6, 9, 2, 12, 7, 11, 4, 10 };
		Deck deck = TestDeckUtils.buildADeck(Suit.HEART, cards);
		
		sequences = deck.findCardSequences();

		assertEquals(expectedSequences, sequences);
	}

	@Test
	public void itShouldReturnASequenceForAscendingSequenceFromOneToFour() {
		int cards[] = { 1, 2, 3, 4, 13, 6, 9, 5, 12, 7, 11, 8, 10 };
		Deck deck = TestDeckUtils.buildADeck(Suit.HEART, cards);
		
		expectedSequences.add(new SequenceOfCardsInADeck(0, 4));

		sequences = deck.findCardSequences();

		assertEquals(expectedSequences, sequences);
	}
	
	@Test
	public void itShouldReturnASequenceThreeAscendingSequences() {
		int cards[] = { 13, 1, 2, 3, 4, 6, 7, 8, 9, 11, 12, 5, 10 };
		Deck deck = TestDeckUtils.buildADeck(Suit.HEART, cards);
		
		expectedSequences.add(new SequenceOfCardsInADeck(0, 5));
		expectedSequences.add(new SequenceOfCardsInADeck(5, 4));
		expectedSequences.add(new SequenceOfCardsInADeck(9, 2));

		sequences = deck.findCardSequences();

		assertEquals(expectedSequences, sequences);
	}
	
	@Test
	public void itShouldReturnASequenceWhenAllNumbersFormADescendingSequence() {
		int cards[] = { 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 13, 12, 11 };
		Deck deck = TestDeckUtils.buildADeck(Suit.HEART, cards);
		
		expectedSequences.add(new SequenceOfCardsInADeck(0, cards.length));

		sequences = deck.findCardSequences();

		assertEquals(expectedSequences, sequences);
	}
	
	@Test
	public void itShouldReturnASequenceWhenAllNumbersFormAnAscendingSequence() {
		int cards[] = { 12, 13, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 1, 2, 3 };
		Deck deck = TestDeckUtils.buildADeck(Suit.HEART, cards);
		
		expectedSequences.add(new SequenceOfCardsInADeck(0, cards.length));

		sequences = deck.findCardSequences();

		assertEquals(expectedSequences, sequences);
	}


	@Test
	public void itShouldReturnTwoSequencesWhenAllNumbersFormAnAscendingAndDescendingSequences() {
		int cards[] = { 12, 13, 1, 2, 3, 4, 5, 6, 7, 8, 7, 6, 5, 4, 3, 2, 1, 13 };
		Deck deck = TestDeckUtils.buildADeck(Suit.HEART, cards);
		
		expectedSequences.add(new SequenceOfCardsInADeck(0, 10));
		expectedSequences.add(new SequenceOfCardsInADeck(9, 9));
		
		sequences = deck.findCardSequences();

		assertEquals(2, sequences.size());
		
		assertEquals(expectedSequences, sequences);
	}
}
