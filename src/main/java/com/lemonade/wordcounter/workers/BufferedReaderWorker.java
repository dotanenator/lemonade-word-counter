package com.lemonade.wordcounter.workers;

import com.lemonade.wordcounter.model.WordCounterData;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.Executor;

public abstract class BufferedReaderWorker implements Runnable {
    private String path;
    private Executor executor = null;
    private BufferedReader reader = null;
    private WordCounterData wordCounterData = null;

    public BufferedReaderWorker(String path, Executor executor, WordCounterData wordCounterData) throws Exception {
        this.path = path;
        this.executor = executor;
        this.wordCounterData = wordCounterData;
        this.reader = getBufferedReader(path);
    }

    @Override
    public void run() {
        try {
            reader.lines().forEach(line -> executor.execute(new WordCounterWorker(line, wordCounterData)));
        } catch (Exception e) {
            System.err.println("error processing file: " + path + ", " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println("error closing reader for file: " + path + ", " + e.getMessage());
                }
            }
        }
    }

    abstract BufferedReader getBufferedReader(String path) throws Exception;
}
