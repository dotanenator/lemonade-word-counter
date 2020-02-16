package com.lemonade.wordcounter.workers;

import com.lemonade.wordcounter.model.WordCounterData;

import java.util.concurrent.Executor;

public class ProcessStringWorker implements Runnable {
    private String words = null;
    private Executor executor = null;
    private WordCounterData wordCounterData = null;

    public ProcessStringWorker(String words, Executor executor, WordCounterData wordCounterData) {
        this.words = words;
        this.executor = executor;
        this.wordCounterData = wordCounterData;
    }

    @Override
    public void run() {
        executor.execute(new WordCounterWorker(words, wordCounterData));
    }
}
