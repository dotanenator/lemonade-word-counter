package com.lemonade.wordcounter;

public class Main {
    public static void main(String[] args) {
        String str = "Hi! My name is (what?), my name is (who?), my name is Slim Shady";
        String filePath = "/Users/dotan.shaked/Downloads/dss.log";
//        String url = "https://www.lemonade.com/";
        String url = "https://ocw.mit.edu/ans7870/6/6.006/s08/lecturenotes/files/t8.shakespeare.txt";

        String id = WordCounter.getInstance().processString(str);
        safeSleep(500);
        WordCounter.getInstance().printAllWords(id);

        try {
            id = WordCounter.getInstance().processFile(filePath);
            safeSleep(500);
            WordCounter.getInstance().printAllWords(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            id = WordCounter.getInstance().processUrl(url);
            safeSleep(5000);
            WordCounter.getInstance().printAllWords(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void safeSleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
