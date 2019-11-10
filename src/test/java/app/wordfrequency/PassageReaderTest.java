package app.wordfrequency;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import app.wordfrequency.PassageReader;
import app.wordfrequency.UniqueWord;

public class PassageReaderTest {
	File text;
	PassageReader reader;

	@Before
	public void setup() {
		text = new File("src/test/resources/testPassage.txt");
		reader = new PassageReader();
	}

	@Test
	public void shouldBeAbleToGetPassageSentences() throws FileNotFoundException {
		ArrayList<String> passageSentences = reader.readSentencesFromFile(text);
		assertEquals(passageSentences.size(), 7);
		assertEquals(passageSentences.get(0), "Hello world hello World Hello world");
	}

	@Test
	public void computeWordFrequenciesComputesCorrectFrequencies() throws FileNotFoundException {
		ArrayList<String> passageSentences = reader.readSentencesFromFile(text);
		HashMap<String, UniqueWord> wordFrequencies = reader.computeWordFrequencies(passageSentences);
		assertEquals(wordFrequencies.get("hello").getWordCount(), 13);
		assertEquals(wordFrequencies.get("world").getWordCount(), 15);
	}

	@Test
	public void sortWordFrequenciesSortsByWordFrequency() throws FileNotFoundException {
		ArrayList<String> passageSentences = reader.readSentencesFromFile(text);
		HashMap<String, UniqueWord> wordFrequencies = reader.computeWordFrequencies(passageSentences);
		ArrayList<UniqueWord> sortedWordFrequencies = reader.sortWordFrequencies(wordFrequencies);
		assertEquals(sortedWordFrequencies.get(0).getWordCount(), 15);
		assertEquals(sortedWordFrequencies.get(1).getWordText(), "hello");
		assertEquals(sortedWordFrequencies.get(1).getLastSentenceUsed(), "Hello world hello World");
	}

}
