package com.playtika.text.analyzer;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TextTestNGTest {

    Text text;
    String textWithoutWords;

    @BeforeTest
    public void setUp() {
        textWithoutWords = "   \n \t ,.'//\n";
    }

    @Test(groups = "constructor",
            expectedExceptions = IllegalArgumentException.class)
    public void textConstructorThrowsExceptionWhenGetNullableParameter() {
        new Text(null);
    }

    @Test(groups = "getLength",
            dependsOnMethods = "correctCountOfWordsTotalLength")
    public void textWithoutWordsHasZeroWordsLength() {
        text = new Text(textWithoutWords);
        int wordsTotalLength = text.getLengthInChars();
        assertThat(wordsTotalLength, is(0));
    }

    @Test(groups = "getLength",
            dependsOnMethods = "correctCountOfWordsTotalLength")
    public void emptyTextHasZeroWordsLength() {
        text = new Text("");
        int wordsTotalLength = text.getLengthInChars();
        assertThat(wordsTotalLength, is(0));
    }

    @Test(groups = "getLength",
            dependsOnMethods = "correctCountOfWordsTotalLength")
    public void countOfWordsTotalLengthThatSplitByNonLetterSymbols() {
        text = new Text("   I,.\n want (WaNt) to Be a GOOd specialist. \n\t I  have 5 dollars");
        int wordsTotalLength = text.getLengthInChars();
        assertThat(wordsTotalLength, is(41));
    }

    @Test(groups = "getLength",
            priority = 1)
    public void correctCountOfWordsTotalLength() {
        text = new Text("I have 5 dollars");
        int wordsTotalLength = text.getLengthInChars();
        assertThat(wordsTotalLength, is(13));
    }

    @Test(groups = "getTopUnigueWords",
            priority = 1)
    public void getTopUniqueWordsDetermination() {
        text = new Text("What is your name? - Roma is my name.");
        List<String> topWords = text.getTopWords(2);
        assertThat(topWords, hasSize(is(2)));
        assertThat(topWords, hasItems("is", "my"));
    }

    @Test(groups = "getTopUnigueWords",
            dependsOnMethods = "getTopUniqueWordsDetermination")
    public void getTopWordsWhenTextStartsFromNonLetterSymbol() {
        text = new Text(".name");
        List<String> topWords = text.getTopWords(2);
        assertThat(topWords, hasSize(is(1)));
        assertThat(topWords, hasItems("name"));
    }

    @Test(groups = "getTopUnigueWords",
            dependsOnMethods = "getTopUniqueWordsDetermination")
    public void getTopUniqueWordsWithLettersInDifferentRegisters() {
        text = new Text("My name is Roma. Is it my name");
        List<String> topWords = text.getTopWords(2);
        assertThat(topWords, hasSize(is(2)));
        assertThat(topWords, hasItems("is", "it"));
    }

    @Test(groups = "getTopUnigueWords",
            dependsOnMethods = "getTopUniqueWordsDetermination")
    public void getLessUniqueWordsThenExpected() {
        text = new Text("is it. it is");
        List<String> topWords = text.getTopWords(3);
        assertThat(topWords, hasSize(is(2)));
        assertThat(topWords, hasItems("is", "it"));
    }

    @Test(groups = "getTopUnigueWords",
            dependsOnMethods = "getTopUniqueWordsDetermination")
    public void emptyTextHasNoUniqueWords() {
        text = new Text("");
        List<String> topWords = text.getTopWords(3);
        assertThat(topWords, is(empty()));
    }

    @Test(groups = "getTopUnigueWords",
            dependsOnMethods = "getTopUniqueWordsDetermination")
    public void textWithoutWordsHasNoUniqueWords() {
        text = new Text(textWithoutWords);
        List<String> topWords = text.getTopWords(3);
        assertThat(topWords, is(empty()));
    }

    @Test(groups = "getTopUnigueWords",
            expectedExceptions = IllegalArgumentException.class,
            dependsOnMethods = "getTopUniqueWordsDetermination")
    public void getTopWordsMethodWithZeroParameter() {
        text = new Text("My name is Roma. It is my name");
        text.getTopWords(0);
    }

    @Test(groups = "getTopUnigueWords",
            expectedExceptions = IllegalArgumentException.class,
            dependsOnMethods = "getTopUniqueWordsDetermination")
    public void getTopWordsMethodWithNegativeParameterThrowsException() {
        text = new Text("My name is Roma. It is my name");
        text.getTopWords(-3);
    }

    @Test(groups = "getWordFrequencies",
            priority = 1)
    public void getWordFrequenciesSimpleFlow() {
        text = new Text("one, one, two");
        Map<String, Integer> wordFrequencies = text.getWordFrequencies();
        assertThat(wordFrequencies.size(), is(equalTo(2)));
        assertThat(wordFrequencies, allOf(
                hasEntry("one", 2),
                hasEntry("two", 1)));
    }

    @Test(groups = "getWordFrequencies",
            dependsOnMethods = "getWordFrequenciesSimpleFlow")
    public void getWordFrequenciesWithDifferentRegisterOfSymbolsInWords() {
        text = new Text("One, one, two");
        Map<String, Integer> wordFrequencies = text.getWordFrequencies();
        assertThat(wordFrequencies.size(), is(equalTo(2)));
        assertThat(wordFrequencies, allOf(
                hasEntry("one", 2),
                hasEntry("two", 1)));
    }

    @Test(groups = "getWordFrequencies",
            dependsOnMethods = "getWordFrequenciesSimpleFlow")
    public void getWordFrequenciesWhenTextStartsFromNonLetterSymbol() {
        text = new Text(",one, one, two");
        Map<String, Integer> wordFrequencies = text.getWordFrequencies();
        assertThat(wordFrequencies.size(), is(equalTo(2)));
        assertThat(wordFrequencies, allOf(
                hasEntry("one", 2),
                hasEntry("two", 1)));
    }

    @Test(groups = "getWordFrequencies",
            dependsOnMethods = "getWordFrequenciesSimpleFlow")
    public void getWordFrequenciesOfOneRootWords() {
        text = new Text("One, onedrive, two");
        Map<String, Integer> wordFrequencies = text.getWordFrequencies();
        assertThat(wordFrequencies.size(), is(equalTo(3)));
        assertThat(wordFrequencies, allOf(
                hasEntry("one", 1),
                hasEntry("two", 1),
                hasEntry("onedrive", 1)));
    }

    @Test(groups = "getWordFrequencies",
            dependsOnMethods = "getWordFrequenciesSimpleFlow")
    public void emptyTextHasEmptyWordFrequenciesMap() {
        text = new Text("");
        Map<String, Integer> wordFrequencies = text.getWordFrequencies();
        assertThat(wordFrequencies.entrySet(), is(empty()));
    }

    @Test(groups = "getWordFrequencies",
            dependsOnMethods = "getWordFrequenciesSimpleFlow")
    public void textWithoutWordsHasEmptyWordFrequenciesMap() {
        text = new Text(textWithoutWords);
        Map<String, Integer> wordFrequencies = text.getWordFrequencies();
        assertThat(wordFrequencies.entrySet(), is(empty()));
    }
}