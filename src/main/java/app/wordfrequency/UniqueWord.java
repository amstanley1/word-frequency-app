package app.wordfrequency;

import java.util.Comparator;

public class UniqueWord {

	private String wordText;
	private String lastSentenceUsed;
	private int wordCount = 0;

	public UniqueWord(String word, String lastSentenceUsed) {
		this.wordText = word;
		this.lastSentenceUsed = lastSentenceUsed;
		wordCount++;
	}

	public int getWordCount() {
		return this.wordCount;
	}

	public String getWordText() {
		return this.wordText;
	}

	public String getLastSentenceUsed() {
		return this.lastSentenceUsed;
	}

	public void updateWordCountAndLastSentenceUsed(String lastSentenceUsed) {
		this.lastSentenceUsed = lastSentenceUsed;
		wordCount++;
	}

	public static Comparator<UniqueWord> WordCountComparator = new Comparator<UniqueWord>() {
		public int compare(UniqueWord w1, UniqueWord w2) {
			Integer UniqueWord1 = w1.getWordCount();
			Integer UniqueWord2 = w2.getWordCount();

			// descending order
			return UniqueWord2.compareTo(UniqueWord1);
		}
	};
}
