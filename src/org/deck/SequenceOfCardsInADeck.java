package org.deck;

class SequenceOfCardsInADeck implements Comparable<SequenceOfCardsInADeck>{
	private int startIndex;
	private int size;

	private static final int MIN_SEQUENCE_SIZE = 2; // When a sequence is
													// created it has at least
													// two members to be
													// considered a sequence

	SequenceOfCardsInADeck(int startIndex) {
		this(startIndex, MIN_SEQUENCE_SIZE);
	}

	SequenceOfCardsInADeck(int startIndex, int size) {
		this.startIndex = startIndex;
		this.size = size;
	}

	void increaseSize() {
		size += 1;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof SequenceOfCardsInADeck))
			return false;

		SequenceOfCardsInADeck other = (SequenceOfCardsInADeck) o;

		return (this.size == other.size)
				&& (this.startIndex == other.startIndex);
	}

	public boolean isInRange(int index) {
		return (index >= this.startIndex) && (index <= getEndIndex());
	}

	private int getEndIndex() {
		return this.startIndex + this.size - 1;
	}

	public int getSize() {
		return this.size;
	}

	@Override
	public int compareTo(SequenceOfCardsInADeck o) {
		return this.size - o.size;
	}

}