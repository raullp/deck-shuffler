package org.deck;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public class Main {

	private static final String PROPERTIES_FILE_NAME = "deck.properties";

	private static final String NUMBER_OF_LONGEST_SEQUENCES = "NumberOfLongestSequences";
	private static final String DEFAULT_NUMBER_OF_LONGEST_SEQUENCES = "0";

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Deck deck = new Deck();
		deck.shuffle();
		
		System.out.println("Generated deck:");
		
		printDeck(deck);

		LongestDeckSequencesPrinter longestSequencePrinter = new LongestDeckSequencesPrinter();

		int numberOfLongestSequences = getNumberOfLongestSequences();

		System.out.println("Longest sequences found in deck:");
		
		PrintWriter console = new PrintWriter(System.out);

		longestSequencePrinter.printLongestSequences(deck, console,
				numberOfLongestSequences);
		
		console.flush();
		console.close();

	}

	private static int getNumberOfLongestSequences() throws IOException,
			FileNotFoundException {
		Properties deckProperties = new Properties();
		deckProperties.load(new FileReader(new File(PROPERTIES_FILE_NAME)));
		int numberOfLongestSequences = Integer.parseInt(deckProperties
				.getProperty(NUMBER_OF_LONGEST_SEQUENCES,
						DEFAULT_NUMBER_OF_LONGEST_SEQUENCES));
		return numberOfLongestSequences;
	}

	private static void printDeck(Deck deck) {
		for(Card c : deck) {
			System.out.print(c + " ");
		}
		System.out.println();
	}

}
