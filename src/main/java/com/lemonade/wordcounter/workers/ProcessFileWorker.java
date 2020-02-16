package com.lemonade.wordcounter.workers;

import com.lemonade.wordcounter.model.WordCounterData;

import java.io.*;
import java.util.concurrent.Executor;

public class ProcessFileWorker extends BufferedReaderWorker {

    public ProcessFileWorker(String path, Executor executor, WordCounterData wordCounterData) throws Exception {
        super(path, executor, wordCounterData);
    }

    @Override
    BufferedReader getBufferedReader(String path) throws Exception {
        return new BufferedReader(new FileReader(path));
    }
}
