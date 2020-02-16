package com.lemonade.wordcounter.utils;

import java.util.HashMap;
import java.util.Map;

public class WordCounterUtil {
    public static Map<String, Integer> getWordCount(String wordStr) {
        Map<String, Integer> res = new HashMap<>();
        String[] words = wordStr.split("\\W+");

        for (String word: words) {
            if (word.isEmpty()) {
                continue;
            }

            if (!res.containsKey(word)) {
                res.put(word, 1);
            } else {
                res.put(word, res.get(word) + 1);
            }
        }

        return res;
    }
}
