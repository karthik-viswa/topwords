package com.karthik.test.count;

import com.karthik.test.Word;

public class WordCountEntry {
    Word word;

    int count;

    public WordCountEntry(Word word, int count) {
        this.word = word;
        this.count = count;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "{" +
                "word=" + word +
                ", count=" + count +
                '}';
    }
}
