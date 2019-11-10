package app.wordfrequency;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class PassageReader {

	public PassageReader() {

	}

	public ArrayList<String> readSentencesFromFile(File text) throws FileNotFoundException {
		Scanner scanner = new Scanner(text);
		ArrayList<String> allSentences = new ArrayList<String>();
		int lineNumber = 1;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] lineSentences = line.split("\\.");
			for (String lineSentence : lineSentences) {
				if (!lineSentence.isEmpty()) {
					allSentences.add(lineSentence.trim());
				}
			}
			lineNumber++;
		}
		scanner.close();
		return allSentences;
	}

	public HashMap<String, UniqueWord> computeWordFrequencies(ArrayList<String> sentences) {
		HashMap<String, UniqueWord> wordFrequencies = new HashMap<String, UniqueWord>();
		for (String sentence : sentences) {
			String[] wordsInSentence = sentence.split("[-:\\s\",]+");
			for (String word : wordsInSentence) {
				if (!word.isEmpty()) {
					word = word.toLowerCase();
					if (wordFrequencies.get(word) != null) {
						wordFrequencies.get(word).updateWordCountAndLastSentenceUsed(sentence);
					} else {
						wordFrequencies.put(word, new UniqueWord(word, sentence));
					}
				}
			}
		}
		return wordFrequencies;
	}

	public ArrayList<UniqueWord> sortWordFrequencies(HashMap<String, UniqueWord> wordFrequencies) {
		Collection<UniqueWord> values = wordFrequencies.values();
		ArrayList<UniqueWord> wordFrequenciesList = new ArrayList<UniqueWord>(values);
		Collections.sort(wordFrequenciesList, UniqueWord.WordCountComparator);
		return wordFrequenciesList;
	}
}
