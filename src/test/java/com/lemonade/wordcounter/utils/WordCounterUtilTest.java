package com.lemonade.wordcounter.utils;

import com.lemonade.wordcounter.WordCounter;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static org.junit.Assert.*;

public class WordCounterUtilTest {

    @Test
    public void stringBasicTest() {
        String wordsStr = "Hi! My name is (what?), my name is (who?), my name is Slim Shady";
        String id = WordCounter.getInstance().processString(wordsStr);
        safeSleep(500);
        Assert.assertEquals(3, WordCounter.getInstance().getWordCount("my", id));
        Assert.assertEquals(1, WordCounter.getInstance().getWordCount("what", id));
        Assert.assertEquals(0, WordCounter.getInstance().getWordCount("hello", id));
    }

    @Test
    public void fileBasicTest() {
        String wordsStr = "Hi! My name is (what?), my name is (who?), my name is Slim Shady";
        Path path = null;

        try {
            path = Files.createTempFile("test", "txt");
            Files.write(path, wordsStr.getBytes());

            String id = WordCounter.getInstance().processFile(path.toString());
            safeSleep(500);

            Assert.assertEquals(3, WordCounter.getInstance().getWordCount("my", id));
            Assert.assertEquals(1, WordCounter.getInstance().getWordCount("what", id));
            Assert.assertEquals(0, WordCounter.getInstance().getWordCount("hello", id));
        } catch (Exception e) {
            fail(e.getMessage());
        } finally {
            if (path != null) {
                try {
                    Files.deleteIfExists(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void fileMultiLinesTest() {
        int maxLines = 100;
        String wordsStr = "Hi! My name is (what?), my name is (who?), my name is Slim Shady";
        Path path = null;

        try {
            path = Files.createTempFile("test", "txt");
            for (int i=0; i<maxLines; i++) {
                Files.write(path, wordsStr.getBytes(), StandardOpenOption.APPEND);
            }

            String id = WordCounter.getInstance().processFile(path.toString());
            safeSleep(500);

            Assert.assertEquals(3 * maxLines, WordCounter.getInstance().getWordCount("my", id));
            Assert.assertEquals(1 * maxLines, WordCounter.getInstance().getWordCount("what", id));
            Assert.assertEquals(0, WordCounter.getInstance().getWordCount("hello", id));
        } catch (Exception e) {
            fail(e.getMessage());
        } finally {
            if (path != null) {
                try {
                    Files.deleteIfExists(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    public void basicUrlTest() {
//
//    }

    private static void safeSleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}