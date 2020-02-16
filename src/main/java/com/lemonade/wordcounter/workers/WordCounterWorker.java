package com.lemonade.wordcounter.workers;

import com.lemonade.wordcounter.model.WordCounterData;
import com.lemonade.wordcounter.utils.WordCounterUtil;

import java.util.Map;

public class WordCounterWorker implements Runnable {
    private String words = null;
    private WordCounterData wordCounterData = null;

    public WordCounterWorker(String words, WordCounterData wordCounterData) {
        this.words = words.toLowerCase();
        this.wordCounterData = wordCounterData;
    }

    public void run() {
        try {
            Map<String, Integer> wordCount = WordCounterUtil.getWordCount(words);
            wordCounterData.updateWords(wordCount);
        } catch (Exception e) {
            System.err.println("error processing words: " + words + ", " + e.getMessage());
        }
    }
}
