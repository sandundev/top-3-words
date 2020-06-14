package com.sandun.mf.topwords.utils;

import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TopWords {
    private static final Pattern REGEX_PATTERN = Pattern.compile("([a-zA-Z]+|'+)+");

    public static String[] findTopThree(final InputStream inputStream) {
        if(inputStream == null) return new String[]{};

        //find word matches in input stream
        Stream<String> wordStream = new Scanner(inputStream).findAll(REGEX_PATTERN)
                .map(MatchResult::group);

        // get Word vs Counts using groupingBy
        Map<String, Long> wordCounts = wordStream
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        //get entry set of wordCounts AND
        // reverseSort the stream by Map.Entry's value attribute,
        // then get first 3 Entry Keys of the Map as an Array []
        return wordCounts.entrySet().parallelStream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(3).map(Map.Entry::getKey).toArray(String[]::new);
    }


}
