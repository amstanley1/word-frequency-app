package app.wordfrequency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

public class UniqueWordTest {
	UniqueWord testWord;
	UniqueWord testWord2;
	UniqueWord testWord3;
	ArrayList<UniqueWord> testWords = new ArrayList<UniqueWord>();
	Comparator<UniqueWord> wordCountComparator = UniqueWord.WordCountComparator;

	@Before
	public void setup() {
		testWord = new UniqueWord("test", "This word is test");
		testWord2 = new UniqueWord("test2", "This word is test2");
		testWord3 = new UniqueWord("test3", "This word is test3");
		testWords.add(testWord);
		testWords.add(testWord2);
		testWords.add(testWord3);
	}

	@Test
	public void shouldBeAbleToUpdateWordCount() {
		testWord.updateWordCountAndLastSentenceUsed("Test word used a second time");
		assertEquals(testWord.getWordCount(), 2);
		assertEquals(testWord.getLastSentenceUsed(), "Test word used a second time");
	}

	@Test
	public void testWordCountComparatorEqual() {
		int result = wordCountComparator.compare(testWord, testWord2);
		assertEquals(result, 0);

	}

	@Test
	public void testWordCountComparatorLessThan() {
		testWord2.updateWordCountAndLastSentenceUsed("test2 used a second time");
		int result = wordCountComparator.compare(testWord2, testWord3);
		assertTrue(result <= 1);
	}

	@Test
	public void testWordCountComparatorGreaterThan() {
		testWord2.updateWordCountAndLastSentenceUsed("test2 used a second time");
		int result = wordCountComparator.compare(testWord3, testWord2);
		assertTrue(result >= 1);
	}

}
