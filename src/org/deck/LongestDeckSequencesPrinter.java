package org.deck;

import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class LongestDeckSequencesPrinter {

	private static final int ALL_SEQUENCES = 0;
	private char sequenceSeparator;
	private char cardSeparator;

	private final static char DEFAULT_SEQUENCE_SEPARATOR = '*';
	private final static char DEFAULT_CARD_SEPARATOR = ' ';

	public LongestDeckSequencesPrinter() {
		this(DEFAULT_SEQUENCE_SEPARATOR, DEFAULT_CARD_SEPARATOR);
	}

	public LongestDeckSequencesPrinter(char sequenceSeparator,
			char cardSeparator) {
		this.sequenceSeparator = sequenceSeparator;
		this.cardSeparator = cardSeparator;
	}

	public void printLongestSequences(Deck deck, Writer outStream,
			int numberOfLongestSequences) throws IOException {
		int index = 0;
		boolean inSequence = false;

		Queue<SequenceOfCardsInADeck> longestSequencesQueue = getInterestedSequences(
				deck, numberOfLongestSequences);

		Card previousCard = null;
		SequenceOfCardsInADeck currentSequence = longestSequencesQueue.poll();

		for (Card card : deck) {

			if (inSequence) {				
				for (;;) { // to avoid nested if anti pattern  
					if (currentSequence.isInRange(index))
						break;

					inSequence = false;
					currentSequence = longestSequencesQueue.poll();

					printSequenceSeparator(outStream);

					if ((currentSequence == null)
							|| !(currentSequence.isInRange(index)))
						break;

					// case of consecutive sequences
					// previous sequence was finished
					// 1 2 3 5 6, * 1 2 3 * was printed, need to add sequence separator
					// to start the new one * 1 2 3 * * 5
					
					inSequence = true;
					printSequenceSeparator(outStream);

					// case of element is member of two consecutive sequence
					// 1 2 3 2 1, * 1 2 3 * * was already printed, need to
					// include the previous card '3' to start
					// the new sequence so it will be printed as * 1 2 3 * * 3 2 1 * 
					if (!currentSequence.isInRange(index - 1))
						break;
					
					printCard(outStream, previousCard);
					
					break;
				}
			} else if (currentSequence != null
					&& currentSequence.isInRange(index)) {
				inSequence = true;
				printSequenceSeparator(outStream);
			}

			printCard(outStream, card);

			previousCard = card;

			index++;
		}

		// ensure last deck's element is member of a longest sequence
		if (inSequence) {
			printSequenceSeparator(outStream);
		}
	}

	private Queue<SequenceOfCardsInADeck> getInterestedSequences(Deck deck,
			int numberOfInterestedSequences) {

		LinkedList<SequenceOfCardsInADeck> allSequences = new LinkedList<SequenceOfCardsInADeck>(
				deck.findCardSequences());

		// Check interested in all sequences
		if ((numberOfInterestedSequences == ALL_SEQUENCES)
				|| (allSequences.size() < numberOfInterestedSequences)) {

			return allSequences;
		}

		@SuppressWarnings("unchecked")
		LinkedList<SequenceOfCardsInADeck> interestedSequences = (LinkedList<SequenceOfCardsInADeck>) allSequences
				.clone();

		Collections.sort(allSequences);

		removeNonInterestedSequences(numberOfInterestedSequences, allSequences);

		interestedSequences.retainAll(allSequences);

		return interestedSequences;
	}

	/**
	 * Removes elements from sorted sequences, it left only the most significant
	 * card sequences (greater ones)
	 * 
	 * @param numberOfInterestedSequences
	 * @param sortedSequences
	 *            A list of of sorted sequences in an ascending mode
	 */
	private void removeNonInterestedSequences(int numberOfInterestedSequences,
			LinkedList<SequenceOfCardsInADeck> sortedSequences) {
		int uninterestedSequencesCount = sortedSequences.size()
				- numberOfInterestedSequences;

		while (uninterestedSequencesCount-- > 0) {
			sortedSequences.removeFirst();
		}
	}

	private void printCard(Writer outStream, Card card) throws IOException {
		outStream.append(card.toString());
		outStream.append(cardSeparator);
	}

	private void printSequenceSeparator(Writer outStream) throws IOException {
		outStream.append(sequenceSeparator);
		outStream.append(cardSeparator);
	}

}
