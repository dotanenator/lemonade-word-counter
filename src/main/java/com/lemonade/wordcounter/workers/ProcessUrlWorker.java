package com.lemonade.wordcounter.workers;

import com.lemonade.wordcounter.model.WordCounterData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Executor;

public class ProcessUrlWorker extends BufferedReaderWorker {
    public ProcessUrlWorker(String path, Executor executor, WordCounterData wordCounterData) throws Exception {
        super(path, executor, wordCounterData);
    }

    @Override
    BufferedReader getBufferedReader(String path) throws Exception{
        URL url = new URL(path);
        URLConnection urlConnection = url.openConnection();
        return new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
    }
}
