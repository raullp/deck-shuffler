package org.deck;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Before;
import org.junit.Test;

public class TestLongestDeckSequence {

	private LongestDeckSequencesPrinter longestDeckSequenceHelper;
	StringWriter outStream;

	@Before
	public void init() {
		outStream = new StringWriter();
		longestDeckSequenceHelper = new LongestDeckSequencesPrinter();
	}

	@Test
	public void itShouldPrintOriginalDeckForADeckWithoutAnySequences()
			throws IOException {
		int cards[] = { 1, 3, 5, 8, 13, 6, 9, 2, 12, 7, 11, 4, 10 };
		Deck deck = TestDeckUtils.buildADeck(Suit.CLUB, cards);
		String expectedOutSequence = "1 3 5 8 13 6 9 2 12 7 11 4 10 ";

		longestDeckSequenceHelper.printLongestSequences(deck, outStream, 0);

		assertEquals(expectedOutSequence, outStream.toString());
	}

	@Test
	public void itShouldPrintHighlightSequenceForADeckWithAllCardasMemberOfASequence()
			throws IOException {
		int cards[] = { 1, 2, 3, 4, 5, 6, 7, 8 };
		Deck deck = TestDeckUtils.buildADeck(Suit.CLUB, cards);
		String expectedOutSequence = "* 1 2 3 4 5 6 7 8 * ";

		longestDeckSequenceHelper.printLongestSequences(deck, outStream, 0);

		assertEquals(expectedOutSequence, outStream.toString());
	}

	@Test
	public void itShouldPrintHighlightAllSequences() throws IOException {
		int cards[] = {1, 4, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 3, 4, 5, 6, 2 , 4 };
		Deck deck = TestDeckUtils.buildADeck(Suit.CLUB, cards);
		String expectedOutSequence = "1 4 * 7 8 * * 1 2 3 4 5 6 7 8 * * 3 4 5 6 * 2 4 ";

		longestDeckSequenceHelper.printLongestSequences(deck, outStream, 0);

		assertEquals(expectedOutSequence, outStream.toString());

	}
	
	@Test
	public void itShouldPrintHighlightedTheLongestSequence() throws IOException {
		int cards[] = {1, 4, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 3, 4, 5, 6, 2 , 4 };
		Deck deck = TestDeckUtils.buildADeck(Suit.CLUB, cards);
		String expectedOutSequence = "1 4 7 8 * 1 2 3 4 5 6 7 8 * 3 4 5 6 2 4 ";

		longestDeckSequenceHelper.printLongestSequences(deck, outStream, 1);

		assertEquals(expectedOutSequence, outStream.toString());
	}
	
	@Test
	public void itShouldPrintHighlightedTheTwoLongestSequences() throws IOException {
		int cards[] = {7, 1, 5, 6, 9, 8, 10, 11, 12, 13, 1, 2, 8, 4, 5, 3, 2, 1, 13, 10, 11 };
		Deck deck = TestDeckUtils.buildADeck(Suit.CLUB, cards);
		String expectedOutSequence = "7 1 5 6 9 8 * 10 11 12 13 1 2 * 8 4 5 * 3 2 1 13 * 10 11 ";

		longestDeckSequenceHelper.printLongestSequences(deck, outStream, 2);

		assertEquals(expectedOutSequence, outStream.toString());
	}
	
	@Test
	public void itShouldPrintHighlightTwiceACardMemberOfTwoConsecutiveSequences()
			throws IOException {
		int cards[] = { 1, 2, 3, 4, 5, 6, 7, 8, 7, 6, 5};
		Deck deck = TestDeckUtils.buildADeck(Suit.CLUB, cards);
		String expectedOutSequence = "* 1 2 3 4 5 6 7 8 * * 8 7 6 5 * ";

		longestDeckSequenceHelper.printLongestSequences(deck, outStream, 0);

		assertEquals(expectedOutSequence, outStream.toString());
	}

}
