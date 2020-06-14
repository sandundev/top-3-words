package com.sandun.mf.topwords.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TopWordsTest {

    private static TopWords objectUnderTest;

    @Test
    public void findTopThree_empty_input_returns_empty_array() {
        String[] top3words = objectUnderTest.findTopThree(toInputStream(""));
        Assertions.assertArrayEquals(new String[]{}, top3words);
    }

    @Test
    public void findTopThree_null_input_returns_empty_array() {
        String[] top3words = objectUnderTest.findTopThree(null);
        Assertions.assertArrayEquals(new String[]{}, top3words);
    }

    @Test
    public void findTopThree_one_word_returns_one_word_array() {
        String[] top3words = objectUnderTest.findTopThree(toInputStream("java"));
        Assertions.assertArrayEquals(new String[]{"java"}, top3words);
    }

    @Test
    public void findTopThree_two_unique_words_returns_two_word_array() {
        String[] top3words = objectUnderTest.findTopThree(toInputStream("java scala"));
        Assertions.assertArrayEquals(new String[]{"java", "scala"}, top3words);
    }

    @Test
    public void findTopThree_three_unique_words_returns_three_word_array() {
        String[] top3words = objectUnderTest.findTopThree(toInputStream("java scala kotlin"));
        Assertions.assertArrayEquals(new String[]{"java", "scala", "kotlin"}, top3words);
    }

    @Test
    public void findTopThree_four_unique_words_returns_any_three_word_array() {
        String[] top3words = objectUnderTest.findTopThree(toInputStream("java node scala kotlin"));
        Assertions.assertArrayEquals(new String[]{"node", "java", "scala"}, top3words);
    }

    @Test
    public void findTopThree_duplicate_words_returns_one_word_array() {
        String[] top3words = objectUnderTest.findTopThree(toInputStream("hello hello hello hello"));
        Assertions.assertArrayEquals(new String[]{"hello"}, top3words);
    }

    @Test
    public void findTopThree_multiple_duplicate_words_in_order_returns_top_three_occurrence_words_in_order() {
        String[] top3words = objectUnderTest.findTopThree(toInputStream("hello hello hello world world a b c"));
        Assertions.assertArrayEquals(new String[]{"hello", "world", "a"}, top3words);
    }

    @Test
    public void findTopThree_multi_line_paragraph_returns_top_three_occurrence_words_in_order() {
        String[] top3words = objectUnderTest.findTopThree(toInputStream("In a village of La Mancha, the name of which I have\n" +
                "no desire to call to\n" +
                "mind, there lived not long since one of those gentlemen that keep a lance\n" +
                "in the lance-rack, an old buckler, a lean hack, and a greyhound for\n" +
                "coursing. An olla of rather more beef than mutton, a salad on most\n" +
                "nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so extra\n" +
                "on Sundays, made away with three-quarters of his income."));
        Assertions.assertArrayEquals(new String[]{"a", "of", "on"}, top3words);
    }

    @Test
    public void findTopThree_duplicate_word_in_mixed_case_returns_one_word_in_lower_case() {
        String[] top3words = objectUnderTest.findTopThree(toInputStream("Hello helLo hEllo hellO"));
        Assertions.assertArrayEquals(new String[]{"hello"}, top3words);
    }


    @Test
    public void findTopThree_duplicate_words_with_apostrophes_returns_one_word_in_lower_case() {
        String[] top3words = objectUnderTest.findTopThree(toInputStream("Hell'o helL'o hEll'o hell'O"));
        Assertions.assertArrayEquals(new String[]{"hell'o"}, top3words);
    }

    @Test
    public void findTopThree_non_words_digits_returns_empty_array() {
        String[] top3words = objectUnderTest.findTopThree(toInputStream("6 1 2 45 678 2 2 2"));
        Assertions.assertArrayEquals(new String[]{}, top3words);
    }

    @Test
    public void findTopThree_non_words_symbols_returns_empty_array() {
        String[] top3words = objectUnderTest.findTopThree(toInputStream("$$ & £ @ $ $ £ £ £ & 6£"));
        Assertions.assertArrayEquals(new String[]{}, top3words);
    }

    @Test
    public void findTopThree_for_large_input_from_file_source_returns_top_three_words() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(new File("src/test/resources/largeTextFile.txt"));
        String[] top3words = objectUnderTest.findTopThree(fileInputStream);
        System.out.println(Arrays.toString(top3words));
        Assertions.assertArrayEquals(new String[]{"a", "of", "on"}, top3words);
    }


    private ByteArrayInputStream toInputStream(String text) {
        return new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
    }

}