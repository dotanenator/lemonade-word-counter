package com.lemonade.wordcounter.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WordCounterData {
    private Map<String, Integer> data = new ConcurrentHashMap<>();

    public void updateWords(Map<String, Integer> words) {
        for (String word: words.keySet()) {
            data.compute(word, (key, val) -> (val == null)? words.get(word): val + words.get(word));
        }
    }

    public int getWordCount(String word) {
        Integer res = data.get(word);
        return res == null? 0: res;
    }

    public void print() {
        System.out.println(data);
    }
}
