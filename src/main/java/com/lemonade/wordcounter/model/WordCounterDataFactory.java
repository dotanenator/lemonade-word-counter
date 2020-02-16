package com.lemonade.wordcounter.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WordCounterDataFactory {
    private static WordCounterDataFactory _instance = null;

    private Map<String, WordCounterData> dataMap = null;

    private WordCounterDataFactory() {
        dataMap = new ConcurrentHashMap<>();
    }

    public static WordCounterDataFactory getInstance() {
        if (_instance == null) {
            _instance = new WordCounterDataFactory();
        }

        return _instance;
    }

    public WordCounterData getWordCounterData(String id) {
        synchronized (dataMap) {
            if (!dataMap.containsKey(id)) {
                dataMap.put(id, new WordCounterData());
            }
        }

        return dataMap.get(id);
    }
}
