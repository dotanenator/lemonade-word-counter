package com.lemonade.wordcounter;

import com.lemonade.wordcounter.model.WordCounterData;
import com.lemonade.wordcounter.model.WordCounterDataFactory;
import com.lemonade.wordcounter.workers.ProcessFileWorker;
import com.lemonade.wordcounter.workers.ProcessStringWorker;
import com.lemonade.wordcounter.workers.ProcessUrlWorker;

import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class WordCounter {
    private static WordCounter _instance = null;

    private Executor executor = Executors.newCachedThreadPool();

    public static WordCounter getInstance() {
        if (_instance == null) {
            _instance = new WordCounter();
        }

        return _instance;
    }

    public String processString(String wordsStr) {
        String id = UUID.randomUUID().toString();
        WordCounterData wordCounterData = WordCounterDataFactory.getInstance().getWordCounterData(id);
        ProcessStringWorker stringWorker = new ProcessStringWorker(wordsStr, executor, wordCounterData);
        executor.execute(stringWorker);

        return id;
    }

    public String processFile(String filePath) throws Exception {
        String id = UUID.randomUUID().toString();
        WordCounterData wordCounterData = WordCounterDataFactory.getInstance().getWordCounterData(id);
        ProcessFileWorker fileWorker = new ProcessFileWorker(filePath, executor, wordCounterData);
        executor.execute(fileWorker);

        return id;
    }

    public String processUrl(String url) throws Exception {
        String id = UUID.randomUUID().toString();
        WordCounterData wordCounterData = WordCounterDataFactory.getInstance().getWordCounterData(id);
        ProcessUrlWorker urlWorker = new ProcessUrlWorker(url, executor, wordCounterData);
        executor.execute(urlWorker);

        return id;
    }

    public int getWordCount(String word, String sessionId) {
        return WordCounterDataFactory.getInstance().getWordCounterData(sessionId).getWordCount(word.toLowerCase());
    }

    public void printAllWords(String sessionId) {
        WordCounterDataFactory.getInstance().getWordCounterData(sessionId).print();
    }
}
