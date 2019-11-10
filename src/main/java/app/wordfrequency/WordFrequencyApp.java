package app.wordfrequency;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class WordFrequencyApp {

	public static void main(String args[]) throws FileNotFoundException {
		File text = new File("src/main/resources/passage.txt");
		PassageReader reader = new PassageReader();

		ArrayList<String> passageSentences = new ArrayList<String>();
		passageSentences = reader.readSentencesFromFile(text);

		HashMap<String, UniqueWord> passageWordFrequencies = reader.computeWordFrequencies(passageSentences);

		ArrayList<UniqueWord> sortedWordFrequencies = reader.sortWordFrequencies(passageWordFrequencies);

		int totalWordCount = 0;
		for (UniqueWord word : sortedWordFrequencies) {
			totalWordCount += word.getWordCount();
		}

		System.out.println("Total words in passage: " + totalWordCount);
		System.out.println();
		System.out.println("Top ten most frequently appearing words:");
		int i = 0;
		while (i < 10) {
			System.out.println(
					sortedWordFrequencies.get(i).getWordText() + ": " + sortedWordFrequencies.get(i).getWordCount());
			i++;
		}
		System.out.println();
		System.out.println("Last sentence containing most frequently used word:");
		System.out.println(sortedWordFrequencies.get(0).getLastSentenceUsed() + ".");
	}
}
